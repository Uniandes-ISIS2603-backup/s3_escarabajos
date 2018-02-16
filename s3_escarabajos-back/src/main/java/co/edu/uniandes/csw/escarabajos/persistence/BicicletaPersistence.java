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
     * Busca si hay alguna bicicleta con el nombre que se envía de argumento
     *
     * @param name: Nombre de la bicicleta que se está buscando
     * @return null si no existe ninguna bicicleta con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public BicicletaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando bicicleta por nombre ", name);

        // Se crea un query para buscar cities con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From BicicletaEntity e where e.name = :name", BicicletaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<BicicletaEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    public List<BicicletaEntity> findAll() {
        LOGGER.info("Consultando todas las cities");
        TypedQuery query = em.createQuery("select u from BicicletaEntity u", BicicletaEntity.class);
        return query.getResultList();
    }

    public BicicletaEntity find(Long id) {
        return em.find(BicicletaEntity.class, id);
    }

    public BicicletaEntity update(BicicletaEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(BicicletaEntity entity) {
        em.remove(entity);
    }
    
}
