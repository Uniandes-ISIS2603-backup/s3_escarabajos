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

    @Inject 
    private FotoPersistence fotoPers;

    /*
      Para crear un Accesorio/Bicicleta:
      Se crea/busca el modeloId al que se le quiere agregar: 
      se crea el accesorio y se le agrega al modelo.
     */
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

    /**
     * Retorna una collecion de items asociados a un modelo cada uno con su instancia original
     * @param idModelo modelo al cual  se le buscaran los items
     * @return lista de items con instancias originales del modelo
     * @throws BusinessLogicException  si el modelo no existe o no tiene items
     */
    public List<ItemEntity> getItemsModelo(Long idModelo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los items");
        ModeloEntity modelo = modeloPers.find(idModelo);
        if (modelo == null) {
            throw new BusinessLogicException("El modelo no existe");
        }
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

    /**
     * Retorna una foto asociado a un item
     *
     * @param itemId El id del item a buscar.
     * @param fotoId El id de la foto a buscar
     * @return El foto encontrada dentro del item.
     * @throws BusinessLogicException Si la foto no se encuentra en el item o si
     * el item no existe
     */
    public FotoEntity getFoto(Long itemId, Long fotoId) throws BusinessLogicException {
        ItemEntity itemEntity = getItem(itemId);
        if (itemEntity == null) {
            throw new BusinessLogicException("El item no existe!");
        }
        List<FotoEntity> fotos = itemEntity.getAlbum();
        FotoEntity foto = fotoPers.findInItem(itemId, fotoId);
        int index = fotos.indexOf(foto);
        if (index >= 0) {
            return fotos.get(index);
        }
        throw new BusinessLogicException("La foto no está asociada a el item");

    }

    /**
     * Obtiene una colección de instancias de FotoEntity asociadas a una
     * instancia de item
     *
     * @param itemId Identificador de la instancia de item
     * @return Colección de instancias de FotoEntity asociadas a la instancia de
     * item
     *
     */
    public List<FotoEntity> listFotos(Long itemId) {
        return getItem(itemId).getAlbum();
    }

    
    /**
     * Se borran las fotos de un item 
     * @param itemId el item al cual se le borraran las fotos
     * @throws BusinessLogicException si el item no existe
     */
    public void deleteFotos(Long itemId) throws BusinessLogicException {
        ItemEntity itemEntity = getItem(itemId);
        if (itemEntity == null) {
            throw new BusinessLogicException("El item no existe!");
        }
        for (FotoEntity fotoEntity : itemEntity.getAlbum()) {
            fotoEntity.getItem().getAlbum().remove(fotoEntity);
            fotoEntity.setItem(null);
            fotoPers.delete(fotoEntity.getId());
        }
    }

}
