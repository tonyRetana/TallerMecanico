USE master
GO
CREATE DATABASE TALLER_MECANICO
GO
USE TALLER_MECANICO
GO
-- Creación de la tabla VENDEDOR
CREATE TABLE VENDEDOR (
    IDENTIFICACION INT PRIMARY KEY,
    NOMBRE VARCHAR(15),
    PRIMERAPELLIDO VARCHAR(15),
    SEGUNDOAPELLIDO VARCHAR(15),
    TELEFONO VARCHAR(12),
    CORREO VARCHAR(30),
    ACTIVO BIT
);
INSERT INTO VENDEDOR (IDENTIFICACION, NOMBRE, ACTIVO)
VALUES	(1, 'Pagina Web',1);
-- Creación de la tabla CLIENTE
CREATE TABLE CLIENTE (
    IDENTIFICACION INT PRIMARY KEY,
    NOMBRE VARCHAR(15),
    PRIMERAPELLIDO VARCHAR(15),
    SEGUNDOAPELLIDO VARCHAR(15),
    TELEFONO VARCHAR(12),
    CORREO VARCHAR(30),
    CONTRASENA VARCHAR(30)
);

-- Creación de la tabla SERVICIO_PRODUCTO
CREATE TABLE SERVICIO_PRODUCTO (
    CODIGOBARRA INT PRIMARY KEY,
    NOMBRE VARCHAR(30),
    PRECIO FLOAT,
    TIPO VARCHAR(8),
    FABRICANTE VARCHAR(30),
    DESCRIPCION VARCHAR(MAX)
);


-- Creación de la tabla INVENTARIO
CREATE TABLE INVENTARIO (
    CODLOTE INT PRIMARY KEY IDENTITY(1,1),
    CODIGOBARRA INT,
    CANTIDAD INT
);

-- Creación de la tabla FACTURA
CREATE TABLE FACTURA (
    NUMFACTURA INT PRIMARY KEY,
    IDCLIENTE INT,
    IDVENDEDOR INT,
    FECHAHORA DATETIME,
    TOTAL FLOAT,
    RESUMEN VARCHAR(MAX)
);

-- Creación de la tabla DETALLE_FACTURA
CREATE TABLE DETALLE_FACTURA (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    CODIGOBARRA INT,
    NUMFACTURA INT,
    CANTIDAD INT,
    FECHA DATETIME DEFAULT GETDATE(),
    TOTAL FLOAT
);

-- Creación de la tabla VENTAS
CREATE TABLE VENTAS (
    ID INT PRIMARY KEY IDENTITY(1,1),
    NUMFACTURA INT,
    TOTAL FLOAT,
    FECHAHORA DATETIME
);

-- Creación de la tabla COMPRAS
CREATE TABLE COMPRAS (
    ID INT PRIMARY KEY IDENTITY(1,1),
    NUMFACTURA INT,
    TOTAL FLOAT,
    FECHAHORA DATETIME
);

-- Agregar llave foránea en tabla INVENTARIO
ALTER TABLE INVENTARIO
ADD FOREIGN KEY (CODIGOBARRA) REFERENCES SERVICIO_PRODUCTO(CODIGOBARRA);

-- Agregar llaves foráneas en tabla FACTURA
ALTER TABLE FACTURA
ADD FOREIGN KEY (IDCLIENTE) REFERENCES CLIENTE(IDENTIFICACION);

ALTER TABLE FACTURA
ADD FOREIGN KEY (IDVENDEDOR) REFERENCES VENDEDOR(IDENTIFICACION);

-- Agregar llaves foráneas en tabla DETALLE_FACTURA
ALTER TABLE DETALLE_FACTURA
ADD FOREIGN KEY (CODIGOBARRA) REFERENCES SERVICIO_PRODUCTO(CODIGOBARRA);

ALTER TABLE DETALLE_FACTURA
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);

-- Agregar llave foránea en tabla VENTAS
ALTER TABLE VENTAS
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);

-- Agregar llave foránea en tabla COMPRAS
ALTER TABLE COMPRAS
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);


