/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.BicicletaUsadaLogic;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaUsadaPersistence;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
public class BicicletaUsadaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BicicletaUsadaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<BicicletaUsadaEntity> data = new ArrayList<>();
    
    private List<ClienteEntity> dataVendedor = new ArrayList<>();
    
    private List<ModeloEntity> dataModelo = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaUsadaEntity.class.getPackage())
                .addPackage(BicicletaUsadaLogic.class.getPackage())
                .addPackage(BicicletaUsadaPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
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
        em.createQuery("delete from BicicletaUsadaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
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
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            dataVendedor.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            BicicletaUsadaEntity entity = factory.manufacturePojo(BicicletaUsadaEntity.class);
            entity.setModeloId(dataModelo.get(0).getId());
            entity.setEstado("En proceso");
            entity.setCliente(dataVendedor.get(1));
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void verificarBicicletaTest() {
        
        BicicletaUsadaEntity newEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setUsada(Boolean.FALSE);
        
        try {
            logic.verificarBiciUsada(newEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prueba para crear un BicicletaUsada
     *
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void createBicicletaUsadaTest() throws BusinessLogicException {
        BicicletaUsadaEntity newEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        newEntity.setModeloId(dataModelo.get(0).getId());
        newEntity.setEstado("En proceso");
        BicicletaUsadaEntity result = logic.createBicicleta(data.get(0).getCliente().getId(), newEntity);
        Assert.assertNotNull(result);
        BicicletaUsadaEntity entity = em.find(BicicletaUsadaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertEquals(newEntity.getFacturaOriginal(), entity.getFacturaOriginal());
    }

    /**
     * Prueba para consultar la lista de BicicletaUsadas de un vendedor
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void getBicicletaUsadasTest() throws BusinessLogicException {
        List<BicicletaUsadaEntity> list = logic.getBicicletasDelVendedor(dataVendedor.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (BicicletaUsadaEntity entity : list) {
            boolean found = false;
            for (BicicletaUsadaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un BicicletaUsada especifica de un vendedor
     * especifico
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void getBicicletaUsadaTest() throws BusinessLogicException {
        BicicletaUsadaEntity entity = data.get(0);
        BicicletaUsadaEntity resultEntity = logic.getBicicleta(dataVendedor.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
        Assert.assertEquals(entity.getFacturaOriginal(), resultEntity.getFacturaOriginal());
        
        try {
            logic.getBicicleta(Long.MIN_VALUE, Long.MIN_VALUE);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Prueba para eliminar un BicicletaUsada
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteBicicletaUsadaTest() throws BusinessLogicException {
        BicicletaUsadaEntity entity = data.get(0);
        logic.deleteBicicleta(dataVendedor.get(1).getId(), entity.getId());
        BicicletaUsadaEntity deleted = em.find(BicicletaUsadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un BicicletaUsada
     *
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @Test
    public void updateBicicletaUsadaTest() throws BusinessLogicException {
        BicicletaUsadaEntity entity = data.get(1);
        BicicletaUsadaEntity pojoEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setEstado("En proceso");
        pojoEntity.setModeloId(dataModelo.get(0).getId());
        
        logic.updateBicicleta(dataVendedor.get(1).getId(), pojoEntity);
        
        BicicletaUsadaEntity resp = em.find(BicicletaUsadaEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado());
        Assert.assertEquals(pojoEntity.getFacturaOriginal(), resp.getFacturaOriginal());
        
        pojoEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setEstado("En proceso");
        pojoEntity.setPrecio(-1.0);
        
        try {
            logic.updateBicicleta(dataVendedor.get(1).getId(), pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
        
        pojoEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setEstado("En proceso");
        pojoEntity.setModeloId(Long.MIN_VALUE);
        
        try {
            logic.updateBicicleta(dataVendedor.get(1).getId(), pojoEntity);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
            Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}
