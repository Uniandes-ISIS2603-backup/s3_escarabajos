/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.AccesorioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class AccesorioLogic {

    /**
     * LOGGER de la clase AccesorioLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(AccesorioLogic.class.getName());

    /**
     * Injecta la persistencia de Accesorio.
     */
    @Inject
    private AccesorioPersistence persistence;

    /**
     * Injecta la logica de item.
     */
    @Inject
    private ItemLogic itemLogic;

    /**
     * Crea un accesorio.
     *
     * @param entity el entity que se desea crear
     * @return el accesorio
     * @throws BusinessLogicException si ya existe el accesorio
     */
    public AccesorioEntity createAccesorio(AccesorioEntity entity) throws BusinessLogicException {

        LOGGER.info("Inicia proceso de creación de un accesorio");
        itemLogic.verificarItem(entity);
        entity.setMultiplicador(1.0);
        // Verifica la regla de negocio que dice que no puede haber dos accesorios con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un accesorio el id \"" + entity.getId() + "\"");
        }
        entity.setDisponible(Boolean.TRUE);
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del accesorio");
        return entity;
    }

    /**
     * Actualiza un accesorio.
     *
     * @param entity el entity que se desea actualizar
     * @return el accesorio
     * @throws BusinessLogicException si no se logra actualizar
     */
    public AccesorioEntity updateAccesorio(AccesorioEntity entity) throws BusinessLogicException {

        AccesorioEntity acc = persistence.find(entity.getId());
        if (acc == null) {
            throw new BusinessLogicException("No existe un accesorio con el id" + entity.getId() + "\"");
        }
        if (acc.getPrecio() <= 0) {
            throw new BusinessLogicException("El accesorio debe tener un precio mayor a 0");
        }
        if (entity.getModeloId() != acc.getModeloId()) {
            throw new BusinessLogicException("No se le puede cambiar el modelo a un accesorio!");
        }
        return persistence.update(entity);
    }

    /**
     * Borra un accesorio.
     *
     * @param id el id del accesorio a borrar
     */
    public void deleteAccesorio(Long id) {
        //Este metodo no se deberia usar. USEN ModeloLogic.removeItem()!!!!!!!!!!!!!!
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Accesorio con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Accesorio con id={0}", id);
    }

    /**
     * Devuelve todos los accesorios que hay en la base de datos.
     *
     * @return todos los accesorios
     */
    public List<AccesorioEntity> getAccesorios() {
        LOGGER.info("Inicia proceso de consultar todos los accesorios");
        List<AccesorioEntity> accesorios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los accesorios");
        return accesorios;
    }

    /**
     * Obtiene un accesorio especifico.
     *
     * @param id el id del accesorio de consulta
     * @return el accesorio
     */
    public AccesorioEntity getAccesorio(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el accesorio con id={0}", id);
        AccesorioEntity accesorio = persistence.find(id);
        if (accesorio == null) {
            LOGGER.log(Level.SEVERE, "El accesorio con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar accesorio con id={0}", id);
        return accesorio;
    }
}
