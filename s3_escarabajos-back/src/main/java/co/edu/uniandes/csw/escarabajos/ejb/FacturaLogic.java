/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.*;
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

    /**
     * Injecta la logica de cliente.
     */
    @Inject
    private ClienteLogic logicCliente;
    
    //TODO: Factura no tiene ninguna regla de negocio.
     public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de factura");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de factura");
        return entity;
    }
//TODO: Cambiar lo de editorials
    public List<FacturaEntity> getFacturas(Long idCliente) throws BusinessLogicException {
         LOGGER.info("Inicia proceso de consultar todas las facturas");
        ClienteEntity cliente = logicCliente.getCliente(idCliente);
        if (cliente.getCompras()== null || cliente.getCompras().isEmpty()) {
            throw new BusinessLogicException("El vendedor aun no tiene ninguna factura");
        }
        LOGGER.info("Termina proceso de consultar todas las facturas");
        return persistence.findAll(idCliente);
    }
    
    public FacturaEntity getFactura(Long idFactura) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar factura con id={0}", idFactura);
        FacturaEntity factura = persistence.find(idFactura);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "La factura con el id {0} no existe", idFactura);
            throw new BusinessLogicException("La factura con el id " + idFactura + "no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", idFactura);
        return factura;
    }

    public FacturaEntity getFacturaCLiente(Long idCliente, Long idFactura) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar factura con id={0}", idFactura);
        FacturaEntity factura = persistence.findFacturaCliente(idCliente, idFactura);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "La factura con el id {0} no existe", idFactura);
            throw new BusinessLogicException("No existe una bicicleta usada con el id " + idFactura);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", idFactura);
        return factura;
    }

    public FacturaEntity updateFactura(Long idCliente, FacturaEntity entity) throws BusinessLogicException  {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta usada con id={0}", entity.getId());
        return null;
    }
    //TODO: que pasa si no existe la Factura? 
    public void deleteFactura(FacturaEntity entity){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la factura con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la factura con id={0}", entity.getId());
    }

    public FacturaEntity getFactura(Long idCliente, Long idFactura) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar factura con id={0}", idFactura);
        FacturaEntity factura = persistence.findFacturaCliente(idCliente, idFactura);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "La factura con el id {0} no existe", idFactura);
            throw new BusinessLogicException("No existe una factura con el id " + idFactura);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", idFactura);
        return factura;
    }
}
