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
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres
 */
@Stateless
public class ModeloLogic {

    public static final String BICICLETA = "Bicicleta";
    public static final String ACCESORIO = "Accesorio";
    public static final String BICICLETAUSADA = "BicicletaUsada";
    public static final String MODELONOEXISTE = "El modelo no existe!";
    public static final String MODELOEXISTE = "El modelo ya existe!";

    private static final Logger LOGGER = Logger.getLogger(ModeloLogic.class.getName());

    @Inject
    private ModeloPersistence persistence;

    @Inject
    private ItemLogic itemLogic;

    @Inject
    private AccesorioLogic accLogic;

    @Inject
    private BicicletaLogic biciLogic;

    /**
     * Obtiene la lista de los registros de Modelo.
     *
     * @param pagina pagina de modelos 
     * @param numModelos numero de modelos por pagina
     * @return Colección de objetos de ModeloEntity.
     */
    public List<ModeloEntity> getModelos(Integer pagina, Integer numModelos) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los modelos");
        List<ModeloEntity> lista = persistence.findAll(pagina,numModelos);
        LOGGER.log(Level.INFO, "Finaliza proceso de consultar todos los modelos");
        return lista;
    }

    /**
     * Retorna la referencia de un item que entre por paramentro
     *
     * @param entity al que se le buscara la referencia del modelo.
     * @return referencia del modelo al que le pertenece el item.
     */
    public String getReferenciaItem(ItemEntity entity) {
        return itemLogic.getReferenciaItem(entity);
    }

    /**
     * Obtiene los datos de una instancia de Modelo a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ModeloEntity con los datos del Modelo consultado.
     */
    public ModeloEntity getModelo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo con id = {0}", id);
        ModeloEntity respuesta = persistence.find(id);
        LOGGER.log(Level.INFO, "Finaliza proceso de consultar un modelo con id = {0}", id);
        return respuesta;
    }

    /**
     * Se encarga de crear un Modelo en la base de datos.
     *
     * @param entity Objeto de ModeloEntity con los datos nuevos
     * @return Objeto de ModeloEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si ya existe el modelo
     */
    public ModeloEntity createModelo(ModeloEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un modelo ");
        ModeloEntity modeloEntity = getModelo(entity.getId());
        if (modeloEntity != null) {
            throw new BusinessLogicException(MODELOEXISTE);
        }
        Date dia = new Date();
        entity.setCreacion(dia);
        modeloEntity = persistence.findByReferencia(entity.getReferencia());
        if (modeloEntity != null) {
            throw new BusinessLogicException(MODELOEXISTE);
        }
        LOGGER.log(Level.INFO, "Finaliza proceso de crear un modelo ");
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Modelo.
     *
     * @param id del modelo
     * @param entity Instancia de ModeloEntity con los nuevos datos.
     * @return Instancia de ModeloEntity con los datos actualizados.
     * @throws BusinessLogicException si se intenta cambiar el tipo
     */
    public ModeloEntity updateModelo(Long id, ModeloEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un modelo ");
        ModeloEntity antes = getModelo(id);
        if (!antes.getTipoModelo().equals(entity.getTipoModelo())) {
            throw new BusinessLogicException("No se le puede cambiar el tipo a un modelo!");
        }
        LOGGER.log(Level.INFO, "Finaliza proceso de actualizar un modelo ");
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Modelo de la base de datos.
     *
     * @param id del modelo
     * @throws BusinessLogicException Por reglas de negocio
     */
    public void deleteModelo(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un modelo ");
        ModeloEntity modeloEntity = getModelo(id);
        if (modeloEntity == null) {
            throw new BusinessLogicException(MODELONOEXISTE);
        }
        List<ItemEntity> items = new ArrayList<>();
        for (ItemEntity item : modeloEntity.getItems()) {
            if (item.getDisponible()) {
                items.add(itemLogic.comprarItem(item.getId()));
            }
        }
        modeloEntity.setItems(items);
        persistence.update(modeloEntity);
        LOGGER.log(Level.INFO, "Finaliza proceso de borrar un modelo ");
    }

    /**
     * Agregar un item al modelo (pre) el item es del mismo tipo que el
     * modelo.(pre)
     *
     * @param item El item a guardar
     * @param modeloId El id de el modelo en el cual se va a guardar el item.
     * @return El item que fue agregado a el modelo.
     * @throws BusinessLogicException por reglas de negocio
     */
    public ItemEntity addItem(ItemEntity item, Long modeloId) throws BusinessLogicException {
        ModeloEntity modeloEntity = getModelo(modeloId);
        if (modeloEntity == null) {
            throw new BusinessLogicException(MODELONOEXISTE);
        }
        List<ItemEntity> resp = modeloEntity.getItems();
        resp.add(item);
        modeloEntity.setItems(resp);
        persistence.update(modeloEntity);
        return item;
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
            throw new BusinessLogicException(MODELONOEXISTE);
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
     * @throws BusinessLogicException si no existe el modelo
     *
     */
    public List<ItemEntity> listItems(Long modeloId) throws BusinessLogicException {
        return itemLogic.getItemsModelo(modeloId);
    }

    /**
     * Borra un item y su relacion con un modelo
     *
     * @param itemsId item a borrar
     * @throws BusinessLogicException si el item o el modelo no existen.
     */
    public void removeItem(Long itemsId) throws BusinessLogicException {
        ItemEntity item = itemLogic.getItem(itemsId);
        if (item == null) {
            throw new BusinessLogicException("El item no existe");
        }
        ModeloEntity modelo = persistence.find(item.getModeloId());
        if (modelo == null) {
            throw new BusinessLogicException(MODELONOEXISTE);
        }
        List<ItemEntity> resp = modelo.getItems();
        resp.remove(item);
        modelo.setItems(resp);
        persistence.update(modelo);
        if (item instanceof BicicletaEntity) {
            biciLogic.deleteBicicleta(item.getId());
        } else if (item instanceof AccesorioEntity) {
            accLogic.deleteAccesorio(item.getId());
        }
    }
}
