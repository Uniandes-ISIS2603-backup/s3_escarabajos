/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.test.logic;

import co.edu.uniandes.csw.escarabajos.ejb.CatalogoLogic;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
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
public class CatalogoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ModeloLogic modeloLogic;

    @Inject
    private CatalogoLogic catalogoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ModeloEntity> data = new ArrayList<>();

    private List<ItemEntity> itemsData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ModeloEntity.class.getPackage())
                .addPackage(ModeloLogic.class.getPackage())
                .addPackage(ModeloPersistence.class.getPackage())
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
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from AccesorioEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AccesorioEntity items = factory.manufacturePojo(AccesorioEntity.class);
            em.persist(items);
            itemsData.add(items);
        }

        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            entity.setTipoModelo(ModeloLogic.ACCESORIO);
            ArrayList<ItemEntity> items = new ArrayList<>();
            items.add(itemsData.get(i));
            entity.setItems(items);
            em.persist(entity);
            data.add(entity);

        }
        for (ModeloEntity modelo : modeloLogic.getModelos()) {
            for (ItemEntity item : modelo.getItems()) {
                item.setModeloId(modelo.getId());
                em.merge(item);
            }
        }

    }

    /**
     * Prueba para buscar las marcas de los accesorios de la aplicacion.
     *
     */
    @Test
    public void getMarcasAccesoriosTest() {
        List<String> list = catalogoLogic.getMarcasAccesorios();
        for (String marca : list) {
            boolean found = false;
            for (ModeloEntity storedEntity : data) {
                if (marca.equals(storedEntity.getMarca()) && storedEntity.getTipoModelo().equals(ModeloLogic.ACCESORIO)) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para buscar las marcas de las bicicletas de la aplicacion.
     *
     */
    @Test
    public void getMarcasBicicletasTest() {
        List<String> list = catalogoLogic.getMarcasBicicletas();
        for (String marca : list) {
            boolean found = false;
            for (ModeloEntity storedEntity : data) {
                if (marca.equals(storedEntity.getMarca()) && storedEntity.getTipoModelo().equals(ModeloLogic.ACCESORIO)) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para buscar las categorias de los accesorios de la aplicacion.
     *
     */
    @Test
    public void getCategoriasAccesoriosTest() {
        List<String> list = catalogoLogic.getCategoriasAccesorios();
        for (String categoria : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (categoria.equals(item.getCategoria())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para buscar las categorias de las bicicletas de la aplicacion.
     *
     */
    @Test
    public void getCategoriasBicicletasTest() {
        List<String> list = catalogoLogic.getCategoriasBicicletas();
        for (String categoria : list) {
            boolean found = false;
            for (ItemEntity item : itemsData) {
                if (categoria.equals(item.getCategoria())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para buscar los colores de los accesorios de la aplicacion.
     *
     */
    @Test
    public void getColoresAccesoriosTest() {
        List<String> list = catalogoLogic.getColoresAccesorios();
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

    /**
     * Prueba para buscar los colores de las bicicletas de la aplicacion.
     *
     */
    @Test
    public void getColoresBicicletasTest() {
        List<String> list = catalogoLogic.getColoresBicicletas();
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
    public void getModelosBicicletasFiltradosTest() {
        ArrayList<List<String>> filtros = new ArrayList<>();
        ArrayList<String> marcas = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        ArrayList<String> colores = new ArrayList<>();
        int contadorModelo = 0;
        double precioMax = 0.0;
        for (ModeloEntity modeloEntity : data) {
            if (modeloEntity.getTipoModelo().equals(ModeloLogic.BICICLETA)) {
                if (!marcas.contains(modeloEntity.getMarca())) {
                    marcas.add(modeloEntity.getMarca());
                }
                contadorModelo++;
            }
        }
        for (ItemEntity itemEntity : itemsData) {
            if (itemEntity instanceof BicicletaEntity) {
                if (!(itemEntity instanceof BicicletaUsadaEntity) && itemEntity.getPrecio() > precioMax) {
                    precioMax = itemEntity.getPrecio();
                }
                if (!categorias.contains(itemEntity.getCategoria())) {
                    categorias.add(itemEntity.getCategoria());
                }
                if (!colores.contains(itemEntity.getColor())) {
                    colores.add(itemEntity.getColor());
                }
            }
        }
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        try {
            List<ModeloEntity> modelos = catalogoLogic.getModelosBicicletasFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE);
            Assert.assertEquals(modelos.size(), contadorModelo);
            int numero = catalogoLogic.getNumeroBicicletasConFiltros(filtros.get(0), filtros.get(1), filtros.get(2), 0.0, Double.MAX_VALUE, 0.0);
            Assert.assertEquals(numero, contadorModelo);
        } catch (BusinessLogicException ex) {
            Assert.assertNotNull(ex);
            Assert.fail();
        }
        double precio = catalogoLogic.getPrecioBicicletas();
        Assert.assertEquals("" + precio, "" + precioMax);

        try {
            filtros = new ArrayList<>();
            filtros.add(null);
            filtros.add(null);
            filtros.add(null);
            catalogoLogic.getModelosBicicletasFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }

        try {
            filtros = new ArrayList<>();
            filtros.add(null);
            filtros.add(null);
            filtros.add(null);
            catalogoLogic.getNumeroBicicletasConFiltros(null, null, null, 0.0, Double.MAX_VALUE, 0.0);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void getModelosAccesoriosFiltradosTest() {
        ArrayList<List<String>> filtros = new ArrayList<>();
        ArrayList<String> marcas = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        ArrayList<String> colores = new ArrayList<>();
        int contadorModelo = 0;
        double precioMax = 0.0;
        for (ModeloEntity modeloEntity : data) {
            if (modeloEntity.getTipoModelo().equals(ModeloLogic.ACCESORIO)) {
                if (!marcas.contains(modeloEntity.getMarca())) {
                    marcas.add(modeloEntity.getMarca());
                }
                contadorModelo++;
            }
        }
        for (ItemEntity itemEntity : itemsData) {
            if (itemEntity instanceof AccesorioEntity) {
                if (itemEntity.getPrecio() > precioMax) {
                    precioMax = itemEntity.getPrecio();
                }
                if (!categorias.contains(itemEntity.getCategoria())) {
                    categorias.add(itemEntity.getCategoria());
                }
                if (!colores.contains(itemEntity.getColor())) {
                    colores.add(itemEntity.getColor());
                }
            }
        }
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        try {
            List<ModeloEntity> modelos = catalogoLogic.getModelosAccesoriosFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE);
            Assert.assertEquals(modelos.size(), contadorModelo);
            int numero = catalogoLogic.getNumeroAccesoriosConFiltros(filtros.get(0), filtros.get(1), filtros.get(2), 0.0, Double.MAX_VALUE, 0.0);
            Assert.assertEquals(numero, contadorModelo);
        } catch (BusinessLogicException ex) {
            Assert.assertNotNull(ex);
            Assert.fail();
        }
        double precio = catalogoLogic.getPrecioAccesorios();
        Assert.assertEquals("" + precio, "" + precioMax);

        try {
            filtros = new ArrayList<>();
            filtros.add(null);
            filtros.add(null);
            filtros.add(null);
            catalogoLogic.getModelosAccesoriosFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }

        try {
            filtros = new ArrayList<>();
            filtros.add(null);
            filtros.add(null);
            filtros.add(null);
            catalogoLogic.getNumeroAccesoriosConFiltros(null, null, null, 0.0, Double.MAX_VALUE, 0.0);
            Assert.fail();
        } catch (BusinessLogicException e) {
            Assert.assertNotNull(e);
        }

    }

    @Test
    public void BuscarTest() {
        List<ModeloEntity> lista = catalogoLogic.buscar(data.get(0).getMarca());
        Assert.assertNotNull(lista);
    }
}