GO

-- Crear el procedimiento almacenado
CREATE OR ALTER PROCEDURE ActualizarDetalleFacturaEInventario
    @IdDetalleFactura INT,
    @Total FLOAT,
    @Cantidad INT,
    @CantidadDe INT,
    @MSJ VARCHAR(100) OUTPUT
AS
BEGIN
    DECLARE @TipoServicio VARCHAR(8);

    -- Obtener el tipo de SERVICIO_PRODUCTO para el CODIGOBARRA asociado al ID_DETALLE
    SELECT @TipoServicio = TIPO
    FROM SERVICIO_PRODUCTO
    WHERE CODIGOBARRA = (SELECT CODIGOBARRA FROM DETALLE_FACTURA WHERE ID_DETALLE = @IdDetalleFactura);

    -- Verificar el tipo de servicio
    IF @TipoServicio = 'PRODUCTO'
    BEGIN
        DECLARE @CantidadInventario INT;

        -- Obtener la cantidad en inventario para el CODIGOBARRA asociado al ID_DETALLE
        SELECT @CantidadInventario = CANTIDAD
        FROM INVENTARIO
        WHERE CODIGOBARRA = (SELECT CODIGOBARRA FROM DETALLE_FACTURA WHERE ID_DETALLE = @IdDetalleFactura);

        -- Verificar si la cantidad recibida es menor o igual que la cantidad en inventario
        IF @Cantidad <= @CantidadInventario
        BEGIN
            -- Actualizar DETALLE_FACTURA
            UPDATE DETALLE_FACTURA
            SET CANTIDAD = @CantidadDe,
                TOTAL = @Total
            WHERE ID_DETALLE = @IdDetalleFactura;

            -- Actualizar INVENTARIO
            UPDATE INVENTARIO
            SET CANTIDAD = @CantidadInventario - @Cantidad
            WHERE CODIGOBARRA = (SELECT CODIGOBARRA FROM DETALLE_FACTURA WHERE ID_DETALLE = @IdDetalleFactura);

            SET @MSJ = 'Actualización exitosa';
        END
        ELSE
        BEGIN
            SET @MSJ = 'La cantidad recibida es mayor que la cantidad en inventario';
        END
    END
    ELSE IF @TipoServicio = 'SERVICIO'
    BEGIN
        -- Actualizar solo el detalle de factura para servicios
        UPDATE DETALLE_FACTURA
        SET CANTIDAD = @CantidadDe,
            TOTAL = @Total
        WHERE ID_DETALLE = @IdDetalleFactura;

        SET @MSJ = 'Actualización de servicio exitosa';
    END
    ELSE
    BEGIN
        SET @MSJ = 'El tipo de servicio no es válido';
    END
END;
go
--Procedimientos Almacenados
USE TALLER_MECANICO;
GO
CREATE or ALTER PROCEDURE ActualizarInventario
    @CODBARRA INT,
    @COMPRA INT,
    @MSJ VARCHAR(100) OUTPUT
AS
BEGIN
    DECLARE @CANTIDAD_ACTUAL INT

    -- Obtener la cantidad actual para el código de barras dado
    SELECT @CANTIDAD_ACTUAL = CANTIDAD
    FROM INVENTARIO
    WHERE CODIGOBARRA = @CODBARRA

    -- Verificar si la compra es posible
    IF (@CANTIDAD_ACTUAL >= @COMPRA)
    BEGIN
        -- Realizar la actualización
        UPDATE INVENTARIO
        SET CANTIDAD = CANTIDAD - @COMPRA
        WHERE CODIGOBARRA = @CODBARRA

        SET @MSJ = 'Transacción exitosa'
    END
    ELSE
    BEGIN
        SET @MSJ = 'No se puede ingresar esa compra'
    END
END;


GO
CREATE or ALTER PROCEDURE InsertarFacturaEnBlanco
    @NUMFACTURA INT output,
    @MSJ VARCHAR(50) OUTPUT
