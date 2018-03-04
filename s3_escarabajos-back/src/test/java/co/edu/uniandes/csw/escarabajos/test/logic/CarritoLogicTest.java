/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.AccesorioLogic;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Mateo
 */
@RunWith(Arquillian.class)
public class CarritoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    private static final Logger LOGGER = Logger.getLogger(CarritoLogicTest.class.getName());
    
    @Inject
    private CarritoLogic logic;
    @Inject
    private ClienteLogic logicCliente;
    @Inject
    private AccesorioLogic logicAccesorio;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<CarritoEntity> data = new ArrayList<CarritoEntity>();
    private List<ItemEntity> dataItems = new ArrayList<ItemEntity>();
    private List<ClienteEntity> dataClientes = new ArrayList<ClienteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoLogic.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        
        em.createQuery("delete from CarritoEntity").executeUpdate();
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from AccesorioEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 5; i++) {
            
            CarritoEntity carrito = factory.manufacturePojo(CarritoEntity.class);
            AccesorioEntity item = factory.manufacturePojo(AccesorioEntity.class);
            ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);

       
            try {
                logicCliente.createCliente(cliente);
                logicAccesorio.createAccesorio(item, Long.MIN_VALUE);
                logic.createCarrito(carrito);
            } catch (BusinessLogicException ex) {
            }
            
            
           
            data.add(carrito);
            dataItems.add(item);
            dataClientes.add(cliente);
            
            carrito.setCliente(cliente);
        }
    }
    
    @Test
    public void createCarrito() throws BusinessLogicException {
        
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        CarritoEntity result = logic.createCarrito(newEntity);
        Assert.assertNotNull(result);
        CarritoEntity entity = logic.findCarrito(result.getId());
    }
    
    @Test
    public void findCarritoTest() {
        
        CarritoEntity entity = data.get(3);
        CarritoEntity resultEntity = logic.findCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void updateCarritoTest() throws BusinessLogicException {
        
        CarritoEntity entity = data.get(2);
        CarritoEntity pojoEntity = factory.manufacturePojo(CarritoEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateCarrito(pojoEntity);
            
        CarritoEntity resp = em.find(CarritoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteCarritoTest() {
        CarritoEntity entity = data.get(0);
        logic.deleteCarrito(entity.getId());
        CarritoEntity deleted = em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void addItemTest() throws BusinessLogicException {
        
        CarritoEntity carrito = data.get(0);

        ItemEntity item = dataItems.get(0);
 
        logic.addItem(carrito.getCliente().getId(), item.getId());

        LOGGER.info(logic.findCarrito(carrito.getId()).getItems().size()+"!!!!!1");
   
    }
    
    @Test
    public void removeItemTest() throws BusinessLogicException{
        
        CarritoEntity carrito = data.get(1);
     
        ItemEntity item = dataItems.get(1);
       
        logic.addItem(carrito.getCliente().getId(),item.getId());
         LOGGER.info(logic.findCarrito(carrito.getId()).getItems().size()+"!!!!!2");
         logic.removeItem(carrito.getCliente().getId(), item.getId());
         LOGGER.info(logic.findCarrito(carrito.getId()).getItems().size()+"!!!!!3");
        
    }
    
    @Test
    public void crearFacturaTest() throws BusinessLogicException {
        
        CarritoEntity carrito = data.get(4);
        
        ItemEntity item1 = dataItems.get(1);
        ItemEntity item2 = dataItems.get(2);
        
        logic.addItem(carrito.getCliente().getId(), item1.getId());
        logic.addItem(carrito.getCliente().getId(), item2.getId());
        LOGGER.info(logic.findCarrito(carrito.getId()).getItems().size()+"!!!!!5");
        FacturaEntity facturaGenerada = logic.crearFactura(carrito.getId());
        LOGGER.info(logic.findCarrito(carrito.getId()).getItems().size()+"!!!!!6");
        double precioTotal = item1.getPrecio()+item2.getPrecio();
        
//        Assert.assertTrue(facturaGenerada.getDinero()==precioTotal);

    }
}
