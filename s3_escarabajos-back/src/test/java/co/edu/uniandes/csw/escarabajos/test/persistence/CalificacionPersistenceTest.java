/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.persistence.CalificacionPersistence;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.gaitan
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest 
{
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Calificacion, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
   /**
     * Inyección de la dependencia a la clase CalificacionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    /**
     * Contexto de Persistencia que se va autilizar para acceder a la Base de
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
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() 
    {
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from EmployeeEntity").executeUpdate();
    }
    
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCalificacionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getPuntaje(),entity.getPuntaje());
    }
    @Test
    public void findCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());
        
        Assert.assertNotNull(resp);
        Assert.assertEquals(entity.getComentario(), resp.getComentario());
        Assert.assertEquals(entity.getPuntaje(), resp.getPuntaje());
    }
    @Test
    public void findAllTest()
    {
         List<CalificacionEntity> list = calificacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list) 
        {
            boolean found = true;
            for (CalificacionEntity entity : data) {
                if (!ent.getId().equals(entity.getId())) {
                    found = false;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void deleteCalificacionTest()
    {
       CalificacionEntity cal = data.get(0);
       calificacionPersistence.delete(cal);
       CalificacionEntity resp = em.find(CalificacionEntity.class, cal.getId());
       Assert.assertNull(resp);
    }
    @Test
    public void updateCalificacionTest()
    {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        calificacionPersistence.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(newEntity.getPuntaje(), resp.getPuntaje());
    }
    
}
