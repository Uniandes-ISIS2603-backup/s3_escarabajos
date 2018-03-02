/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
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
    
    @Inject
    private CarritoLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<CarritoEntity> data = new ArrayList<CarritoEntity>();
    private List<ItemEntity> dataItems = new ArrayList<ItemEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoLogic.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(FacturaEntity.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            
            CarritoEntity entity = factory.manufacturePojo(CarritoEntity.class);
            ItemEntity item = factory.manufacturePojo(ItemEntity.class);
            
            em.persist(entity);
            em.persist(item);
            
            data.add(entity);
            dataItems.add(item);
        }
    }
    
    @Test
    public void createCarrito() throws BusinessLogicException {
        
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        CarritoEntity result = logic.createCarrito(newEntity);
        Assert.assertNotNull(result);
        CarritoEntity entity = em.find(CarritoEntity.class, result.getId());
    }
    
    @Test
    public void findCarritoTest() {
        
        CarritoEntity entity = data.get(0);
        CarritoEntity resultEntity = logic.findCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void updateCarritoTest() throws BusinessLogicException {
        
        CarritoEntity entity = data.get(0);
        CarritoEntity pojoEntity = factory.manufacturePojo(CarritoEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateCarrito(pojoEntity);
            
        CarritoEntity resp = em.find(CarritoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteBookTest() {
        CarritoEntity entity = data.get(0);
        logic.deleteCarrito(entity.getId());
        CarritoEntity deleted = em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void addItemTest() throws BusinessLogicException {
        
        CarritoEntity carrito = data.get(0);
        CarritoEntity pojoCarritoEntity = factory.manufacturePojo(CarritoEntity.class);
        pojoCarritoEntity.setId(carrito.getId());
        
        ItemEntity item = dataItems.get(0);
        ItemEntity pojoItemEntity = factory.manufacturePojo(ItemEntity.class);
        pojoItemEntity.setId(item.getId());
        
        logic.addItem(pojoCarritoEntity, pojoItemEntity);
        
        CarritoEntity rpta = em.find(CarritoEntity.class, carrito.getId());
        
        boolean encontrado = false;
        
        for( int i = 0; i < rpta.getItems().size(); i++ ){
            
            if( rpta.getItems().get(i).getId() == item.getId() ){
                
                encontrado = true;
            }
        }
        
        assert(encontrado);
    }
    
    @Test
    public void removeItemTest() throws BusinessLogicException{
        
        CarritoEntity carrito = data.get(0);
        CarritoEntity pojoCarritoEntity = factory.manufacturePojo(CarritoEntity.class);
        pojoCarritoEntity.setId(carrito.getId());
        
        ItemEntity item = dataItems.get(0);
        ItemEntity pojoItemEntity = factory.manufacturePojo(ItemEntity.class);
        pojoItemEntity.setId(item.getId());
        
        logic.addItem(pojoCarritoEntity, pojoItemEntity);
        
        logic.removeItem(pojoCarritoEntity, pojoItemEntity);
        
        CarritoEntity rpta = em.find(CarritoEntity.class, carrito.getId());
        
        boolean noEncontrado = true;
        
        for( int i = 0; i < rpta.getItems().size(); i++ ){
            
            if( rpta.getItems().get(i).getId() == item.getId() ){
                
                noEncontrado = false;
            }
        }
        assert(noEncontrado);
    }
    
    @Test
    public void crearFacturaTest() throws BusinessLogicException {
        
        CarritoEntity carrito = data.get(0);
        CarritoEntity pojoCarritoEntity = factory.manufacturePojo(CarritoEntity.class);
        pojoCarritoEntity.setId(carrito.getId());
        pojoCarritoEntity.setPrecioTotal(0.0);
        
        ItemEntity item1 = dataItems.get(0);
        ItemEntity pojoItem1Entity = factory.manufacturePojo(ItemEntity.class);
        pojoItem1Entity.setId(item1.getId());
        
        ItemEntity item2 = dataItems.get(1);
        ItemEntity pojoItem2Entity = factory.manufacturePojo(ItemEntity.class);
        pojoItem2Entity.setId(item2.getId());
        
        logic.addItem(pojoCarritoEntity, pojoItem1Entity);
        logic.addItem(pojoCarritoEntity, pojoItem2Entity);
        
        FacturaEntity facturaGenerada = logic.crearFactura(pojoCarritoEntity);
        
        double precioTotal = pojoItem1Entity.getPrecio() + pojoItem2Entity.getPrecio();
        
        assert(facturaGenerada.getDinero()==precioTotal);

    }
}
