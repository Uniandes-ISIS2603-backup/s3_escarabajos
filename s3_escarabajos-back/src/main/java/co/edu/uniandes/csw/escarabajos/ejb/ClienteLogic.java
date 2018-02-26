/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.beltran
 */
@Stateless
public class ClienteLogic {
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     public ClienteEntity createCliente(ClienteEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de cliente");
        // Verifica la regla de negocio que dice que no puede haber dos clientes con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una cliente con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear el cliente
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cliente");
        return entity;
    }

    public List<ClienteEntity> getClientes() {
        LOGGER.info("Inicia proceso de consultar todos los clientes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ClienteEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los clientes");
        return editorials;
    }

    public ClienteEntity getCliente(Long id) {
        return persistence.find(id);
    }

    public ClienteEntity updateCliente(ClienteEntity entity) throws BusinessLogicException  {
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un cliente con el nombre \"" + entity.getName() + "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteCliente(ClienteEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar cliente con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar cliente con id={0}", entity.getId());
    }
}
