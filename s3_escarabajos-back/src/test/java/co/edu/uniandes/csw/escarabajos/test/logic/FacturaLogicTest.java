/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.FacturaLogic;
import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.FacturaPersistence;
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
public class FacturaLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private FacturaLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    private List<ClienteEntity> dataCliente = new ArrayList<ClienteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
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
            
            data.add(entity);
        }
    }
    
    @Test
    public void createFacturaTest() throws BusinessLogicException{
        
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = logic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
    }
    
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateFactura(dataCliente.get(1).getId(),pojoEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        logic.deleteFactura(entity);
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void getFacturasTest() throws BusinessLogicException {
        List<FacturaEntity> list = logic.getFacturas(dataCliente.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = logic.getFactura(dataCliente.get(1).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
