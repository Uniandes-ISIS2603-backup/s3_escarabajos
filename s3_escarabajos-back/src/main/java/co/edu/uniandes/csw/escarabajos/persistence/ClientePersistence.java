/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
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
public class ClientePersistence {

    /**
     * LOGGER de la clase ClientePersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    /**
     * Contexto de la persistencia.
     */
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crea un nuevo cliente.
     *
     * @param entity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando un cliente nuevo");
        em.persist(entity);
        LOGGER.info("Creando una cliente nuevo");
        return entity;
    }

    /**
     * Devuelve si hay algun cliente con el nombre que se envía de argumento.
     *
     * @param name: Nombre del cliente que se está buscando
     * @return null si no existe ningun cliente con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ClienteEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando cliente por nombre ", name);

        // Se crea un query para buscar clientes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.nombre = :nombre", ClienteEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", name);
        // Se invoca el query se obtiene la lista resultado
        List<ClienteEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    /**
     * Devuelve todos los clientes en la base de datos.
     *
     * @return lista de clientes
     */
    public List<ClienteEntity> findAll() {
        LOGGER.info("Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    /**
     * Devuelve un cliente especifico.
     *
     * @param id cliente buscado
     * @return cliente encontrado
     */
    public ClienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando cliente con id={0}", id);
        return em.find(ClienteEntity.class, id);
    }

    /**
     * Actualiza un cliente especifico.
     *
     * @param entity cliente que se va a actualizar
     * @return cliente actualizado
     */
    public ClienteEntity update(ClienteEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina un cliente especifico.
     *
     * @param id cliente que se va a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando cliente con id={0}", id);
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }

    /**
     * Devuelve todos los vendedores en la base de datos.
     *
     * @return lista de vendedores
     */
    public List<ClienteEntity> findAllVendedores() {
        LOGGER.info("Consultando todos los clientes vendedores");
        TypedQuery query = em.createQuery("select u from ClienteEntity u where u.direccion IS NOT NULL", ClienteEntity.class);
        return query.getResultList();
    }
}
