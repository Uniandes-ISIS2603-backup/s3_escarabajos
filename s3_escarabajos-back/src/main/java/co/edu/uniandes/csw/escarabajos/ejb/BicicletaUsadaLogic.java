/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaUsadaPersistence;
import co.edu.uniandes.csw.escarabajos.persistence.ModeloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.santacruza
 */
@Stateless
public class BicicletaUsadaLogic {

    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());

    /**
     * Injecta la persistencia de bicicleta usada.
     */
    @Inject
    private BicicletaUsadaPersistence persistence;
    /**
     * Injecta la logica de item.
     */
    @Inject
    private ItemLogic logicItem;
    /**
     * Injecta la logica de vendedor.
     */
    @Inject
    private ClienteLogic logicVendedor;
    /**
     * Injecta la persistencia de modelo.
     */
    @Inject
    private ModeloPersistence modeloPersistence;

    /**
     * Metodo que verifica las reglas de negocio.
     *
     * @param entity datos de la bicicleta a verficar 
     * @return bicicleta cumpliendo las reglas de negocio.
     * @throws BusinessLogicException Por reglas de negocio
     */
    public BicicletaUsadaEntity verificarBiciUsada(BicicletaUsadaEntity entity) throws BusinessLogicException {
        if (entity.getUsada() != true) {
            throw new BusinessLogicException("La bicicleta usada debe tener el estado de usada en true");
        }
        return entity;
    }

    /**
     * Devuelve todas las bicicletas de un vendedor.
     *
     * @param idVendedor vendedor especifico
     * @return lista de bicicletas usadas.
     * @throws BusinessLogicException Por reglas de negocio.
     */
    public List<BicicletaUsadaEntity> getBicicletasDelVendedor(Long idVendedor) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los bicicleta usadas usadas");
        ClienteEntity vendedor = logicVendedor.getCliente(idVendedor);
        if (vendedor.getBicicletasUsadas() == null || vendedor.getBicicletasUsadas().isEmpty()) {
            throw new BusinessLogicException("El vendedor aun no tiene ninguna bicicleta usada");
        }
        LOGGER.info("Termina proceso de consultar todos los bicicleta usadas usadas");
        return persistence.findAllBicis(idVendedor);
    }

    /**
     * Devuelve una bicicleta especifica de un vendedor especifico.
     *
     * @param idVendedor vendedor especifico
     * @param idBici bicicleta especifica
     * @return bicicleta usada
     * @throws BusinessLogicException por reglas de negocio
     */
    public BicicletaUsadaEntity getBicicleta(Long idVendedor, Long idBici) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar bicicleta usada con id={0}", idBici);
        BicicletaUsadaEntity bici = persistence.find(idVendedor, idBici);
        if (bici == null) {
            LOGGER.log(Level.SEVERE, "El bicicleta usada con el id {0} no existe", idBici);
            throw new BusinessLogicException("No existe una bicicleta usada con el id " + idBici);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar bicicleta usada con id={0}", idBici);
        return bici;
    }

    /**
     * Crea una bicicleta nueva asociada a un vendedor.
     *
     * @param idVendedor vendedor especifico
     * @param entity datos de bicicleta a crear.
     * @return bicicleta nueva
     * @throws BusinessLogicException Por reglasd de negocio
     */
    public BicicletaUsadaEntity createBicicleta(Long idVendedor, BicicletaUsadaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creaciÃ³n de bicicleta usada");
        entity.setUsada(Boolean.TRUE);
        entity.setEstado("En proceso");
        logicItem.verificarItem(entity);
        verificarBiciUsada(entity);
        ClienteEntity vendedor = logicVendedor.getCliente(idVendedor);
        entity.setCliente(vendedor);
        LOGGER.info("Termina proceso de creaciÃ³n de bicicleta usada");
        return persistence.create(entity);
    }

    /**
     * Modifica una bicleta asociada a un vendedor
     *
     * @param idVendedor vendedor especifico
     * @param entity nuevos ddatos de la bicicleta
     * @return Bicicleta modificada
     * @throws BusinessLogicException Por reglas de negocio
     */
    public BicicletaUsadaEntity updateBicicleta(Long idVendedor, BicicletaUsadaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta usada con id={0}", entity.getId());
        entity.setUsada(Boolean.TRUE);
        if (entity.getPrecio() < 0) {
            throw new BusinessLogicException("El precio debe ser mayor a 0");
        }
        ModeloEntity modelo = modeloPersistence.find(entity.getModeloId());
        if (modelo == null) {
            throw new BusinessLogicException("La bicicleta usada debe tener un modelo");
        }
        verificarBiciUsada(entity);
        ClienteEntity vendedor = logicVendedor.getCliente(idVendedor);
        entity.setCliente(vendedor);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar bicicleta usada con id={0}", entity.getId());
        return persistence.update(entity);
    }

    /**
     * Elimina una bicicleta asociada a un vendedor
     *
     * @param idVendedor vendedor especifico
     * @param idBici bicicleta especifica
     * @throws BusinessLogicException Por reglas de negocio
     */
    public void deleteBicicleta(Long idVendedor, Long idBici) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar bicicleta usada con id={0}", idBici);
        BicicletaUsadaEntity aBorrar = getBicicleta(idVendedor, idBici);
        persistence.delete(aBorrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar bicicleta usada con id={0}", idBici);
    }

}
