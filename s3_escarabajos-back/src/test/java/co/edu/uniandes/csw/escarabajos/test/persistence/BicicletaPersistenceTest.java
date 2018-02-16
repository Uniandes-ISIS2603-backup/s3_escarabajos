package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
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
 * @author c.santacruza
 */
@RunWith(Arquillian.class)
public class BicicletaPersistenceTest {
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Bicicleta, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(BicicletaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase BicicletaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private BicicletaPersistence biciPersistence;

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
        em.createQuery("delete from BicicletaEntity").executeUpdate();
    }

    /**
     *
     */
    private List<BicicletaEntity> data = new ArrayList<BicicletaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Bicicleta.
     *
     *
     */
    @Test
    public void createBicicletaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);
        BicicletaEntity result = biciPersistence.create(newEntity);

        Assert.assertNotNull(result);

        BicicletaEntity entity = em.find(BicicletaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
    }

    /**
     * Prueba para consultar la lista de Bicicletas.
     *
     *
     */
    @Test
    public void getBicicletasTest() {
        List<BicicletaEntity> list = biciPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (BicicletaEntity ent : list) {
            boolean found = false;
            for (BicicletaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Bicicleta.
     *
     *
     */
    @Test
    public void getBicicletaTest() {
        BicicletaEntity entity = data.get(0);
        BicicletaEntity newEntity = biciPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
    }

    /**
     * Prueba para eliminar un Bicicleta.
     *
     *
     */
    @Test
    public void deleteBicicletaTest() {
        BicicletaEntity entity = data.get(0);
        biciPersistence.delete( biciPersistence.find(entity.getId()));
        BicicletaEntity deleted = em.find(BicicletaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Bicicleta.
     *
     *
     */
    @Test
    public void updateBicicletaTest() {
        BicicletaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaEntity newEntity = factory.manufacturePojo(BicicletaEntity.class);

        newEntity.setId(entity.getId());

        biciPersistence.update(newEntity);

        BicicletaEntity resp = em.find(BicicletaEntity.class, entity.getId());

       
        Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
    }
}
