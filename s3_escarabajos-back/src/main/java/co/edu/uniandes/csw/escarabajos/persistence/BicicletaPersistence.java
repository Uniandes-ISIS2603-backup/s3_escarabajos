package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import java.util.List;
import java.util.logging.Level;
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
public class BicicletaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BicicletaPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto bicicleta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BicicletaEntity create(BicicletaEntity entity) {
        LOGGER.info("Creando una bicicleta nueva");
        em.persist(entity);
        LOGGER.info("Creando una bicicleta nueva");
        return entity;
    }

    /**
     * Devuelve todas las bicicletas.
     *
     * @return lista de bicicletas
     */
    public List<BicicletaEntity> findAll() {
        LOGGER.info("Consultando todas las bicicletas");
        TypedQuery query = em.createQuery("select u from BicicletaEntity u where u.usada = 0", BicicletaEntity.class);
        return query.getResultList();
    }

    /**
     * Encuentra una bicicleta especifica
     *
     * @param id
     * @return bicicleta
     */
    public BicicletaEntity find(Long id) {
        return em.find(BicicletaEntity.class, id);
    }

    /**
     * Modifica los datos de una bicicleta existente.
     *
     * @param entity
     * @return bicicleta con nuevos datos.
     */
    public BicicletaEntity update(BicicletaEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina una bicicleta
     *
     * @param id
     */
    public void delete(Long id) {
        BicicletaEntity bici = find(id);
        em.remove(bici);
    }

    /**
     * Devuelve todas las bicicletas de la tienda con el mismo modelo.
     *
     * @param modeloId
     * @return lista de bicicletas
     */
    public List<BicicletaEntity> findByModelo(Long modeloId) {
        LOGGER.log(Level.INFO, "Consultando las bicicletas por modelo ", modeloId);
        TypedQuery query = em.createQuery("Select e From BicicletaEntity e where e.modeloId = :modeloId and e.usada = 0", BicicletaEntity.class);
        query = query.setParameter("modeloId", modeloId);
        return query.getResultList();
    }
}
