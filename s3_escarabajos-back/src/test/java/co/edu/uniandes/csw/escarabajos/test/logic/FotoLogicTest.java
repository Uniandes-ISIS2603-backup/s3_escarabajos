/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.FotoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.FotoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ReclamoLogic;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.FotoPersistence;
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
 * @author Andres
 */
@RunWith(Arquillian.class)
public class FotoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private FotoLogic logic;
    
    @Inject ReclamoLogic reclamoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<FotoEntity> data = new ArrayList<>();
    
     private List<ReclamoEntity> reclamodata = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FotoEntity.class.getPackage())
                .addPackage(FotoLogic.class.getPackage())
                .addPackage(FotoPersistence.class.getPackage())
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
        em.createQuery("delete from FotoEntity").executeUpdate();
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
            
            ReclamoEntity entity = factory.manufacturePojo(ReclamoEntity.class);
            
            em.persist(entity);
            
            reclamodata.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            
            FotoEntity entity = factory.manufacturePojo(FotoEntity.class);
            entity.setReclamo(reclamodata.get(i));
            reclamodata.get(i).getAlbum().add(entity);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createFotoTest() throws BusinessLogicException{
        
        FotoEntity newEntity = factory.manufacturePojo(FotoEntity.class);
        FotoEntity result = logic.createFoto(reclamodata.get(0).getId(),newEntity,FotoLogic.RECLAMO);
        Assert.assertNotNull(result);
    }
    
    @Test
    public void updateFotoTest() throws BusinessLogicException {
        
        FotoEntity entity = data.get(0);
        FotoEntity pojoEntity = factory.manufacturePojo(FotoEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateFoto(entity.getReclamo().getId(),pojoEntity,FotoLogic.RECLAMO);

        FotoEntity resp = em.find(FotoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    @Test
    public void deleteFotoTest() {
        FotoEntity entity = data.get(0);
        logic.deleteFoto(entity);
        FotoEntity deleted = em.find(FotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void getFotosTest() {
        List<FotoEntity> list = new ArrayList<>();
        try{
       list = logic.getFotos(reclamodata.get(0).getId(),FotoLogic.RECLAMO);
        }catch(BusinessLogicException e){
            Assert.fail();
        }
        Assert.assertEquals(reclamodata.get(0).getAlbum().size(), list.size());
        for (FotoEntity entity : list) {
            boolean found = false;
            for (FotoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFotoTest() {
        FotoEntity entity = data.get(0);
        FotoEntity resultEntity = logic.getFoto(entity.getReclamo().getId(),entity.getId(),FotoLogic.RECLAMO);
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
}