AS
BEGIN

    BEGIN TRY
        DECLARE @NuevoNumeroFactura INT;

        -- Obtener el último número de factura existente
        SELECT TOP 1 @NuevoNumeroFactura = NUMFACTURA
        FROM FACTURA
        ORDER BY NUMFACTURA DESC;

        -- Si no hay facturas, asignar el número 1; de lo contrario, incrementar el número de factura
        IF @NuevoNumeroFactura IS NULL
            SET @NuevoNumeroFactura = 1;
        ELSE
            SET @NuevoNumeroFactura = @NuevoNumeroFactura + 1;

        -- Insertar una nueva factura en blanco
        INSERT INTO FACTURA (FECHAHORA, TOTAL, RESUMEN, NUMFACTURA)
        VALUES (GETDATE(), 0.0, '', @NuevoNumeroFactura);

        -- Asignar el número de factura a la variable @MSJ
        SET @MSJ = 'Factura Generada';
		SET @NUMFACTURA = @NuevoNumeroFactura;
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al insertar la factura.';
    END CATCH;
	SELECT @MSJ
END;
GO
CREATE or ALTER PROCEDURE InsertarFacturaCompra
    @NUMFACTURA INT output,
	@TOTAL FLOAT,
	@CODIGOBARRA INT,
	@CANTIDAD INT,
    @MSJ VARCHAR(50) OUTPUT
AS
BEGIN

    BEGIN TRY
        DECLARE @NuevoNumeroFactura INT;

        -- Obtener el último número de factura existente
        SELECT TOP 1 @NuevoNumeroFactura = NUMFACTURA
        FROM FACTURA
        ORDER BY NUMFACTURA DESC;

        -- Si no hay facturas, asignar el número 1; de lo contrario, incrementar el número de factura
        IF @NuevoNumeroFactura IS NULL
            SET @NuevoNumeroFactura = 1;
        ELSE
            SET @NuevoNumeroFactura = @NuevoNumeroFactura + 1;

        -- Insertar una nueva factura en blanco
        INSERT INTO FACTURA (FECHAHORA, TOTAL, NUMFACTURA, RESUMEN)
		VALUES (GETDATE(), @TOTAL,  @NuevoNumeroFactura, 'Se insertó más productos con el CODIGO: ' + CAST(@CODIGOBARRA AS VARCHAR(MAX)));

		INSERT INTO DETALLE_FACTURA(FECHA,TOTAL,CODIGOBARRA,NUMFACTURA,CANTIDAD)
		VALUES (GETDATE(),@TOTAL,@CODIGOBARRA,@NuevoNumeroFactura,@CANTIDAD);

		INSERT INTO COMPRAS (FECHAHORA,NUMFACTURA,TOTAL)
		VALUES (GETDATE(),@NuevoNumeroFactura,@TOTAL)

        -- Asignar el número de factura a la variable @MSJ
        SET @MSJ = 'Factura Generada';
		SET @NUMFACTURA = @NuevoNumeroFactura;
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al insertar la factura.';
    END CATCH;
	SELECT @MSJ
END;
GO
USE TALLER_MECANICO;
GO

CREATE or ALTER PROCEDURE InsertarCompraVenta
    @NUMFACTURA INT,
    @TIPO VARCHAR(30),
    @TOTAL FLOAT,
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        IF @TIPO = 'Compra'
        BEGIN
            -- Insertar en la tabla COMPRAS
            INSERT INTO COMPRAS (NUMFACTURA, TOTAL, FECHAHORA)
            VALUES (@NUMFACTURA, @TOTAL, GETDATE());
        END
        ELSE IF @TIPO = 'Venta'
        BEGIN
            -- Insertar en la tabla VENTAS
            INSERT INTO VENTAS (NUMFACTURA, TOTAL, FECHAHORA)
            VALUES (@NUMFACTURA, @TOTAL, GETDATE());
        END
        ELSE
        BEGIN
            -- Tipo no válido
            SET @MSJ = 'Tipo no válido. El valor debe ser "Compra" o "Venta".';
            RETURN;
        END;

        -- Asignar mensaje de éxito a la variable @MSJ
        SET @MSJ = 'Operación realizada correctamente.';
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al realizar la operación.';
    END CATCH;
	SELECT @MSJ
