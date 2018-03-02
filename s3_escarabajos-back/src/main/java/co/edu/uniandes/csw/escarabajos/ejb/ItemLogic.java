/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.*;
import java.util.ArrayList;
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

    @Inject
    private AccesorioPersistence accPers;

    @Inject
    private BicicletaPersistence biciPers;

    @Inject
    private ModeloPersistence modeloPers;

    /**
     * Devuelve todos los items que hay en la base de datos.
     *
     * @return Lista de entidades de tipo item.
     */
    public List<ItemEntity> getItems() {
        ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();
        LOGGER.info("Inicia proceso de consultar todos los items");
        for (ItemEntity bicicleta : biciPers.findAll()) {
            items.add(bicicleta);
        }
        for (ItemEntity accesorio : accPers.findAll()) {
            items.add(accesorio);
        }
        LOGGER.info("Termina proceso de consultar todos los items");
        return items;
    }

    /**
     * Busca un item por ID
     *
     * @param id El id del item a buscar
     * @return El item encontrado, null si no lo encuentra.
     */
    public ItemEntity getItem(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar item con id={0}", id);
        ItemEntity item = biciPers.find(id);
        if (item == null) {
            item = accPers.find(id);
        }
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar item con id={0}", id);
        return item;
    }

    public void verificarItem(ItemEntity entity) throws BusinessLogicException {
        if (entity.getPrecio() < 0) {
            throw new BusinessLogicException("El item no debe tener un precio negativo");
        }
        ModeloEntity modelo = modeloPers.find(entity.getModeloId());
        if (modelo == null) {
            throw new BusinessLogicException("El item debe tener un modelo");
        }

    }

    public List<ItemEntity> getItemsModelo(Long idModelo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los items");
        ArrayList<ItemEntity> items = new ArrayList<>();
        List<AccesorioEntity> result1 = accPers.findByModelo(idModelo);
        if (result1 != null) {
            for (ItemEntity accesorioEntity : result1) {
                items.add(accesorioEntity);
            }
        }
        List<BicicletaEntity> result2 = biciPers.findByModelo(idModelo);
        if (result2 != null) {
            for (ItemEntity bicicletaEntity : result2) {
                items.add(bicicletaEntity);
            }
        }
        if (items.isEmpty()) {
            throw new BusinessLogicException("El Modelo que consulta aún no tiene items");
        }
        return items;
    }

}
