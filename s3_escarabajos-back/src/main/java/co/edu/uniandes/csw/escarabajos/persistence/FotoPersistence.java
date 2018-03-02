
package co.edu.uniandes.csw.escarabajos.persistence;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Foto.
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos
 * SQL.
 * @author Andres
 */
@Stateless
public class FotoPersistence {

    private static final Logger LOGGER = Logger.getLogger(FotoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    /**
     * Crear una foto
     * 
     * Crea una nueva foto con la información recibida en la entidad.
     * @param entity La entidad que representa la nueva foto
     * @return  La entidad creada
     */
    public FotoEntity create(FotoEntity entity) {
        LOGGER.info("Creando una foto nueva");
        em.persist(entity);
        LOGGER.info("Foto creada");
        return entity;
    }

    /**
     * Actualizar una foto
     * 
     * Actualiza la entidad que recibe en la base de datos
     * @param entity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public FotoEntity update(FotoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando review con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Eliminar una Foto
     * 
     * Elimina la foto asociada al ID que recibe
     * @param id El ID de la foto que se desea borrar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando foto con id={0}", id);
        FotoEntity entity = em.find(FotoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Busca si hay alguna foto con el id que se envía de argumento
     *
     * @param id: id correspondiente a la foto buscada.
     * @return una foto.
     */
    public FotoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando foto con id={0}", id);
        return em.find(FotoEntity.class, id);
    }
    
    /**
     * Buscar una foto
     * 
     * Busca si hay alguna foto asociada a un item y con un ID específico
     * @param itemid El ID del item con respecto al cual se busca
     * @param fotoid El ID de la foto buscada
     * @return La foto encontrada o null. Nota: Si existe una o más fotos 
     * devuelve siempre la primera que encuentra
     */
    public FotoEntity findInItem(Long itemid, Long fotoid) {
        TypedQuery<FotoEntity> q = em.createQuery("select p from FotoEntity p where (p.item.id = :itemid) and (p.id = :fotoid)", FotoEntity.class);
        q.setParameter("itemid", itemid);
        q.setParameter("fotoid", fotoid);
        List<FotoEntity> results = q.getResultList();
        LOGGER.info(results.size()+"!!!!!!!!!!!");
        FotoEntity foto = null;
        if (results == null) {
            foto = null;
        } else if (results.isEmpty()) {
            foto = null;
        } else if (results.size() >= 1) {
            foto = results.get(0);
        }

        return foto;
    }

    /**
     * Buscar una foto
     * 
     * Busca si hay alguna foto asociada a un item y con un ID específico
     * @param reclamoid El ID del reclamo con respecto al cual se busca
     * @param fotoid El ID de la foto buscada
     * @return La foto encontrada o null. Nota: Si existe una o más fotos 
     * devuelve siempre la primera que encuentra
     */
    public FotoEntity findInReclamo(Long reclamoid, Long fotoid) {
        TypedQuery<FotoEntity> q = em.createQuery("select p from FotoEntity p where (p.reclamo.id = :reclamoid) and (p.id = :fotoid)", FotoEntity.class);
        q.setParameter("reclamoid", reclamoid);
        q.setParameter("fotoid", fotoid);
        List<FotoEntity> results = q.getResultList();
        FotoEntity foto = null;
        if (results == null) {
            foto = null;
        } else if (results.isEmpty()) {
            foto = null;
        } else if (results.size() >= 1) {
            foto = results.get(0);
        }

        return foto;
    }
}
