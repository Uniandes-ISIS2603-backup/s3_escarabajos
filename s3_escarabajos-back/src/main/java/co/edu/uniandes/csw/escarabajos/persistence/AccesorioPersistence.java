/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateo
 */
@Stateless
public class AccesorioPersistence {

    //--------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------
    /**
     * LOGGER de la clase AccesorioPersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(CarritoPersistence.class.getName());

    /**
     * Contexto de la persistencia.
     */
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    //--------------------------------------------------------------
    // Metodos CRUD
    //--------------------------------------------------------------
    /**
     * Devuelve un accesorio especifico.
     *
     * @param id el id del accesorio buscado
     * @return el accesorio encontrado
     */
    public AccesorioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando accesorio con id={0}", id);
        return em.find(AccesorioEntity.class, id);
    }

    /**
     * Devuelve la lista de todos los accesorios en la base de datos.
     *
     * @return lista de accesorios
     */
    public List<AccesorioEntity> findAll() {
        LOGGER.info("Consultando todos los accesorios");
        Query q = em.createQuery("select u from AccesorioEntity u");
        return q.getResultList();
    }

    /**
     * Crea un accesorio nuevo.
     *
     * @param entity el accesorio que se desea agregar
     * @return el accesorio creado
     */
    public AccesorioEntity create(AccesorioEntity entity) {
        LOGGER.info("Creando un accesorio nuevo");
        em.persist(entity);
        LOGGER.info("Accesorio creado");
        return entity;
    }

    /**
     * Actualiza un accesorio especifico.
     *
     * @param entity el accesorio con la nueva informacion
     * @return el accesorio actualizado.
     */
    public AccesorioEntity update(AccesorioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando accesorio con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Elimina un accesorio especifico.
     *
     * @param id el id del accesorio que se desea eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando accesorio con id={0}", id);
        AccesorioEntity entity = em.find(AccesorioEntity.class, id);
        em.remove(entity);
    }

    /**
     * Devuelve una lista de accesorios por un modelo especifico.
     *
     * @param modeloId modelo especifico
     * @return el accesorio encontrado
     */
    public List<AccesorioEntity> findByModelo(Long modeloId) {
        LOGGER.log(Level.INFO, "Consultando las accesorios por modelo ", modeloId);
        TypedQuery query = em.createQuery("Select e From AccesorioEntity e where e.modeloId = :modeloId", AccesorioEntity.class);
        query = query.setParameter("modeloId", modeloId);
        return query.getResultList();
    }
}
