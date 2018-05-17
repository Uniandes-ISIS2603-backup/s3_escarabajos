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
public class ReclamoLogicTest {

    /**
     * PodamFactory.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyecta la logica de reclamo.
     */
    @Inject
    private ReclamoLogic reclamoLogic;

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
     * Datos de reclamos.
     */
    private List<ReclamoEntity> data = new ArrayList<ReclamoEntity>();

    /**
     * Datos de factura.
     */
    private List<FacturaEntity> facturaData = new ArrayList<FacturaEntity>();

    /**
     * Datos de cliente.
     */
    private List<ClienteEntity> clienteData = new ArrayList<ClienteEntity>();

    /**
     * Creacion del deployment
     *
     * @return deployment
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReclamoEntity.class.getPackage())
                .addPackage(ReclamoLogic.class.getPackage())
                .addPackage(ReclamoPersistence.class.getPackage())
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
        em.createQuery("delete from ReclamoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);

            em.persist(entity);
            facturaData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            clienteData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ReclamoEntity entity = factory.manufacturePojo(ReclamoEntity.class);
            entity.renaudar();
            entity.setFactura(facturaData.get(i));
            entity.setCliente(clienteData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Reclamo.
     */
    @Test
    public void createReclamoTest() throws BusinessLogicException {
        ReclamoEntity newEntity = factory.manufacturePojo(ReclamoEntity.class);
        //newEntity.setAlbum(pics);
        ReclamoEntity result = reclamoLogic.createReclamo(newEntity, facturaData.get(0).getId(), clienteData.get(2).getId());
        Assert.assertNotNull(result);
        ReclamoEntity entity = em.find(ReclamoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
        Assert.assertEquals(newEntity.getRazon(), entity.getRazon());
        Assert.assertEquals(newEntity.getFactura(), entity.getFactura());
        Assert.assertTrue(entity.isEnProceso());
    }

    /**
     * Prueba para consultar la lista de Reclamos.
     */
    @Test
    public void getReclamosTest() {
        List<ReclamoEntity> list = reclamoLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReclamoEntity entity : list) {
            boolean found = false;
            for (ReclamoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Reclamo.
     */
    @Test
    public void getReclamoTest() {
        ReclamoEntity entity = data.get(0);
        ReclamoEntity resultEntity = reclamoLogic.find(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getMensaje(), entity.getMensaje());
        Assert.assertEquals(resultEntity.getRazon(), entity.getRazon());
        Assert.assertEquals(resultEntity.getFactura(), entity.getFactura());
        Assert.assertTrue(resultEntity.isEnProceso());

    }

    /**
     * Prueba para actualizar un Reclamo.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateReclamoTest() throws BusinessLogicException {
        ReclamoEntity entity = data.get(0);
        ReclamoEntity pojoEntity = factory.manufacturePojo(ReclamoEntity.class);
        reclamoLogic.updateMensajeReclamo(pojoEntity, entity.getId());
        pojoEntity.setId(entity.getId());
        ReclamoEntity resp = em.find(ReclamoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertTrue(resp.getMensaje().contains(pojoEntity.getMensaje()));
        Assert.assertEquals(resp.getRazon(), entity.getRazon());
        Assert.assertTrue(resp.isEnProceso());
    }

    @Test
    /**
     * Prueba para terminar un reclamo.
     */
    public void terminarReclamoTest() {
        Assert.assertTrue(reclamoLogic.find(data.get(0).getId()).isEnProceso());
        reclamoLogic.terminarReclamo(data.get(0).getId());
        Assert.assertFalse(reclamoLogic.find(data.get(0).getId()).isEnProceso());

    }

    /**
     * Prueba el metodo getReclamoPorFactura.
     */
    @Test
    public void getReclamoByFacturaTest() {
        Assert.assertEquals(data.get(0), reclamoLogic.getReclamoPorfactura(facturaData.get(0).getId()));
        Assert.assertEquals(data.get(1), reclamoLogic.getReclamoPorfactura(facturaData.get(1).getId()));
        Assert.assertEquals(data.get(2), reclamoLogic.getReclamoPorfactura(facturaData.get(2).getId()));
    }
}
