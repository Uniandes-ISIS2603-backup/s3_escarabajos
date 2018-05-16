--Borrar los datos de las tablas
delete from car_Items;
delete from item_album;
delete from reclamo_album;
delete from factura_items;
delete from lista_deseos_items;
delete from ReclamoEntity;
delete from FacturaEntity;
delete from CalificacionEntity;
delete from CarritoEntity;
delete from listadeseosentity;
delete from ItemEntity;
delete from ModeloEntity;
delete from ClienteEntity;

--Datos de prueba ModeloEntity con tipo modelo Bicicleta

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (1000, 'Cayman', 'EUR', 5, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (2000, 'Element', 'PHP', 4, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (3000, '6 Series', 'IDR', 3, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (4000, 'Hombre', 'KPW', 3.2, 'Bicicleta');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (5000, 'Elantra', 'CNY', 1, 'Bicicleta');


insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (6000, '6 Series', 'SILAA BMX', 5, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (7000, 'Hombre', 'CASCO BMX', 4, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (8000, 'Elantra', 'RODILLERAS BMX', 3, 'Accesorio');

--Datos de prueba BicicletaEntity

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (1000,'BicicletaEntity', 760000.78, '#EF5B5B', 0, 'MKT', 1000,1000,1,0.8);
insert into Item_album (itemEntity_ID, album) values (1000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (2000,'BicicletaEntity', 397000.10, '#5BEF68', 0, 'Suburban 2500', 2000,2000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (2000, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (3000,'BicicletaEntity', 331000.52, '#5BBBEF', 0, 'Jetta', 3000,3000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (3000, 'data/Imagenes/BicicletaTest3.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (4000,'BicicletaEntity', 251000.36, '#8F5BEF', 0, 'A3', 4000,4000,1,0.4);
insert into Item_album (itemEntity_ID, album) values (4000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (5000,'BicicletaEntity', 118000.77, '#8F5BEF', 0, 'C70', 5000,5000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (5000, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (6000,'BicicletaEntity', 760000.78, '#EF5B5B', 0, 'MKT', 1000,1000,1,0.5);


insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (7000,'AccesorioEntity', 760000.78, '#EF5B5B', 0, 'C60', 6000,6000,1,0.6);
insert into Item_album (itemEntity_ID, album) values (7000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (8000,'AccesorioEntity', 760000.78, '#5BBBEF', 0, 'MYK', 7000,7000,1,0.8);
insert into Item_album (itemEntity_ID, album) values (8000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (9000,'AccesorioEntity', 760000.78, '#8F5BEF', 0, 'QWE', 8000,8000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (9000, 'data/Imagenes/BicicletaTest1.jpg');


--Datos de prueba ClienteEntity(con telefono y direccion no null)
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (1000, '9898989', 'coc@hotmale.com', 'Camilo','pedro69','468651684','Frankfurtcalle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (2000, '123', 'bss@gmail.com', 'Felipe','saga','1232123','calle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (3000, '1234', 'a121@gmail.com', 'Pedro','saga2','12345','Elm Street');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (4000, '12334', 'jorge@hotmale.com', 'Jorge','giorginio1232','468651684','Frankfurtcalle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (5000, '433211', 'sant@gmail.com', 'Santiago','s.beltran','12332123','calle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (6000, '2211123', 'and@gmail.com', 'Andres','a.varonm','122345','Elm Street');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (7000, '9898989', 'mate@hotmale.com', 'Mateo','m.devia','4686511684','Frankfurtcalle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (8000, '22211234', 'nico@gmail.com', 'Nicolas','n.gaitan','11232123','calle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (9000, '2212145', 'preubaCliente@gmail.com', 'Test Cliente','testC','12322245','Elm Street');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (10000, '1122', 'pruebaAdmin@gmail.com', 'Test Admin','testA','1211121345','Elm Street');

--Datos de pruebaFacturaEntity
insert into FacturaEntity values (1000, 200000.0, 1000);
insert into FacturaEntity values (2000, 200000.0, 2000);
insert into FacturaEntity values (3000, 200000.0, 3000);
insert into FacturaEntity values (4000, 200000.0, 4000);
insert into FacturaEntity values (5000, 200000.0, 5000);
insert into FacturaEntity values (6000, 200000.0, 9000);
insert into FacturaEntity values (7000, 200000.0, 9000);
insert into FacturaEntity values (8000, 200000.0, 9000);
insert into FacturaEntity values (9000, 200000.0, 9000);
insert into FacturaEntity values (10000, 200000.0, 9000);
insert into FacturaEntity values (11000, 200000.0, 9000);



--Datos de prueba BicicletUsadaEntity

insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible,multiplicador) values (10000,'BicicletaUsadaEntity', 276000.71, '#EF5B5B', 1, 'Aerostar', 1000, 1000, 'http://intel.com/vestibulum/rutrum/rutrum.json', 'En proceso',1000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (1000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible,multiplicador) values (20000,'BicicletaUsadaEntity', 395000.40, '#EFC55B', 1, 'Rabbit', 2000, 2000, 'http://discovery.com/montes/nascetur/ridiculus/mus/etiam/vel/augue.js', 'En proceso',1000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (2000, 'data/Imagenes/BicicletaTest2.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible,multiplicador) values (30000,'BicicletaUsadaEntity', 757000.94, '#8F5BEF', 1, 'LS', 3000, 3000, 'https://blogger.com/ultrices/posuere.jsp', 'En proceso',1000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (3000, 'data/Imagenes/BicicletaTest3.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible,multiplicador) values (40000,'BicicletaUsadaEntity', 757000.05, '#8F5BEF', 1, 'G', 4000, 4000, 'https://dagondesign.com/aliquam.js', 'En proceso',2000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (4000, 'data/Imagenes/BicicletaTest1.jpg');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id,disponible,multiplicador) values (50000,'BicicletaUsadaEntity', 929000.71, '#5BBBEF', 1, 'Escort', 5000, 5000, 'https://netlog.com/in.jpg', 'En proceso',2000,1,1.0);
insert into Item_album (itemEntity_ID, album) values (5000, 'data/Imagenes/BicicletaTest2.jpg');

--Join Factura Items
insert into Factura_Items values (1000, 1000);
insert into Factura_Items values (2000, 2000);
insert into Factura_Items values (3000, 3000);
insert into Factura_Items values (4000, 4000);
insert into Factura_Items values (5000, 5000);
insert into Factura_Items values (6000, 6000);
insert into Factura_Items values (7000, 7000);
insert into Factura_Items values (8000, 8000);
insert into Factura_Items values (9000, 1000);
insert into Factura_Items values (10000, 2000);
insert into Factura_Items values (11000, 3000);


--Datos de prueba ReclamoEntity
insert into ReclamoEntity values (1000,1, 'La bicicleta no ha llegado. La pedí hace 2 semanas y no he recibido información alguna sobre su estado, estoy pendiente a respuesta.', 'El pedido no ha llegado', 1000, 1000);
insert into ReclamoEntity values (2000,0, 'Las rodilleras me llegaron en una talla menor a la pedida', 'Rodilleras pequeñas', 2000, 2000);
insert into ReclamoEntity values (3000,1, 'Quiero exigir la garantía por una bicicleta que se rompió', 'Garantía', 3000, 3000);
insert into ReclamoEntity values (4000,1, 'Los frenos no funcionan correctamente, se demoran mucho en comenzar a funcionar', 'Frenos dañados', 4000, 4000);
insert into ReclamoEntity values (5000,0, 'El casco llegó sin seguro.', 'El casco llegó sin seguro.', 5000, 5000);
insert into ReclamoEntity values (6000,1, 'La bicicleta no ha llegado. La pedí hace 2 semanas y no he recibido información alguna sobre su estado, estoy pendiente a respuesta.', 'El pedido no ha llegado', 9000, 6000);
insert into ReclamoEntity values (7000,0, 'Las rodilleras me llegaron en una talla menor a la pedida', 'Rodilleras pequeñas', 9000, 7000);
insert into ReclamoEntity values (8000,1, 'Quiero exigir la garantía por una bicicleta que se rompió', 'Garantía', 9000, 8000);
insert into ReclamoEntity values (9000,1, 'Los frenos no funcionan correctamente, se demoran mucho en comenzar a funcionar', 'Frenos dañados', 9000, 9000);
insert into ReclamoEntity values (10000,0, 'El casco llegó sin seguro.', 'El casco llegó sin seguro.', 9000, 10000);


--Datos carritos
insert into carritoentity values(1000,0.0, 1000);
insert into carritoentity values(2000,0.0, 2000);
insert into carritoentity values(3000,0.0, 3000);
insert into carritoentity values(4000,0.0, 4000);
insert into carritoentity values(5000,0.0, 5000);
insert into carritoentity values(6000,0.0, 6000);
insert into carritoentity values(7000,0.0, 7000);
insert into carritoentity values(8000,0.0, 8000);
insert into carritoentity values(9000,0.0, 9000);
insert into carritoentity values(10000,0.0, 10000);


--Datos lista de deseos
insert into listadeseosentity values(1000,0.0, 1000);
insert into listadeseosentity values(2000,0.0, 2000);
insert into listadeseosentity values(3000,0.0, 3000);
insert into listadeseosentity values(4000,0.0, 4000);
insert into listadeseosentity values(5000,0.0, 5000);
insert into listadeseosentity values(6000,0.0, 6000);
insert into listadeseosentity values(7000,0.0, 7000);
insert into listadeseosentity values(8000,0.0, 8000);
insert into listadeseosentity values(9000,0.0, 9000);
insert into listadeseosentity values(10000,0.0, 10000);


--Datos calificacion
insert into CalificacionEntity values(1000, 'Muy lindo color', 5, 1000, 1000);
insert into CalificacionEntity values(2000, 'Perfecta para moverse por la ciudad', 5, 2000, 1000);
insert into CalificacionEntity values(3000, 'Buena velocidad', 5, 3000, 2000);
insert into CalificacionEntity values(4000, 'Se siente muy rico al andar', 5, 4000, 2000);
insert into CalificacionEntity values(5000, 'Horrible', 3, 5000, 3000);
insert into CalificacionEntity values(6000, 'Feo', 5, 6000, 3000);
insert into CalificacionEntity values(7000, 'Bonito', 5, 7000, 4000);
insert into CalificacionEntity values(8000, 'Muy buenos frenos', 5, 8000, 4000);





