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

    /**
     * LOGGER de la clase BicicletaLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());

    /**
     * Injecta la persistencia de bicicleta.
     */
    @Inject
    private BicicletaPersistence persistence;
    /**
     * Injecta la logica de item.
     */
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
     * Devuelve una bicicleta especifica.
     *
     * @param id de la bicicleta
     * @return Datos de la bicicleta especifica
     * @throws BusinessLogicException Por reglas de negocio
     */
    public BicicletaEntity getBicicleta(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar bicicleta con id={0}", id);
        BicicletaEntity bici = persistence.find(id);
        if (bici == null) {
            LOGGER.log(Level.SEVERE, "El bicicleta con el id {0} no existe", id);
            throw new BusinessLogicException("La bicicleta con el id " + id + "no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar bicicleta con id={0}", id);
        return bici;
    }

    /**
     * Crea una nueva bicicleta.
     *
     * @param entity La entidad de tipo bicicleta de la nueva bicicleta a
     * persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el atributo usada es false o si no esta
     * asociada a una categoria o si no esta asociada a un modelo
     */
    public BicicletaEntity createBicicleta(BicicletaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de bicicleta");
        entity.setUsada(Boolean.FALSE);
        entity.setMultiplicador(1.0);
        logic.verificarItem(entity);
        verificarBicicleta(entity);
        persistence.create(entity);
        entity.setDisponible(Boolean.TRUE);
        LOGGER.info("Termina proceso de creación de bicicleta");
        return entity;
    }

    /**
     * Actualizar un bicicleta especifica.
     *
     * @param id El ID del bicicleta a actualizar
     * @param entity La entidad del bicicleta con los cambios deseados
     * @return La entidad del bicicleta luego de actualizarla
     * @throws BusinessLogicException Si el atributo usada es false o si no esta
     * asociada a una categoria o si no esta asociada a un modelo
     */
    public BicicletaEntity updateBicicleta(Long id, BicicletaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta con id={0}", id);
        BicicletaEntity bici = persistence.find(id);
        if (bici == null) {
            LOGGER.log(Level.SEVERE, "El bicicleta con el id {0} no existe", id);
            throw new BusinessLogicException("La bicicleta con el id " + id + "no existe");
        }
        entity.setUsada(Boolean.FALSE);
        if (entity.getPrecio() <= 0) {
            throw new BusinessLogicException("La bicicleta debe tener un precio mayor a 0");
        }
        if (entity.getModeloId() != bici.getModeloId()) {
            throw new BusinessLogicException("No se le puede cambiar el modelo a una bicicleta!");
        }
        verificarBicicleta(entity);
        BicicletaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar bicicleta con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Eliminar una bicicleta especifica.
     *
     * @param id de la bicicleta a eliminar.
     */
    public void deleteBicicleta(Long id) {
        //Este metodo no se deberia usar. USEN ModeloLogic.removeItem()!!!!!!!!!!!!!!
        LOGGER.log(Level.INFO, "Inicia proceso de borrar bicicleta con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar bicicleta con id={0}", id);
    }

    /**
     * Metodo que verifica las reglas de negocio.
     *
     * @param entity Datos de la bicicleta a veficar
     * @return bicicleta cumpliendo las reglas de negocio.
     * @throws BusinessLogicException por reglas de negocio
     */
    public BicicletaEntity verificarBicicleta(BicicletaEntity entity) throws BusinessLogicException {
        if (entity.getUsada()) {
            throw new BusinessLogicException("La bicicleta debe ser nueva");
        }
        if (entity.getCategoria() == null || " ".equals(entity.getCategoria())) {
            throw new BusinessLogicException("La bicicleta debe estar asociada a una categoria valida");
        }
        return entity;
    }

    /**
     * Devuelve todas las bicicletas con un modelo especifico.
     *
     * @param id del modelo para filtrar
     * @return lista de bicicletas
     */
    public List<BicicletaEntity> findByModelo(Long id) {
        return persistence.findByModelo(id);
    }
}
