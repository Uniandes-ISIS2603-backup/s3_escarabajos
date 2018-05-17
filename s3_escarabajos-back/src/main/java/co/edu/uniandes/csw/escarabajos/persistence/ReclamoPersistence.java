/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
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
public class ReclamoPersistence {

    /**
     * LOGGER de la clase ReclamoPersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(ReclamoPersistence.class.getName());

    /**
     * Contexto de la persistencia.
     */
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crea un nuevo reclamo.
     *
     * @param entity objeto reclamo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReclamoEntity create(ReclamoEntity entity) {
        LOGGER.info("Creando un reclamo nueva");
        em.persist(entity);
        LOGGER.info("Creando un reclamo nueva");
        return entity;
    }

    /**
     * Devuelve todos los reclamos de la base de datos.
     *
     * @return lista de reclamos
     */
    public List<ReclamoEntity> findAll() {
        LOGGER.info("Consultando todos los reclamos");
        TypedQuery query = em.createQuery("select u from ReclamoEntity u", ReclamoEntity.class);
        return query.getResultList();
    }

    /**
     * Devuelve un reclamo especifico.
     *
     * @param id el id del reclamo
     * @return el reclamo encontrado
     */
    public ReclamoEntity find(Long id) {
        return em.find(ReclamoEntity.class, id);
    }

    /**
     * Actualiza un reclamo espcifico.
     *
     * @param entity reclamo con los datos a actualizar
     * @return reclamo actualizado
     */
    public ReclamoEntity update(ReclamoEntity entity) {
        return em.merge(entity);
    }

    /**
     * Devuelve los reclamos de una factura especifica.
     *
     * @param facturaId factura especifica
     * @return reclamo encontrado
     */
    public List<ReclamoEntity> getReclamosByFactura(Long facturaId) {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.factura.id = :facturaId)", ReclamoEntity.class);
        q.setParameter("facturaId", facturaId);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }

    /**
     * Devuelve los reclamos que estan en proceso
     *
     * @return lista de reclamos
     */
    public List<ReclamoEntity> getReclamosEnProceso() {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.enProceso = :proceso)", ReclamoEntity.class);
        q.setParameter("proceso", true);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }

    /**
     * Devuelve la lista de reclamos terminados.
     *
     * @return lista de reclamos
     */
    public List<ReclamoEntity> getReclamosTerminados() {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.enProceso = :proceso)", ReclamoEntity.class);
        q.setParameter("proceso", false);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }

    /**
     * Devuelve los reclamos de una factura especifica.
     *
     * @param facturaId factura especifica
     * @return reclamos encontrado
     */
    public ReclamoEntity getReclamoPorFactura(Long facturaId) {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.factura.id = :facturaId)", ReclamoEntity.class);
        q.setParameter("facturaId", facturaId);
        List<ReclamoEntity> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Devuelve los reclamos de un cliente especifico.
     *
     * @param clienteId cliente especifico
     * @return lista de reclamos
     */
    public List<ReclamoEntity> getReclamoPorCliente(Long clienteId) {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.cliente.id = :clienteId)", ReclamoEntity.class);
        q.setParameter("clienteId", clienteId);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }
}
