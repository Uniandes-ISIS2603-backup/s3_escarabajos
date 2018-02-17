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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres
 */
public class ModeloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ModeloPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto modelo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ModeloEntity create(ModeloEntity entity) {
        LOGGER.info("Creando un modelo nuevo");
        em.persist(entity);
        LOGGER.info("Creando un modelo nuevo");
        return entity;
    }

    /**
     * Busca si hay algun modelo con el nombre que se envía de argumento
     *
     * @param name: Nombre del modelo que se está buscando
     * @return null si no existe ningun modelo con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ModeloEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando city por nombre ", name);

        // Se crea un query para buscar modelos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ModeloEntity e where e.name = :name",ModeloEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ModeloEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public List<ModeloEntity> findAll() {
        LOGGER.info("Consultando todos los modelos");
        TypedQuery query = em.createQuery("select u from ModeloEntity u", ModeloEntity.class);
        return query.getResultList();
    }

    public ModeloEntity find(Long id) {
        return em.find(ModeloEntity.class, id);
    }

    public ModeloEntity update(ModeloEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(ModeloEntity entity) {
        em.remove(entity);
    }
}
