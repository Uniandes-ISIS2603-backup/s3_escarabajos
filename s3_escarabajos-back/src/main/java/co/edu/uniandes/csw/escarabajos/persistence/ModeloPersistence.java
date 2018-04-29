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
public class ModeloPersistence {

    public static final Logger LOGGER = Logger.getLogger(ModeloPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param entity objeto modelo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ModeloEntity create(ModeloEntity entity) {
        LOGGER.info("Creando un modelo nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el modelo en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un modelo nuevo");
        return entity;
    }

    /**
     * Actualiza un modelo.
     *
     * @param entity: el modelo que viene con los nuevos cambios. Por ejemplo el
     * nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un modelo con los cambios aplicados.
     */
    public ModeloEntity update(ModeloEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando modelo con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Borra un modelo de la base de datos recibiendo como argumento el id del
     * modelo
     *
     * @param id: id correspondiente a el modelo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando modelo con id={0}", id);
        ModeloEntity entity = em.find(ModeloEntity.class, id);
        em.remove(entity);

    }

    /**
     * Busca si hay algun modelo con el id que se envía de argumento
     *
     * @param id: id correspondiente a el modelo buscado.
     * @return un modelo.
     */
    public ModeloEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando modelo con id={0}", id);
        return em.find(ModeloEntity.class, id);
    }

    /**
     * Devuelve todos los modelos de la base de datos.
     *
     * @return una lista con todos losmodelos que encuentre en la base de datos,
     * "select u from ModeloEntity u" es como un "select * from ModeloEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ModeloEntity> findAll() {
        LOGGER.info("Consultando todos los modelos");
        // Se crea un query para buscar todos los modelos en la base de datos.
        TypedQuery query = em.createQuery("select u from ModeloEntity u", ModeloEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de modelos.
        return query.getResultList();
    }

    /**
     * Busca si hay algun modelo con la referencia que se envía de argumento
     *
     * @param referencia: Referencia del modelo que se está buscando
     * @return null si no existe ningun modelo con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ModeloEntity findByReferencia(String referencia) {
        LOGGER.log(Level.INFO, "Consultando modelo por referencia ", referencia);

        // Se crea un query para buscar clientes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ModeloEntity e where e.referencia = :referencia", ModeloEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("referencia", referencia);
        // Se invoca el query se obtiene la lista resultado
        List<ModeloEntity> sameReferencia = query.getResultList();
        if (sameReferencia.isEmpty()) {
            return null;
        } else {
            return sameReferencia.get(0);
        }
    }

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
}
