--Borrar los datos de las tablas
delete from car_Items;
delete from clienteEntity_itementity;
delete from CalificacionEntity;
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

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (1,'BicicletaEntity', 760.78, 'Puce', 0, 'MKT', 1,1);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (2,'BicicletaEntity', 397.10, 'Blue', 0, 'Suburban 2500', 2,2);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (3,'BicicletaEntity', 331.52, 'Green', 0, 'Jetta', 3,3);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (4,'BicicletaEntity', 251.36, 'Goldenrod', 0, 'A3', 4,4);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (5,'BicicletaEntity', 118.77, 'Pink', 0, 'C70', 5,5);
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (6,'BicicletaEntity', 760.78, 'Puce', 0, 'MKT', 1,1);


insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (7,'AccesorioEntity', 760.78, 'Puce', 0, 'C60', 6,6);
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (8,'AccesorioEntity', 760.78, 'Green', 0, 'MYK', 7,7);
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id) values (9,'AccesorioEntity', 760.78, 'pink', 0, 'QWE', 8,8);


--Datos de prueba VendedorEntity(con telefono y direccion no null)
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (1, '9898989', 'coc@hotmale.com', 'Camilo','pedro69','468651684','Frankfurtcalle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (2, '1111111', 'hol@gmail.com', 'Felipe','saga','1232123','calle8-9');
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion) values (3, '1111111', 'hol@gmail.com', 'pedro','saga',null,null);

--Datos de prueba BicicletUsadaEntity

insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (100,'BicicletaUsadaEntity', 276.71, 'Puce', 1, 'Aerostar', 1, 1, 'http://intel.com/vestibulum/rutrum/rutrum.json', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (200,'BicicletaUsadaEntity', 395.40, 'Maroon', 1, 'Rabbit', 2, 2, 'http://discovery.com/montes/nascetur/ridiculus/mus/etiam/vel/augue.js', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (300,'BicicletaUsadaEntity', 757.94, 'Green', 1, 'LS', 3, 3, 'https://blogger.com/ultrices/posuere.jsp', 'En proceso',1);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (400,'BicicletaUsadaEntity', 757.05, 'Green', 1, 'G', 4, 4, 'https://dagondesign.com/aliquam.js', 'En proceso',2);
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado,cliente_id) values (500,'BicicletaUsadaEntity', 929.71, 'Khaki', 1, 'Escort', 5, 5, 'https://netlog.com/in.jpg', 'En proceso',2);

--Datos de prueba ClienteEntity
--Datos de prueba ReclamoEntity
insert into ReclamoEntity values (1,1, 'La bicicleta no ha llegado. La pedí hace 2 semanas y no he recibido información alguna sobre su estado, estoy pendiente a respuesta.', 'El pedido no ha llegado', null, null);
insert into ReclamoEntity values (2,0, 'Las rodilleras me llegaron en una talla menor a la pedida', 'Rodilleras pequeñas', null, null);
insert into ReclamoEntity values (3,1, 'Quiero exigir la garantía por una bicicleta que se rompió', 'Garantía', null, null);
insert into ReclamoEntity values (4,1, 'Los frenos no funcionan correctamente, se demoran mucho en comenzar a funcionar', 'Frenos dañados', null, null);
insert into ReclamoEntity values (5,0, 'El casco llegó sin seguro.', 'El casco llegó sin seguro.', null, null);


--Datos carrito
insert into carritoentity values(1,0.0, null);

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

insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(1,'Muy buenos frenos', 5, 1, 1);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(2,'Lindos colores', 4, 2, 1);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(3,'Asiento muy comodo', 3, 3, 2);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id)values(4,'Demasiado pesada', 2, 1, 2);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(5,'Muy fea en diseño y no sirve para lo que dice', 1, 2, 3);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(6,'Excelente tracción', 5, 3, 3);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(7,'Ideal para BMX', 4, 1, 4);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(8,'Me gustan sus colores', 3, 2, 4);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(9,'Diseño muy feo', 2, 3, 5);
insert into CalificacionEntity (id, comentario, puntaje, cliente_id, modelo_id) values(10,'Muy infantil', 1, null, 5);


