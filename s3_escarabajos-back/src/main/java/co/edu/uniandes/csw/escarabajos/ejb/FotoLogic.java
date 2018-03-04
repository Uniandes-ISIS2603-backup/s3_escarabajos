package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.FotoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de foto.
 *
 * @author Andres
 */
@Stateless
public class FotoLogic {

    public static final String RECLAMO = "Reclamo";
    public static final String ITEM = "Item";
    private static final Logger LOGGER = Logger.getLogger(FotoLogic.class.getName());

    @Inject
    private FotoPersistence persistence;

    @Inject
    private ItemLogic itemLogic;

    @Inject
    private ReclamoLogic reclamoLogic;

    /**
     * Obtiene la lista de los registros de fotos que pertenecen a un
     * item/Reclamo.
     *
     * @param id id del Item/Reclamo el cual es padre de las fotos.
     * @param tipo RECLAMO o ITEM dependiendo delo que se quiera buscar
     * @return Colección de objetos de FotoEntity.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     * Error cuando el item no tiene fotos.
     */
    public List<FotoEntity> getFotos(Long id, String tipo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos las fotos");
        List<FotoEntity> respuesta = null;
        if (tipo.equals(ITEM)) {
            ItemEntity item = itemLogic.getItem(id);
            if (item==null) {
                throw new BusinessLogicException("El item no existe");
            }
            if (item.getAlbum() == null) {
                throw new BusinessLogicException("El item que consulta aún no tiene fotos");
            }
            if (item.getAlbum().isEmpty()) {
                throw new BusinessLogicException("El item que consulta aún no tiene fotos");
            }
            respuesta = item.getAlbum();
        } else if (tipo.equals(RECLAMO)) {
            ReclamoEntity reclamo = reclamoLogic.find(id);
            if (reclamo==null) {
                throw new BusinessLogicException("El reclamo no existe");
            }
            if (reclamo.getAlbum() == null) {
                throw new BusinessLogicException("El reclamo que consulta aún no tiene fotos");
            }
            if (reclamo.getAlbum().isEmpty()) {
                throw new BusinessLogicException("El reclamo que consulta aún no tiene fotos");
            }
            respuesta = reclamo.getAlbum();
        }
        return respuesta;
    }

    /**
     * Obtiene los datos de una instancia de foto a partir de su ID. La
     * existencia del elemento padre item/reclamo se debe garantizar.
     *
     * @param id El id del item/reclamo buscado
     * @param fotoid Identificador de la foto a consultar
     * @param tipo RECLAMO o ITEM dependiendo delo que se quiera buscar
     * @return Instancia de FotoEntity con los datos de la foto consultada.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException si no encuentra el item
     *
     */
    public FotoEntity getFoto(Long id, Long fotoid, String tipo) throws BusinessLogicException {
        if (tipo.equals(ITEM)) {
            ItemEntity item = itemLogic.getItem(id);
            if (item==null) {
                throw new BusinessLogicException("El item no existe");
            }
            return persistence.findInItem(id, fotoid);
        } else if (tipo.equals(RECLAMO)) {
            ReclamoEntity reclamo = reclamoLogic.find(id);
            if (reclamo==null) {
                throw new BusinessLogicException("El reclamo no existe");
            }
            return persistence.findInReclamo(id, fotoid);
        }
        return null;
    }

    /**
     * Se encarga de crear una foto en la base de datos.
     *
     * @param entity Objeto de FotoEntity con los datos nuevos
     * @param id id del Item/Reclamo el cual sera padre de la nueva foto.
     * @param tipo RECLAMO o ITEM dependiendo delo que se quiera crear
     * @return Objeto de FotoEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException si no encuentra el item
     */
    public FotoEntity createFoto(Long id, FotoEntity entity, String tipo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de crear foto");
        if (tipo.equals(ITEM)) {
            ItemEntity item = itemLogic.getItem(id);
            if (item==null) {
                throw new BusinessLogicException("El item no existe");
            }
            entity.setItem(item);
        } else if (tipo.equals(RECLAMO)) {
            ReclamoEntity reclamo = reclamoLogic.find(id);
            if (reclamo==null) {
                throw new BusinessLogicException("El reclamo no existe");
            }
            entity.setReclamo(reclamo);
        }

        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de la Foto.
     *
     * @param entity Instancia de FotoEntity con los nuevos datos.
     * @param id id del item/reclamo el cual sera padre de la foto actualizada.
     * @param tipo RECLAMO o ITEM dependiendo delo que se quiera actualizar
     * @return Instancia de FotoEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException si no encuentra el item
     */
    public FotoEntity updateFoto(Long id, FotoEntity entity, String tipo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar foto");
        if (tipo.equals(ITEM)) {
            ItemEntity item = itemLogic.getItem(id);
            if (item==null) {
                throw new BusinessLogicException("El item no existe");
            }
            entity.setItem(item);
        } else if (tipo.equals(RECLAMO)) {
            ReclamoEntity reclamo = reclamoLogic.find(id);
            if (reclamo==null) {
                throw new BusinessLogicException("El reclamo no existe");
            }
            entity.setReclamo(reclamo);
        }
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de foto de la base de datos.
     *
     * @param old FotoEntity a eliminar.
     *
     */
    public void deleteFoto(FotoEntity old){
        LOGGER.info("Inicia proceso de borrar foto");
        persistence.delete(old.getId());
    }
}