END;
GO

USE TALLER_MECANICO;
GO

CREATE or ALTER PROCEDURE EliminarServicioProducto
    @CODIGOBARRA INT,
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        -- Verificar si existe el código de barras en DETALLE_FACTURA
        IF EXISTS (SELECT 1 FROM DETALLE_FACTURA WHERE CODIGOBARRA = @CODIGOBARRA)
        BEGIN
            SET @MSJ = 'Imposible de eliminar, ya existe una factura relacionada.';
        END;
		ELSE
		BEGIN
			-- Eliminar de la tabla SERVICIO_PRODUCTO
			DELETE FROM INVENTARIO WHERE CODIGOBARRA = @CODIGOBARRA;
			DELETE FROM SERVICIO_PRODUCTO WHERE CODIGOBARRA = @CODIGOBARRA;
			SET @MSJ = 'Eliminación exitosa.';
		END
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al intentar eliminar el servicio o producto.';
    END CATCH;
	SELECT @MSJ
END;
GO

USE TALLER_MECANICO;
GO

CREATE PROCEDURE InsertarCliente
    @IDENTIFICACION INT,
    @NOMBRE VARCHAR(15),
    @PRIMERAPELLIDO VARCHAR(15),
    @SEGUNDOAPELLIDO VARCHAR(15),
    @TELEFONO VARCHAR(12),
    @CORREO VARCHAR(30),
    @CONTRASENA VARCHAR(30),
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        -- Verificar si ya existe la identificación, el número o el correo
        IF EXISTS (SELECT 1 FROM CLIENTE WHERE IDENTIFICACION = @IDENTIFICACION)
        BEGIN
            SET @MSJ = 'Imposible de insertar, la identificación ya existe.';
        END;
		ELSE IF EXISTS (SELECT 1 FROM CLIENTE WHERE TELEFONO = @TELEFONO)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el número de teléfono ya existe.';
        END;
        ELSE IF EXISTS (SELECT 1 FROM CLIENTE WHERE CORREO = @CORREO)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el correo electrónico ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla CLIENTE
			INSERT INTO CLIENTE (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, CONTRASENA)
			VALUES (@IDENTIFICACION, @NOMBRE, @PRIMERAPELLIDO, @SEGUNDOAPELLIDO, @TELEFONO, @CORREO, @CONTRASENA);
			SET @MSJ = 'Inserción exitosa.';
		END
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al intentar insertar el cliente.';
    END CATCH;
	SELECT @MSJ
END;
GO

USE TALLER_MECANICO;
GO

CREATE PROCEDURE InsertarVendedor
    @IDENTIFICACION INT,
    @NOMBRE VARCHAR(15),
    @PRIMERAPELLIDO VARCHAR(15),
    @SEGUNDOAPELLIDO VARCHAR(15),
    @TELEFONO VARCHAR(12),
    @CORREO VARCHAR(30),
    @ACTIVO BIT,
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        -- Verificar si ya existe la identificación
        IF EXISTS (SELECT 1 FROM VENDEDOR WHERE IDENTIFICACION = @IDENTIFICACION)
        BEGIN
            SET @MSJ = 'Imposible de insertar, la identificación ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla VENDEDOR
			INSERT INTO VENDEDOR (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, ACTIVO)
			VALUES (@IDENTIFICACION, @NOMBRE, @PRIMERAPELLIDO, @SEGUNDOAPELLIDO, @TELEFONO, @CORREO, @ACTIVO);
			SET @MSJ = 'Inserción exitosa.';
		END
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al intentar insertar el vendedor.';
    END CATCH;
	SELECT @MSJ
END;
GO

GO

