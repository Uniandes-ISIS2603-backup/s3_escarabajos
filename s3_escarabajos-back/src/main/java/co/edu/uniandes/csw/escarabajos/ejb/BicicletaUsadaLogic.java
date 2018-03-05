/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
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

    @Inject
    private BicicletaUsadaPersistence persistence;
    @Inject
    private ItemLogic logicItem;
    @Inject
    private VendedorLogic logicVendedor;
    @Inject
    private ModeloPersistence modeloPersistence;

    
    public BicicletaUsadaEntity verificarBiciUsada(BicicletaUsadaEntity entity) throws BusinessLogicException{
        if (entity.getUsada() != true) {
            throw new BusinessLogicException("La bicicleta usada debe tener el estado de usada en true");
        }
        if (entity.getCategoria() == null || entity.getCategoria().equals("")) {
            throw new BusinessLogicException("La bicicleta usada debe estar asociada a una categoria valida");
        }
        if (!entity.getEstado().equals("En proceso")) {
            throw new BusinessLogicException("La bicicleta usada debe tener estado inicial En proceso");
        }
        return entity;
    }
    /**
     * Devuelve todos los bicicleta usadas usadas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo bicicleta usada.
     */
    public List<BicicletaUsadaEntity> getBicicletasDelVendedor(Long idVendedor) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los bicicleta usadas usadas");
        VendedorEntity vendedor = logicVendedor.getVendedor(idVendedor);
        if(vendedor.getBicicletasUsadas() == null || vendedor.getBicicletasUsadas().isEmpty()){
            throw new BusinessLogicException("El vendedor aun no tiene ninguna bicicleta usada");
        }
        LOGGER.info("Termina proceso de consultar todos los bicicleta usadas usadas");
        return persistence.findAllBicis(idVendedor);
    }

    /**
     * Busca un bicicleta usada por ID
     *
     * @param id El id del bicicleta usada a buscar
     * @return El bicicleta usada encontrado, null si no lo encuentra.
     */
    public BicicletaUsadaEntity getBicicleta(Long idVendedor,Long idBici) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar bicicleta usada con id={0}", idBici);
        BicicletaUsadaEntity bici = persistence.find(idVendedor,idBici);
        if (bici == null) {
            LOGGER.log(Level.SEVERE, "El bicicleta usada con el id {0} no existe", idBici);
            throw new BusinessLogicException("No existe una bicicleta usada con el id "+ idBici);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar bicicleta usada con id={0}", idBici);
        return bici;
    }

    /**
     * Guardar una nueva bicicleta usada
     *
     * @param entity La entidad de tipo bicicleta usada del nuevo bicicleta
     * usada a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el atributo usada es false o si no esta
     * asociada a una categoria o si no esta asociada a un modelo
     */
    public BicicletaUsadaEntity createBicicleta(Long idVendedor,BicicletaUsadaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de bicicleta usada");
        entity.setEstado("En proceso");
        logicItem.verificarItem(entity);
        verificarBiciUsada(entity);
        VendedorEntity vendedor = logicVendedor.getVendedor(idVendedor);
        entity.setVendedor(vendedor);
        if (persistence.find(idVendedor,entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una bicicleta usada con el id \"" + entity.getId()+ "\"");
        }
        LOGGER.info("Termina proceso de creación de bicicleta usada");
        return persistence.create(entity);
    }

    /**
     * Actualizar un bicicleta usada por ID
     *
     * @param id El ID del bicicleta usada a actualizar
     * @param entity La entidad del bicicleta usada con los cambios deseados
     * @return La entidad del bicicleta usada luego de actualizarla
     * @throws BusinessLogicException Si el atributo usada es false o si no esta
     * asociada a una categoria o si no esta asociada a un modelo
     */
    public BicicletaUsadaEntity updateBicicleta(Long idVendedor, BicicletaUsadaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta usada con id={0}", entity.getId());
        if(entity.getPrecio() < 0 ){
            throw new BusinessLogicException("El precio debe ser mayor a 0");
        }
        ModeloEntity modelo = modeloPersistence.find(entity.getModeloId());
        if (modelo == null) {
            throw new BusinessLogicException("La bicicleta usada debe tener un modelo");
        }
        verificarBiciUsada(entity);
        VendedorEntity vendedor = logicVendedor.getVendedor(idVendedor);
        entity.setVendedor(vendedor);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar bicicleta usada con id={0}", entity.getId());
        return persistence.update(entity);
    }

    /**
     * Eliminar un bicicleta usada por ID
     *
     * @param id El ID de la bicicleta usada a eliminar
     */
    public void deleteBicicleta(Long idVendedor,Long idBici) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar bicicleta usada con id={0}", idBici);
        BicicletaUsadaEntity aBorrar = getBicicleta(idVendedor, idBici);
        persistence.delete(aBorrar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar bicicleta usada con id={0}", idBici);
    }
    
    
}
