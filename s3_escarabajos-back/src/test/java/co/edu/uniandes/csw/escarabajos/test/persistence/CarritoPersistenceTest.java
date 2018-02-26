/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Mateo
 */
@RunWith(Arquillian.class)
public class CarritoPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase CarritoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CarritoPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     * Contenedor para el conjunto de datos de prueba.
     */
    private List<CarritoEntity> data = new ArrayList<CarritoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public CarritoPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try {
            utx.begin();
            em.joinTransaction();
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
    
    private void clearData() {
        em.createQuery("delete from CarritoEntity").executeUpdate();
    }


    private void insertData() {
        
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CarritoEntity entity = factory.manufacturePojo(CarritoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class CarritoPersistence.
     */
    @Test
    public void testFind() throws Exception {
        
        CarritoEntity entity = data.get(0);
        CarritoEntity newEntity = persistence.find(entity.getIdCliente());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIdCliente(), newEntity.getIdCliente());
    }

    /**
     * Test of create method, of class CarritoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        CarritoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        CarritoEntity entity = em.find(CarritoEntity.class, result.getIdCliente());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getIdCliente(), entity.getIdCliente());
    }

    /**
     * Test of update method, of class CarritoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        CarritoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);

        newEntity.setIdCliente(entity.getIdCliente());

        persistence.update(newEntity);

        CarritoEntity resp = em.find(CarritoEntity.class, entity.getIdCliente());

        Assert.assertEquals(newEntity.getIdCliente(), resp.getIdCliente());
    }
    
}
