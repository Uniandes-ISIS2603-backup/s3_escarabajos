/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

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

    /**
     * LOGGER de la clase ClienteLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    /**
     * Inyecta la persistencia de cliente.
     */
    @Inject
    private ClientePersistence persistence;

    /**
     * Inyecta la persistencia de carrito.
     */
    @Inject
    private CarritoPersistence carritoPersistence;

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente la nueva entidad a persistir
     * @return el cliente creado
     */
    public ClienteEntity createCliente(ClienteEntity cliente) {
        LOGGER.info("Inicia proceso de creación de cliente");
        return persistence.create(cliente);
    }

    /**
     * Devuelve todos los clientes en la base de datos.
     *
     * @return lista de clientes
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.info("Inicia proceso de consultar todos los clientes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ClienteEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los clientes");
        return editorials;
    }

    /**
     * Devuelve un cliente especifico.
     *
     * @param id cliente buscado
     * @return cliente encontrado
     */
    public ClienteEntity getCliente(Long id) {
        LOGGER.info("Inicia proceso de consultar clientes");
        return persistence.find(id);
    }

    /**
     * Actualiza un cliente especifico
     *
     * @param entity cliente especifico
     * @return cliente actualizado
     * @throws BusinessLogicException por reglas de negocio
     */
    public ClienteEntity updateCliente(ClienteEntity entity) throws BusinessLogicException {
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe un cliente con el id \"");
        }
        return persistence.update(entity);
    }

    /**
     * Elimina un cliente especifico.
     *
     * @param id cliente que se va a eliminar
     */
    public void deleteCliente(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar cliente con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar cliente con id={0}", id);
    }

    /**
     * Devuelve todos los vendedores en la base de datos
     *
     * @return lista de vendedores
     */
    public List<ClienteEntity> getVendedores() {
        LOGGER.info("Inicia proceso de consultar todos los clientes vendedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ClienteEntity> editorials = persistence.findAllVendedores();
        LOGGER.info("Termina proceso de consultar todos los clientes vendedores");
        return editorials;
    }

}
