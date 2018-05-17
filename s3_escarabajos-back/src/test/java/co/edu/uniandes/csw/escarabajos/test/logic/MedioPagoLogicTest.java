/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.MedioPagoPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author jp.carreno
 */
@RunWith(Arquillian.class)
public class MedioPagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MedioPagoLogic logic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MedioPagoEntity> data = new ArrayList<MedioPagoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoLogic.class.getPackage())
                .addPackage(MedioPagoPersistence.class.getPackage())
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
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {

            MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
            em.persist(entity);

            data.add(entity);
        }
    }

    @Test
    public void createMedioPagoTest() throws BusinessLogicException {

        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setTipo("pse");
        MedioPagoEntity result = logic.createMedioPago(newEntity);
        Assert.assertNotNull(result);

        newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setTipo("paypal");
        result = logic.createMedioPago(newEntity);
        Assert.assertNotNull(result);

        newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setTipo("tarjeta de credito");
        result = logic.createMedioPago(newEntity);
        Assert.assertNotNull(result);

        newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setTipo("tarjeta debito");
        result = logic.createMedioPago(newEntity);
        Assert.assertNotNull(result);

        newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setTipo("tarjeta debit1");
        try {
            logic.createMedioPago(newEntity);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void updateMedioPagoTest() throws BusinessLogicException {

        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity pojoEntity = factory.manufacturePojo(MedioPagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTipo("pse");
        logic.updateMedioPago(entity.getId(), pojoEntity);
        MedioPagoEntity resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        pojoEntity = factory.manufacturePojo(MedioPagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTipo("paypal");
        logic.updateMedioPago(entity.getId(), pojoEntity);
        resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        pojoEntity = factory.manufacturePojo(MedioPagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTipo("tarjeta de credito");
        logic.updateMedioPago(entity.getId(), pojoEntity);
        resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        pojoEntity = factory.manufacturePojo(MedioPagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTipo("tarjeta debito");
        logic.updateMedioPago(entity.getId(), pojoEntity);
        resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        pojoEntity = factory.manufacturePojo(MedioPagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setTipo("NOT");
        try {
            logic.getMedioPago(Long.MIN_VALUE);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());

        try {
            logic.getMedioPago(Long.MIN_VALUE);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void deleteMedioPagoTest() {
        MedioPagoEntity entity = data.get(0);
        logic.deleteMedioPago(entity.getId());
        MedioPagoEntity deleted = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getMedioPagosTest() {
        List<MedioPagoEntity> list = logic.getMedioPagos();
        Assert.assertEquals(data.size(), list.size());
        for (MedioPagoEntity entity : list) {
            boolean found = false;
            for (MedioPagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getMedioPagoTest() throws BusinessLogicException {
        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity resultEntity = logic.getMedioPago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());

        try {
            logic.getMedioPago(Long.MIN_VALUE);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
