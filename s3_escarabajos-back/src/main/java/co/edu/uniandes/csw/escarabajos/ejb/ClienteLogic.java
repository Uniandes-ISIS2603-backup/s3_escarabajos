/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
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

    @Inject
    private CarritoPersistence carritoPersistence;
    

     
     public ClienteEntity createCliente(ClienteEntity cliente) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de cliente");
        CarritoEntity carrito = new CarritoEntity();
        carrito.setCliente(cliente);
        cliente.setCarrito(carrito);
        persistence.create(cliente);
        carritoPersistence.create(carrito);
        LOGGER.info("Termina proceso de creación de cliente");
        return cliente;
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
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe un cliente con el id \"");
        }
        return persistence.update(entity);
    }
    
    public void deleteCliente(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar cliente con id={0}", id);    
        carritoPersistence.deleteCarrito(getCliente(id).getCarrito().getId());
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar cliente con id={0}", id);
    }
    
   
}
