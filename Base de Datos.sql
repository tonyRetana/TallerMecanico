USE master
GO
CREATE DATABASE TALLER_MECANICO
GO
USE TALLER_MECANICO
GO
-- Creaci�n de la tabla VENDEDOR
CREATE TABLE VENDEDOR (
    IDENTIFICACION INT PRIMARY KEY,
    NOMBRE VARCHAR(15),
    PRIMERAPELLIDO VARCHAR(15),
    SEGUNDOAPELLIDO VARCHAR(15),
    TELEFONO VARCHAR(12),
    CORREO VARCHAR(30),
    ACTIVO BIT
);

-- Creaci�n de la tabla CLIENTE
CREATE TABLE CLIENTE (
    IDENTIFICACION INT PRIMARY KEY,
    NOMBRE VARCHAR(15),
    PRIMERAPELLIDO VARCHAR(15),
    SEGUNDOAPELLIDO VARCHAR(15),
    TELEFONO VARCHAR(12),
    CORREO VARCHAR(30),
    CONTRASENA VARCHAR(30)
);

-- Creaci�n de la tabla SERVICIO_PRODUCTO
CREATE TABLE SERVICIO_PRODUCTO (
    CODIGOBARRA INT PRIMARY KEY,
    NOMBRE VARCHAR(30),
    PRECIO FLOAT,
    TIPO VARCHAR(8),
    FABRICANTE VARCHAR(30),
    DESCRIPCION VARCHAR(MAX)
);

-- Creaci�n de la tabla INVENTARIO
CREATE TABLE INVENTARIO (
    CODLOTE INT PRIMARY KEY IDENTITY(1,1),
    CODIGOBARRA INT,
    CANTIDAD INT
);

-- Creaci�n de la tabla FACTURA
CREATE TABLE FACTURA (
    NUMFACTURA INT PRIMARY KEY,
    IDCLIENTE INT,
    IDVENDEDOR INT,
    FECHAHORA DATETIME,
    TOTAL FLOAT,
    RESUMEN VARCHAR(MAX)
);

-- Creaci�n de la tabla DETALLE_FACTURA
CREATE TABLE DETALLE_FACTURA (
    ID_DETALLE INT PRIMARY KEY IDENTITY(1,1),
    CODIGOBARRA INT,
    NUMFACTURA INT,
    CANTIDAD INT,
    FECHA DATETIME DEFAULT GETDATE(),
    TOTAL FLOAT
);

-- Creaci�n de la tabla VENTAS
CREATE TABLE VENTAS (
    ID INT PRIMARY KEY IDENTITY(1,1),
    NUMFACTURA INT,
    TOTAL FLOAT,
    FECHAHORA DATETIME
);

-- Creaci�n de la tabla COMPRAS
CREATE TABLE COMPRAS (
    ID INT PRIMARY KEY IDENTITY(1,1),
    NUMFACTURA INT,
    TOTAL FLOAT,
    FECHAHORA DATETIME
);

-- Agregar llave for�nea en tabla INVENTARIO
ALTER TABLE INVENTARIO
ADD FOREIGN KEY (CODIGOBARRA) REFERENCES SERVICIO_PRODUCTO(CODIGOBARRA);

-- Agregar llaves for�neas en tabla FACTURA
ALTER TABLE FACTURA
ADD FOREIGN KEY (IDCLIENTE) REFERENCES CLIENTE(IDENTIFICACION);

ALTER TABLE FACTURA
ADD FOREIGN KEY (IDVENDEDOR) REFERENCES VENDEDOR(IDENTIFICACION);

-- Agregar llaves for�neas en tabla DETALLE_FACTURA
ALTER TABLE DETALLE_FACTURA
ADD FOREIGN KEY (CODIGOBARRA) REFERENCES SERVICIO_PRODUCTO(CODIGOBARRA);

ALTER TABLE DETALLE_FACTURA
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);

-- Agregar llave for�nea en tabla VENTAS
ALTER TABLE VENTAS
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);

-- Agregar llave for�nea en tabla COMPRAS
ALTER TABLE COMPRAS
ADD FOREIGN KEY (NUMFACTURA) REFERENCES FACTURA(NUMFACTURA);

GO
--Procedimientos Almacenados
USE TALLER_MECANICO;
GO

CREATE or ALTER PROCEDURE InsertarFacturaEnBlanco
    @IDCLIENTE INT,
    @IDVENDEDOR INT,
    @NUMFACTURA INT,
    @MSJ VARCHAR(50) OUTPUT
AS
BEGIN
    BEGIN TRY
        DECLARE @NuevoNumeroFactura INT;

        -- Obtener el �ltimo n�mero de factura existente
        SELECT TOP 1 @NuevoNumeroFactura = NUMFACTURA
        FROM FACTURA
        ORDER BY NUMFACTURA DESC;

        -- Si no hay facturas, asignar el n�mero 1; de lo contrario, incrementar el n�mero de factura
        IF @NuevoNumeroFactura IS NULL
            SET @NuevoNumeroFactura = 1;
        ELSE
            SET @NuevoNumeroFactura = @NuevoNumeroFactura + 1;

        -- Insertar una nueva factura en blanco
        INSERT INTO FACTURA (IDCLIENTE, IDVENDEDOR, FECHAHORA, TOTAL, RESUMEN, NUMFACTURA)
        VALUES (@IDCLIENTE, @IDVENDEDOR, GETDATE(), 0.0, '', @NuevoNumeroFactura);

        -- Asignar el n�mero de factura a la variable @MSJ
        SET @MSJ = CAST(@NUMFACTURA AS VARCHAR(MAX));
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

