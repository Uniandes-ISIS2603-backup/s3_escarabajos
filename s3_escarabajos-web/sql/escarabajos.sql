--Borrar los datos de las tablas
delete from car_Items;
delete from item_album;
delete from reclamo_album;
delete from factura_items;
delete from lista_deseos_items;
delete from ReclamoEntity;
delete from CalificacionEntity;
delete from CarritoEntity;
delete from listadeseosentity;
delete from ItemEntity;
delete from ModeloEntity;
delete from MedioPagoEntity;
delete from FacturaEntity;
delete from ClienteEntity;

--Datos de prueba ModeloEntity con tipo modelo Bicicleta

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (1000, 'Firmstrong', 'Bruiser Man Beach Cruiser', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (1000,'BicicletaEntity', 120000, '#000000', 0, 'Cruiser', 1000,1000,1,1);
insert into Item_album (itemEntity_ID, album) values (1000, 'https://images-na.ssl-images-amazon.com/images/I/81kYoGzcLDL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (1000, 'https://images-na.ssl-images-amazon.com/images/I/913wgp0voNL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (1001,'BicicletaEntity', 120000, '#FFFF00', 0, 'Cruiser', 1000,1000,1,1);
insert into Item_album (itemEntity_ID, album) values (1001, 'https://images-na.ssl-images-amazon.com/images/I/81G8UnH6QIL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (1001, 'https://images-na.ssl-images-amazon.com/images/I/51MpJGBu01L.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (1002,'BicicletaUsadaEntity', 100000, '#FFFFFF', 1, 'Cruiser', 1000,1000,1,0.5);
insert into Item_album (itemEntity_ID, album) values (1002, 'https://images-na.ssl-images-amazon.com/images/I/910U4s-J8-L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (1002, 'https://images-na.ssl-images-amazon.com/images/I/910U4s-J8-L._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (2000, 'Firmstrong', 'Bella Womens Beach Cruiser', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (2000,'BicicletaEntity', 220000, '#FFC0CB', 0, 'Cruiser', 2000,2000,1,1);
insert into Item_album (itemEntity_ID, album) values (2000, 'https://images-na.ssl-images-amazon.com/images/I/81mwnj%2B13nL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (2000, 'https://images-na.ssl-images-amazon.com/images/I/81brb9cDDkL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (2001,'BicicletaEntity', 220000, '#800080', 0, 'Cruiser', 2000,2000,1,1);
insert into Item_album (itemEntity_ID, album) values (2001, 'https://images-na.ssl-images-amazon.com/images/I/81QcwWo2VGL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (2001, 'https://images-na.ssl-images-amazon.com/images/I/71hGcqHe6kL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (2002,'BicicletaUsadaEntity', 10000, '#FF0000', 1, 'Cruiser', 2000,2000,1,0.6);
insert into Item_album (itemEntity_ID, album) values (2002, 'https://images-na.ssl-images-amazon.com/images/I/71JwkMdRB-L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (2002, 'https://images-na.ssl-images-amazon.com/images/I/51s9rabbtwL.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (3000, 'GMC', 'Yukon Fat Bike', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (3000,'BicicletaEntity', 1000000, '#0000FF', 0, 'Fitness', 3000,3000,1,1);
insert into Item_album (itemEntity_ID, album) values (3000, 'https://images-na.ssl-images-amazon.com/images/I/91K66vIoTiL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (3000, 'https://images-na.ssl-images-amazon.com/images/I/811kN6KJBOL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (4000, 'GMC', 'Topkick Dual Suspension', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (4000,'BicicletaEntity', 4000000, '#0000FF', 0, 'Mountain', 4000,4000,1,1);
insert into Item_album (itemEntity_ID, album) values (4000, 'https://images-na.ssl-images-amazon.com/images/I/91woc0SP1dL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (4000, 'https://images-na.ssl-images-amazon.com/images/I/81DraJbLizL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (5000,'Schwinn', ' Perla 7 Speed Cruiser', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (5000,'BicicletaEntity', 600000, '#12C3DD', 0, 'Cuiser', 5000,5000,1,1);
insert into Item_album (itemEntity_ID, album) values (5000, 'https://images-na.ssl-images-amazon.com/images/I/91B%2BhcrJCKL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5000, 'https://images-na.ssl-images-amazon.com/images/I/81t8EuABnFL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5000, 'https://images-na.ssl-images-amazon.com/images/I/81HpT6MOLoL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5000, 'https://images-na.ssl-images-amazon.com/images/I/61-RSg4pLEL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (5001,'BicicletaEntity', 600000, '#FF0000', 0, 'Cuiser', 5000,5000,1,1);
insert into Item_album (itemEntity_ID, album) values (5001, 'https://images-na.ssl-images-amazon.com/images/I/91GsiroidaL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5001, 'https://images-na.ssl-images-amazon.com/images/I/91GsiroidaL._SL1500_.jpg');;
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (5002,'BicicletaUsadaEntity', 500000, '#FFC0CB', 1, 'Cuiser', 5000,5000,1,0.7);
insert into Item_album (itemEntity_ID, album) values (5002, 'https://images-na.ssl-images-amazon.com/images/I/91X7NGL0ARL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5002, 'https://images-na.ssl-images-amazon.com/images/I/61KDokX5MmL._SL1000_.jpg');
insert into Item_album (itemEntity_ID, album) values (5002, 'https://images-na.ssl-images-amazon.com/images/I/81KTXfpArhL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (5002, 'https://images-na.ssl-images-amazon.com/images/I/81BggFSoEzL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (6000, 'Schwinn', 'Sanctuary 7-Speed Cruiser', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (6000,'BicicletaEntity', 2000000, '#855a46', 0, 'Cuiser', 6000,6000,1,0.4);
insert into Item_album (itemEntity_ID, album) values (6000, 'https://images-na.ssl-images-amazon.com/images/I/91LPyJRxmEL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (6000, 'https://images-na.ssl-images-amazon.com/images/I/91VXruOcu6L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (6000, 'https://images-na.ssl-images-amazon.com/images/I/81PwvMdj93L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (6000, 'https://images-na.ssl-images-amazon.com/images/I/61zySarb66L._SL1000_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (7000, 'Roadmaster', 'Granite Peak', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (7000,'BicicletaEntity', 200000, '#000000', 0, 'Mountain', 7000,7000,1,0.3);
insert into Item_album (itemEntity_ID, album) values (7000, 'https://images-na.ssl-images-amazon.com/images/I/51s6wT7fDtL.jpg');
insert into Item_album (itemEntity_ID, album) values (7000, 'https://images-na.ssl-images-amazon.com/images/I/41GFn3E8pxL.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (8000, 'Dynacraft', 'Island Breeze Cruiser', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (8000,'BicicletaEntity', 300000, '#800080', 0, 'Cuiser', 8000,8000,1,0.7);
insert into Item_album (itemEntity_ID, album) values (8000, 'https://images-na.ssl-images-amazon.com/images/I/81os1AqAkEL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (8000, 'https://images-na.ssl-images-amazon.com/images/I/81DraJbLizL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (9000, 'Dynacraft', '7 speed Cityscape', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (9000,'BicicletaEntity', 5000000, '#000000', 0, 'E-Bike', 9000,9000,1,0.9);
insert into Item_album (itemEntity_ID, album) values (9000, 'https://images-na.ssl-images-amazon.com/images/I/51cyoW5hSoL.jpg');
insert into Item_album (itemEntity_ID, album) values (9000, 'https://images-na.ssl-images-amazon.com/images/I/71CT3fHO%2B5L._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (10000, 'Raleigh', ' Venture Thru Comfort', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (10000,'BicicletaEntity', 300000, '#000000', 0, 'Comfort', 10000,10000,1,0.3);
insert into Item_album (itemEntity_ID, album) values (10000, 'https://images-na.ssl-images-amazon.com/images/I/81VP4QqmVDL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (10000, 'https://images-na.ssl-images-amazon.com/images/I/81sRoi1nKfL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (10001,'BicicletaEntity', 500000, '#12C3DD', 0, 'Comfort',10000,10000,1,1);
insert into Item_album (itemEntity_ID, album) values (10001, 'https://images-na.ssl-images-amazon.com/images/I/816twdJdmeL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (10001, 'https://images-na.ssl-images-amazon.com/images/I/81aBmT%2BVgdL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (11000, 'Raleigh', 'Tristar 3-Speed', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (11000,'BicicletaEntity', 4000000, '#0000FF', 0, 'TRIKE',11000,11000,1,1);
insert into Item_album (itemEntity_ID, album) values (11000, 'https://images-na.ssl-images-amazon.com/images/I/71bLZAaU%2BXL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (11000, 'https://images-na.ssl-images-amazon.com/images/I/71EdRFl725L._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (11001,'BicicletaUsadaEntity', 3000000, '#855a46', 1, 'TRIKE',11000,11000,1,0.2);
insert into Item_album (itemEntity_ID, album) values (11001, 'https://images-na.ssl-images-amazon.com/images/I/71KE8dKB1PL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (11001, 'https://images-na.ssl-images-amazon.com/images/I/71QHKaKA7GL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (12000, 'Raleigh', 'Superbe City', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (12000,'BicicletaEntity', 400000, '#000000', 0, 'City',12000,12000,1,0.1);
insert into Item_album (itemEntity_ID, album) values (12000, 'https://images-na.ssl-images-amazon.com/images/I/71BVDmJ1DbL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (12000, 'https://images-na.ssl-images-amazon.com/images/I/710BxXvDgxL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (12001,'BicicletaEntity', 400000, '#FFC0CB', 0, 'City',12000,12000,1,1);
insert into Item_album (itemEntity_ID, album) values (12001, 'https://images-na.ssl-images-amazon.com/images/I/71B6tx6e2yL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (12001, 'https://images-na.ssl-images-amazon.com/images/I/71l5bpf7e-L._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (13000, 'Raleigh', 'Revere 1 Endurance', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (13000,'BicicletaEntity', 200000, '#12C3DD', 0, 'Fitness',13000,13000,1,1);
insert into Item_album (itemEntity_ID, album) values (13000, 'https://images-na.ssl-images-amazon.com/images/I/718T9oodl8L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (13000, 'https://images-na.ssl-images-amazon.com/images/I/71dbRHQ-vTL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (14000, 'Kent', 'Dual Drive Tandem', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (14000,'BicicletaEntity', 3000000, '#008000', 0, 'Tandem',14000,14000,1,1);
insert into Item_album (itemEntity_ID, album) values (14000, 'https://images-na.ssl-images-amazon.com/images/I/81Fi6V7wciL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (14000, 'https://images-na.ssl-images-amazon.com/images/I/81CxTJ7K%2BeL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (14001,'BicicletaUsadaEntity', 2000000, '#FF0000', 1, 'Tandem',14000,14000,1,1);
insert into Item_album (itemEntity_ID, album) values (14001, 'https://images-na.ssl-images-amazon.com/images/I/61pHAXzvC1L._SL1000_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (15000, 'Kent', '7-Speed Folding Bike', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (15000,'BicicletaEntity', 200000, '#0000FF', 0, 'Folding',15000,15000,1,1);
insert into Item_album (itemEntity_ID, album) values (15000, 'https://images-na.ssl-images-amazon.com/images/I/91BFfnDDBfL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (15000, 'https://images-na.ssl-images-amazon.com/images/I/81H5fp%2BwM6L._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (16000, 'Kent', 'Lucky Star', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (16000,'BicicletaEntity', 600000, '#FFC0CB', 0, 'Kids',16000,16000,1,1);
insert into Item_album (itemEntity_ID, album) values (16000, 'https://images-na.ssl-images-amazon.com/images/I/81XZFO-XfOL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (17000, 'Gama', 'Hybrid Urban Commuter Road', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (17000,'BicicletaEntity', 100000, '#000000', 0, 'City',17000,17000,1,1);
insert into Item_album (itemEntity_ID, album) values (17000, 'https://images-na.ssl-images-amazon.com/images/I/81Y8TEMRxkL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (17000, 'https://images-na.ssl-images-amazon.com/images/I/81Z8209J-EL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (18000, 'Gama', '7 Speed Shimano Hybrid', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (18000,'BicicletaEntity', 900000, '#FF0000', 0, 'Fitness',18000,18000,1,1);
insert into Item_album (itemEntity_ID, album) values (18000, 'https://images-na.ssl-images-amazon.com/images/I/711UkQj7ryL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (18000, 'https://images-na.ssl-images-amazon.com/images/I/81jJflySbWL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (18000, 'https://images-na.ssl-images-amazon.com/images/I/612hOF0x3NL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (19000, 'Public', 'Talus 1', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (19000,'BicicletaEntity', 600000, '#000000', 0, 'Mountain',19000,19000,1,1);
insert into Item_album (itemEntity_ID, album) values (19000, 'https://images-na.ssl-images-amazon.com/images/I/81eUeTRmxWL._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (19000, 'https://images-na.ssl-images-amazon.com/images/I/81pICB6rMTL._SL1500_.jpg');

insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (20000, 'Public', 'Cadent 1 Urban', 0, 'Bicicleta');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (20000,'BicicletaEntity', 700000, '#D2B48C', 0, 'Fitness',20000,20000,1,1);
insert into Item_album (itemEntity_ID, album) values (20000, 'https://images-na.ssl-images-amazon.com/images/I/815ZYVMF53L._SL1500_.jpg');
insert into Item_album (itemEntity_ID, album) values (20000, 'https://images-na.ssl-images-amazon.com/images/I/81FMWzwT7cL._SL1500_.jpg');



insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (21000, 'Firmstrong', 'CASCO ALETA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (22000, 'Razor', 'CASCO BMX', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (23000, 'GMC', 'CASCO ULTIMATE', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (24000, 'Sthwinn', 'CASCO XTREME', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (25000, 'Dynatraft', 'CASCO CNY', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (26000, 'Roadmaster', 'RODILLERAS EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (27000, 'Kent', 'RODILLERAS BMX', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (28000, 'GMC', 'RODILLERAS PRO', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (29000, 'Biking', 'RODILLERAS CARRETERA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (30000, 'Kent', 'RODILLERAS USA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (31000, 'Tony Hawk', 'PANTALONETA SENCILLA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (32000, 'Planet Bike', 'PANTALONETA LIVIANA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (33000, 'GMC', ' PANTALONETA EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (34000, 'Nike', 'PANTALONETA NIKE', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (35000, 'Firmstrong', 'PANTALONETA XS', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (36000, 'Roadmaster', 'CAMISETA EQUIPO', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (37000, 'GMC', 'CAMISETA MUJER', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (38000, 'Cayman', 'CAMISETA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (39000, 'Firmstrong', 'CAMISETA CLASICA', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (40000, 'Roadmaster', 'CAMISETA DEPORTIVA', 0, 'Accesorio');


--Datos de prueba AccesorioEntity

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21000,'AccesorioEntity', 250000, '#000000', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (21000, 'https://images-na.ssl-images-amazon.com/images/I/71rFOHzTphL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21001,'AccesorioEntity', 250000, '#0000FF', 0, 'Cascos', 21000,21000,1,0.3);
insert into Item_album (itemEntity_ID, album) values (21001, 'https://images-na.ssl-images-amazon.com/images/I/71rClg8nVeL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21002,'AccesorioEntity', 250000, '#FF0000', 0, 'Cascos', 21000,21000,1,0.2);
insert into Item_album (itemEntity_ID, album) values (21002, 'https://images-na.ssl-images-amazon.com/images/I/71Qj-JnBaTL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21003,'AccesorioEntity', 250000, '#E5EEF4', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (21003, 'https://images-na.ssl-images-amazon.com/images/I/71rhfQJH5DL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21004,'AccesorioEntity', 250000, '#c4f441', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (21004, 'https://images-na.ssl-images-amazon.com/images/I/71s71UaXLkL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21005,'AccesorioEntity', 250000, '#41484D', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (21005, 'https://images-na.ssl-images-amazon.com/images/I/61HAq2rbO3L._SL1200_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (22000,'AccesorioEntity', 126000, '#FFFFFF', 0, 'Cascos', 22000,22000,1,0.7);
insert into Item_album (itemEntity_ID, album) values (22000, 'https://images-na.ssl-images-amazon.com/images/I/71BAYoGYiGL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (22001,'AccesorioEntity', 126000, '#000000', 0, 'Cascos', 22000,22000,1,1);
insert into Item_album (itemEntity_ID, album) values (22001, 'https://images-na.ssl-images-amazon.com/images/I/81178Vovh-L._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (23000,'AccesorioEntity', 85000, '#0000FF', 0, 'Cascos', 23000,23000,1,1);
insert into Item_album (itemEntity_ID, album) values (23000, 'https://images-na.ssl-images-amazon.com/images/I/610ffdnzGGL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (23001,'AccesorioEntity', 85000, '#000000', 0, 'Cascos', 23000,23000,1,0.8);
insert into Item_album (itemEntity_ID, album) values (23001, 'https://images-na.ssl-images-amazon.com/images/I/81iwgmoYuxL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (24000,'AccesorioEntity', 73250, '#FF0000', 0, 'Cascos', 24000,24000,1,1);
insert into Item_album (itemEntity_ID, album) values (24000, 'https://images-na.ssl-images-amazon.com/images/I/61R-JqGJPmL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (24001,'AccesorioEntity', 73250, '#000000', 0, 'Cascos', 24000,24000,1,1);
insert into Item_album (itemEntity_ID, album) values (24001, 'https://images-na.ssl-images-amazon.com/images/I/61yjq4AjPbL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (25000,'AccesorioEntity', 123499, '#000000', 0, 'Cascos', 25000,25000,1,1);
insert into Item_album (itemEntity_ID, album) values (25000, 'https://images-na.ssl-images-amazon.com/images/I/51YWLca77FL._SL1001_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (25001,'AccesorioEntity', 123499, '#FFFFFF', 0, 'Cascos', 25000,25000,1,1);
insert into Item_album (itemEntity_ID, album) values (25001, 'https://images-na.ssl-images-amazon.com/images/I/517MeKNIOVL._SL1001_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (26000,'AccesorioEntity', 450000, '#000000', 0, 'Rodilleras', 26000,26000,1,0.5);
insert into Item_album (itemEntity_ID, album) values (26000, 'https://images-na.ssl-images-amazon.com/images/I/61qpGLzOjIL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (26001,'AccesorioEntity', 450000, '#d6bb84', 0, 'Rodilleras', 26000,26000,1,1);
insert into Item_album (itemEntity_ID, album) values (26001, 'https://images-na.ssl-images-amazon.com/images/I/61MkRnOMikL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (27000,'AccesorioEntity', 670000, '#000000', 0, 'Rodilleras', 27000,27000,1,0.4);
insert into Item_album (itemEntity_ID, album) values (27000, 'https://images-na.ssl-images-amazon.com/images/I/517x2B9vi3L._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (27001,'AccesorioEntity', 670000, '#DBDBDB', 0, 'Rodilleras', 27000,27000,1,1);
insert into Item_album (itemEntity_ID, album) values (27001, 'https://images-na.ssl-images-amazon.com/images/I/51dOd-cs6YL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (28000,'AccesorioEntity', 890000, '#000000', 0, 'Rodilleras', 28000,28000,1,1);
insert into Item_album (itemEntity_ID, album) values (28000, 'https://images-na.ssl-images-amazon.com/images/I/71WDCcz5dlL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (28001,'AccesorioEntity', 890000, '#000000', 0, 'Rodilleras', 28000,28000,1,1);
insert into Item_album (itemEntity_ID, album) values (28001, 'https://images-na.ssl-images-amazon.com/images/I/71WDCcz5dlL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (29000,'AccesorioEntity', 72000, '#0000FF', 0, 'Rodilleras', 29000,29000,1,1);
insert into Item_album (itemEntity_ID, album) values (29000, 'https://images-na.ssl-images-amazon.com/images/I/91wa0Txl8qL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (29001,'AccesorioEntity', 72000, '#FF0000', 0, 'Rodilleras', 29000,29000,1,1);
insert into Item_album (itemEntity_ID, album) values (29001, 'https://images-na.ssl-images-amazon.com/images/I/81fk34wedmL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (30000,'AccesorioEntity', 800000, '#000000', 0, 'Rodilleras', 30000,30000,1,1);
insert into Item_album (itemEntity_ID, album) values (30000, 'https://images-na.ssl-images-amazon.com/images/I/61XvekZXT3L._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (31000,'AccesorioEntity', 700000, '#FF0000', 0, 'Ropa', 31000,31000,1,1);
insert into Item_album (itemEntity_ID, album) values (31000, 'https://images-na.ssl-images-amazon.com/images/I/81%2BSfvSt2CL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (32000,'AccesorioEntity', 600000, '#FFFFFF', 0, 'Ropa', 32000,32000,1,1);
insert into Item_album (itemEntity_ID, album) values (32000, 'http://www.nki.com.co/wp-content/uploads/2016/09/109-510x600.png');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (33000,'AccesorioEntity', 100000, '#FF0000', 0, 'Ropa', 33000,33000,1,1);
insert into Item_album (itemEntity_ID, album) values (33000, 'https://myxopro.com/store/877-thickbox_default/Pantaloneta-deportiva.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (34000,'AccesorioEntity', 60000, '#FF0000', 0, 'Ropa', 34000,34000,1,1);
insert into Item_album (itemEntity_ID, album) values (34000, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201803/14/00199441018027____1__640x640.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (35000,'AccesorioEntity', 70000, '#0000FF', 0, 'Ropa', 35000,35000,1,1);
insert into Item_album (itemEntity_ID, album) values (35000, 'http://www.tiendadim.com/imagen-pantaloneta_training-1104796-800-600-1-75.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (36000,'AccesorioEntity', 50000, '#FFFFFF', 0, 'Ropa', 36000,36000,1,1);
insert into Item_album (itemEntity_ID, album) values (36000, 'https://http2.mlstatic.com/confeccion-camisetas-deportivas-equipos-uniformes-deportivos-D_NQ_NP_242215-MPE25135908590_102016-F.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (37000,'AccesorioEntity', 55000, '#0000FF', 0, 'Ropa', 37000,37000,1,1);
insert into Item_album (itemEntity_ID, album) values (37000, 'https://i.linio.com/p/5e5cbcca63dac49e18b03335b7c7dc21-catalog.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (38000,'AccesorioEntity', 230000, '#FF0000', 0, 'Ropa', 38000,38000,1,1);
insert into Item_album (itemEntity_ID, album) values (38000, 'https://estaticoscssar3.subeunescalon.com/articulos/95-large_default/camiseta-deportiva-tecnic.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (39000,'AccesorioEntity', 165000, '#c4f441', 0, 'Ropa', 39000,39000,1,1);
insert into Item_album (itemEntity_ID, album) values (39000, 'http://pasegol.cl/web/wp-content/uploads/2017/02/197c9dd1d2ddc59b5f82c9df254ffb14.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (40000,'AccesorioEntity', 174000, '#bb87dd', 0, 'Ropa', 40000,40000,1,1);
insert into Item_album (itemEntity_ID, album) values (40000, 'https://i.pinimg.com/originals/52/a6/8e/52a68e098cd02415b8eefb9b17133403.jpg');


--Datos de prueba ClienteEntity(con telefono y direccion no null)
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (1000, '9898989', 'coc@hotmale.com', 'Camilo','pedro69','468651684','Frankfurtcalle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (2000, '123', 'bss@gmail.com', 'Felipe','saga','1232123','calle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (3000, '1234', 'a121@gmail.com', 'Pedro','saga2','12345','Elm Street', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (4000, '12334', 'jorge@hotmale.com', 'Jorge','giorginio1232','468651684','Frankfurtcalle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (5000, '433211', 'sant@gmail.com', 'Santiago','s.beltran','12332123','calle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (6000, '2211123', 'and@gmail.com', 'Andres','a.varonm','122345','Elm Street', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (7000, '9898989', 'mate@hotmale.com', 'Mateo','m.devia','4686511684','Frankfurtcalle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (8000, '22211234', 'nico@gmail.com', 'Nicolas','n.gaitan','11232123','calle8-9', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (9000, '2212145', 'preubaCliente@gmail.com', 'Test Cliente','testC','12322245','Elm Street', null);
insert into ClienteEntity (id, cedula, correo, nombre, usuario,telefono,direccion, vendedor) values (10000, '1122', 'pruebaAdmin@gmail.com', 'Test Admin','testA','1211121345','Elm Street', null);

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

-- medios de pago
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(1000, 'Tarjeta de Credito', 1000, 1000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(2000, 'Tarjeta Debito', 1000, 1000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(3000, 'PSE', 1000, 1000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(4000, 'PayPal', 1000, 1000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(5000, 'PSE', 2000, 2000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(6000, 'PayPal', 2000, 2000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(7000, 'Tarjeta Debito', 3000, 3000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(8000, 'PSE', 3000, 3000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(9000, 'PayPal', 3000, 3000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(10000, 'Tarjeta Debito', 4000, 4000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(11000, 'PSE', 4000, 4000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(12000, 'PayPal', 4000, 4000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(13000, 'Tarjeta Debito', 5000, 5000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(14000, 'PSE', 5000, 5000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(15000, 'PayPal', 5000, 5000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(16000, 'Tarjeta de Credito', 6000, 6000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(17000, 'Tarjeta Debito', 6000, 6000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(18000, 'Tarjeta de Credito', 7000, 7000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(19000, 'Tarjeta Debito', 7000, 7000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(20000, 'Tarjeta de Credito', 8000, 8000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(21000, 'Tarjeta Debito', 8000, 8000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(22000, 'Tarjeta de Credito', 9000, 9000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(23000, 'Tarjeta Debito', 9000, 9000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(24000, 'PSE', 9000, 9000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(25000, 'PayPal', 9000, 9000);

insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(26000, 'Tarjeta de Credito', 10000, 10000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(27000, 'Tarjeta Debito', 10000, 10000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(28000, 'PSE', 10000, 10000);
insert into MedioPagoEntity (id, tipo, cliente_Id, factura_id) values(29000, 'PayPal', 10000, 10000);



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

-- calificaciones

insert into CalificacionEntity values (1000002, 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 5,2000,1000);
insert into CalificacionEntity values (1000003, 'In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 5,3000,1000);
insert into CalificacionEntity values (1000004, 'Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum.', 2,4000,1000);
insert into CalificacionEntity values (1000005, 'In congue. Etiam justo.', 5,5000,2000);
insert into CalificacionEntity values (1000006, 'Ut at dolor quis odio consequat varius. Integer ac leo.', 4,6000,2000);
insert into CalificacionEntity values (1000007, 'Aliquam sit amet diam in magna bibendum imperdiet.', 5,7000,2000);
insert into CalificacionEntity values (1000008, 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 4,8000,2000);
insert into CalificacionEntity values (1000009, 'Morbi a ipsum. Integer a nibh.', 1,1000,3000);
insert into CalificacionEntity values (10000010, 'Integer a nibh. In quis justo.', 4,2000,3000);
insert into CalificacionEntity values (10000011, 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 5,3000,3000);
insert into CalificacionEntity values (10000012, 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', 2,4000,3000);
insert into CalificacionEntity values (10000013, 'Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', 4,5000,4000);
insert into CalificacionEntity values (10000014, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 1,6000,4000);
insert into CalificacionEntity values (10000015, 'Etiam justo. Etiam pretium iaculis justo.', 5,7000,4000);
insert into CalificacionEntity values (10000016, 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 3,8000,4000);
insert into CalificacionEntity values (10000017, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 5,1000,5000);
insert into CalificacionEntity values (10000018, 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 5,2000,5000);
insert into CalificacionEntity values (10000019, 'Suspendisse potenti. Nullam porttitor lacus at turpis.', 3,3000,5000);
insert into CalificacionEntity values (10000020, 'Pellentesque ultrices mattis odio. Donec vitae nisi.', 5,4000,5000);
insert into CalificacionEntity values (10000021, 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet.', 2,5000,6000);
insert into CalificacionEntity values (10000022, 'Aliquam quis turpis eget elit sodales scelerisque.', 5,6000,6000);
insert into CalificacionEntity values (10000023, 'Nullam varius. Nulla facilisi.', 5,7000,6000);
insert into CalificacionEntity values (10000024, 'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.', 3,8000,6000);
insert into CalificacionEntity values (10000025, 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.', 4,1000,7000);
insert into CalificacionEntity values (10000026, 'Etiam pretium iaculis justo. In hac habitasse platea dictumst.', 4,2000,7000);
insert into CalificacionEntity values (10000027, 'Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 3,3000,7000);
insert into CalificacionEntity values (10000028, 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.', 2,4000,7000);
insert into CalificacionEntity values (10000029, 'Suspendisse potenti.', 3,5000,8000);
insert into CalificacionEntity values (10000030, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum.', 3,6000,8000);
insert into CalificacionEntity values (10000031, 'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 1,7000,8000);
insert into CalificacionEntity values (10000032, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 4,8000,8000);
insert into CalificacionEntity values (10000033, 'Nulla facilisi. Cras non velit nec nisi vulputate nonummy.', 2,1000,9000);
insert into CalificacionEntity values (10000034, 'Etiam faucibus cursus urna.', 3,2000,9000);
insert into CalificacionEntity values (10000035, 'Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla.', 4,3000,9000);
insert into CalificacionEntity values (10000036, 'Etiam faucibus cursus urna.', 2,4000,9000);
insert into CalificacionEntity values (10000037, 'Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus.', 1,5000,10000);
insert into CalificacionEntity values (10000038, 'Aenean sit amet justo.', 3,6000,10000);
insert into CalificacionEntity values (10000039, 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien.', 3,7000,10000);
insert into CalificacionEntity values (10000040, 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.', 3,8000,10000);
insert into CalificacionEntity values (10000041, 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.', 1,1000,11000);
insert into CalificacionEntity values (10000042, 'Nulla ut erat id mauris vulputate elementum. Nullam varius.', 4,2000,11000);
insert into CalificacionEntity values (10000043, 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique.', 2,3000,11000);
insert into CalificacionEntity values (10000044, 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 1,4000,11000);
insert into CalificacionEntity values (10000045, 'Suspendisse potenti.', 3,5000,12000);
insert into CalificacionEntity values (10000046, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.', 1,6000,12000);
insert into CalificacionEntity values (10000047, 'Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', 1,7000,12000);
insert into CalificacionEntity values (10000048, 'Morbi vel lectus in quam fringilla rhoncus.', 4,8000,12000);
insert into CalificacionEntity values (10000049, 'Phasellus id sapien in sapien iaculis congue.', 2,1000,13000);
insert into CalificacionEntity values (10000050, 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 4,2000,13000);
insert into CalificacionEntity values (10000051, 'Phasellus in felis.', 4,3000,13000);
insert into CalificacionEntity values (10000052, 'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', 5,4000,13000);
insert into CalificacionEntity values (10000053, 'In congue.', 2,5000,14000);
insert into CalificacionEntity values (10000054, 'Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 3,6000,14000);
insert into CalificacionEntity values (10000055, 'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', 3,7000,14000);
insert into CalificacionEntity values (10000056, 'Integer ac leo. Pellentesque ultrices mattis odio.', 3,8000,14000);
insert into CalificacionEntity values (10000057, 'Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.', 4,1000,15000);
insert into CalificacionEntity values (10000058, 'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 1,2000,15000);
insert into CalificacionEntity values (10000059, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.', 3,3000,15000);
insert into CalificacionEntity values (10000060, 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 1,4000,15000);
insert into CalificacionEntity values (10000061, 'Maecenas pulvinar lobortis est. Phasellus sit amet erat.', 4,5000,16000);
insert into CalificacionEntity values (10000062, 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 4,6000,16000);
insert into CalificacionEntity values (10000063, 'In hac habitasse platea dictumst.', 2,7000,16000);
insert into CalificacionEntity values (10000064, 'Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.', 1,8000,16000);
insert into CalificacionEntity values (10000065, 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', 5,1000,17000);
insert into CalificacionEntity values (10000066, 'Proin risus.', 1,2000,17000);
insert into CalificacionEntity values (10000067, 'Nulla tellus. In sagittis dui vel nisl.', 3,3000,17000);
insert into CalificacionEntity values (10000068, 'In hac habitasse platea dictumst.', 5,4000,17000);
insert into CalificacionEntity values (10000069, 'Morbi a ipsum.', 5,5000,18000);
insert into CalificacionEntity values (10000070, 'Suspendisse potenti.', 5,6000,18000);
insert into CalificacionEntity values (10000071, 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 1,7000,18000);
insert into CalificacionEntity values (10000072, 'Duis mattis egestas metus.', 5,8000,18000);
insert into CalificacionEntity values (10000073, 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 5,1000,19000);
insert into CalificacionEntity values (10000074, 'Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis.', 2,2000,19000);
insert into CalificacionEntity values (10000075, 'Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 1,3000,19000);
insert into CalificacionEntity values (10000076, 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros.', 3,4000,19000);
insert into CalificacionEntity values (10000077, 'Nulla tellus. In sagittis dui vel nisl. Duis ac nibh.', 2,5000,20000);
insert into CalificacionEntity values (10000078, 'In quis justo. Maecenas rhoncus aliquam lacus.', 1,6000,20000);
insert into CalificacionEntity values (10000079, 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 1,7000,20000);
insert into CalificacionEntity values (10000080, 'Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 5,8000,20000);
insert into CalificacionEntity values (10000081, 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 5,1000,21000);
insert into CalificacionEntity values (10000082, 'Pellentesque ultrices mattis odio. Donec vitae nisi.', 1,2000,21000);
insert into CalificacionEntity values (10000083, 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', 3,3000,21000);
insert into CalificacionEntity values (10000084, 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 5,4000,21000);
insert into CalificacionEntity values (10000085, 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis.', 5,5000,22000);
insert into CalificacionEntity values (10000086, 'Nullam molestie nibh in lectus. Pellentesque at nulla.', 4,6000,22000);
insert into CalificacionEntity values (10000087, 'Integer tincidunt ante vel ipsum.', 2,7000,22000);
insert into CalificacionEntity values (10000088, 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', 5,8000,22000);
insert into CalificacionEntity values (10000089, 'Cras in purus eu magna vulputate luctus.', 1,1000,23000);
insert into CalificacionEntity values (10000090, 'Pellentesque ultrices mattis odio.', 3,2000,23000);
insert into CalificacionEntity values (10000091, 'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.', 2,3000,23000);
insert into CalificacionEntity values (10000092, 'Quisque ut erat. Curabitur gravida nisi at nibh.', 5,4000,23000);
insert into CalificacionEntity values (10000093, 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', 1,5000,24000);
insert into CalificacionEntity values (10000094, 'Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna.', 1,6000,24000);
insert into CalificacionEntity values (10000095, 'Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 3,7000,24000);
insert into CalificacionEntity values (10000096, 'Nulla facilisi.', 5,8000,24000);
insert into CalificacionEntity values (10000097, 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 5,1000,25000);
insert into CalificacionEntity values (10000098, 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla.', 5,2000,25000);
insert into CalificacionEntity values (10000099, 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 3,3000,25000);
insert into CalificacionEntity values (100000100, 'Quisque ut erat. Curabitur gravida nisi at nibh.', 3,4000,25000);
insert into CalificacionEntity values (100000101, 'In blandit ultrices enim.', 1,5000,26000);
insert into CalificacionEntity values (100000102, 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 1,6000,26000);
insert into CalificacionEntity values (100000103, 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 5,7000,26000);
insert into CalificacionEntity values (100000104, 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 3,8000,26000);
insert into CalificacionEntity values (100000105, 'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', 1,1000,27000);
insert into CalificacionEntity values (100000106, 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.', 1,2000,27000);
insert into CalificacionEntity values (100000107, 'Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante.', 4,3000,27000);
insert into CalificacionEntity values (100000108, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.', 5,4000,27000);
insert into CalificacionEntity values (100000109, 'In quis justo. Maecenas rhoncus aliquam lacus.', 2,5000,28000);
insert into CalificacionEntity values (100000110, 'Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 2,6000,28000);
insert into CalificacionEntity values (100000111, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.', 3,7000,28000);
insert into CalificacionEntity values (100000112, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 3,8000,28000);
insert into CalificacionEntity values (100000113, 'Quisque ut erat.', 2,1000,29000);
insert into CalificacionEntity values (100000114, 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 1,2000,29000);
insert into CalificacionEntity values (100000115, 'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', 2,3000,29000);
insert into CalificacionEntity values (100000116, 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', 5,4000,29000);
insert into CalificacionEntity values (100000117, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 4,5000,30000);
insert into CalificacionEntity values (100000118, 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 2,6000,30000);
insert into CalificacionEntity values (100000119, 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 1,7000,30000);
insert into CalificacionEntity values (100000120, 'Praesent blandit. Nam nulla.', 3,8000,30000);
insert into CalificacionEntity values (100000121, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.', 4,1000,31000);
insert into CalificacionEntity values (100000122, 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 1,2000,31000);
insert into CalificacionEntity values (100000123, 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 4,3000,31000);
insert into CalificacionEntity values (100000124, 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 3,4000,31000);
insert into CalificacionEntity values (100000125, 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 1,5000,32000);
insert into CalificacionEntity values (100000126, 'Proin at turpis a pede posuere nonummy.', 5,6000,32000);
insert into CalificacionEntity values (100000127, 'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.', 2,7000,32000);
insert into CalificacionEntity values (100000128, 'Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 1,8000,32000);
insert into CalificacionEntity values (100000129, 'Ut at dolor quis odio consequat varius. Integer ac leo.', 2,1000,33000);
insert into CalificacionEntity values (100000130, 'Vivamus in felis eu sapien cursus vestibulum.', 1,2000,33000);
insert into CalificacionEntity values (100000131, 'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 2,3000,33000);
insert into CalificacionEntity values (100000132, 'Fusce posuere felis sed lacus.', 5,4000,33000);
insert into CalificacionEntity values (100000133, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', 3,5000,34000);
insert into CalificacionEntity values (100000134, 'In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.', 4,6000,34000);
insert into CalificacionEntity values (100000135, 'Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', 1,7000,34000);
insert into CalificacionEntity values (100000136, 'Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 3,8000,34000);
insert into CalificacionEntity values (100000137, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 1,1000,35000);
insert into CalificacionEntity values (100000138, 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim.', 4,2000,35000);
insert into CalificacionEntity values (100000139, 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 4,3000,35000);
insert into CalificacionEntity values (100000140, 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.', 5,4000,35000);
insert into CalificacionEntity values (100000141, 'Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo.', 1,5000,36000);
insert into CalificacionEntity values (100000142, 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', 5,6000,36000);
insert into CalificacionEntity values (100000143, 'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy.', 4,7000,36000);
insert into CalificacionEntity values (100000144, 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue.', 2,8000,36000);
insert into CalificacionEntity values (100000145, 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 1,1000,37000);
insert into CalificacionEntity values (100000146, 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.', 3,2000,37000);
insert into CalificacionEntity values (100000147, 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 5,3000,37000);
insert into CalificacionEntity values (100000148, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 1,4000,37000);
insert into CalificacionEntity values (100000149, 'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti.', 5,5000,38000);
insert into CalificacionEntity values (100000150, 'Sed ante. Vivamus tortor.', 3,6000,38000);
insert into CalificacionEntity values (100000151, 'Aenean sit amet justo. Morbi ut odio.', 1,7000,38000);
insert into CalificacionEntity values (100000152, 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', 1,8000,38000);
insert into CalificacionEntity values (100000153, 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 4,1000,39000);
insert into CalificacionEntity values (100000154, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.', 5,2000,39000);
insert into CalificacionEntity values (100000155, 'Nullam porttitor lacus at turpis.', 3,3000,39000);
insert into CalificacionEntity values (100000156, 'Duis at velit eu est congue elementum.', 2,4000,39000);
insert into CalificacionEntity values (100000157, 'Nulla nisl. Nunc nisl.', 5,5000,40000);
insert into CalificacionEntity values (100000158, 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 1,6000,40000);
insert into CalificacionEntity values (100000159, 'Etiam vel augue.', 4,7000,40000);
insert into CalificacionEntity values (100000160, 'Suspendisse accumsan tortor quis turpis.', 3,8000,40000);





