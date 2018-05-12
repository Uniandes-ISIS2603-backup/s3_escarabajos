/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.persistence.ListaDeseosPersistence;
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
public class ListaDeseosPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase ListaDeseosPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ListaDeseosPersistence persistence;

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
    private List<ListaDeseosEntity> data = new ArrayList<ListaDeseosEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ListaDeseosEntity.class.getPackage())
                .addPackage(ListaDeseosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    public ListaDeseosPersistenceTest() {
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
        em.createQuery("delete from ListaDeseosEntity").executeUpdate();
    }


    private void insertData() {
        
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ListaDeseosEntity entity = factory.manufacturePojo(ListaDeseosEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ListaDeseosPersistence.
     */
    @Test
    public void testFind() {
        
        ListaDeseosEntity entity = data.get(0);
        ListaDeseosEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Test of create method, of class ListaDeseosPersistence.
     */
    @Test
    public void testCreate()  {
        PodamFactory factory = new PodamFactoryImpl();
        ListaDeseosEntity newEntity = factory.manufacturePojo(ListaDeseosEntity.class);
        ListaDeseosEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ListaDeseosEntity entity = em.find(ListaDeseosEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of update method, of class ListaDeseosPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        ListaDeseosEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ListaDeseosEntity newEntity = factory.manufacturePojo(ListaDeseosEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        ListaDeseosEntity resp = em.find(ListaDeseosEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteListaDeseosTest() {
        ListaDeseosEntity entity = data.get(0);
        persistence.deleteListaDeseos(entity.getId());
        ListaDeseosEntity deleted = em.find(ListaDeseosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
