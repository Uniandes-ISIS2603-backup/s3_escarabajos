/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres
 */
@Stateless
public class ItemLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ItemLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de items de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ItemPersistence persistence;

    //@Inject
    //private AccesorioLogic accLogic;
    
    //@Inject
    //private BicicletaLogic biciLogic;
    /**
     * Devuelve todos los items que hay en la base de datos.
     * @return Lista de entidades de tipo item.
     */
    public List<ItemEntity> getItems() {
        LOGGER.info("Inicia proceso de consultar todos los items");
        List<ItemEntity> items = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los items");
        return items;
    }

    /**
     * Busca un item por ID
     * @param id El id del item a buscar
     * @return El item encontrado, null si no lo encuentra.
     */
    public ItemEntity getItem(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar item con id={0}", id);
        ItemEntity item = persistence.find(id);
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar item con id={0}", id);
        return item;
    }

    /**
     * Guardar un nuevo item
     * @param entity La entidad de tipo item del nuevo item a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN ya existe en la persitencia.
     */
    public ItemEntity createItem(ItemEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de item");
        if (!validateInstancia(entity)) {
            //throw new BusinessLogicException("El item no es una de sus subclases");
        }
        persistence.create(entity);
        if (entity instanceof BicicletaEntity) {
           // biciLogic.create((BicicletaEntity)entity);
        }
        else if(entity instanceof AccesorioEntity)
        {
            // accLogic.create((AccesorioEntity)entity);
        }
        LOGGER.info("Termina proceso de creación de item");
        return entity;
    }

    /**
     * Actualizar un item por ID
     * @param id El ID del item a actualizar
     * @param entity La entidad del item con los cambios deseados
     * @return La entidad del item luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ItemEntity updateItem(Long id, ItemEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar item con id={0}", id);
        if (!validateInstancia(entity)) {
            //throw new BusinessLogicException("El item no es una de sus subclases");
        }
        ItemEntity newEntity = persistence.update(entity);
         if (entity instanceof BicicletaEntity) {
           // biciLogic.update((BicicletaEntity)entity);
        }
        else if(entity instanceof AccesorioEntity)
        {
            // accLogic.update((AccesorioEntity)entity);
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar item con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Eliminar un item por ID
     * @param id El ID del item a eliminar
     */
    public void deleteItem(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar item con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar item con id={0}", id);
    }

    /**
     * Verifica que el entity sea de tipo accesorio o bicicleta
     * @param item entity que se desea verificar
     * @return true si es instancia de las subClases
     */
    private boolean validateInstancia(ItemEntity item) {
        return item instanceof AccesorioEntity || item instanceof BicicletaEntity;
    }
}