CREATE PROCEDURE InsertarCompraVenta
    @IDCLIENTE INT,
    @IDVENDEDOR INT,
    @NUMFACTURA INT,
    @TIPO VARCHAR(30),
    @TOTAL FLOAT,
    @FECHAHORA DATETIME,
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        IF @TIPO = 'Compra'
        BEGIN
            -- Insertar en la tabla COMPRAS
            INSERT INTO COMPRAS (NUMFACTURA, TOTAL, FECHAHORA)
            VALUES (@NUMFACTURA, @TOTAL, @FECHAHORA);
        END
        ELSE IF @TIPO = 'Venta'
        BEGIN
            -- Insertar en la tabla VENTAS
            INSERT INTO VENTAS (NUMFACTURA, TOTAL, FECHAHORA)
            VALUES (@NUMFACTURA, @TOTAL, @FECHAHORA);
        END
        ELSE
        BEGIN
            -- Tipo no v�lido
            SET @MSJ = 'Tipo no v�lido. El valor debe ser "Compra" o "Venta".';
            RETURN;
        END;

        -- Insertar en la tabla FACTURA
        INSERT INTO FACTURA (NUMFACTURA, IDCLIENTE, IDVENDEDOR, FECHAHORA, TOTAL, RESUMEN)
        VALUES (@NUMFACTURA, @IDCLIENTE, @IDVENDEDOR, @FECHAHORA, @TOTAL, '');

        -- Asignar mensaje de �xito a la variable @MSJ
        SET @MSJ = 'Operaci�n realizada correctamente.';
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al realizar la operaci�n.';
    END CATCH;
	SELECT @MSJ
END;
GO

USE TALLER_MECANICO;
GO

CREATE PROCEDURE EliminarServicioProducto
    @CODIGOBARRA INT,
    @MSJ VARCHAR(MAX) OUTPUT
AS
BEGIN
    BEGIN TRY
        -- Verificar si existe el c�digo de barras en DETALLE_FACTURA
        IF EXISTS (SELECT 1 FROM DETALLE_FACTURA WHERE CODIGOBARRA = @CODIGOBARRA)
        BEGIN
            SET @MSJ = 'Imposible de eliminar, ya existe una factura relacionada.';
        END;
		ELSE
		BEGIN
			-- Eliminar de la tabla SERVICIO_PRODUCTO
			DELETE FROM SERVICIO_PRODUCTO WHERE CODIGOBARRA = @CODIGOBARRA;
			SET @MSJ = 'Eliminaci�n exitosa.';
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
        -- Verificar si ya existe la identificaci�n, el n�mero o el correo
        IF EXISTS (SELECT 1 FROM CLIENTE WHERE IDENTIFICACION = @IDENTIFICACION)
        BEGIN
            SET @MSJ = 'Imposible de insertar, la identificaci�n ya existe.';
        END;
		ELSE IF EXISTS (SELECT 1 FROM CLIENTE WHERE TELEFONO = @TELEFONO)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el n�mero de tel�fono ya existe.';
        END;
        ELSE IF EXISTS (SELECT 1 FROM CLIENTE WHERE CORREO = @CORREO)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el correo electr�nico ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla CLIENTE
			INSERT INTO CLIENTE (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, CONTRASENA)
			VALUES (@IDENTIFICACION, @NOMBRE, @PRIMERAPELLIDO, @SEGUNDOAPELLIDO, @TELEFONO, @CORREO, @CONTRASENA);
			SET @MSJ = 'Inserci�n exitosa.';
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
        -- Verificar si ya existe la identificaci�n
        IF EXISTS (SELECT 1 FROM VENDEDOR WHERE IDENTIFICACION = @IDENTIFICACION)
        BEGIN
            SET @MSJ = 'Imposible de insertar, la identificaci�n ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla VENDEDOR
			INSERT INTO VENDEDOR (IDENTIFICACION, NOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, TELEFONO, CORREO, ACTIVO)
			VALUES (@IDENTIFICACION, @NOMBRE, @PRIMERAPELLIDO, @SEGUNDOAPELLIDO, @TELEFONO, @CORREO, @ACTIVO);
			SET @MSJ = 'Inserci�n exitosa.';
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
        -- Verificar si ya existe el c�digo de barras
        IF EXISTS (SELECT 1 FROM SERVICIO_PRODUCTO WHERE CODIGOBARRA = @CODIGOBARRA)
        BEGIN
            SET @MSJ = 'Imposible de insertar, el c�digo de barras ya existe.';
        END;
		ELSE
		BEGIN
			-- Insertar en la tabla SERVICIO_PRODUCTO
			INSERT INTO SERVICIO_PRODUCTO (CODIGOBARRA, NOMBRE, PRECIO, TIPO, FABRICANTE, DESCRIPCION)
			VALUES (@CODIGOBARRA, @NOMBRE, @PRECIO, @TIPO, @FABRICANTE, @DESCRIPCION);
			SET @MSJ = 'Inserci�n exitosa.';
		END
        
    END TRY
    BEGIN CATCH
        -- Manejo de errores: asignar un mensaje de error a la variable @MSJ
        SET @MSJ = 'Error al intentar insertar el servicio o producto.';
    END CATCH;
	SELECT @MSJ
END;
GO