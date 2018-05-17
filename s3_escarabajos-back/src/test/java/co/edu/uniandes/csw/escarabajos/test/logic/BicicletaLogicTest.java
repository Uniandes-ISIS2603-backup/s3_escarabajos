/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.BicicletaLogic;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author c.santacruza
 */
@RunWith(Arquillian.class)
public class BicicletaLogicTest {

    /**
     * PodamFactory.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyecta la logica de bicicleta.
     */
    @Inject
    private BicicletaLogic logic;

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
     * Datos de bicicleta.
     */
    private List<BicicletaEntity> data = new ArrayList<BicicletaEntity>();

    /**
     * Datos de modelo.
     */
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
            entity.setTipoModelo("Bicicleta");
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
     * @throws BusinessLogicException Por reglas de negocio
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

    @Test
    public void verificarBicicletaTest() {

        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setUsada(Boolean.TRUE);

        try {
            logic.verificarBicicleta(newEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }

        newEntity = factory.manufacturePojo(BicicletaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setUsada(Boolean.FALSE);
        newEntity.setCategoria(null);

        try {
            logic.verificarBicicleta(newEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }

        newEntity = factory.manufacturePojo(BicicletaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setUsada(Boolean.FALSE);
        newEntity.setCategoria(" ");

        try {
            logic.verificarBicicleta(newEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prueba para consultar la lista de Bicicletas de un vendedor
     *
     * @throws BusinessLogicException Por reglas de negocio
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
        try {
            logic.getBicicleta(Long.MIN_VALUE);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prueba para consultar un Bicicleta especifica de un vendedor especifico
     *
     * @throws BusinessLogicException Por reglas de negocio
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
     *
     * @throws BusinessLogicException Por reglas de negocio
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
     *
     * @throws BusinessLogicException Por reglas de negocio
     */
    @Test
    public void updateBicicletaTest() throws BusinessLogicException {
        BicicletaEntity entity = data.get(1);
        BicicletaEntity pojoEntity = factory.manufacturePojo(BicicletaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setUsada(Boolean.FALSE);
        pojoEntity.setModeloId(entity.getModeloId());

        logic.updateBicicleta(entity.getId(), pojoEntity);

        BicicletaEntity resp = em.find(BicicletaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCategoria(), resp.getCategoria());
        Assert.assertFalse(resp.getUsada());

        try {
            logic.updateBicicleta(Long.MIN_VALUE, null);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }

        entity = data.get(1);
        pojoEntity = factory.manufacturePojo(BicicletaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setUsada(Boolean.FALSE);
        pojoEntity.setModeloId(entity.getModeloId());
        pojoEntity.setPrecio(-1.0);
        try {
            logic.updateBicicleta(entity.getId(), pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }

        entity = data.get(1);
        pojoEntity = factory.manufacturePojo(BicicletaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setUsada(Boolean.FALSE);
        pojoEntity.setModeloId(Long.MIN_VALUE);
        pojoEntity.setPrecio(20.0);

        try {
            logic.updateBicicleta(entity.getId(), pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prueba del metodo findByModelo.
     */
    @Test
    public void findByModelo() {
        Assert.assertNotNull(logic.findByModelo(data.get(1).getModeloId()));
    }
}
