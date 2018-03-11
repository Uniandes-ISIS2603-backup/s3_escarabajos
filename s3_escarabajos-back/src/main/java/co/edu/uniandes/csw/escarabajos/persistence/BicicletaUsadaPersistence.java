/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author c.santacruza
 */
@Stateless
public class BicicletaUsadaPersistence {

    public static final Logger LOGGER = Logger.getLogger(BicicletaUsadaPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crea una nueva bicicleta usada.
     *
     * @param entity
     * @return bicicleta usada
     */
    public BicicletaUsadaEntity create(BicicletaUsadaEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Encuentra una bicicleta usada asociada a un vendedor.
     *
     * @param idVendedor
     * @param idBici
     * @return bicicleta usada
     */
    public BicicletaUsadaEntity find(Long idVendedor, Long idBici) {
        TypedQuery<BicicletaUsadaEntity> q = em.createQuery("select p from BicicletaUsadaEntity p where (p.vendedor.id = :vendedorid) and (p.id = :biciid) and p.usada = 1", BicicletaUsadaEntity.class);
        q.setParameter("vendedorid", idVendedor);
        q.setParameter("biciid", idBici);
        List<BicicletaUsadaEntity> results = q.getResultList();
        BicicletaUsadaEntity bici = null;
        if (results == null) {
            bici = null;
        } else if (results.isEmpty()) {
            bici = null;
        } else if (results.size() >= 1) {
            bici = results.get(0);
        }

        return bici;
    }

    /**
     * Borra una bicicleta usada asociacda a un vendedor.
     *
     * @param id
     */
    public void delete(Long id) {
        BicicletaUsadaEntity bici = em.find(BicicletaUsadaEntity.class, id);
        em.remove(bici);
    }

    /**
     * Encuentra todas las bicicletas usadas con el mismo estado.
     *
     * @param estado
     * @return bicicleta usada con estado "estado".
     */
    public BicicletaUsadaEntity findEstado(String estado) {
        TypedQuery query = em.createQuery("Select e From BicicletaUsadaEntity e where e.estado = :estado and e.usada = 1", BicicletaUsadaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("estado", estado);
        // Se invoca el query se obtiene la lista resultado
        List<BicicletaUsadaEntity> sameEstado = query.getResultList();
        if (sameEstado.isEmpty()) {
            return null;
        } else {
            return sameEstado.get(0);
        }
    }

    /**
     * Devuelve todas las bicicletas de un vendedor.
     *
     * @param idVendedor
     * @return lista bicicletas
     */
    public List<BicicletaUsadaEntity> findAllBicis(Long idVendedor) {
        LOGGER.info("Consultando todas las bicicletas del vendedor con id");
        TypedQuery query = em.createQuery("select u from BicicletaUsadaEntity u where (u.vendedor.id  = :idVendedor) and u.usada = 1 ", BicicletaUsadaEntity.class);
        query.setParameter("idVendedor", idVendedor);
        return query.getResultList();
    }

    /**
     * Modifica los datos de una bicicleta usada.
     *
     * @param bici
     * @return bicicleta usada con nuevos datos.
     */
    public BicicletaUsadaEntity update(BicicletaUsadaEntity bici) {
        return em.merge(bici);
    }
}
