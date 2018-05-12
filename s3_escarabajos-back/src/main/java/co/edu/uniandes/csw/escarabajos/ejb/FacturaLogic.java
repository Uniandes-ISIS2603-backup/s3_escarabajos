/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.FacturaPersistence;
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
public class FacturaLogic {

    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    /**
     * Injecta la persistencia de Factura.
     */
    @Inject
    private FacturaPersistence persistence;

    /**
     * Devuelve todos los facturas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo factura.
     */
    public List<FacturaEntity> getFacturas() {
        LOGGER.info("Inicia proceso de consultar todas las facturas");
        List<FacturaEntity> facturas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las facturas");
        return facturas;
    }

    /**
     * Devuelve una factura especifica
     * @param id de la factura
     * @return Datos de la factura especifica
     * @throws BusinessLogicException Por reglas de negocio
     */
    public FacturaEntity getFactura(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar factura con id={0}", id);
        FacturaEntity factura = persistence.find(id);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "La factura con el id {0} no existe", id);
            throw new BusinessLogicException("La bicicleta con el id " + id + "no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", factura.getId());
        return factura;
    }

    /**
     * Guardar una nueva factura
     *
     * @param entity La entidad de tipo factura de la factura a
     * persistir.
     * @return La entidad luego de persistirla
     */
    public FacturaEntity createFactura(FacturaEntity entity){
        LOGGER.info("Inicia proceso de creación de bicicleta");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de bicicleta");
        return entity;
    }

    /**
     * Actualizar un factura especifica
     *
     * @param id El ID de la factura a actualizar
     * @param entity La entidad de factura con los cambios deseados
     * @return La entidad de factura luego de actualizarla
     * @throws BusinessLogicException Si el atributo usada es false o si no esta
     * asociada a una categoria o si no esta asociada a un modelo
     */
    public FacturaEntity updateFactura(Long id, FacturaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una factura ");
        FacturaEntity factura = persistence.find(id);
        if (factura == null) {
            throw new BusinessLogicException("No existe una factura con el id" + id + "\"");
        }
        return persistence.update(entity);
    }

    /**
     * Eliminar un factura especifica
     *
     * @param id de la factura a borrar
     */
    public void deleteFactura(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar factura con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar factura con id={0}", id);
    }
}
