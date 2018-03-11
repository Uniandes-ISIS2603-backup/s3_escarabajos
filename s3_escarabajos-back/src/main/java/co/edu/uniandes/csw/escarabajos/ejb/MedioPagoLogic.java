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

     public MedioPagoEntity createMedioPago(MedioPagoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de medio de pago");
        // Verifica la regla de negocio que dice que no puede haber dos medios de pago con el mismo nombre
    /*    List<MedioPagoEntity> todos = persistence.findAll();
        for(int i = 0; i < todos.size();i++)
        {
            if(todos.get(i).getNumeroTarjeta() == entity.getNumeroTarjeta())
            {
                throw new BusinessLogicException("Ya existe un medio de pago con el numero de tarjeta \"" + entity.getNumeroTarjeta() + "\"");
            }
        }*/
        // Invoca la persistencia para crear el cliente
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de medio de pago");
        return entity;
    }

    public List<MedioPagoEntity> getMedioPagos() {
        LOGGER.info("Inicia proceso de consultar todos los medios de pago");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<MedioPagoEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los medios de pago");
        return editorials;
    }

    public MedioPagoEntity getMedioPago(Long id) {
        return persistence.find(id);
    }

    public MedioPagoEntity updateMedioPago(MedioPagoEntity entity) throws BusinessLogicException  {
        return persistence.update(entity);
    }
    
    public void deleteMedioPago(MedioPagoEntity entity){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el medio de pago con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el medio de pago con id={0}", entity.getId());
    }
}
