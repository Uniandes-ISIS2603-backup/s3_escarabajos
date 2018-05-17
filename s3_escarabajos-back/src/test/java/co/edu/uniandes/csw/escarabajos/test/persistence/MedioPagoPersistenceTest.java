/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.persistence.MedioPagoPersistence;
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
 * @author jp.carreno
 */
@RunWith(Arquillian.class)
public class MedioPagoPersistenceTest {

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de MedioPago, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase MedioPagoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MedioPagoPersistence medioPagoPersistence;

    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
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
     *
     *
     */
    @Before
    public void setUp() {
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
    }

    /**
     * Datos de medio de pago.
     */
    private List<MedioPagoEntity> data = new ArrayList<MedioPagoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Medio de Pago.
     *
     *
     */
    @Test
    public void createMedioPagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        MedioPagoEntity result = medioPagoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedioPagoEntity entity = em.find(MedioPagoEntity.class, result.getId());

        //Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        //   Assert.assertTrue(newEntity.getDinero() == entity.getDinero());
    }

    /**
     * Prueba para consultar la lista de Medios de Pago.
     *
     *
     */
    @Test
    public void getMedioPagosTest() {
        List<MedioPagoEntity> list = medioPagoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedioPagoEntity ent : list) {
            boolean found = false;
            for (MedioPagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Medio de Pago.
     *
     *
     */
    @Test
    public void getMedioPagoTest() {
        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity newEntity = medioPagoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);

        //   Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        //  Assert.assertTrue(newEntity.getDinero() == entity.getDinero());
    }

    /**
     * Prueba para eliminar un Medio de Pago.
     *
     *
     */
    @Test
    public void deleteMedioPagoTest() {
        MedioPagoEntity entity = data.get(0);
        medioPagoPersistence.delete(entity.getId());
        MedioPagoEntity deleted = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Medio de Pago.
     *
     *
     */
    @Test
    public void updateMedioPagoTest() {
        MedioPagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);

        newEntity.setId(entity.getId());

        medioPagoPersistence.update(newEntity);

        MedioPagoEntity resp = em.find(MedioPagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
    }
}
