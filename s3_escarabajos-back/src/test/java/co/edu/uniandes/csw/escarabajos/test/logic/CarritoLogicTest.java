/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.AccesorioLogic;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
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
 * @author Mateo
 */
@RunWith(Arquillian.class)
public class CarritoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CarritoLogic logic;
    @Inject
    private ClienteLogic logicCliente;
    @Inject
    private AccesorioLogic logicAccesorio;

    @Inject
    private ModeloLogic logicModelo;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CarritoEntity> data = new ArrayList<>();
    private List<ItemEntity> dataItems = new ArrayList<>();
    private List<ClienteEntity> dataClientes = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoEntity.class.getPackage())
                .addPackage(CarritoLogic.class.getPackage())
                .addPackage(CarritoPersistence.class.getPackage())
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(FacturaEntity.class.getPackage())
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

        em.createQuery("delete from CarritoEntity").executeUpdate();
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
        em.createQuery("delete from AccesorioEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        try {
            ModeloEntity modelo = factory.manufacturePojo(ModeloEntity.class);
            modelo.setTipoModelo(ModeloLogic.ACCESORIO);
            logicModelo.createModelo(modelo);
            for (int i = 0; i < 7; i++) {

                CarritoEntity carrito = factory.manufacturePojo(CarritoEntity.class);
                AccesorioEntity item = factory.manufacturePojo(AccesorioEntity.class);
                ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
                item.setModeloId(modelo.getId());

                logicCliente.createCliente(cliente);
                logicAccesorio.createAccesorio(item);
                logic.createCarrito(carrito);

                data.add(carrito);
                dataItems.add(item);
                dataClientes.add(cliente);

                carrito.setCliente(cliente);
            }
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }

    }

    /**
     * prueba el metodo createCarrito
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createCarrito() throws BusinessLogicException {

        CarritoEntity newEntity = factory.manufacturePojo(CarritoEntity.class);
        CarritoEntity result = logic.createCarrito(newEntity);
        Assert.assertNotNull(result);
        CarritoEntity entity = logic.findCarrito(result.getId());
    }

    /**
     * prueba el metodo findCarrito
     */
    @Test
    public void findCarritoTest() {

        CarritoEntity entity = data.get(3);
        CarritoEntity resultEntity = logic.findCarrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * prueba el metodo updateCarrito
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateCarritoTest() throws BusinessLogicException {

        CarritoEntity entity = data.get(2);
        CarritoEntity pojoEntity = factory.manufacturePojo(CarritoEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateCarrito(pojoEntity);

        CarritoEntity resp = em.find(CarritoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    /**
     * prueba el metodo deleteCarrito
     */
    @Test
    public void deleteCarritoTest() {
        CarritoEntity entity = data.get(0);
        logic.deleteCarrito(entity.getId());
        CarritoEntity deleted = em.find(CarritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * prueba el metodo addItem
     *
     * @throws BusinessLogicException
     */
    @Test
    public void addItemTest() throws BusinessLogicException {

        CarritoEntity carrito = data.get(0);

        ItemEntity item = dataItems.get(0);

        logic.addItem(carrito.getId(), item.getId());
        
        carrito = logic.findCarrito(carrito.getId());
        
        assert(carrito.getItems().contains(item));
    }

    /**
     * prueba el metodo removeItem
     */
    @Test
    public void removeItemTest() throws BusinessLogicException {
        CarritoEntity carrito = data.get(6);

        ItemEntity item = dataItems.get(6);

        logic.addItem(carrito.getId(), item.getId());
        logic.removeItem(carrito.getId(), item.getId());

        assert(carrito.getItems().isEmpty());
    }

    /**
     * prueba el metodo crearFactura
     *
     * @throws BusinessLogicException
     */
    @Test
    public void crearFacturaTest() throws BusinessLogicException {

        CarritoEntity carrito = data.get(4);

        ItemEntity item1 = dataItems.get(1);
        ItemEntity item2 = dataItems.get(2);

        carrito.setPrecioTotal(0.0);
        logic.addItem(carrito.getId(), item1.getId());
        logic.addItem(carrito.getId(), item2.getId());
        FacturaEntity facturaGenerada = logic.crearFactura(carrito.getId());
        carrito = logic.findCarrito(carrito.getId());

        double precioTotal = item1.getPrecio() + item2.getPrecio();

        assert (facturaGenerada.getDinero() == precioTotal);

    }
}
