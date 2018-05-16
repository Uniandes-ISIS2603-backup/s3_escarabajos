/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.AccesorioLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ListaDeseosLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ListaDeseosPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ListaDeseosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ListaDeseosLogic logic;
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

    private List<ListaDeseosEntity> data = new ArrayList<>();
    private List<ItemEntity> dataItems = new ArrayList<>();
    private List<ClienteEntity> dataClientes = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ListaDeseosEntity.class.getPackage())
                .addPackage(ListaDeseosLogic.class.getPackage())
                .addPackage(ListaDeseosPersistence.class.getPackage())
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

        em.createQuery("delete from ListaDeseosEntity").executeUpdate();
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

                ListaDeseosEntity listadeseos = factory.manufacturePojo(ListaDeseosEntity.class);
                AccesorioEntity item = factory.manufacturePojo(AccesorioEntity.class);
                ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
                item.setModeloId(modelo.getId());

                logicCliente.createCliente(cliente);
                logicAccesorio.createAccesorio(item);
                logic.createListaDeseos(listadeseos);

                data.add(listadeseos);
                dataItems.add(item);
                dataClientes.add(cliente);

                listadeseos.setCliente(cliente);
            }
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }

    }

    /**
     * prueba el metodo createListaDeseos
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createListaDeseos() throws BusinessLogicException {

        ListaDeseosEntity newEntity = factory.manufacturePojo(ListaDeseosEntity.class);
        ListaDeseosEntity result = logic.createListaDeseos(newEntity);
        Assert.assertNotNull(result);
        ListaDeseosEntity entity = logic.findListaDeseos(result.getId());
    }

    /**
     * prueba el metodo findListaDeseos
     */
    @Test
    public void findListaDeseosTest() throws BusinessLogicException {

        ListaDeseosEntity entity = data.get(3);
        ListaDeseosEntity resultEntity = logic.findListaDeseos(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        
        try {
            
            logic.findListaDeseos(Long.MAX_VALUE);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * prueba el metodo updateListaDeseos
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateListaDeseosTest() throws BusinessLogicException {

        ListaDeseosEntity entity = data.get(2);
        ListaDeseosEntity pojoEntity = factory.manufacturePojo(ListaDeseosEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateListaDeseos(pojoEntity);

        ListaDeseosEntity resp = em.find(ListaDeseosEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        
        try {
            
           ListaDeseosEntity listadeseos = logic.findListaDeseos(Long.MAX_VALUE);
           logic.updateListaDeseos(listadeseos);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * prueba el metodo deleteListaDeseos
     */
    @Test
    public void deleteListaDeseosTest() {
        ListaDeseosEntity entity = data.get(0);
        logic.deleteListaDeseos(entity.getId());
        ListaDeseosEntity deleted = em.find(ListaDeseosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * prueba el metodo addItem
     *
     * @throws BusinessLogicException
     */
    @Test
    public void addItemTest() throws BusinessLogicException {

        ListaDeseosEntity listadeseos = data.get(0);

        ItemEntity item = dataItems.get(0);

        logic.addItem(listadeseos.getId(), item.getId());
        
        listadeseos = logic.findListaDeseos(listadeseos.getId());
        
        assert(listadeseos.getItems().contains(item));
        
        try {
           logic.addItem(Long.MAX_VALUE, Long.MAX_VALUE);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
        
        try {
           logic.addItem(data.get(0).getId(), Long.MAX_VALUE);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
        
        try {
            ListaDeseosEntity listadeseos2 = data.get(0);
            ItemEntity item2 = dataItems.get(0);
            logic.addItem(listadeseos2.getId(), item2.getId());
            logic.addItem(listadeseos2.getId(), item2.getId());
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Test
    public void getItemsTest () throws BusinessLogicException {
        
        ListaDeseosEntity listadeseos = data.get(0);
        
        logic.getItems(listadeseos.getId());
        
        try {

           logic.getItems(Long.MAX_VALUE);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * prueba el metodo removeItem
     */
    @Test
    public void removeItemTest() throws BusinessLogicException {
        ListaDeseosEntity listadeseos = data.get(6);

        ItemEntity item = dataItems.get(6);

        logic.addItem(listadeseos.getId(), item.getId());
        logic.removeItem(listadeseos.getId(), item.getId());

        assert(listadeseos.getItems().isEmpty());
        
        try {
           
           ListaDeseosEntity listadeseos3 = logic.findListaDeseos(Long.MAX_VALUE);
           logic.removeItem(listadeseos3.getId(), Long.MAX_VALUE);
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * prueba el metodo crearFactura
     *
     * @throws BusinessLogicException
     */
    @Test
    public void crearFacturaTest() throws BusinessLogicException {

        ListaDeseosEntity listadeseos = data.get(4);

        ItemEntity item1 = dataItems.get(1);
        ItemEntity item2 = dataItems.get(2);

        listadeseos.setPrecioTotal(0.0);
        logic.addItem(listadeseos.getId(), item1.getId());
        logic.addItem(listadeseos.getId(), item2.getId());
        FacturaEntity facturaGenerada = logic.crearFactura(listadeseos.getId());
        listadeseos = logic.findListaDeseos(listadeseos.getId());

        double precioTotal = item1.getPrecio() + item2.getPrecio();

        assert (facturaGenerada.getDinero() == precioTotal);
        
        try {
           
           ListaDeseosEntity listadeseos4 = logic.findListaDeseos(Long.MAX_VALUE);
           logic.crearFactura(listadeseos4.getId());
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
           
           ListaDeseosEntity listadeseos4 = data.get(0);
           logic.crearFactura(listadeseos4.getId());
           Assert.fail();
        }
        catch(Exception e){
           Assert.assertNotNull(e);
           Logger.getLogger(ModeloLogicTest.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
