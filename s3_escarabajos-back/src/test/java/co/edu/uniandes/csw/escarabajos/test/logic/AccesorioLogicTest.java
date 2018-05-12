/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.AccesorioLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.AccesorioPersistence;
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
 * @author Mateo
 */
@RunWith(Arquillian.class)
public class AccesorioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AccesorioLogic logic;

    @Inject
    private ModeloLogic logicModelo;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AccesorioEntity> data = new ArrayList<AccesorioEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AccesorioEntity.class.getPackage())
                .addPackage(AccesorioLogic.class.getPackage())
                .addPackage(AccesorioPersistence.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            AccesorioEntity entity = factory.manufacturePojo(AccesorioEntity.class);

            em.persist(entity);
            data.add(entity);
        }

    }

    /**
     * prueba el metodo createAccesorio
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createAccesorioTest() throws BusinessLogicException {

        ModeloEntity modelo = factory.manufacturePojo(ModeloEntity.class);
        modelo.setTipoModelo(ModeloLogic.ACCESORIO);
        logicModelo.createModelo(modelo);
        AccesorioEntity newEntity = factory.manufacturePojo(AccesorioEntity.class);
        newEntity.setModeloId(modelo.getId());
        AccesorioEntity result = logic.createAccesorio(newEntity);
        Assert.assertNotNull(result);
        AccesorioEntity entity = em.find(AccesorioEntity.class, result.getId());

        Assert.assertEquals(entity, result);
    }

    /**
     * prueba el metodo updateAccesorio
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateAccesorioTest() throws BusinessLogicException {

        AccesorioEntity entity = data.get(0);
        AccesorioEntity pojoEntity = factory.manufacturePojo(AccesorioEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setModeloId(entity.getModeloId());

        logic.updateAccesorio(pojoEntity);

        AccesorioEntity resp = em.find(AccesorioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        pojoEntity = factory.manufacturePojo(AccesorioEntity.class);
        pojoEntity.setId(Long.MIN_VALUE);
        try {
            logic.updateAccesorio(pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }
        pojoEntity = factory.manufacturePojo(AccesorioEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setPrecio(-1.0);
        try {
            logic.updateAccesorio(pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }
        pojoEntity.setId(entity.getId());
        pojoEntity.setModeloId(Long.MIN_VALUE);
        try {
            logic.updateAccesorio(pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * prueba el metodo deleteAccesorio
     */
    @Test
    public void deleteAccesorioTest() {
        AccesorioEntity entity = data.get(0);
        logic.deleteAccesorio(entity.getId());
        AccesorioEntity deleted = em.find(AccesorioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * prueba el metodo getAccesorios
     */
    @Test
    public void getAccesoriosTest() {
        List<AccesorioEntity> list = logic.getAccesorios();
        Assert.assertEquals(data.size(), list.size());
        for (AccesorioEntity entity : list) {
            boolean found = false;
            for (AccesorioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * prueba el metodo getAccesorio
     */
    @Test
    public void getAccesorioTest() {
        AccesorioEntity entity = data.get(0);
        AccesorioEntity resultEntity = logic.getAccesorio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        resultEntity = logic.getAccesorio(Long.MIN_VALUE);
        Assert.assertNull(resultEntity);
    }
}
