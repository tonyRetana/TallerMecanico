GO
USE TALLER_MECANICO;
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
	select *from CLIENTE
	select*from VENTAS

	select NUMFACTURA,TOTAL,FECHAHORA from VENTAS where   NUMFACTURA=12
	select CODIGOBARRA,CANTIDAD,TOTAL from DETALLE_FACTURA
	select * from VENTAS 

	Select i.CODIGOBARRA,NOMBRE,PRECIO,DESCRIPCION,CANTIDAD from SERVICIO_PRODUCTO sp inner join INVENTARIO i  on sp.CODIGOBARRA = i.CODIGOBARRA