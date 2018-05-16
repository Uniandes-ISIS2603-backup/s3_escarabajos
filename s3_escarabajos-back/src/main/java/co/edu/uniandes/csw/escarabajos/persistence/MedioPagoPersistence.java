/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
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
 * @author jp.carreno
 */
@Stateless
public class MedioPagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MedioPagoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Encuentra un medio de pago especifica
     *
     * @param id del medio de pago 
     * @return medio de pago
     */
    public MedioPagoEntity find(Long id) {
        return em.find(MedioPagoEntity.class, id);
    }

    /**
     * Devuelve todos los medios de pago.
     *
     * @return lista de medios de pago
     */
    public List<MedioPagoEntity> findAll() {
        LOGGER.info("Consultando todos los medios de pago");
        Query q = em.createQuery("select u from MedioPagoEntity u", MedioPagoEntity.class);
        return q.getResultList();
    }

    /**
     *
     * @param entity objeto medio de pago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MedioPagoEntity create(MedioPagoEntity entity) {
        LOGGER.info("Creando un medio de pago nuevo");
        em.persist(entity);
        LOGGER.info("MedioPago creado");
        return entity;
    }

    /**
     * Modifica los datos de un medio de pago existente.
     *
     * @param entity datos nuevos del medio de pago
     * @return medio de pago con nuevos datos.
     */
    public MedioPagoEntity update(MedioPagoEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina un medio de pago
     *
     * @param id medio de pago a eliminar
     */
    public void delete(Long id) {
        MedioPagoEntity factura = find(id);
        em.remove(factura);
    }
    
    /**
     * Encuentra los medios de pago de un cliente especifico
     *
     * @param idCliente id del cliente 
     * @return lista de medios de pago
     */
    public List<MedioPagoEntity> findMediosByClienteId(Long idCliente){
        
        LOGGER.log(Level.INFO, "Consultando el medio de pago del cliente con id= {0}", idCliente);
        TypedQuery<MedioPagoEntity> q = em.createQuery("select u from MedioPagoEntity u where u.cliente.id = :id", MedioPagoEntity.class);
        
        q = q.setParameter("id", idCliente);
        
        List<MedioPagoEntity> lista = q.getResultList();
        
        return lista;
    }
}
