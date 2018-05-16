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
    public static final String CALIF = "calificacionMin";
    public static final String PRECIOMIN = "precioMin";
    public static final String PRECIOMAX = "precioMax";
    public static final String MARCAS = "marcas";
    public static final String CATEGORIAS = "categorias";
    public static final String COLORES = "colores";

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
        return query.getResultList();
    }

    /**
     * Devuelve todas las categorias de la base da datos
     *
     * @param nombre del tipo de modelo a buscar
     * @return una lista con todas las categorias de un tipo de item que
     * encuentre en la base de datos;
     */
    public List<String> findCategorias(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar categorias");
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
        LOGGER.log(Level.INFO, "Inicia proceso de consultar colores");
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
     * Metodo auxiliar que se encarga de disminiur repeticiones.
     *
     * @param sql2 string sql.
     * @param filtros lista de marcas,categorias y colores
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @param page pagina a mostrar.
     * @param maxRecords numero me modelos a mostrar por pagina
     * @param tipo a buscar
     * @return lista de modelos filtrados.
     */
    public TypedQuery query(String sql2, List<List<String>> filtros, Double precioMin, Double precioMax, Double calificacionMin, Integer page, Integer maxRecords, String tipo) {
        String sql = sql2;
        sql = query2(filtros, sql, tipo);
        sql += "AND e.id IN (SELECT a.modeloId FROM " + tipo + "Entity a WHERE a.precio between :precioMin AND :precioMax AND a.disponible = TRUE";
        if (tipo.equals(ModeloLogic.BICICLETA)) {
            sql += " AND a.usada = FALSE";
        }
        sql += ") ";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        query.setParameter(CALIF, calificacionMin);
        query.setParameter(PRECIOMIN, precioMin);
        if ((int) ((double) precioMax) == -1) {
            query.setParameter(PRECIOMAX, Double.MAX_VALUE);
        } else {
            query.setParameter(PRECIOMAX, precioMax);
        }
        if (page != null && maxRecords != null) {
            query.setFirstResult((page
                    - 1) * maxRecords);
            query.setMaxResults(maxRecords);
        }
        if (!filtros.get(0).isEmpty()) {
            query.setParameter(MARCAS, filtros.get(0));
        }
        if (!filtros.get(1).isEmpty()) {
            query.setParameter(CATEGORIAS, filtros.get(1));
        }
        if (!filtros.get(2).isEmpty()) {
            query.setParameter(COLORES, filtros.get(2));
        }
        return query;
    }

    /**
     * Metodo auxiliar que se encarga de disminuir la deuda tecnica.
     *
     * @param filtros a agregar
     * @param sql2 a cambiar.
     * @param tipo
     * @return sql
     */
    public String query2(List<List<String>> filtros, String sql2, String tipo) {
        String sql = sql2;
        if (!filtros.get(0).isEmpty()) {
            sql += " AND e.marca IN :marcas ";
        }
        if (!filtros.get(1).isEmpty() && !filtros.get(2).isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM " + tipo + "Entity a WHERE a.categoria IN :categorias AND a.color in :colores) ";
        } else if (!filtros.get(1).isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM " + tipo + "Entity a WHERE a.categoria IN :categorias) ";
        } else if (!filtros.get(2).isEmpty()) {
            sql += "AND e.id IN (SELECT a.modeloId FROM " + tipo + "Entity a WHERE a.color IN :colores) ";
        }
        return sql;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos por los parametros
     * dados.
     *
     * @param filtros lista de marcas,categorias y colores
     * @param precioMin precio min que debe tener.
     * @param precioMax precio max que deben tener los modelos. si es -1 no hay
     * limite.
     * @param calificacionMin calificacion minima que deben tener los modelos.
     * @param page pagina a mostrar.
     * @param maxRecords numero me modelos a mostrar por pagina
     * @param tipo
     * @return lista de modelos filtrados.
     */
    public List<ModeloEntity> filtrar(List<List<String>> filtros, Double precioMin, Double precioMax, Double calificacionMin, Integer page, Integer maxRecords, String tipo) {
        LOGGER.log(Level.INFO, "Inicia proceso de filtrar Acesorios");
        String sql = "Select e From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        TypedQuery query = query(sql, filtros, precioMin, precioMax, calificacionMin, page, maxRecords, tipo);
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        return lista;
    }

    /**
     * Metodo que se encarga de filtrar todos los modelos por los parametros
     * dados.
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
     * @param tipo a contar
     * @return lista de modelos filtrados.
     */
    public Integer contarFiltrados(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, String tipo) {
        LOGGER.log(Level.INFO, "Inicia proceso de contar accesorios filtrados");
        String sql = "Select count(e) From ModeloEntity e where e.calificacionMedia >= :calificacionMin ";
        List<List<String>> filtros = new ArrayList<>();
        filtros.add(marcas);
        filtros.add(categorias);
        filtros.add(colores);
        TypedQuery query = query(sql, filtros, precioMin, precioMax, calificacionMin, null, null, tipo);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    /**
     * Metodo que se encarga de consultar el precio del tipo mas caro de la
     * aplicacion.
     *
     * @param tipo a busacar
     * @return precio de la bicicleta mas cara;
     */
    public Double getPrecio(String tipo) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el precio de las bicicletas.");
        TypedQuery query;
        String sql = "Select max(e.precio) From " + tipo + "Entity e ";
        if (tipo.equals(ModeloLogic.BICICLETA)) {
            sql += " where e.usada = FALSE";
        }
        query = em.createQuery(sql, String.class);
        Object temp = query.getSingleResult();
        if (temp != null) {
            temp = temp.toString();
        } else {
            temp = 0.0;
        }
        return Double.parseDouble(temp.toString());
    }

    /**
     * Metodo que se encarga de buscar accesorios en la basde de datos.
     *
     * @param busqueda String a buscar en la base de datos;
     * @return lista de modelos que contienen la busqueda
     */
    public List<ModeloEntity> buscar(String busqueda) {
        LOGGER.info("Inicia proceso de busqueda");
        String[] busquedas = busqueda.split(" ");
        List<ModeloEntity> lista = new ArrayList<>();
        for (String string : busquedas) {
            String sql = "SELECT e FROM ModeloEntity e WHERE e.referencia = :busqueda OR e.marca = :busqueda OR "
                    + "e.id in (SELECT a.modeloId FROM AccesorioEntity a WHERE a.categoria = :busqueda OR a.color = :busqueda)"
                    + " OR e.id in (SELECT a.modeloId FROM BicicletaEntity a WHERE a.categoria = :busqueda OR a.color = :busqueda)"
                    + " ORDER BY e.calificacionMedia DESC";
            TypedQuery query = em.createQuery(sql, ModeloEntity.class);
            query.setParameter("busqueda", string);
            List<ModeloEntity> temp = query.getResultList();
            if (temp != null) {
                for (ModeloEntity modeloEntity : temp) {
                    if (!lista.contains(modeloEntity)) {
                        lista.add(modeloEntity);
                    }
                }
            }
        }
        LOGGER.info(lista.toString());
        return lista;
    }

    public List<ModeloEntity> getPropagandas(String tipo) {
        String sql = "Select e From ModeloEntity e WHERE e.id IN (SELECT a.modeloId FROM " + tipo + "Entity a WHERE a.multiplicador < 1  AND a.disponible = TRUE";
        if (tipo.equals(ModeloLogic.BICICLETA)) {
            sql += " AND a.usada = FALSE";
        }
        sql += ") ORDER BY e.calificacionMedia DESC";
        TypedQuery query = em.createQuery(sql, ModeloEntity.class);
        List<ModeloEntity> lista = query.getResultList();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        return lista;
    }
}
