package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.persistence.ItemPersistence;
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
 * @item Andres
 */
@RunWith(Arquillian.class)
public class ItemPersistenceTest {


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(ItemPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

 
    @Inject
    private ItemPersistence itemPersistence;

 
    @PersistenceContext
    private EntityManager em;

  
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * 
     */
    @Before
    public void configTest() {
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
        em.createQuery("delete from ItemEntity").executeUpdate();
    }

    /**
     * 
     */
    private List<ItemEntity> data = new ArrayList<ItemEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ItemEntity entity = factory.manufacturePojo(ItemEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Item.
     *
     * 
     */
    @Test
    public void createItemTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ItemEntity newEntity = factory.manufacturePojo(ItemEntity.class);
        ItemEntity result = itemPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ItemEntity entity = em.find(ItemEntity.class, result.getId());

        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
    }

    /**
     * Prueba para consultar la lista de Items.
     *
     * 
     */
    
    @Test
    public void getItemsTest() {
        List<ItemEntity> list = itemPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ItemEntity ent : list) {
            boolean found = false;
            for (ItemEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Item.
     *
     * 
     */
    @Test
    public void getItemTest() {
        ItemEntity entity = data.get(0);
        ItemEntity newEntity = itemPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
    }

    /**
     * Prueba para eliminar un Item.
     *
     * 
     */
    @Test
    public void deleteItemTest() {
        ItemEntity entity = data.get(0);
        itemPersistence.delete(entity.getId());
        ItemEntity deleted = em.find(ItemEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Item.
     *
     * 
     */
    @Test
    public void updateItemTest() {
        ItemEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ItemEntity newEntity = factory.manufacturePojo(ItemEntity.class);

        newEntity.setId(entity.getId());
        itemPersistence.update(newEntity);

        ItemEntity resp = em.find(ItemEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPrecio(), resp.getPrecio());
    }
}