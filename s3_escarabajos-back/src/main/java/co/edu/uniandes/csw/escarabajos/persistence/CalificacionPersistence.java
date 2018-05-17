/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.gaitan
 */
@Stateless
public class CalificacionPersistence {

    /**
     * LOGGER de la clase CalificacionPersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    /**
     * Contexto de la persistencia.
     */
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crea una calificacion nueva.
     *
     * @param entity objeto calificacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity entity) {
        LOGGER.info("Creando una calificacion nueva");
        em.persist(entity);
        LOGGER.info("Creando una calificacion nueva");
        return entity;
    }

    /**
     * Devuelve todas las calificaciones en la base de datos.
     *
     * @return lista de calificaciones
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.info("Consultando todas las calificaciones");
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }

    /**
     * Devuelve una calificacion especifica.
     *
     * @param id calificacion buscada
     * @return calificacion encontrada
     */
    public CalificacionEntity find(Long id) {
        return em.find(CalificacionEntity.class, id);
    }

    /**
     * Actualiza una calificacion especifica.
     *
     * @param entity calificacion que se va a actualizar
     * @return calificacion actualizada
     */
    public CalificacionEntity update(CalificacionEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina una calificacion especifica.
     *
     * @param entity calificacion que se va a eliminar
     */
    public void delete(CalificacionEntity entity) {
        em.remove(entity);
    }

    /**
     * Elimina una calificacion especifica por id.
     *
     * @param id id de la calificacion que se va a eliminar
     */
    public void delete(Long id) {
        em.remove(find(id));
    }

    /**
     * Devuelve todas las calificaciones de un modelo especifico.
     *
     * @param modeloId modelo especifico
     * @return lista de calificaciones
     */
    public List<CalificacionEntity> getCalificacionesPorModelo(Long modeloId) {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.modelo.id = :modeloid)", CalificacionEntity.class);
        q.setParameter("modeloid", modeloId);
        List<CalificacionEntity> results = q.getResultList();
        return results;
    }

    /**
     * Devuelve todas las calificaciones de un cliente especifico.
     *
     * @param clienteId cliente especifico
     * @return lista de clasificaciones
     */
    public List<CalificacionEntity> getCalificacionesPorCliente(Long clienteId) {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.cliente.id = :clienteid)", CalificacionEntity.class);
        q.setParameter("clienteid", clienteId);
        List<CalificacionEntity> results = q.getResultList();
        return results;
    }

    /**
     * Devuelve todas las calificaciones de un modelo y cliente especificos.
     *
     * @param clienteId cliente especifico
     * @param modeloId modelo especifico
     * @return lista de calificaciones
     */
    public List<CalificacionEntity> getCalificacionesPorClienteAndModelo(Long clienteId, Long modeloId) {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where p.cliente.id = :clienteid and p.modelo.id = :modeloid", CalificacionEntity.class);
        q.setParameter("clienteid", clienteId);
        q.setParameter("modeloid", modeloId);
        List<CalificacionEntity> results = q.getResultList();
        return results;
    }
}
