/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @modelo Andres
 */
@Stateless
public class ModeloLogic {

    private static final Logger LOGGER = Logger.getLogger(ModeloLogic.class.getName());

    @Inject
    private ModeloPersistence persistence;

    @Inject
    private ItemLogic itemLogic;

    @Inject
    private AccesorioLogic accLogic;

    //@Inject
    //private BicicletaLogic biciLogic;
    /**
     * Obtiene la lista de los registros de Modelo.
     *
     * @return Colección de objetos de ModeloEntity.
     */
    public List<ModeloEntity> getModelos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los modelos");
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Modelo a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ModeloEntity con los datos del Modelo consultado.
     */
    public ModeloEntity getModelo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Modelo en la base de datos.
     *
     * @param entity Objeto de ModeloEntity con los datos nuevos
     * @return Objeto de ModeloEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     * si ya existe el modelo
     */
    public ModeloEntity createModelo(ModeloEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un modelo ");
        ModeloEntity modeloEntity = getModelo(entity.getId());
        if (modeloEntity != null) {
            throw new BusinessLogicException("El modelo ya existe!");
        }
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Modelo.
     *
     * @param id
     * @param entity Instancia de ModeloEntity con los nuevos datos.
     * @return Instancia de ModeloEntity con los datos actualizados.
     */
    public ModeloEntity updateModelo(Long id, ModeloEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un modelo ");
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Modelo de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteModelo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un modelo ");
        deleteItems(id);
        persistence.delete(id);
    }

    /**
     * Agregar un item al modelo
     *
     * @param item El item a guardar
     * @param modeloId El id de el modelo en el cual se va a guardar el item.
     * @return El item que fue agregado a el modelo.
     */
    public ItemEntity addItem(ItemEntity item, Long modeloId) throws BusinessLogicException {
        ModeloEntity modeloEntity = getModelo(modeloId);
        if (modeloEntity == null) {
            throw new BusinessLogicException("El modelo no existe!");
        }
        modeloEntity.getItems().add(item);
        return item;
    }

    /**
     * Borrar un item de un modelo
     *
     * @param itemId El item que se desea borrar de el modelo.
     * @param modeloId El modelo de el cual se desea eliminar.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException si no encuentra el item
     */
    public void removeItem(Long itemId, Long modeloId) throws BusinessLogicException {
        ModeloEntity modeloEntity = getModelo(modeloId);
        ItemEntity item = itemLogic.getItem(itemId);
        if (item == null) {
            throw new BusinessLogicException("El item no existe!");
        }
        modeloEntity.getItems().remove(item);
        if (item instanceof BicicletaEntity) {
            //biciLogic.deleteBicicleta((BicicletaEntity)item);
        } else if (item instanceof AccesorioEntity) {
            accLogic.deleteAccesorio((AccesorioEntity) item);
        }
    }
     /**
      * Borra un item conociendo su entity y el de su modelo
      * @param item item a borrar
      * @param modelo modelo al cual se le borrara el item
      * OBS: este metodo hace lo mismo que el anterior pero es mas eficiente (No vuelve a buscar los entities)
      */
    private void removeItemPriv(ItemEntity item,ModeloEntity modelo) {
        modelo.getItems().remove(item);
        if (item instanceof BicicletaEntity) {
            //biciLogic.deleteBicicleta((BicicletaEntity)item);
        } else if (item instanceof AccesorioEntity) {
            accLogic.deleteAccesorio((AccesorioEntity) item);
        }
    }
    

    /**
     * Retorna un item asociado a un modelo
     *
     * @param modeloId El id del modelo a buscar.
     * @param itemId El id del item a buscar
     * @return El item encontrado dentro del modelo.
     * @throws BusinessLogicException Si el item no se encuentra en el modelo
     */
    public ItemEntity getItem(Long modeloId, Long itemId) throws BusinessLogicException {
        ModeloEntity modeloEntity = getModelo(modeloId);
        if (modeloEntity == null) {
            throw new BusinessLogicException("El modelo no existe!");
        }
        List<ItemEntity> items = itemLogic.getItemsModelo(modeloId);
        ItemEntity item = itemLogic.getItem(itemId);
        int index = items.indexOf(item);
        if (index >= 0) {
            return items.get(index);
        }
        throw new BusinessLogicException("El item no está asociado a el modelo");

    }

    /**
     * Obtiene una colección de instancias de ItemEntity asociadas a una
     * instancia de Modelo
     *
     * @param modeloId Identificador de la instancia de Modelo
     * @return Colección de instancias de ItemEntity asociadas a la instancia de
     * Modelo
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException si no existe el modelo
     *
     */
    public List<ItemEntity> listItems(Long modeloId) throws BusinessLogicException {
        return itemLogic.getItemsModelo(modeloId);
    }

    /**
     * Borra todos los items de un modelo que entra por parametro
     *
     * @param modeloId el modelo al cual se le borraran todos los items
     */
    public void deleteItems(Long modeloId) {
        ModeloEntity modeloEntity = getModelo(modeloId);
        for (ItemEntity item : modeloEntity.getItems()) {
                removeItemPriv(item, modeloEntity);
        }
    }
}
