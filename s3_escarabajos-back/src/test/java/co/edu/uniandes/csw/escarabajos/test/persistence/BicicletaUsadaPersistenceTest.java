package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaUsadaPersistence;
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
public class BicicletaUsadaPersistenceTest {

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
                .addPackage(BicicletaUsadaEntity.class.getPackage())
                .addPackage(BicicletaUsadaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase BicicletaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private BicicletaUsadaPersistence biciPersistence;

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
        em.createQuery("delete from BicicletaUsadaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Datos de bicicleta usada.
     */
    private List<BicicletaUsadaEntity> data = new ArrayList<BicicletaUsadaEntity>();

    /**
     * Datos de vendedor.
     */
    private List<ClienteEntity> dataVendedor = new ArrayList<ClienteEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            dataVendedor.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            BicicletaUsadaEntity entity = factory.manufacturePojo(BicicletaUsadaEntity.class);
            entity.setCliente(dataVendedor.get(0));
            entity.setUsada(Boolean.TRUE);
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
    public void createBicicletaUsadaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaUsadaEntity newEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);
        newEntity.setUsada(Boolean.TRUE);
        BicicletaUsadaEntity result = biciPersistence.create(newEntity);

        Assert.assertNotNull(result);

        BicicletaUsadaEntity entity = em.find(BicicletaUsadaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertTrue(entity.getUsada());
        Assert.assertEquals(newEntity.getFacturaOriginal(), entity.getFacturaOriginal());
    }

    /**
     * Prueba para consultar la lista de Bicicletas.
     *
     *
     */
    @Test
    public void getBicicletasDelVendedorTest() {
        ClienteEntity vendedor = dataVendedor.get(0);
        vendedor.setBicicletasUsadas(data);
        List<BicicletaUsadaEntity> list = biciPersistence.findAllBicis(vendedor.getId());
        Assert.assertEquals(data.size(), list.size());
        for (BicicletaUsadaEntity ent : list) {
            boolean found = false;
            for (BicicletaUsadaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar todas la bicicletas usadas con un estado dado.
     */
    @Test
    public void getEstadoBicicleta() {
        BicicletaUsadaEntity entity = data.get(0);
        BicicletaUsadaEntity newEntity = biciPersistence.findEstado(entity.getEstado());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
    }

    /**
     * Prueba para consultar una Bicicleta.
     */
    @Test
    public void getBicicletaUsadaTest() {
        BicicletaUsadaEntity entity = data.get(0);
        BicicletaUsadaEntity newEntity = biciPersistence.find(dataVendedor.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertTrue(entity.getUsada());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        Assert.assertEquals(newEntity.getFacturaOriginal(), entity.getFacturaOriginal());
    }

    /**
     * Prueba para eliminar un Bicicleta.
     *
     *
     */
    @Test
    public void deleteBicicletaUsadaTest() {
        BicicletaUsadaEntity entity = data.get(0);
        biciPersistence.delete(entity.getId());
        BicicletaUsadaEntity deleted = em.find(BicicletaUsadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Bicicleta.
     *
     *
     */
    @Test
    public void updateBicicletaUsadaTest() {
        BicicletaUsadaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BicicletaUsadaEntity newEntity = factory.manufacturePojo(BicicletaUsadaEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setUsada(Boolean.TRUE);
        biciPersistence.update(newEntity);

        BicicletaUsadaEntity resp = em.find(BicicletaUsadaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getEstado(), resp.getEstado());
        Assert.assertTrue(entity.getUsada());
        Assert.assertEquals(newEntity.getFacturaOriginal(), resp.getFacturaOriginal());
    }
}
