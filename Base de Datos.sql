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