CREATE PROCEDURE InsertarServicioProducto
    @CODIGOBARRA INT,
    @NOMBRE VARCHAR(30),
    @PRECIO FLOAT,
    @TIPO VARCHAR(8),
    @FABRICANTE VARCHAR(30),
    @DESCRIPCION VARCHAR(MAX),
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        -- Verificar si ya existe el código de barras
        IF EXISTS (SELECT 1 FROM SERVICIO_PRODUCTO WHERE CODIGOBARRA = @CODIGOBARRA)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el código de barras ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla SERVICIO_PRODUCTO
			INSERT INTO SERVICIO_PRODUCTO (CODIGOBARRA, NOMBRE, PRECIO, TIPO, FABRICANTE, DESCRIPCION)
			VALUES (@CODIGOBARRA, @NOMBRE, @PRECIO, @TIPO, @FABRICANTE, @DESCRIPCION);
			SET @MSJ = 'Inserción exitosa.';
		END
        
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al intentar insertar el servicio o producto.';
    END CATCH;
	SELECT @MSJ
END;
GO
/*******************************************/
/*Datos de prueba*/
INSERT INTO VENDEDOR (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, ACTIVO)
VALUES
    (2, 'Juan', 'Pérez', 'Gómez', '1234567890', 'juan@example.com', 1),
    (3, 'María', 'López', 'Rodríguez', '9876543210', 'maria@example.com', 1),
    (4, 'Carlos', 'González', 'Sánchez', '5555555555', 'carlos@example.com', 1),
    (5, 'Ana', 'Martínez', 'Fernández', '7777777777', 'ana@example.com', 1),
    (6, 'Luis', 'Hernández', 'Pérez', '9999999999', 'luis@example.com', 0),
    (7, 'Laura', 'Díaz', 'García', '1111111111', 'laura@example.com', 0),
    (8, 'Pedro', 'Sánchez', 'López', '2222222222', 'pedro@example.com', 0),
    (9, 'Marta', 'Fernández', 'Pérez', '3333333333', 'marta@example.com', 0),
    (10, 'Sergio', 'Rodríguez', 'Gómez', '4444444444', 'sergio@example.com', 0),
    (11, 'Elena', 'García', 'Martínez', '6666666666', 'elena@example.com', 1);
	select * from VENDEDOR where IDENTIFICACION>1
	SELECT IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO FROM VENDEDOR where ACTIVO =1 and IDENTIFICACION>1



	-- Productos
INSERT INTO SERVICIO_PRODUCTO (CODIGOBARRA, NOMBRE, PRECIO, TIPO, FABRICANTE, DESCRIPCION)
VALUES
    (1001, 'Aceite de Motor', 30.50, 'PRODUCTO', 'Shell', 'Aceite de motor sintético, 5W-30.'),
    (1002, 'Filtro de Aceite', 12.75, 'PRODUCTO', 'Bosch', 'Filtro de aceite de alta eficiencia.'),
    (1003, 'Batería de Coche', 120.00, 'PRODUCTO', 'Exide', 'Batería de 12V, 650 CCA.'),
    (1004, 'Llantas Deportivas', 220.00, 'PRODUCTO', 'Michelin', 'Juego de 4 llantas deportivas, tamaño 18".'),
    (1005, 'Limpiaparabrisas', 8.50, 'PRODUCTO', 'Rain-X', 'Limpiaparabrisas de alto rendimiento.')

-- Servicios
INSERT INTO SERVICIO_PRODUCTO (CODIGOBARRA, NOMBRE, PRECIO, TIPO, FABRICANTE, DESCRIPCION)
VALUES
    (2001, 'Cambio de Aceite', 45.00, 'SERVICIO', 'TALLER', 'Incluye cambio de aceite y reemplazo del filtro de aceite.'),
    (2002, 'Alineación de Ruedas', 65.00, 'SERVICIO', 'TALLER', 'Alineación de las ruedas delanteras y traseras.'),
    (2003, 'Revisión de Frenos', 25.00, 'SERVICIO', 'TALLER', 'Inspección completa del sistema de frenos.'),
    (2004, 'Cambio de Batería', 40.00, 'SERVICIO', 'TALLER', 'Reemplazo de batería agotada por una nueva.'),
    (2005, 'Lavado Exterior', 20.00, 'SERVICIO', 'TALLER', 'Lavado y secado exterior del vehículo.')
    
