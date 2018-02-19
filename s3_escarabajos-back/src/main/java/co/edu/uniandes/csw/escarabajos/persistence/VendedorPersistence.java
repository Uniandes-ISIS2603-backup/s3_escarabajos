/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.beltran
 */
@Stateless
public class VendedorPersistence {
     private static final Logger LOGGER = Logger.getLogger(VendedorPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto vendedor que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public VendedorEntity create(VendedorEntity entity) {
        LOGGER.info("Creando un vendedor nuevo");
        em.persist(entity);
        LOGGER.info("Creando una vendedor nuevo");
        return entity;
    }
    
    /**
     * Busca si hay algun vendedor con el nombre que se envía de argumento
     *
     * @param name: Nombre del vendedor que se está buscando
     * @return null si no existe ningun vendedor con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public VendedorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando vendedor por nombre ", name);

        // Se crea un query para buscar vendedores con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From VendedorEntity e where e.name = :name", VendedorEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<VendedorEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public List<VendedorEntity> findAll() {
        LOGGER.info("Consultando todos los vendedores");
        TypedQuery query = em.createQuery("select u from VendedorEntity u", VendedorEntity.class);
        return query.getResultList();
    }

    public VendedorEntity find(Long id) {
        return em.find(VendedorEntity.class, id);
    }

    public VendedorEntity update(VendedorEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(Long id) {
         LOGGER.log(Level.INFO, "Borrando vendedor con id={0}", id);
        VendedorEntity entity = em.find(VendedorEntity.class, id);
        em.remove(entity);
    }
}
