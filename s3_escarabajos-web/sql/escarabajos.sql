--Borrar los datos de las tablas
delete from car_Items;
delete from clienteEntity_itementity;
delete from item_album;
delete from reclamo_album;
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


insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (6, '6 Series', 'SILAA BMX', 11, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (7, 'Hombre', 'CASCO BMX', 10, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (8, 'Elantra', 'RODILLERAS BMX', 17, 'Accesorio');

--Datos de prueba BicicletaEntity

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible) values (1,'BicicletaEntity', 760000.78, '#EF5B5B', 0, 'MKT', 1,1,1);
insert into Item_album (itemEntity_ID, album) values (1, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible) values (2,'BicicletaEntity', 397000.10, '#5BEF68', 0, 'Suburban 2500', 2,2,1);
insert into Item_album (itemEntity_ID, album) values (2, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible) values (3,'BicicletaEntity', 331000.52, '#5BBBEF', 0, 'Jetta', 3,3,1);
insert into Item_album (itemEntity_ID, album) values (3, 'data/Imagenes/BicicletaTest3.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible) values (4,'BicicletaEntity', 251000.36, '#8F5BEF', 0, 'A3', 4,4,1);
insert into Item_album (itemEntity_ID, album) values (4, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible) values (5,'BicicletaEntity', 118000.77, '#8F5BEF', 0, 'C70', 5,5,1);
insert into Item_album (itemEntity_ID, album) values (5, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible) values (6,'BicicletaEntity', 760000.78, '#EF5B5B', 0, 'MKT', 1,1,1);


insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible) values (7,'AccesorioEntity', 760000.78, '#EF5B5B', 0, 'C60', 6,6,1);
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible) values (8,'AccesorioEntity', 760000.78, '#5BBBEF', 0, 'MYK', 7,7,1);
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible) values (9,'AccesorioEntity', 760000.78, '#8F5BEF', 0, 'QWE', 8,8,1);


--Datos de prueba VendedorEntity(con telefono y direccion no null)
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (1, '9898989', 'coc@hotmale.com', 'Camilo','pedro69','468651684','Frankfurtcalle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (2, '1111111', 'hol@gmail.com', 'Felipe','saga','1232123','calle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (3, '1111111', 'hol@gmail.com', 'pedro','saga',null,null);

--Datos de prueba BicicletUsadaEntity

insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible) values (100,'BicicletaUsadaEntity', 276000.71, '#EF5B5B', 1, 'Aerostar', 1, 1, 'http://intel.com/vestibulum/rutrum/rutrum.json', 'En proceso',1,1);
insert into Item_album (itemEntity_ID, album) values (100, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible) values (200,'BicicletaUsadaEntity', 395000.40, '#EFC55B', 1, 'Rabbit', 2, 2, 'http://discovery.com/montes/nascetur/ridiculus/mus/etiam/vel/augue.js', 'En proceso',1,1);
insert into Item_album (itemEntity_ID, album) values (200, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible) values (300,'BicicletaUsadaEntity', 757000.94, '#8F5BEF', 1, 'LS', 3, 3, 'https://blogger.com/ultrices/posuere.jsp', 'En proceso',1,1);
insert into Item_album (itemEntity_ID, album) values (300, 'data/Imagenes/BicicletaTest3.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible) values (400,'BicicletaUsadaEntity', 757000.05, '#8F5BEF', 1, 'G', 4, 4, 'https://dagondesign.com/aliquam.js', 'En proceso',2,1);
insert into Item_album (itemEntity_ID, album) values (400, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible) values (500,'BicicletaUsadaEntity', 929000.71, '#5BBBEF', 1, 'Escort', 5, 5, 'https://netlog.com/in.jpg', 'En proceso',2,1);
insert into Item_album (itemEntity_ID, album) values (500, 'data/Imagenes/BicicletaTest2.jpg');
--Datos de prueba ClienteEntity
--Datos de prueba ReclamoEntity
insert into ReclamoEntity values (1,1, 'La bicicleta no ha llegado. La pedí hace 2 semanas y no he recibido información alguna sobre su estado, estoy pendiente a respuesta.', 'El pedido no ha llegado', null, null);
insert into ReclamoEntity values (2,0, 'Las rodilleras me llegaron en una talla menor a la pedida', 'Rodilleras pequeñas', null, null);
insert into ReclamoEntity values (3,1, 'Quiero exigir la garantía por una bicicleta que se rompió', 'Garantía', null, null);
insert into ReclamoEntity values (4,1, 'Los frenos no funcionan correctamente, se demoran mucho en comenzar a funcionar', 'Frenos dañados', null, null);
insert into ReclamoEntity values (5,0, 'El casco llegó sin seguro.', 'El casco llegó sin seguro.', null, null);


--Datos carrito
insert into carritoentity values(1,0.0, 1);

insert into car_items values(1,1);
insert into car_items values(1,2);
insert into car_items values(1,3);
insert into car_items values(1,6);

--datos lista de deseos
insert into carritoentity values(2,0.0, null);

insert into car_items values(2,1);
insert into car_items values(2,2);
insert into car_items values(2,3);
insert into car_items values(2,6);




