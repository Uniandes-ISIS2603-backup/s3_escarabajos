/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
import java.util.Objects;
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

    /**
     * PodamFactory.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyecta la logica de modelo.
     */
    @Inject
    private ModeloLogic modeloLogic;

    /**
     * EntityManager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * UserTransaction.
     */
    @Inject
    private UserTransaction utx;

    /**
     * Datos de modelo.
     */
    private List<ModeloEntity> data = new ArrayList<>();

    /**
     * Datos de item.
     */
    private List<ItemEntity> itemsData = new ArrayList();

    /**
     * Creacion del deployment
     *
     * @return deployment
     */
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
        em.createQuery("delete from AccesorioEntity").executeUpdate();
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
            AccesorioEntity items = factory.manufacturePojo(AccesorioEntity.class);
            em.persist(items);
            itemsData.add(items);
        }

        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            entity.setTipoModelo(ModeloLogic.ACCESORIO);
            ArrayList<ItemEntity> items = new ArrayList<>();
            items.add(itemsData.get(i));
            entity.setItems(items);
            em.persist(entity);
            data.add(entity);

        }
        for (ModeloEntity modelo : modeloLogic.getModelos(null, null)) {
            for (ItemEntity item : modelo.getItems()) {
                item.setModeloId(modelo.getId());
                em.merge(item);
            }
        }

    }

    /**
     * Prueba para crear un Modelo.
     */
    @Test
    public void createModeloTest() {
        try {
            ModeloEntity newEntity = factory.manufacturePojo(ModeloEntity.class);
            ModeloEntity result = modeloLogic.createModelo(newEntity);
            Assert.assertNotNull(result);
            ModeloEntity entity = em.find(ModeloEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertTrue(newEntity.getCalificacionMedia() == entity.getCalificacionMedia());
            Assert.assertEquals(newEntity.getMarca(), entity.getMarca());
            Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
            Assert.assertEquals(newEntity.getTipoModelo(), entity.getTipoModelo());
        } catch (BusinessLogicException e) {
            Assert.fail();
        }
    }

    /**
     * Prueba para consultar la lista de Modelos.
     */
    @Test
    public void getModelosTest() {

        List<ModeloEntity> list = modeloLogic.getModelos(1, Integer.MAX_VALUE);
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
     * Prueba para consultar un Modelo.
     */
    @Test
    public void getModeloTest() {
        ModeloEntity entity = data.get(0);
        ModeloEntity newEntity = modeloLogic.getModelo(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertTrue(newEntity.getCalificacionMedia() == entity.getCalificacionMedia());
        Assert.assertEquals(newEntity.getMarca(), entity.getMarca());
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
        Assert.assertEquals(newEntity.getTipoModelo(), entity.getTipoModelo());
    }

    /**
     * Prueba para eliminar un Modelo.
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteModeloTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        modeloLogic.deleteModelo(entity.getId());
        ModeloEntity deleted = modeloLogic.getModelo(entity.getId());
        for (ItemEntity item : deleted.getItems()) {
            Assert.assertFalse(item.getDisponible());
        }

    }

    /**
     * Prueba el metodo de buscar item en modelo.
     */
    @Test
    public void getItemModeloTest() {
        try {
            ItemEntity item = modeloLogic.getItem(data.get(0).getId(), itemsData.get(0).getId());
            Assert.assertNotNull(item);
            Assert.assertTrue(Objects.equals(itemsData.get(0).getId(), item.getId()));
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }

    /**
     * Prueba el metodo que retorna todos los items de un modelo.
     */
    @Test
    public void listItemsModeloTest() {
        List<ItemEntity> items;
        try {
            items = modeloLogic.listItems(data.get(0).getId());
            Assert.assertTrue(Objects.equals(items.get(0).getModeloId(), data.get(0).getId()));
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Prueba que agregue un item al modelo.
     */
    @Test
    public void addItem() {
        try {
            AccesorioEntity items = factory.manufacturePojo(AccesorioEntity.class);
            modeloLogic.addItem(items, data.get(0).getId());
        } catch (BusinessLogicException e) {
            Assert.fail();
        }
    }

    /**
     * Prueba que se borre un item de un modelo.
     */
    @Test
    public void removeItem() {
        try {
            modeloLogic.removeItem(itemsData.get(2).getId());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }

    /**
     * Prueba para actualizar un Modelo.
     */
    @Test
    public void updateModeloTest() {
        try {
            ModeloEntity entity = data.get(0);
            ModeloEntity pojoEntity = factory.manufacturePojo(ModeloEntity.class);
            pojoEntity.setId(entity.getId());
            pojoEntity.setTipoModelo(entity.getTipoModelo());
            modeloLogic.updateModelo(pojoEntity.getId(), pojoEntity);
            ModeloEntity resp = em.find(ModeloEntity.class, entity.getId());
            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertTrue(pojoEntity.getCalificacionMedia() == resp.getCalificacionMedia());
            Assert.assertEquals(pojoEntity.getMarca(), resp.getMarca());
            Assert.assertEquals(pojoEntity.getReferencia(), resp.getReferencia());
            Assert.assertEquals(pojoEntity.getTipoModelo(), resp.getTipoModelo());
        } catch (BusinessLogicException e) {
            Assert.fail();
        }
    }
}
