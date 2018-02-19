/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
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
public class AccesorioPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private AccesorioPersistence persistence;

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
    private List<AccesorioEntity> data = new ArrayList<AccesorioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AccesorioEntity.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public AccesorioPersistenceTest() {
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
        em.createQuery("delete from AccesorioEntity").executeUpdate();
    }


    private void insertData() {
        
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AccesorioEntity entity = factory.manufacturePojo(AccesorioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AccesorioPersistence.
     */
    @Test
    public void testCreate() {

        PodamFactory factory = new PodamFactoryImpl();
        AccesorioEntity newEntity = factory.manufacturePojo(AccesorioEntity.class);
        AccesorioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        AccesorioEntity entity = em.find(AccesorioEntity.class, result.getId());
        //Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of find method, of class AccesorioPersistence.
     */
    @Test
    public void testFind() throws Exception {
        
        AccesorioEntity entity = data.get(0);
        AccesorioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of delete method, of class AccesorioPersistence.
     */
    @Test
    public void testDelete() {
        
        AccesorioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        AccesorioEntity deleted = em.find(AccesorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of findByName method, of class AccesorioPersistence.
     */
    @Test
    public void testFindByName() {
    }

    /**
     * Test of findAll method, of class AccesorioPersistence.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * Test of update method, of class AccesorioPersistence.
     */
    @Test
    public void testUpdate() {
    }
    
}
