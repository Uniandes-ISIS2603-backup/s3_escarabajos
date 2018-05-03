/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres
 */
@Stateless
public class CatalogoPersistence {

    public static final Logger LOGGER = Logger.getLogger(CatalogoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Devuelve todas las marcas de la base da datos
     *
     * @param nombre del tipo de modelo a buscar
     * @return una lista con todas las marcas que encuentre en la base de datos;
     */
    public List<String> findMarcas(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar marcas");
        TypedQuery query = em.createQuery("Select distinct(e.marca) From ModeloEntity e where e.tipoModelo =  :name", String.class);
        query.setParameter("name", nombre);
        List<String> lista = query.getResultList();
        return lista;
    }

    /**
     * Devuelve todas las categorias de la base da datos
     *
     * @param nombre del tipo de modelo a buscar
     * @return una lista con todas las categorias de un tipo de item que
     * encuentre en la base de datos;
     */
    public List<String> findCategorias(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar marcas");
        TypedQuery query;
        switch (nombre) {
            case ModeloLogic.ACCESORIO:
                query = em.createQuery("Select distinct(e.categoria) From AccesorioEntity e", String.class);
                break;
            case ModeloLogic.BICICLETA:
                query = em.createQuery("Select distinct(e.categoria) From BicicletaEntity e", String.class);
                break;
            default:
                query = em.createQuery("Select distinct(e.categoria) From BicicletaUsadaEntity e", String.class);
                break;
        }
        List<String> lista = query.getResultList();

        if (lista == null) {
            lista = new ArrayList<>();

        }
        LOGGER.log(Level.INFO, lista.toString());
        return lista;
    }

    /**
     * Devuelve todas los colores de la base da datos
     *
     * @param nombre del tipo de modelo a buscar
     * @return una lista con todas los colores de un tipo de item que encuentre
     * en la base de datos;
     */
    public List<String> findColores(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar marcas");
        TypedQuery query;
        switch (nombre) {
            case ModeloLogic.ACCESORIO:
                query = em.createQuery("Select distinct(e.color) From AccesorioEntity e", String.class);
                break;
            case ModeloLogic.BICICLETA:
                query = em.createQuery("Select distinct(e.color) From BicicletaEntity e", String.class);
                break;
            default:
                query = em.createQuery("Select distinct(e.color) From BicicletaUsadaEntity e", String.class);
                break;
        }
        List<String> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();

        }
        return lista;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos de accesorios por los
     * parametros dados.
     *
     * @param marcas marcas que debe tener, si esta vacia no verificar las
     * marcas.
     * @param categorias categorias a verificar, si esta vacia no verificar las
     * categorias.
     * @param colores colores a verficar, si esta vacia no verificar los
     * colores.
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @param page pagina a mostrar.
     * @param maxRecords numero me modelos a mostrar por pagina
     * @return lista de modelos filtrados.
     */
    public List<ModeloEntity> filtrarAccesorios(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, Integer page, Integer maxRecords) {
        LOGGER.log(Level.INFO, "Inicia proceso de filtrar Acesorios");
        String sql = "Select e From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        if (!marcas.isEmpty()) {
            sql += " AND e.marca IN :marcas ";
        }
        if (!categorias.isEmpty() && !colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria IN :categorias AND a.color in :colores) ";
        } else if (!categorias.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria IN :categorias) ";
        } else if (!colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.color IN :colores) ";
        }
        sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.precio between :precioMin AND :precioMax AND a.disponible = TRUE) ";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        query.setParameter("calificacionMin", calificacionMin);
        query.setParameter("precioMin", precioMin);
        if (precioMax == -1) {
            query.setParameter("precioMax", Double.MAX_VALUE);
        } else {
            query.setParameter("precioMax", precioMax);
        }
        if (page != null && maxRecords != null) {
            query.setFirstResult((page
                    - 1) * maxRecords);
            query.setMaxResults(maxRecords);
        }
        if (!marcas.isEmpty()) {
            query.setParameter("marcas", marcas);
        }
        if (!categorias.isEmpty()) {
            query.setParameter("categorias", categorias);
        }
        if (!colores.isEmpty()) {
            query.setParameter("colores", colores);
        }
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        LOGGER.log(Level.INFO, lista.toString());
        return lista;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos de bicicletas por los
     * parametros dados.
     *
     * @param marcas marcas que debe tener, si esta vacia no verificar las
     * marcas.
     * @param categorias categorias a verificar, si esta vacia no verificar las
     * categorias.
     * @param colores colores a verficar, si esta vacia no verificar los
     * colores.
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @param page pagina a mostrar.
     * @param maxRecords numero me modelos a mostrar por pagina
     * @return lista de modelos filtrados.
     */
    public List<ModeloEntity> filtrarBicicletas(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, Integer page, Integer maxRecords) {
        LOGGER.log(Level.INFO, "Inicia proceso de filtrar bicicletas");
        String sql = "Select e From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        if (!marcas.isEmpty()) {
            sql += " AND e.marca IN :marcas ";
        }
        if (!categorias.isEmpty() && !colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria IN :categorias AND a.color in :colores) ";
        } else if (!categorias.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria IN :categorias) ";
        } else if (!colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.color IN :colores) ";
        }
        sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.precio between :precioMin AND :precioMax AND a.disponible = TRUE) ";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        query.setParameter("calificacionMin", calificacionMin);
        query.setParameter("precioMin", precioMin);
        if (precioMax == -1) {
            query.setParameter("precioMax", Double.MAX_VALUE);
        } else {
            query.setParameter("precioMax", precioMax);
        }
        if (page != null && maxRecords != null) {
            query.setFirstResult((page
                    - 1) * maxRecords);
            query.setMaxResults(maxRecords);
        }
        if (!marcas.isEmpty()) {
            query.setParameter("marcas", marcas);
        }
        if (!categorias.isEmpty()) {
            query.setParameter("categorias", categorias);
        }
        if (!colores.isEmpty()) {
            query.setParameter("colores", colores);
        }
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        LOGGER.log(Level.INFO, lista.toString());
        return lista;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos de accesorios por los
     * parametros dados.
     *
     * @param marcas marcas que debe tener, si esta vacia no verificar las
     * marcas.
     * @param categorias categorias a verificar, si esta vacia no verificar las
     * categorias.
     * @param colores colores a verficar, si esta vacia no verificar los
     * colores.
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @return lista de modelos filtrados.
     */
    public Integer contarAccesoriosFiltrados(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin) {
        LOGGER.log(Level.INFO, "Inicia proceso de contar accesorios filtrados");
        String sql = "Select count(e) From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        if (!marcas.isEmpty()) {
            sql += " AND e.marca IN :marcas ";
        }
        if (!categorias.isEmpty() && !colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria IN :categorias AND a.color in :colores) ";
        } else if (!categorias.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria IN :categorias) ";
        } else if (!colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.color IN :colores) ";
        }
        sql += "AND e.id IN (SELECT a.modeloId FROM AccesorioEntity a WHERE a.precio between :precioMin AND :precioMax AND a.disponible = TRUE) ";
        TypedQuery query = em.createQuery(sql, String.class);
        query.setParameter("calificacionMin", calificacionMin);
        query.setParameter("precioMin", precioMin);
        if (precioMax == -1) {
            query.setParameter("precioMax", Double.MAX_VALUE);
        } else {
            query.setParameter("precioMax", precioMax);
        }
        if (!marcas.isEmpty()) {
            query.setParameter("marcas", marcas);
        }
        if (!categorias.isEmpty()) {
            query.setParameter("categorias", categorias);
        }
        if (!colores.isEmpty()) {
            query.setParameter("colores", colores);
        }
        Integer resp = Integer.parseInt(query.getSingleResult().toString());
        LOGGER.log(Level.INFO, resp.toString());
        return resp;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos de bicicletas por los
     * parametros dados.
     *
     * @param marcas marcas que debe tener, si esta vacia no verificar las
     * marcas.
     * @param categorias categorias a verificar, si esta vacia no verificar las
     * categorias.
     * @param colores colores a verficar, si esta vacia no verificar los
     * colores.
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @return lista de modelos filtrados.
     */
    public Integer contarBicicletasFiltradas(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin) {
        LOGGER.log(Level.INFO, "Inicia proceso de contar bicicletas filtradas");
        String sql = "Select count(e) From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        if (!marcas.isEmpty()) {
            sql += " AND e.marca IN :marcas ";
        }
        if (!categorias.isEmpty() && !colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria IN :categorias AND a.color in :colores) ";
        } else if (!categorias.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria IN :categorias) ";
        } else if (!colores.isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.color IN :colores) ";
        }
        sql += "AND e.id IN (SELECT a.modeloId FROM BicicletaEntity a WHERE a.precio between :precioMin AND :precioMax AND a.disponible = TRUE) ";
        TypedQuery query = em.createQuery(sql, String.class);
        query.setParameter("calificacionMin", calificacionMin);
        query.setParameter("precioMin", precioMin);
        if (precioMax == -1) {
            query.setParameter("precioMax", Double.MAX_VALUE);
        } else {
            query.setParameter("precioMax", precioMax);
        }
        if (!marcas.isEmpty()) {
            query.setParameter("marcas", marcas);
        }
        if (!categorias.isEmpty()) {
            query.setParameter("categorias", categorias);
        }
        if (!colores.isEmpty()) {
            query.setParameter("colores", colores);
        }
        Integer resp = Integer.parseInt(query.getSingleResult().toString());
        LOGGER.log(Level.INFO, resp.toString());
        return resp;
    }

    /**
     * Metodo que se encarga de consultar el precio de la bicicleta mas cara de
     * la aplicacion.
     *
     * @return precio de la bicicleta mas cara;
     */
    public Double getPrecioBicicletas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el precio de las bicicletas.");
        TypedQuery query;
        query = em.createQuery("Select max(e.precio) From BicicletaEntity e ", String.class);
        Double resp = 0.0;
        try {
            String temp = query.getSingleResult().toString();
            resp = Double.parseDouble(temp);
        } catch (NullPointerException e) {
            //empty
        }
        return resp;
    }

    /**
     * Metodo que se encarga de consultar el precio del accesorio mas caro de la
     * aplicacion.
     *
     * @return precio del accesorio mas caro.
     */
    public Double getPrecioAccesorios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el precio de los accesorios");
        TypedQuery query;
        query = em.createQuery("Select max(e.precio) From AccesorioEntity e ", String.class);
        Double resp = 0.0;
        try {
            String temp = query.getSingleResult().toString();
            resp = Double.parseDouble(temp);
        } catch (NullPointerException e) {
            //empty
        }
        return resp;
    }

    /**
     * Metodo que se encarga de buscar accesorios en la basde de datos.
     *
     * @param busqueda String a buscar en la base de datos;
     * @return lista de modelos que contienen la busqueda
     */
    public List<ModeloEntity> buscarAccesorios(String busqueda) {
        String sql = "SELECT e FROM modeloEntity e WHERE e.referencia = :busqueda OR e.marca = :busqueda OR ";
        sql += "e.id in (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria = :busqueda OR a.color = :busqueda) ORDER BY e.calificacionMedia DESC";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        query.setParameter("busqueda", busqueda);
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        LOGGER.log(Level.INFO, lista.toString());
        return lista;
    }

    /**
     * Metodo que se encarga de buscar bicicletas en la basde de datos.
     *
     * @param busqueda String a buscar en la base de datos;
     * @return lista de modelos que contienen la busqueda
     */
    public List<ModeloEntity> buscarBicicletas(String busqueda) {
        String sql = "SELECT e FROM modeloEntity e WHERE e.referencia = :busqueda OR e.marca = :busqueda OR ";
        sql += "e.id in (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria = :busqueda OR a.color = :busqueda) ORDER BY e.calificacionMedia DESC";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        query.setParameter("busqueda", busqueda);
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        LOGGER.log(Level.INFO, lista.toString());
        return lista;
    }
}
