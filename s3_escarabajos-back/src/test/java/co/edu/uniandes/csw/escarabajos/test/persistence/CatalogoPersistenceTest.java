/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.persistence;

import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.persistence.CatalogoPersistence;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
 * @author Andres
 */
@RunWith(Arquillian.class)
public class CatalogoPersistenceTest {

    private final static Logger LOGGER = Logger.getLogger(CalificacionPersistenceTest.class.getName());

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Modelo, el descriptor de la base
     * de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ModeloEntity.class.getPackage())
                .addPackage(ItemEntity.class.getPackage())
                .addPackage(AccesorioEntity.class.getPackage())
                .addPackage(BicicletaEntity.class.getPackage())
                .addPackage(CatalogoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase CatalogoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private CatalogoPersistence persistence;

    /**
     * Inyección de la dependencia a la clase CatalogoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ModeloPersistence modPersistence;

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
        em.createQuery("delete from AccesorioEntity").executeUpdate();
        em.createQuery("delete from BicicletaEntity").executeUpdate();
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();

    }

    /**
     * lista que tiene los datos de prueba
     */
    private List<ModeloEntity> modelosData = new ArrayList<>();
    /**
     * lista que tiene los datos de prueba
     */
    private List<ItemEntity> itemsData = new ArrayList<>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            entity.setTipoModelo(ModeloLogic.ACCESORIO);
            em.persist(entity);
            entity.setId(modPersistence.findByReferencia(entity.getReferencia()).getId());
            modelosData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            entity.setTipoModelo(ModeloLogic.BICICLETA);
            em.persist(entity);
            entity.setId(modPersistence.findByReferencia(entity.getReferencia()).getId());
            modelosData.add(entity);
        }
        for (int i = 0; i < 6; i++) {
            AccesorioEntity entity = factory.manufacturePojo(AccesorioEntity.class);
            entity.setModeloId(modelosData.get(i % 3).getId());
            entity.setDisponible(Boolean.TRUE);
            entity.setPrecio(10.0);
            em.persist(entity);
            itemsData.add(entity);
        }
        for (int i = 0; i < 6; i++) {
            BicicletaEntity entity = factory.manufacturePojo(BicicletaEntity.class);
            entity.setModeloId(modelosData.get((i % 3) + 3).getId());
            entity.setDisponible(Boolean.TRUE);
            entity.setUsada(false);
            entity.setPrecio(10.0);
            em.persist(entity);
            itemsData.add(entity);
        }
    }

    /**
     * Prueba el metodo que busca las marcas.
     *
     *
     */
    @Test
    public void findMarcasTest() {
        List<String> list = persistence.findMarcas(ModeloLogic.ACCESORIO);
        for (String marca : list) {
            boolean found = false;
            for (ModeloEntity storedEntity : modelosData) {
                if (marca.equals(storedEntity.getMarca()) && storedEntity.getTipoModelo().equals(ModeloLogic.ACCESORIO)) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        list = persistence.findMarcas(ModeloLogic.BICICLETAUSADA);
        Assert.assertNotNull(list);
    }

    /**
     * Prueba el metodo que busca las categorias.
     *
     *
     */
    @Test
    public void findCategoriasTest() {
        List<String> list = persistence.findCategorias(ModeloLogic.ACCESORIO);
        for (String categoria : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (categoria.equals(item.getCategoria())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        list = persistence.findCategorias(ModeloLogic.BICICLETA);
        for (String categoria : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (categoria.equals(item.getCategoria())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        list = persistence.findMarcas(ModeloLogic.BICICLETAUSADA);
        Assert.assertNotNull(list);
    }

    /**
     * Prueba el metodo que busca los colores.
     *
     */
    @Test
    public void findColoresTest() {
        List<String> list = persistence.findColores(ModeloLogic.ACCESORIO);
        for (String color : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (color.equals(item.getColor())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        list = persistence.findColores(ModeloLogic.BICICLETA);
        for (String color : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (color.equals(item.getColor())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        list = persistence.findColores(ModeloLogic.BICICLETAUSADA);
        for (String color : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (color.equals(item.getColor())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void filtrarAccesoriosTest() {
        ArrayList<List<String>> filtros = new ArrayList<>();
        filtros.add(new ArrayList<>());
        filtros.add(new ArrayList<>());
        filtros.add(new ArrayList<>());
        List<ModeloEntity> modelos = persistence.filtrarAccesorios(filtros, 0.0, -1.0, 0.0, null, null);
        Assert.assertEquals(3, modelos.size());
        Assert.assertEquals(3, (int) persistence.contarAccesoriosFiltrados(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        ArrayList<String> marcas = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        ArrayList<String> colores = new ArrayList<>();
        marcas.add(modelosData.get(0).getMarca());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarAccesorios(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarAccesoriosFiltrados(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(0).getMarca());
        categorias.add(itemsData.get(0).getCategoria());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarAccesorios(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarAccesoriosFiltrados(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(0).getMarca());
        colores.add(itemsData.get(0).getColor());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarAccesorios(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarAccesoriosFiltrados(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(0).getMarca());
        categorias.add(itemsData.get(0).getCategoria());
        colores.add(itemsData.get(0).getColor());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarAccesorios(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarAccesoriosFiltrados(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add("NOTTT!!!!");
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarAccesorios(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(0, modelos.size());
        Assert.assertEquals(0, (int) persistence.contarAccesoriosFiltrados(marcas, categorias, colores, 0.0, -1.0, 0.0));
    }

    @Test
    public void filtrarBicicletasTest() {
        ArrayList<List<String>> filtros = new ArrayList<>();
        filtros.add(new ArrayList<>());
        filtros.add(new ArrayList<>());
        filtros.add(new ArrayList<>());
        List<ModeloEntity> modelos = persistence.filtrarBicicletas(filtros, 0.0, -1.0, 0.0, null, null);
        Assert.assertEquals(3, modelos.size());
        Assert.assertEquals(3, (int) persistence.contarBicicletasFiltradas(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        ArrayList<String> marcas = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        ArrayList<String> colores = new ArrayList<>();
        marcas.add(modelosData.get(3).getMarca());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarBicicletas(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarBicicletasFiltradas(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(3).getMarca());
        categorias.add(itemsData.get(6).getCategoria());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarBicicletas(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarBicicletasFiltradas(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(3).getMarca());
        categorias.add(itemsData.get(6).getCategoria());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarBicicletas(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarBicicletasFiltradas(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add(modelosData.get(3).getMarca());
        colores.add(itemsData.get(6).getColor());
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarBicicletas(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(1, modelos.size());
        Assert.assertEquals(1, (int) persistence.contarBicicletasFiltradas(marcas, categorias, colores, 0.0, -1.0, 0.0));

        filtros = new ArrayList<>();
        marcas = new ArrayList<>();
        categorias = new ArrayList<>();
        colores = new ArrayList<>();
        marcas.add("NOTTT!!!!");
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        modelos = persistence.filtrarBicicletas(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, modelosData.size() + 1);
        Assert.assertEquals(0, modelos.size());
        Assert.assertEquals(0, (int) persistence.contarBicicletasFiltradas(marcas, categorias, colores, 0.0, -1.0, 0.0));
    }

    @Test
    public void getPrecioAccesoriosTest() {
        Assert.assertEquals(10, (int) ((double) persistence.getPrecioAccesorios()));
    }

    @Test
    public void getPrecioBicicletasTest() {
        Assert.assertEquals(10, (int) ((double) persistence.getPrecioBicicletas()));
    }

    @Test
    public void buscarTest() {
        List<ModeloEntity> lista = persistence.buscar(modelosData.get(0).getMarca()+" "+itemsData.get(0).getCategoria()+" "+"NOT");
        Assert.assertNotNull(lista);
    }

}
