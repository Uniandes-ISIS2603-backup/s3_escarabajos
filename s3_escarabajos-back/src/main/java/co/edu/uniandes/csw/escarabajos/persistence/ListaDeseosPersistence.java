/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mateo
 */
@Stateless
public class ListaDeseosPersistence {

    //--------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------
    /**
     * LOGGER de la clase ListaDeseosPersistence.
     */
    private static final Logger LOGGER = Logger.getLogger(ListaDeseosPersistence.class.getName());

    /**
     * Contexto de la persistencia.
     */
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    //--------------------------------------------------------------
    // Metodos CRUD
    //--------------------------------------------------------------
    /**
     * Devuelve una lista de deseos especifica.
     *
     * @param clienteId el id del cliente
     * @return la lista de deseos encontrada
     */
    public ListaDeseosEntity find(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando listadeseos con id={0}", clienteId);
        return em.find(ListaDeseosEntity.class, clienteId);
    }

    /**
     * Crea una nueva lista de deseos.
     *
     * @param entity la lista de deseos que se va a crear
     * @return la lista de deseos creada
     */
    public ListaDeseosEntity create(ListaDeseosEntity entity) {
        LOGGER.info("Creando un listadeseos nuevo");
        em.persist(entity);
        LOGGER.info("ListaDeseos creado");
        return entity;
    }

    /**
     * Actualiza una lista de deseos especifica.
     *
     * @param entity la lista de deseos con los datos a actualizar
     * @return la lista de deseos actualizada
     */
    public ListaDeseosEntity update(ListaDeseosEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando listadeseos con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Elimina una lista de deseos especifica.
     *
     * @param id el id de la lista de deseos a eliminar
     */
    public void deleteListaDeseos(Long id) {

        LOGGER.log(Level.INFO, "Borrando listadeseos con id={0}", id);
        ListaDeseosEntity entity = em.find(ListaDeseosEntity.class, id);
        em.remove(entity);
    }

    /**
     * Devuelve la lista de deseos de un cliente especifico.
     *
     * @param clienteId cliente especifico
     * @return lista de deseos encontrada
     */
    public ListaDeseosEntity findListaDeseosByClienteId(Long clienteId) {

        LOGGER.log(Level.INFO, "Consultando el listadeseos del cliente con id= {0}", clienteId);
        TypedQuery<ListaDeseosEntity> q = em.createQuery("select u from ListaDeseosEntity u where u.cliente.id = :clienteId", ListaDeseosEntity.class);

        q = q.setParameter("clienteId", clienteId);

        List<ListaDeseosEntity> lista = q.getResultList();

        if (lista.isEmpty()) {

            return null;

        } else {
            return lista.get(0);
        }
    }
}
