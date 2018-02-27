/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;


import java.util.ArrayList;
import java.util.List;


import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
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
 * @author Andres
 */
@RunWith(Arquillian.class)
public class ModeloLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ModeloLogic modeloLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ModeloEntity> data = new ArrayList<ModeloEntity>();
    
    private List<ItemEntity> itemsData = new ArrayList();
    
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ModeloEntity.class.getPackage())
                .addPackage(ModeloLogic.class.getPackage())
                .addPackage(ModeloPersistence.class.getPackage())
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
         em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ItemEntity items = factory.manufacturePojo(ItemEntity.class);
            em.persist(items);
            itemsData.add(items);
        }
        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                itemsData.get(i).setModelo(entity);
            }
        }
    }

    /**
     * Prueba para crear un Modelo
     *
     *
     */
    @Test
    public void createModeloTest() throws BusinessLogicException {
        ModeloEntity newEntity = factory.manufacturePojo(ModeloEntity.class);
        ModeloEntity result = modeloLogic.createModelo(newEntity);
        Assert.assertNotNull(result);
        ModeloEntity entity = em.find(ModeloEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
    }

    /**
     * Prueba para consultar la lista de Modelos
     *
     *
     */
    @Test
    public void getModelosTest() {
        List<ModeloEntity> list = modeloLogic.getModelos();
        Assert.assertEquals(data.size(), list.size());
        for (ModeloEntity entity : list) {
            boolean found = false;
            for (ModeloEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Modelo
     *
     *
     */
    @Test
    public void getModeloTest() {
        ModeloEntity entity = data.get(0);
        ModeloEntity resultEntity = modeloLogic.getModelo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Modelo
     *
     *
     */
    @Test
    public void deleteModeloTest() {
        ModeloEntity entity = data.get(0);
        modeloLogic.deleteModelo(entity.getId());
        ModeloEntity deleted = em.find(ModeloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Modelo
     *
     *
     */
    @Test
    public void updateModeloTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        ModeloEntity pojoEntity = factory.manufacturePojo(ModeloEntity.class);

        pojoEntity.setId(entity.getId());

        modeloLogic.updateModelo(pojoEntity.getId(), pojoEntity);

        ModeloEntity resp = em.find(ModeloEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}