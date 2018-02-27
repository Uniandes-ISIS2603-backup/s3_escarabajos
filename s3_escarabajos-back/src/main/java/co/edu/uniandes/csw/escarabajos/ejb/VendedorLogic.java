/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.VendedorPersistence;
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
public class VendedorLogic {
    private static final Logger LOGGER = Logger.getLogger(VendedorLogic.class.getName());

    @Inject
    private VendedorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     public VendedorEntity createCliente(VendedorEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de vendedor");
        // Verifica la regla de negocio que dice que no puede haber dos vendedores con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un vendedor con el nombre \"" + entity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear el vendedor
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cliente");
        return entity;
    }

    public List<VendedorEntity> getVendedores() {
        LOGGER.info("Inicia proceso de consultar todos los vendedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<VendedorEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los vendedores");
        return editorials;
    }

    public VendedorEntity getVendedor(Long id) {
        return persistence.find(id);
    }

    public VendedorEntity updateVendedor(VendedorEntity entity) throws BusinessLogicException  {
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un vendedor con el nombre \"" + entity.getNombre()+ "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteVendedor(VendedorEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar vendedor con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar vendedor con id={0}", entity.getId());
    }
}
