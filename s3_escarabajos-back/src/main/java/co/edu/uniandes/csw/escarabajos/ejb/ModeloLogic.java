/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

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
     */
    public ModeloEntity createModelo(ModeloEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un modelo ");
        
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Modelo.
     *
     * @param entity Instancia de ModeloEntity con los nuevos datos.
     * @return Instancia de ModeloEntity con los datos actualizados.
     */
    public ModeloEntity updateModelo(Long id,ModeloEntity entity) {
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
        persistence.delete(id);
    }

     /**
     * Agregar un item al modelo
     *
     * @param itemId El id item a guardar
     * @param modeloId El id de el modelo en el cual se va a guardar el
     * item.
     * @return El item que fue agregado a el modelo.
     */
    public ItemEntity addItem(Long itemId, Long modeloId) {
        ModeloEntity modeloEntity = getModelo(modeloId);
        ItemEntity itemEntity = itemLogic.getItem(itemId);
        itemEntity.setModelo(modeloEntity);
        return itemEntity;
    }

    /**
     * Borrar un item de un modelo
     *
     * @param itemId El item que se desea borrar de el modelo.
     * @param modeloId El modelo de el cual se desea eliminar.
     */
    public void removeItem(Long itemId, Long modeloId) {
        ModeloEntity modeloEntity = getModelo(modeloId);
        ItemEntity item = itemLogic.getItem(itemId);
        item.setModelo(null);
        modeloEntity.getItems().remove(item);
    }

    /**
     * Remplazar items de un modelo
     *
     * @param items Lista de items que serán los de el modelo.
     * @param modeloId El id de el modelo que se quiere actualizar.
     * @return La lista de items actualizada.
     */
    public List<ItemEntity> replaceItems(Long modeloId, List<ItemEntity> items) {
        ModeloEntity modelo = getModelo(modeloId);
        List<ItemEntity> itemList = itemLogic.getItems();
        for (ItemEntity item : itemList) {
            if (items.contains(item)) {
                item.setModelo(modelo);
            } else if (item.getModelo() != null && item.getModelo().equals(modelo)) {
                item.setModelo(null);
            }
        }
        modelo.setItems(items);
        return items;
    }

    /**
     * Retorna todos los items asociados a un modelo
     *
     * @param modeloId El ID del modelo buscado
     * @return La lista de items de el modelo
     */
    public List<ItemEntity> getItems(Long modeloId) {
        return getModelo(modeloId).getItems();
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
        List<ItemEntity> items = getModelo(modeloId).getItems();
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
     *
     */
    public List<ItemEntity> listItems(Long modeloId) {
        return getModelo(modeloId).getItems();
    }
}
