
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
public class ReclamoLogicTest 
{

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ReclamoLogic reclamoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ReclamoEntity> data = new ArrayList<ReclamoEntity>();

    private List<FacturaEntity> facturaData = new ArrayList<FacturaEntity>();
    
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
            ReclamoEntity entity = factory.manufacturePojo(ReclamoEntity.class);
            entity.renaudar();
            entity.setFactura(facturaData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Reclamo
     *
     *
     */
    @Test
    public void createReclamoTest() throws BusinessLogicException {
        ReclamoEntity newEntity = factory.manufacturePojo(ReclamoEntity.class);
        List<String> pics = new ArrayList<>();
        pics.add(factory.manufacturePojo(String.class));
        //newEntity.setAlbum(pics);
        ReclamoEntity result = reclamoLogic.createReclamo(newEntity, facturaData.get(0).getId());
        Assert.assertNotNull(result);
        ReclamoEntity entity = em.find(ReclamoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
        Assert.assertEquals(newEntity.getRazon(), entity.getRazon());
        Assert.assertEquals(newEntity.getFactura(), entity.getFactura());
        Assert.assertTrue(entity.isEnProceso());
    }

    /**
     * Prueba para consultar la lista de Reclamos
     *
     *
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
     * Prueba para consultar un Reclamo
     *
     *
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
     * Prueba para eliminar un Reclamo
     *
     *
     */
    @Test
    public void deleteReclamoTest() {
        ReclamoEntity entity = data.get(0);
        reclamoLogic.delete(entity.getId());
        ReclamoEntity deleted = em.find(ReclamoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Reclamo
     *
     *
     */
    @Test
    public void updateReclamoTest() throws BusinessLogicException {
        ReclamoEntity entity = data.get(0);
        ReclamoEntity pojoEntity = factory.manufacturePojo(ReclamoEntity.class);
        pojoEntity.setId(entity.getId());
        reclamoLogic.updateMensajeReclamo(pojoEntity, pojoEntity.getId());

        ReclamoEntity resp = em.find(ReclamoEntity.class, pojoEntity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getRazon(), resp.getRazon());
        Assert.assertTrue(resp.isEnProceso());
    }
    /**
     * Prueba para obtener un reclamo por su factura
     */
    @Test
    public void getReclamoByFactura()
    {
        List<ReclamoEntity> factura0 = reclamoLogic.findByFactura(facturaData.get(0).getId());
        Assert.assertEquals(factura0.size(), 1);
        List<ReclamoEntity> factura1 = reclamoLogic.findByFactura(facturaData.get(1).getId());
        Assert.assertEquals(factura1.size(), 1);
        List<ReclamoEntity> factura2 = reclamoLogic.findByFactura(facturaData.get(2).getId());
        Assert.assertEquals(factura2.size(), 1);
    }
    @Test
     /**
     * Prueba para terminar un reclamo
     */
    public void terminarReclamoTest()
    {
        Assert.assertTrue(reclamoLogic.find(data.get(0).getId()).isEnProceso());
        reclamoLogic.terminarReclamo(data.get(0).getId());
        Assert.assertFalse(reclamoLogic.find(data.get(0).getId()).isEnProceso());
                
    }
    @Test
    public void getReclamoByFacturaTest()
    {
        Assert.assertEquals(data.get(0), reclamoLogic.getReclamoPorfactura(facturaData.get(0).getId()).get(0));
        Assert.assertEquals(data.get(1), reclamoLogic.getReclamoPorfactura(facturaData.get(1).getId()).get(0));
        Assert.assertEquals(data.get(2), reclamoLogic.getReclamoPorfactura(facturaData.get(2).getId()).get(0));
    }
    @Test
    public void deleteByFacturaTest() throws BusinessLogicException
    {
        reclamoLogic.deleteReclamoByFacturaId(facturaData.get(0).getId(), data.get(0).getId());
        Assert.assertNull(reclamoLogic.find(data.get(0).getId()));
    }
}
