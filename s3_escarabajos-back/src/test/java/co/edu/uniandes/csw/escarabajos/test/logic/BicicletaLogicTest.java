/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.BicicletaLogic;
import co.edu.uniandes.csw.escarabajos.ejb.BicicletaLogic;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
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
 * @author c.santacruza
 */
@RunWith(Arquillian.class)
public class BicicletaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private BicicletaLogic logic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<BicicletaEntity> data = new ArrayList<BicicletaEntity>();

    private List<ModeloEntity> dataModelo = new ArrayList<ModeloEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(BicicletaLogic.class.getPackage())
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
            em.persist(entity);
            dataModelo.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);
            entity.setModeloId(dataModelo.get(0).getId());
            entity.setUsada(Boolean.FALSE);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Bicicleta
     *
     *
     */
    @Test
    public void createBicicletaTest() throws BusinessLogicException {
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setUsada(Boolean.FALSE);
        
        BicicletaEntity result = logic.createBicicleta(newEntity);
        Assert.assertNotNull(result);
        
        BicicletaEntity entity = em.find(BicicletaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
        Assert.assertFalse(entity.getUsada());
    }

    /**
     * Prueba para consultar la lista de Bicicletas de un vendedor
     */
    @Test
    public void getBicicletasTest() throws BusinessLogicException {
        List<BicicletaEntity> list = logic.getBicicletas();
        Assert.assertEquals(data.size(), list.size());
        for (BicicletaEntity entity : list) {
            boolean found = false;
            for (BicicletaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Bicicleta especifica de un vendedor
     * especifico
     */
    @Test
    public void getBicicletaTest() throws BusinessLogicException {
        BicicletaEntity entity = data.get(0);
        BicicletaEntity resultEntity = logic.getBicicleta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCategoria(), resultEntity.getCategoria());
        Assert.assertFalse(resultEntity.getUsada());
    }

    /**
     * Prueba para eliminar un Bicicleta
     */
    @Test
    public void deleteBicicletaTest() throws BusinessLogicException {
        BicicletaEntity entity = data.get(0);
        logic.deleteBicicleta(entity.getId());
        BicicletaEntity deleted = em.find(BicicletaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Bicicleta
     */
    @Test
    public void updateBicicletaTest() throws BusinessLogicException {
        BicicletaEntity entity = data.get(1);
        BicicletaEntity pojoEntity = factory.manufacturePojo(BicicletaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setUsada(Boolean.FALSE);
        pojoEntity.setModeloId(dataModelo.get(0).getId());

        logic.updateBicicleta(entity.getId(), pojoEntity);

        BicicletaEntity resp = em.find(BicicletaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCategoria(), resp.getCategoria());
        Assert.assertFalse(resp.getUsada());
    }
}