/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.MedioPagoPersistence;
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
public class MedioPagoLogic {
    private static final Logger LOGGER = Logger.getLogger(MedioPagoLogic.class.getName());

    @Inject
    private MedioPagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    
    /**
    * Guardar un nuevo medio de pago
    *
    * @param entity La entidad de tipo medio de pago del medio de pago a
    * persistir.
    * @return La entidad luego de persistirla
    */
    public MedioPagoEntity createMedioPago(MedioPagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de medio de pago");
        //TODO: No hay la regla de negocio
        String tipo = entity.getTipo();
        
        if(!tipo.equalsIgnoreCase("pse") && !tipo.equalsIgnoreCase("paypal") && !tipo.equalsIgnoreCase("tarjeta de credito") && !tipo.equalsIgnoreCase("tarjeta debito")){
            throw new BusinessLogicException("El tipo de medio de pago no es valido");
        }
        // Invoca la persistencia para crear el cliente
        return persistence.create(entity);
    }

     /**
     * Devuelve todos los medios de pago que hay en la base de datos.
     *
     * @return Lista de entidades de tipo medio de pago.
     */
    public List<MedioPagoEntity> getMedioPagos() {
        LOGGER.info("Inicia proceso de consultar todos los medios de pago");
        List<MedioPagoEntity> mediosPago = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los medios de pago");
        return mediosPago;
    }

    /**
     * Devuelve un medio de pago especifica
     * @param id del medio de pago
     * @return Datos del medio de pago especifico
     * @throws BusinessLogicException Por reglas de negocio
     */
    public MedioPagoEntity getMedioPago(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar medio de pago con id={0}", id);
        MedioPagoEntity medioPago = persistence.find(id);
        if (medioPago == null) {
            LOGGER.log(Level.SEVERE, "El medio de pago con el id {0} no existe", id);
            throw new BusinessLogicException("El medio de pago con el id: " + id + "no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar medio de pago con id={0}", medioPago.getId());
        return medioPago;
    }

    /**
     * Actualizar un medio de pago especifico
     *
     * @param id El ID del medio de pago a actualizar
     * @param entity La entidad de medio de pago con los cambios deseados
     * @return La entidad de medio de pago luego de actualizarla
     * @throws BusinessLogicException Si no existe un medio de pago con el id dado
     */
    public MedioPagoEntity updateMedioPago(Long id, MedioPagoEntity entity) throws BusinessLogicException  {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un medio de pago ");
        MedioPagoEntity medioPago = persistence.find(id);
        if (medioPago == null) {
            throw new BusinessLogicException("No existe un medio de pago con el id" + id + "\"");
        }
        String tipo = entity.getTipo();
        if(!tipo.equalsIgnoreCase("pse") && !tipo.equalsIgnoreCase("paypal") && !tipo.equalsIgnoreCase("tarjeta de credito") && !tipo.equalsIgnoreCase("tarjeta debito")){
            throw new BusinessLogicException("El tipo de medio de pago no es valido");
        }
        return persistence.update(entity);
    }
    
    /**
     * Eliminar un medo de pago especifico
     *
     * @param id del medio de pago a borrar
     */
    public void deleteMedioPago(Long id){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el medio de pago con id={0}", id);    
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el medio de pago con id={0}", id);
    }

    /**
     * Devuelve todos los facturas de un cliente que hay en la base de datos.
     * @param idCliente id del cliente del que se buscan las facturas
     * @return Lista de entidades de tipo factura.
     */
    public List<MedioPagoEntity> getMediosPagoCliente(Long idCliente){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar medios de pago de un cliente");
        List <MedioPagoEntity> mediosPago = persistence.findMediosByClienteId(idCliente);
        LOGGER.log(Level.INFO, "Termina proceso de consultar medios de pago de un cliente");
        return mediosPago;
    }
}
