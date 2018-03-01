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
 * @author jp.carreno
 */
@Stateless
public class FacturaLogic {
    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    @Inject
    private FacturaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de factura");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de factura");
        return entity;
    }

    public List<FacturaEntity> getFacturas() {
        LOGGER.info("Inicia proceso de consultar todas las facturas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<FacturaEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los facturas");
        return editorials;
    }

    public FacturaEntity getFactura(Long id) {
        return persistence.find(id);
    }

    public FacturaEntity updateFactura(FacturaEntity entity) throws BusinessLogicException  {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe una factura con el id \"" + entity.getId() + "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteFactura(FacturaEntity entity){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la factura con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la factura con id={0}", entity.getId());
    }
}
