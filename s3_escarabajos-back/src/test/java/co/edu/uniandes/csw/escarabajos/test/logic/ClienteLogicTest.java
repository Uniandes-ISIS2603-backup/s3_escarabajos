/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ClientePersistence;
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
public class ClienteLogicTest {

    /**
     * PodamFactory.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    private ClienteLogic logic;

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
     * Datos de cliente.
     */
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    /**
     * Creacion del deployment
     *
     * @return deployment
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from CarritoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() throws BusinessLogicException {

        for (int i = 0; i < 3; i++) {

            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            logic.createCliente(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba el metodo getCliente.
     */
    @Test
    public void findClienteTest() {

        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = logic.getCliente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba el metodo updateCliente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateClienteTest() throws BusinessLogicException {

        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateCliente(pojoEntity);

        ClienteEntity resp = logic.getCliente(entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    /**
     * Prueba el metodo createCliente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createClienteTest() throws BusinessLogicException {

        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = logic.createCliente(newEntity);
        Assert.assertNotNull(result);
        ClienteEntity entity = logic.getCliente(result.getId());
    }

    /**
     * Prueba del metodo getClientes.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getClientesTest() throws BusinessLogicException {
        List<ClienteEntity> list = logic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba del metodo deleteCliente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        logic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
