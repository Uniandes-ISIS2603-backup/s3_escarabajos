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

    /**
     * LOGGER de la clase ItemLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemLogic.class.getName());

    /**
     * Inyecta la persistencia de bicicleta.
     */
    @Inject
    private BicicletaPersistence biciPers;

    /**
     * Inyecta la persistencia de modelo.
     */
    @Inject
    private ModeloPersistence modeloPers;

    /**
     * Inyecta la persistencia de accesorio.
     */
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
     * Devuelve un item por ID
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
        verificarModelo(modelo, entity);
    }

    /**
     * Metodo que verifica todas las reglas de negocio de un item antes de
     * crearlo.
     *
     * @param modelo a verificar
     * @param entity item a verificar
     * @throws BusinessLogicException si no se cumple una de las reglas de
     * negocio.
     */
    public void verificarModelo(ModeloEntity modelo, ItemEntity entity) throws BusinessLogicException {
        if (modelo == null) {
            throw new BusinessLogicException("El item debe tener un modelo");
        }
        String tipo = modelo.getTipoModelo();
        if (!(tipo.equals(ModeloLogic.BICICLETA) && entity instanceof BicicletaEntity || tipo.equals(ModeloLogic.ACCESORIO) && entity instanceof AccesorioEntity)) {
            throw new BusinessLogicException("El item debe ser del mismo tipo que el modelo");
        }
    }

    /**
     * Devuelve la referencia de un item que entre por paramentro
     *
     * @param entity al que se le buscara la referencia del modelo.
     * @return referencia del modelo al que le pertenece el item.
     */
    public String getReferenciaItem(ItemEntity entity) {
        ModeloEntity modelo = modeloPers.find(entity.getModeloId());
        return modelo.getReferencia();
    }

    /**
     * Devuelve una collecion de items asociados a un modelo cada uno con su
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
     * Metodo que se encarga de desactivar un item en el catalogo
     *
     * @param id del item a desactiva
     * @return item comprado
     * @throws BusinessLogicException si el item no existe o ya esta comprado.
     */
    public ItemEntity comprarItem(Long id) throws BusinessLogicException {
        ItemEntity item = biciPers.find(id);
        if (item == null) {
            item = accPers.find(id);
        }
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
            throw new BusinessLogicException("El item no existe!");

        } else {
            if (!item.getDisponible()) {
                throw new BusinessLogicException("El item no esta disponible!");
            }
            item.setDisponible(Boolean.FALSE);
            if (item instanceof AccesorioEntity) {
                  LOGGER.info("@@@3"+item.getDisponible());
                accPers.update((AccesorioEntity) item);
                         LOGGER.info("@@@5"+item.getDisponible());
            } else if (item instanceof BicicletaEntity) {
                  LOGGER.info("@@@4");
                biciPers.update((BicicletaEntity) item);
            }
            return item;
        }
    }

    /**
     * Metodo que se encarga de actualizar un item en el catalogo que no este comprado
     *
     * @param multiplicador del item a actualizar
     * @param id a actualizar
     * @return item comprado
     * @throws BusinessLogicException si el item no existe o ya esta comprado.
     */
    public ItemEntity multiplicarItem(Double multiplicador, Long id) throws BusinessLogicException {
        ItemEntity item = getItem(id);
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
            throw new BusinessLogicException("El item no existe!");
        }
        if (item.getDisponible()) {
            item.setMultiplicador(multiplicador);
            if (item instanceof AccesorioEntity) {
                accPers.update((AccesorioEntity) item);
            } else if (item instanceof BicicletaEntity) {
                biciPers.update((BicicletaEntity) item);
            }
        }
        return item;

    }

    /**
     * Devuelve una collecion de bicicletas asociadas a un modelo cada uno con su
     * instancia original
     *
     * @param modelosId modelo al cual se le buscaran los bicicletas
     * @return lista de bicicletas con instancias originales del modelo
     */
    public List<BicicletaEntity> getBicicletasModelo(Long modelosId) {
        return biciPers.findByModelo(modelosId);
    }

    /**
     * Devuelve una collecion de accesorios asociados a un modelo cada uno con su
     * instancia original
     *
     * @param modelosId modelo al cual se le buscaran los accesorios
     * @return lista de accesorios con instancias originales del modelo
     */
    public List<AccesorioEntity> getAccesoriosModelo(Long modelosId) {
        return accPers.findByModelo(modelosId);
    }

}
