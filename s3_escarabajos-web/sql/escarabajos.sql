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



insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (21000, 'Firmstrong', 'SILAA BMX', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (22000, 'Razor', 'CASCO BMX', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (23000, 'GMC', 'RODILLERAS BMX', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (24000, 'Sthwinn', 'KPW', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (25000, 'Dynatraft', 'CNY', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (26000, 'Roadmaster', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (27000, 'Kent', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (28000, 'GMC', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (29000, 'Biking', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (30000, 'Kent', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (31000, 'Tony Hawk', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (32000, 'Planet Bike', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (33000, 'GMC', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (34000, 'Cayman', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (35000, 'Firmstrong', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (36000, 'Roadmaster', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (37000, 'GMC', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (38000, 'Cayman', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (39000, 'Firmstrong', 'EUR', 0, 'Accesorio');
insert into ModeloEntity (id, marca, referencia, calificacionMedia, tipoModelo) values (40000, 'Roadmaster', 'EUR', 0, 'Accesorio');


--Datos de prueba AccesorioEntity

insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (21000,'AccesorioEntity', 250000, '#000000', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (21000, 'https://images-na.ssl-images-amazon.com/images/I/71rFOHzTphL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (22000,'AccesorioEntity', 250000, '#0000FF', 0, 'Cascos', 21000,21000,1,0.3);
insert into Item_album (itemEntity_ID, album) values (22000, 'https://images-na.ssl-images-amazon.com/images/I/71rClg8nVeL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (23000,'AccesorioEntity', 250000, '#FF0000', 0, 'Cascos', 21000,21000,1,0.2);
insert into Item_album (itemEntity_ID, album) values (23000, 'https://images-na.ssl-images-amazon.com/images/I/71Qj-JnBaTL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (24000,'AccesorioEntity', 250000, '#E5EEF4', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (24000, 'https://images-na.ssl-images-amazon.com/images/I/71rhfQJH5DL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (25000,'AccesorioEntity', 250000, '#c4f441', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (25000, 'https://images-na.ssl-images-amazon.com/images/I/71s71UaXLkL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (26000,'AccesorioEntity', 250000, '#41484D', 0, 'Cascos', 21000,21000,1,1);
insert into Item_album (itemEntity_ID, album) values (26000, 'https://images-na.ssl-images-amazon.com/images/I/61HAq2rbO3L._SL1200_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (27000,'AccesorioEntity', 126000, '#FFFFFF', 0, 'Cascos', 22000,22000,1,0.7);
insert into Item_album (itemEntity_ID, album) values (27000, 'https://images-na.ssl-images-amazon.com/images/I/71BAYoGYiGL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (28000,'AccesorioEntity', 126000, '#000000', 0, 'Cascos', 22000,22000,1,1);
insert into Item_album (itemEntity_ID, album) values (28000, 'https://images-na.ssl-images-amazon.com/images/I/81178Vovh-L._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (29000,'AccesorioEntity', 85000, '#0000FF', 0, 'Cascos', 23000,23000,1,1);
insert into Item_album (itemEntity_ID, album) values (29000, 'https://images-na.ssl-images-amazon.com/images/I/610ffdnzGGL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (30000,'AccesorioEntity', 85000, '#000000', 0, 'Cascos', 23000,23000,1,0.8);
insert into Item_album (itemEntity_ID, album) values (30000, 'https://images-na.ssl-images-amazon.com/images/I/81iwgmoYuxL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (31000,'AccesorioEntity', 73250, '#FF0000', 0, 'Cascos', 24000,24000,1,1);
insert into Item_album (itemEntity_ID, album) values (31000, 'https://images-na.ssl-images-amazon.com/images/I/61R-JqGJPmL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (32000,'AccesorioEntity', 73250, '#000000', 0, 'Cascos', 24000,24000,1,1);
insert into Item_album (itemEntity_ID, album) values (32000, 'https://images-na.ssl-images-amazon.com/images/I/61yjq4AjPbL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (33000,'AccesorioEntity', 123499, '#000000', 0, 'Cascos', 25000,25000,1,1);
insert into Item_album (itemEntity_ID, album) values (33000, 'https://images-na.ssl-images-amazon.com/images/I/51YWLca77FL._SL1001_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (34000,'AccesorioEntity', 123499, '#FFFFFF', 0, 'Cascos', 25000,25000,1,1);
insert into Item_album (itemEntity_ID, album) values (34000, 'https://images-na.ssl-images-amazon.com/images/I/517MeKNIOVL._SL1001_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (35000,'AccesorioEntity', 450000, '#000000', 0, 'Rodilleras', 26000,26000,1,0.5);
insert into Item_album (itemEntity_ID, album) values (35000, 'https://images-na.ssl-images-amazon.com/images/I/61qpGLzOjIL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (36000,'AccesorioEntity', 450000, '#d6bb84', 0, 'Rodilleras', 26000,26000,1,1);
insert into Item_album (itemEntity_ID, album) values (36000, 'https://images-na.ssl-images-amazon.com/images/I/61MkRnOMikL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (37000,'AccesorioEntity', 670000, '#000000', 0, 'Rodilleras', 27000,27000,1,0.4);
insert into Item_album (itemEntity_ID, album) values (37000, 'https://images-na.ssl-images-amazon.com/images/I/517x2B9vi3L._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (38000,'AccesorioEntity', 670000, '#DBDBDB', 0, 'Rodilleras', 27000,27000,1,1);
insert into Item_album (itemEntity_ID, album) values (38000, 'https://images-na.ssl-images-amazon.com/images/I/51dOd-cs6YL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (39000,'AccesorioEntity', 890000, '#000000', 0, 'Rodilleras', 28000,28000,1,1);
insert into Item_album (itemEntity_ID, album) values (39000, 'https://images-na.ssl-images-amazon.com/images/I/71WDCcz5dlL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (40000,'AccesorioEntity', 890000, '#000000', 0, 'Rodilleras', 28000,28000,1,1);
insert into Item_album (itemEntity_ID, album) values (40000, 'https://images-na.ssl-images-amazon.com/images/I/71WDCcz5dlL._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (41000,'AccesorioEntity', 72000, '#0000FF', 0, 'Rodilleras', 29000,29000,1,1);
insert into Item_album (itemEntity_ID, album) values (41000, 'https://images-na.ssl-images-amazon.com/images/I/91wa0Txl8qL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (42000,'AccesorioEntity', 72000, '#FF0000', 0, 'Rodilleras', 29000,29000,1,1);
insert into Item_album (itemEntity_ID, album) values (42000, 'https://images-na.ssl-images-amazon.com/images/I/81fk34wedmL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (43000,'AccesorioEntity', 800000, '#000000', 0, 'Rodilleras', 30000,30000,1,1);
insert into Item_album (itemEntity_ID, album) values (43000, 'https://images-na.ssl-images-amazon.com/images/I/61XvekZXT3L._SL1000_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (44000,'AccesorioEntity', 700000, '#FF0000', 0, 'Ropa', 31000,31000,1,1);
insert into Item_album (itemEntity_ID, album) values (44000, 'https://images-na.ssl-images-amazon.com/images/I/81%2BSfvSt2CL._SL1500_.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (45000,'AccesorioEntity', 600000, '#FFFFFF', 0, 'Ropa', 32000,32000,1,1);
insert into Item_album (itemEntity_ID, album) values (45000, 'http://www.nki.com.co/wp-content/uploads/2016/09/109-510x600.png');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (46000,'AccesorioEntity', 100000, '#FF0000', 0, 'Ropa', 33000,33000,1,1);
insert into Item_album (itemEntity_ID, album) values (46000, 'https://myxopro.com/store/877-thickbox_default/Pantaloneta-deportiva.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (47000,'AccesorioEntity', 60000, '#FF0000', 0, 'Ropa', 34000,34000,1,1);
insert into Item_album (itemEntity_ID, album) values (47000, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201803/14/00199441018027____1__640x640.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (48000,'AccesorioEntity', 70000, '#0000FF', 0, 'Ropa', 35000,35000,1,1);
insert into Item_album (itemEntity_ID, album) values (48000, 'http://www.tiendadim.com/imagen-pantaloneta_training-1104796-800-600-1-75.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (49000,'AccesorioEntity', 50000, '#FFFFFF', 0, 'Ropa', 36000,36000,1,1);
insert into Item_album (itemEntity_ID, album) values (49000, 'https://http2.mlstatic.com/confeccion-camisetas-deportivas-equipos-uniformes-deportivos-D_NQ_NP_242215-MPE25135908590_102016-F.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (50000,'AccesorioEntity', 55000, '#0000FF', 0, 'Ropa', 37000,37000,1,1);
insert into Item_album (itemEntity_ID, album) values (50000, 'https://i.linio.com/p/5e5cbcca63dac49e18b03335b7c7dc21-catalog.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (51000,'AccesorioEntity', 230000, '#FF0000', 0, 'Ropa', 38000,38000,1,1);
insert into Item_album (itemEntity_ID, album) values (51000, 'https://estaticoscssar3.subeunescalon.com/articulos/95-large_default/camiseta-deportiva-tecnic.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (52000,'AccesorioEntity', 165000, '#c4f441', 0, 'Ropa', 39000,39000,1,1);
insert into Item_album (itemEntity_ID, album) values (52000, 'http://pasegol.cl/web/wp-content/uploads/2017/02/197c9dd1d2ddc59b5f82c9df254ffb14.jpg');
insert into ItemEntity (id, dtype, precio, color, usada, categoria, modeloId,mod_id,disponible,multiplicador) values (53000,'AccesorioEntity', 174000, '#bb87dd', 0, 'Ropa', 40000,40000,1,1);
insert into Item_album (itemEntity_ID, album) values (53000, 'https://i.pinimg.com/originals/52/a6/8e/52a68e098cd02415b8eefb9b17133403.jpg');


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
insert into FacturaEntity values (11000, 200000.0, 9000);


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





