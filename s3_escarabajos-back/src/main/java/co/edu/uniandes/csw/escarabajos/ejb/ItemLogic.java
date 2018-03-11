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
    private BicicletaPersistence biciPers;

    @Inject
    private ModeloPersistence modeloPers;

    @Inject
    private FotoPersistence fotoPers;

    @Inject
    private AccesorioPersistence accPers;


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
        ArrayList<ItemEntity> items = new ArrayList<>();
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

    /**
     * Metodo que verifica todas las reglas de negocio de un item antes de
     * crearlo.
     *
     * @param entity item a verificar
     * @throws BusinessLogicException si no se cumple una de las reglas de
     * negocio.
     */
    public void verificarItem(ItemEntity entity) throws BusinessLogicException {
        if (entity.getPrecio() < 0) {
            throw new BusinessLogicException("El item no debe tener un precio negativo");
        }
        if (getItem(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un accesorio el id \"" + entity.getId() + "\"");
        }
        ModeloEntity modelo = modeloPers.find(entity.getModeloId());
        if (modelo == null) {
            throw new BusinessLogicException("El item debe tener un modelo");
        }
        String tipo = modelo.getTipoModelo();
        if (!(tipo.equals(ModeloLogic.BICICLETA) && entity instanceof BicicletaEntity || tipo.equals(ModeloLogic.ACCESORIO) && entity instanceof AccesorioEntity)) {
            throw new BusinessLogicException("El item debe ser del mismo tipo que el modelo");
        }

    }

    /**
     * Retorna una collecion de items asociados a un modelo cada uno con su
     * instancia original
     *
     * @param idModelo modelo al cual se le buscaran los items
     * @return lista de items con instancias originales del modelo
     * @throws BusinessLogicException si el modelo no existe
     */
    public List<ItemEntity> getItemsModelo(Long idModelo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los items");
        ModeloEntity modelo = modeloPers.find(idModelo);
        if (modelo == null) {
            throw new BusinessLogicException("El modelo no existe");
        }
        ArrayList<ItemEntity> items = new ArrayList<>();
        List<AccesorioEntity> result1 = getAccesoriosModelo(idModelo);
        if (result1 != null) {
            for (ItemEntity accesorioEntity : result1) {
                items.add(accesorioEntity);
            }
        }
        List<BicicletaEntity> result2 = getBicicletasModelo(idModelo);
        if (result2 != null) {
            for (ItemEntity bicicletaEntity : result2) {
                items.add(bicicletaEntity);
            }
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
     *@throws BusinessLogicException Por reglasd de negocio
     */
    public List<FotoEntity> listFotos(Long itemId) throws BusinessLogicException {
        ItemEntity itemEntity = getItem(itemId);
        if (itemEntity == null) {
            throw new BusinessLogicException("El item no existe!");
        }
        return getItem(itemId).getAlbum();
    }

    /**
     * Retorna una collecion de bicicletas asociadas a un modelo cada uno con su
     * instancia original
     *
     * @param modelosId modelo al cual se le buscaran los bicicletas
     * @return lista de bicicletas con instancias originales del modelo
     */
    public List<BicicletaEntity> getBicicletasModelo(Long modelosId) {
        return biciPers.findByModelo(modelosId);
    }

    /**
     * Retorna una collecion de accesorios asociados a un modelo cada uno con su
     * instancia original
     *
     * @param modelosId modelo al cual se le buscaran los accesorios
     * @return lista de accesorios con instancias originales del modelo
     */
    public List<AccesorioEntity> getAccesoriosModelo(Long modelosId) {
        return accPers.findByModelo(modelosId);
    }

}
