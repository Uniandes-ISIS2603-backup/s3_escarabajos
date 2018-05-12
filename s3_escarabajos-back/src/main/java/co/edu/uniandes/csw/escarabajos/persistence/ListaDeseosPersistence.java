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
    private static final Logger LOGGER = Logger.getLogger(ListaDeseosPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    //--------------------------------------------------------------
    // Metodos CRUD
    //--------------------------------------------------------------
    /**
     * encuentra un listadeseos
     *
     * @param clienteId el id del cliente
     * @return el listadeseos
     */
    public ListaDeseosEntity find(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando listadeseos con id={0}", clienteId);
        return em.find(ListaDeseosEntity.class, clienteId);
    }

    /**
     * crea un listadeseos
     *
     * @param entity el listadeseos a crear
     * @return el listadeseos
     */
    public ListaDeseosEntity create(ListaDeseosEntity entity) {
        LOGGER.info("Creando un listadeseos nuevo");
        em.persist(entity);
        LOGGER.info("ListaDeseos creado");
        return entity;
    }

    /**
     * actualiza un listadeseos
     *
     * @param entity el listadeseos a actualizar
     * @return el listadeseos
     */
    public ListaDeseosEntity update(ListaDeseosEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando listadeseos con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * borra el listadeseos
     *
     * @param id el id del listadeseos a borrar
     */
    public void deleteListaDeseos(Long id) {

        LOGGER.log(Level.INFO, "Borrando listadeseos con id={0}", id);
        ListaDeseosEntity entity = em.find(ListaDeseosEntity.class, id);
        em.remove(entity);
    }

    /**
     *
     * @param clienteId
     * @return
     */
    public ListaDeseosEntity findListaDeseosByClienteId(Long clienteId) {

        LOGGER.log(Level.INFO, "Consultando el listadeseos del cliente con id= {0}", clienteId);
        TypedQuery<ListaDeseosEntity> q = em.createQuery("select u from ListaDeseosEntity u where u.cliente.id = :clienteId", ListaDeseosEntity.class);
        
        q = q.setParameter("clienteId", clienteId);

        List<ListaDeseosEntity> lista =q.getResultList();
        
        if (lista.isEmpty()) {

            return null;
            
        } else {
            return lista.get(0);
        }
    }
}