--Inventarios
INSERT INTO INVENTARIO (CODIGOBARRA, CANTIDAD)
VALUES
    (1001, FLOOR(RAND() * (100 - 50 + 1)) + 50),
    (1002, FLOOR(RAND() * (100 - 50 + 1)) + 50),
    (1003, FLOOR(RAND() * (100 - 50 + 1)) + 50),
    (1004, FLOOR(RAND() * (100 - 50 + 1)) + 50),
    (1005, FLOOR(RAND() * (100 - 50 + 1)) + 50);


INSERT INTO CLIENTE (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, CONTRASENA)
VALUES
    (1, 'Juan', 'Pérez', 'García', '1234567890', 'juan.perez@example.com', 'contraseña123'),
    (2, 'María', 'López', 'Martínez', '9876543210', 'maria.lopez@example.com', 'clave456'),
    (3, 'Carlos', 'González', 'Hernández', '5678901234', 'carlos.gonzalez@example.com', 'password789'),
    (4, 'Ana', 'Rodríguez', 'Fernández', '6789012345', 'ana.rodriguez@example.com', 'segura123'),
    (5, 'Luis', 'Martínez', 'Sánchez', '7890123456', 'luis.martinez@example.com', 'cliente567'),
    (6, 'Laura', 'García', 'Pérez', '8901234567', 'laura.garcia@example.com', 'registro789'),
    (7, 'Miguel', 'Hernández', 'González', '9012345678', 'miguel.hernandez@example.com', 'seguridad123'),
    (8, 'Carmen', 'Sánchez', 'López', '0123456789', 'carmen.sanchez@example.com', 'cliente987'),
    (9, 'Javier', 'Fernández', 'Rodríguez', '2345678901', 'javier.fernandez@example.com', 'registro567'),
    (10, 'Isabel', 'Pérez', 'López', '3456789012', 'isabel.perez@example.com', 'segura456');

	/*SELECT * FROM SERVICIO_PRODUCTO
	select * from INVENTARIO
	SELECT 1 FROM SERVICIO_PRODUCTO WHERE CODIGOBARRA = 13
	select * from FACTURA
	select sp.CODIGOBARRA,NOMBRE,PRECIO from SERVICIO_PRODUCTO sp inner join INVENTARIO i on sp.CODIGOBARRA=i.CODIGOBARRA 
	select * from CLIENTE
	select * from VENDEDOR
	SELECT * FROM DETALLE_FACTURA
	UPDATE VENDEDOR SET NOMBRE = ?,PRIMERAPELLIDO=?, SEGUNDOAPELLIDO=?, TELEFONO=?, CORREO=?, ACTIVO=? WHERE IDENTIFICACION = ?
	INSERT INTO DETALLE_FACTURA(CANTIDAD,CODIGOBARRA) VALUES (?,?)

	SELECT * FROM INVENTARIO
	Select * from DETALLE_FACTURA
	select ID_DETALLE, NOMBRE, CANTIDAD, TOTAL from DETALLE_FACTURA DF inner join SERVICIO_PRODUCTO sp on df.CODIGOBARRA=sp.CODIGOBARRA where 

	Select CODIGOBARRA,NOMBRE,PRECIO,DESCRIPCION from SERVICIO_PRODUCTO


	select * from DETALLE_FACTURA
	

	UPDATE DETALLE_FACTURA
        SET CANTIDAD = 2,
            TOTAL = 120
        WHERE ID_DETALLE = 22;
		
		
	select*from VENTAS
	select * from FACTURA
	select * from FACTURA*/

	select * from INVENTARIO
	select * from COMPRAS
	select * from DETALLE_FACTURA
	select * from FACTURA

	select*from VENTAS

	select NUMFACTURA,TOTAL,FECHAHORA from VENTAS where   NUMFACTURA=12
	select CODIGOBARRA,CANTIDAD,TOTAL from DETALLE_FACTURA
	select * from VENTAS 