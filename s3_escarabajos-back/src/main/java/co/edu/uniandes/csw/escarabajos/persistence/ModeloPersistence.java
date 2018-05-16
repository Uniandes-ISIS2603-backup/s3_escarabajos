/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
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
     * Crea un modelo en la base de datos
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
     * @param page pagina de modelos
     * @param maxRecords numero de modelos por pagina
     * @return una lista con todos losmodelos que encuentre en la base de datos,
     * "select u from ModeloEntity u" es como un "select * from ModeloEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ModeloEntity> findAll(Integer page, Integer maxRecords) {
        LOGGER.info("Consultando todos los modelos");
        // Se crea un query para buscar todos los modelos en la base de datos.
        TypedQuery query = em.createQuery("select u from ModeloEntity u ORDER BY u.calificacionMedia DESC", ModeloEntity.class);
          if (page != null && maxRecords != null) {
            query.setFirstResult((page
                    - 1) * maxRecords);
            query.setMaxResults(maxRecords);
        }
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
}
