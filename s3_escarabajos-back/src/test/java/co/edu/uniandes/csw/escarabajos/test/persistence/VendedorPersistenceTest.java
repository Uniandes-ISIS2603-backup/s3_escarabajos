/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import co.edu.uniandes.csw.escarabajos.persistence.VendedorPersistence;
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
 * @author s.beltran
 */
@RunWith(Arquillian.class)
public class VendedorPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private VendedorPersistence vendedorPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
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
    
    private void clearData() {
        em.createQuery("delete from VendedorEntity").executeUpdate();
    }
    
    private List<VendedorEntity> data = new ArrayList<VendedorEntity>();
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createVendedorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        VendedorEntity result = vendedorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    @Test
    public void getVendedoresTest() {
        List<VendedorEntity> list = vendedorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (VendedorEntity ent : list) {
            boolean found = false;
            for (VendedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getVendedorTest() {
        VendedorEntity entity = data.get(0);
        VendedorEntity newEntity = vendedorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void deleteVendedorTest() {
        VendedorEntity entity = data.get(0);
        vendedorPersistence.delete(entity.getId());
        VendedorEntity deleted = em.find(VendedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateVendedorTest() {
        VendedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);

        newEntity.setId(entity.getId());

        vendedorPersistence.update(newEntity);

        VendedorEntity resp = em.find(VendedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }  
}
