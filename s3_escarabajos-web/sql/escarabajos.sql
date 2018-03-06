--Borrar los datos de las tablas

delete from ItemEntity;
delete from ModeloEntity;
delete from CarritoEntity;

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

--Datos de prueba BicicletUsadaEntity

insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado) values (100,'BicicletaUsadaEntity', 276.71, 'Puce', 1, 'Aerostar', 1, 1, 'http://intel.com/vestibulum/rutrum/rutrum.json', 'En proceso');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado) values (200,'BicicletaUsadaEntity', 395.40, 'Maroon', 1, 'Rabbit', 2, 2, 'http://discovery.com/montes/nascetur/ridiculus/mus/etiam/vel/augue.js', 'En proceso');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado) values (300,'BicicletaUsadaEntity', 757.94, 'Green', 1, 'LS', 3, 3, 'https://blogger.com/ultrices/posuere.jsp', 'En proceso');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado) values (400,'BicicletaUsadaEntity', 757.05, 'Green', 1, 'G', 4, 4, 'https://dagondesign.com/aliquam.js', 'En proceso');
insert into ItemEntity (id,dtype, precio, color, usada, categoria, modeloId, mod_id, facturaOriginal, estado) values (500,'BicicletaUsadaEntity', 929.71, 'Khaki', 1, 'Escort', 5, 5, 'https://netlog.com/in.jpg', 'En proceso');

--Datos de prueba AccesorioEntity

insert into ItemEntity (id, dtype, precio, color, modeloId,mod_id) values (11,'AccesorioEntity', 760.78, 'Puce', 1,1);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (12,'AccesorioEntity', 397.10, 'Blue', 2,2);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (13,'AccesorioEntity', 331.52, 'Green', 3,3);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (14,'AccesorioEntity', 251.36, 'Goldenrod', 4,4);
insert into ItemEntity (id, dtype,precio, color, usada, categoria, modeloId,mod_id) values (15,'AccesorioEntity', 118.77, 'Pink', 5,5);

