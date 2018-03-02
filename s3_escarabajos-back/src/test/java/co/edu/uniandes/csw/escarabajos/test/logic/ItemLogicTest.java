package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.persistence.AccesorioPersistence;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andres
 */
@RunWith(Arquillian.class)
public class ItemLogicTest {
    

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ItemLogic itemLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ItemEntity> data = new ArrayList<ItemEntity>();
    
    private List<ModeloEntity> dataModelos = new ArrayList<ModeloEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(AccesorioEntity.class.getPackage())
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(ModeloEntity.class.getPackage())
                .addPackage(ModeloPersistence.class.getPackage())
                .addPackage(ItemLogic.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
                .addPackage(BicicletaPersistence.class.getPackage())
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
        em.createQuery("delete from AccesorioEntity").executeUpdate();
        em.createQuery("delete from BicicletaEntity").executeUpdate();
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
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            dataModelos.add(entity);
             em.persist(entity);
        }
        for (int i = 0; i < 3; i++) {
            AccesorioEntity entity = factory.manufacturePojo(AccesorioEntity.class);
            data.add(entity);
            entity.setModeloId(dataModelos.get(i).getId());
             em.persist(entity);
        }
         for (int i = 0; i < 3; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);
            data.add(entity);
            entity.setModeloId(dataModelos.get(i).getId());
             em.persist(entity);
        }

    }
    /**
     * Prueba para consultar la lista de Authors
     *
     *
     */
    @Test
    public void getItemsTest() {
        List<ItemEntity> list = itemLogic.getItems();
        Assert.assertEquals(data.size(), list.size());
        for (ItemEntity entity : list) {
            boolean found = false;
            for (ItemEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Author
     *
     *
     */
    @Test
    public void getItemTest() {
        ItemEntity entity = data.get(0);
        ItemEntity resultEntity = itemLogic.getItem(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
