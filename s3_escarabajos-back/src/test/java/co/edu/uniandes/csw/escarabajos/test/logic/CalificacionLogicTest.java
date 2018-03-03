
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.*;
import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class CalificacionLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalificacionLogic calificacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    private List<ModeloEntity> modelosData = new ArrayList<ModeloEntity>();

    private List<ClienteEntity> clientesdata = new ArrayList<ClienteEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            em.persist(entity);
            modelosData.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            clientesdata.add(entity);
        }
        for (int i = 0; i < 3; i++) 
        {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setModelo(modelosData.get(i));
            entity.setCliente(clientesdata.get(i));
            entity.setPuntaje(i + 1);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Calificacion
     *
     *
     */
    @Test
    public void createCalificacionTest() throws BusinessLogicException 
    {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setPuntaje(3);
        CalificacionEntity result = calificacionLogic.crearCalificacion(newEntity, modelosData.get(0).getId(), clientesdata.get(0).getId());
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertTrue(newEntity.getPuntaje() == entity.getPuntaje());
        Assert.assertEquals(newEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
    }

    /**
     * Prueba para consultar la lista de Calificacions
     *
     *
     */
    @Test
    public void getCalificacionsTest() {
        List<CalificacionEntity> list = calificacionLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Calificacion
     *
     *
     */
    @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resultEntity = calificacionLogic.find(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertTrue(resultEntity.getPuntaje() == entity.getPuntaje());
        Assert.assertEquals(resultEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(resultEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(resultEntity.getCliente(), entity.getCliente());
        
    }

    /**
     * Prueba para eliminar un Calificacion
     *
     *
     */
    @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        calificacionLogic.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Calificacion
     *
     *
     */
    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setPuntaje(1);
        calificacionLogic.updateCalificacion(pojoEntity, modelosData.get(1).getId(), clientesdata.get(0).getId());

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
        Assert.assertTrue(pojoEntity.getPuntaje() == resp.getPuntaje());
        Assert.assertEquals(pojoEntity.getModelo(), resp.getModelo());
        Assert.assertEquals(pojoEntity.getCliente(), resp.getCliente());
    }
    /**
     * Prueba para verificar las calificaciones de un modelo.
     */
    @Test
    public void darCalificacionesPorModeloTest() throws BusinessLogicException
    {
        List<CalificacionEntity> modelo0 = calificacionLogic.getCalificacionesPorModelo(modelosData.get(0).getId());
        Assert.assertEquals(modelo0.size(), 1);
        List<CalificacionEntity> modelo1 = calificacionLogic.getCalificacionesPorModelo(modelosData.get(1).getId());
        Assert.assertEquals(modelo1.size(), 1);
        List<CalificacionEntity> modelo2 = calificacionLogic.getCalificacionesPorModelo(modelosData.get(2).getId());
        Assert.assertEquals(modelo2.size(), 1);
        
        CalificacionEntity e = factory.manufacturePojo(CalificacionEntity.class);
        calificacionLogic.crearCalificacion(e, modelosData.get(0).getId(), clientesdata.get(0).getId());
        modelo0 = calificacionLogic.getCalificacionesPorModelo(modelosData.get(0).getId());
        Assert.assertEquals(2, modelo0.size());
    }
    @Test
    public void darCalificacionesPorClienteTest() throws BusinessLogicException
    {
        List<CalificacionEntity> cliente0 = calificacionLogic.getCalificacionesPorCliente(clientesdata.get(0).getId());
        Assert.assertEquals(cliente0.size(), 1);
        List<CalificacionEntity> cliente1 = calificacionLogic.getCalificacionesPorCliente(clientesdata.get(1).getId());
        Assert.assertEquals(cliente1.size(), 1);
        List<CalificacionEntity> cliente2 = calificacionLogic.getCalificacionesPorCliente(clientesdata.get(2).getId());
        Assert.assertEquals(cliente2.size(), 1);
        
        CalificacionEntity e = factory.manufacturePojo(CalificacionEntity.class);
        calificacionLogic.crearCalificacion(e, modelosData.get(0).getId(), clientesdata.get(0).getId());
        cliente0 = calificacionLogic.getCalificacionesPorCliente(clientesdata.get(0).getId());
        Assert.assertEquals(2, cliente0.size());
    }
    @Test
    public void getCalificacionesPorClienteAndModeloTest() throws BusinessLogicException
    {
        List<CalificacionEntity> mc0 = calificacionLogic.getCalificacionesPorClienteAndModelo(clientesdata.get(0).getId(), modelosData.get(0).getId());
        Assert.assertEquals(mc0.size(), 1);
        List<CalificacionEntity> mc1 = calificacionLogic.getCalificacionesPorClienteAndModelo(clientesdata.get(1).getId(), modelosData.get(1).getId());
        Assert.assertEquals(mc1.size(), 1);
        List<CalificacionEntity> mc2 = calificacionLogic.getCalificacionesPorClienteAndModelo(clientesdata.get(2).getId(), modelosData.get(2).getId());
        Assert.assertEquals(mc2.size(), 1);
        
        CalificacionEntity e = factory.manufacturePojo(CalificacionEntity.class);
        calificacionLogic.crearCalificacion(e, modelosData.get(0).getId(), clientesdata.get(0).getId());
        mc0 = calificacionLogic.getCalificacionesPorClienteAndModelo(clientesdata.get(0).getId(), modelosData.get(0).getId());
        Assert.assertEquals(2, mc0.size());
    }
    /**
     * Prueba para verificar la calificacion media de un modelo.
     */
    @Test
    public void calificacionMediaModelo() throws BusinessLogicException
    {
        Assert.assertEquals(1, calificacionLogic.getCalificacionMedia(modelosData.get(0).getId()), 0.01);
        Assert.assertEquals(2, calificacionLogic.getCalificacionMedia(modelosData.get(1).getId()), 0.01);
        Assert.assertEquals(3, calificacionLogic.getCalificacionMedia(modelosData.get(2).getId()), 0.01);

        CalificacionEntity e = factory.manufacturePojo(CalificacionEntity.class);
        e.setPuntaje(3);
        calificacionLogic.crearCalificacion(e, modelosData.get(0).getId(), clientesdata.get(0).getId());
        Assert.assertEquals(-1, calificacionLogic.getCalificacionMedia(Long.MIN_VALUE), 0.01);
        Assert.assertEquals(2, calificacionLogic.getCalificacionMedia(modelosData.get(0).getId()), 0.01);
    }
}
