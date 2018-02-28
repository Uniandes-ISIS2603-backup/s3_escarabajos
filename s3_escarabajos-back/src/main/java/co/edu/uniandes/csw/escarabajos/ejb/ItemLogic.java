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
   // private ItemPersistence persistence;

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
      //  List<ItemEntity> items = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los items");
        return null;
    }

    /**
     * Busca un item por ID
     * @param id El id del item a buscar
     * @return El item encontrado, null si no lo encuentra.
     */
    public ItemEntity getItem(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar item con id={0}", id);
        /*ItemEntity item = persistence.find(id);
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar item con id={0}", id);*/
        return null;
    }




}
