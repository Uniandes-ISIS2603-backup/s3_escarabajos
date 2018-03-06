/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

/**
 *
 * @author n.gaitan
 */

import co.edu.uniandes.csw.escarabajos.persistence.ReclamoPersistence;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
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

@RunWith(Arquillian.class)
public class ReclamoPersistenceTest 
{
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Reclamo, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReclamoEntity.class.getPackage())
                .addPackage(ReclamoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
   /**
     * Inyección de la dependencia a la clase ReclamoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ReclamoPersistence reclamoPersistence;
    
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
        em.createQuery("delete from ReclamoEntity").executeUpdate();
    }
    
    private List<ReclamoEntity> data = new ArrayList<ReclamoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            ReclamoEntity entity = factory.manufacturePojo(ReclamoEntity.class);
            entity.renaudar();
            if( i == 1 ) entity.terminar();
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCalificacionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ReclamoEntity newEntity = factory.manufacturePojo(ReclamoEntity.class);
        ReclamoEntity result = reclamoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ReclamoEntity entity = em.find(ReclamoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
        Assert.assertEquals(newEntity.getRazon(),entity.getRazon());
    }
    @Test
    public void findCalificacionTest()
    {
        ReclamoEntity entity = data.get(0);

        ReclamoEntity resp = em.find(ReclamoEntity.class, entity.getId());
        
        Assert.assertNotNull(resp);
        Assert.assertEquals(entity.getMensaje(), resp.getMensaje());
        Assert.assertEquals(entity.getRazon(), resp.getRazon());
    }
    @Test
    public void findAllTest()
    {
        List<ReclamoEntity> list = reclamoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
    }
    @Test
    public void deleteCalificacionTest()
    {
       ReclamoEntity cal = data.get(0);
       reclamoPersistence.delete(cal.getId());
       ReclamoEntity resp = em.find(ReclamoEntity.class, cal.getId());
       Assert.assertNull(resp);
    }
    @Test
    public void updateCalificacionTest()
    {
        ReclamoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReclamoEntity newEntity = factory.manufacturePojo(ReclamoEntity.class);

        newEntity.setId(entity.getId());

        reclamoPersistence.update(newEntity);

        ReclamoEntity resp = em.find(ReclamoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMensaje(), resp.getMensaje());
        Assert.assertEquals(newEntity.getRazon(), resp.getRazon());
    }
    @Test
    public void getReclamosEnProcesoTest()
    { 
        List<ReclamoEntity> enProceso = reclamoPersistence.getReclamosEnProceso();
        
        Assert.assertEquals(2, enProceso.size());
        Assert.assertTrue(data.get(0).getId().equals(enProceso.get(0).getId()) || data.get(0).getId().equals(enProceso.get(1).getId()));
        Assert.assertTrue(data.get(0).getId().equals(enProceso.get(0).getId()) || data.get(2).getId().equals(enProceso.get(1).getId()));
    }
     @Test
    public void getReclamosTerminadosTest()
    { 
        List<ReclamoEntity> noEnProceso = reclamoPersistence.getReclamosTerminados();
        
        Assert.assertEquals(1, noEnProceso.size());
        Assert.assertEquals(data.get(1), noEnProceso.get(0));
    }
}
