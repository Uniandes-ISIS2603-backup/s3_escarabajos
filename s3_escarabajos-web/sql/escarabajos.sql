--Borrar los datos de las tablas

delete from ItemEntity;
delete from ModeloEntity;
delete from CarritoEntity;
delete from ClienteEntity;
delete from FacturaEntity;
delete from ReclamoEntity;
--Datos de prueba ModeloEntity con tipo modelo Bicicleta

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (1, 'Cayman', 'EUR', 18, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (2, 'Element', 'PHP', 44, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (3, '6 Series', 'IDR', 11, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (4, 'Hombre', 'KPW', 10, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (5, 'Elantra', 'CNY', 17, 'Bicicleta');

--Datos de prueba BicicletaEntity

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (1,'BicicletaEntity', 760.78, 'Puce', 0, 'MKT', 1,1);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (2,'BicicletaEntity', 397.10, 'Blue', 0, 'Suburban 2500', 2,2);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (3,'BicicletaEntity', 331.52, 'Green', 0, 'Jetta', 3,3);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (4,'BicicletaEntity', 251.36, 'Goldenrod', 0, 'A3', 4,4);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (5,'BicicletaEntity', 118.77, 'Pink', 0, 'C70', 5,5);

--Datos de prueba VendedorEntity
insert into ClienteEntity (id, cedula, correo, nombre, usuario) values (1, '9898989', 'coc@hotmale.com', 'Camilo','pedro69');
UPDATE ClienteEntity
SET telefono = '468651684', direccion= 'Frankfurtcalle8-9'
WHERE id = 1;

--Datos de prueba BicicletUsadaEntity

insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (100,'BicicletaUsadaEntity', 276.71, 'Puce', 1, 'Aerostar', 1, 1, 'http://intel.com/vestibulum/rutrum/rutrum.json', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (200,'BicicletaUsadaEntity', 395.40, 'Maroon', 1, 'Rabbit', 2, 2, 'http://discovery.com/montes/nascetur/ridiculus/mus/etiam/vel/augue.js', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (300,'BicicletaUsadaEntity', 757.94, 'Green', 1, 'LS', 3, 3, 'https://blogger.com/ultrices/posuere.jsp', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (400,'BicicletaUsadaEntity', 757.05, 'Green', 1, 'G', 4, 4, 'https://dagondesign.com/aliquam.js', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (500,'BicicletaUsadaEntity', 929.71, 'Khaki', 1, 'Escort', 5, 5, 'https://netlog.com/in.jpg', 'En proceso',1);

--Datos de prueba ClienteEntity
--Datos de prueba ReclamoEntity
insert into ReclamoEntity values (1,1, 'La bicicleta no ha llegado. La pedí hace 2 semanas y no he recibido información alguna sobre su estado, estoy pendiente a respuesta.', 'El pedido no ha llegado', null, null);
insert into ReclamoEntity values (2,0, 'Las rodilleras me llegaron en una talla menor a la pedida', 'Rodilleras pequeñas', null, null);
insert into ReclamoEntity values (3,1, 'Quiero exigir la garantía por una bicicleta que se rompió', 'Garantía', null, null);
insert into ReclamoEntity values (4,1, 'Los frenos no funcionan correctamente, se demoran mucho en comenzar a funcionar', 'Frenos dañados', null, null);
insert into ReclamoEntity values (5,0, 'El casco llegó sin seguro.', 'El casco llegó sin seguro.', null, null);


