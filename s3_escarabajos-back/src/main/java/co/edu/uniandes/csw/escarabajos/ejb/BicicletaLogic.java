/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.BicicletaPersistence;
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
public class BicicletaLogic {

    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());

    @Inject
    private BicicletaPersistence persistence;
    @Inject
    private ItemLogic logic;

    /**
     * Devuelve todos los bicicletas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo bicicleta.
     */
    public List<BicicletaEntity> getBicicletas() {
        LOGGER.info("Inicia proceso de consultar todos los bicicletas");
        List<BicicletaEntity> bicis = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los bicicletas");
        return bicis;
    }

    /**
     * Busca un bicicleta por ID
     *
     * @param id El id del bicicleta a buscar
     * @return El bicicleta encontrado, null si no lo encuentra.
     */
    public BicicletaEntity getBicicleta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar bicicleta con id={0}", id);
        BicicletaEntity bici = persistence.find(id);
        if (bici == null) {
            LOGGER.log(Level.SEVERE, "El bicicleta con el id {0} no existe", id);
            
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar bicicleta con id={0}", id);
        return bici;
    }

    /**
     * Guardar una nueva bicicleta
     *
     * @param entity La entidad de tipo bicicleta del nuevo bicicleta a
     * persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el atributo usada es false o
     * si no esta asociada a una categoria o
     * si no esta asociada a un modelo
     */
    public BicicletaEntity createBicicleta(BicicletaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de bicicleta");
        logic.verificarItem(entity);
        verificarBicicleta(entity);
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de bicicleta");
        return entity;
    }

    /**
     * Actualizar un bicicleta por ID
     *
     * @param id El ID del bicicleta a actualizar
     * @param entity La entidad del bicicleta con los cambios deseados
     * @return La entidad del bicicleta luego de actualizarla
     * @throws BusinessLogicException Si el atributo usada es false o
     * si no esta asociada a una categoria o
     * si no esta asociada a un modelo
     */
    public BicicletaEntity updateBicicleta(Long id, BicicletaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta con id={0}", id);
        logic.verificarItem(entity);
        verificarBicicleta(entity);
        BicicletaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar bicicleta con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Eliminar un bicicleta por ID
     *
     * @param id El ID del bicicleta a eliminar
     */
    public void deleteBicicleta(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar bicicleta con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar bicicleta con id={0}", id);
    }
    
    public BicicletaEntity verificarBicicleta(BicicletaEntity entity) throws BusinessLogicException{
         if (entity.getUsada() != false) {
            throw new BusinessLogicException("La bicicleta debe ser nueva");
        }
        if (entity.getCategoria() == null || entity.getCategoria().equals(" ")) {
            throw new BusinessLogicException("La bicicleta debe estar asociada a una categoria valida");
        }
        return entity;
    } 
}
