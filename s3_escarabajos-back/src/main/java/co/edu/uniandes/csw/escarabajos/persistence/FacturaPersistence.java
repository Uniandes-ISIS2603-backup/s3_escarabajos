package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.carreno
 */
@Stateless
public class FacturaPersistence {

    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto factura que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando una factura nueva");
        em.persist(entity);
        LOGGER.info("Se creo una factura nueva");
        return entity;
    }

    /**
     * Devuelve todas las facturas.
     *
     * @return lista de facturas
     */
    public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todas las facturas");
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }

    /**
     * Encuentra una factura especifica
     *
     * @param id de la factura 
     * @return factura
     */
    public FacturaEntity find(Long id) {
        return em.find(FacturaEntity.class, id);
    }

    /**
     * Modifica los datos de una factura existente.
     *
     * @param entity datos nuevos de la factura
     * @return factura con nuevos datos.
     */
    public FacturaEntity update(FacturaEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina una factura
     *
     * @param id factura a eliminar
     */
    public void delete(Long id) {
        FacturaEntity factura = find(id);
        em.remove(factura);
    }
    
    /**
     * Encuentra las facturas de un cliente especifico
     *
     * @param idCliente id del cliente 
     * @return lista de facturas
     */
    public List<FacturaEntity> findFacturasByClienteId(Long idCliente){
        
        LOGGER.log(Level.INFO, "Consultando la factura del cliente con id= {0}", idCliente);
        TypedQuery<FacturaEntity> q = em.createQuery("select u from FacturaEntity u where u.cliente.id = :id", FacturaEntity.class);
        
        q = q.setParameter("id", idCliente);
        
        List<FacturaEntity> lista = q.getResultList();
        
        return lista;
    }
}
