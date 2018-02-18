package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.persistence.ItemPersistence;
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
public class ItemPersistenceTest {
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Item, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(ItemPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase ItemPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ItemPersistence itemPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
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
        em.createQuery("delete from ItemEntity").executeUpdate();
    }

    /**
     *
     */
    private List<ItemEntity> data = new ArrayList<ItemEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
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

        Assert.assertEquals(newEntity.getId(), entity.getId());
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
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar un Item.
     *
     *
     */
    @Test
    public void deleteItemTest() {
        ItemEntity entity = data.get(0);
        itemPersistence.delete( itemPersistence.find(entity.getId()));
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

       
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
}
