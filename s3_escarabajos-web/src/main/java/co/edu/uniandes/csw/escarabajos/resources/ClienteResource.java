/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import co.edu.uniandes.csw.escarabajos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;

import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 * <pre>Clase que implementa el recurso "clientes".
 * URL: /api/clientes
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "clientes".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author s.beltran  
 * @version 1.0
 */
public class ClienteResource {
    /**
     * <h1>POST /api/clientes : Crear una cliente.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ClienteDetailDTO}.
     * 
     * Crea un nuevo cliente con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo cliente .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el cliente.
     * </code>
     * </pre>
     * @param cliente {@link ClienteDetailDTO} - el cliente que se desea guardar.
     * @return JSON {@link ClienteDetailDTO}  - el cliente guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    
    @Inject
    ClienteLogic logic;
    
    @POST
    public ClienteDTO createCliente(ClienteDetailDTO cliente) throws BusinessLogicException {
        return new ClienteDTO(logic.createCliente(cliente.toEntity()));
    }
    /**
     * <h1>GET /api/clientes : Obtener todos las clientes.</h1>
     * 
     * <pre>Busca y devuelve todas los clientes que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los clientes de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ClienteDetailDTO} - Los clientes encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ClienteDetailDTO> getClientes() {
        return listEntity2DTO(logic.getClientes());    
    }
    
    /**
     * Convierte una lista de ClienteEntity a una lista de ClienteDetailDTO.
     *
     * @param entityList Lista de ClienteEntity a convertir.
     * @return Lista de ClienteDetailDTO convertida.
     * 
     */
    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }
     /**
     * <h1>GET /api/clientes/{id} : Obtener cliente por id.</h1>
     * 
     * <pre>Busca cliente con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve cliente correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un cliente con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del cliente que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDetailDTO} - El cliente buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) {
        ClienteEntity entity = logic.getCliente(id);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return new ClienteDetailDTO(entity);
    }
    
     /**
     * <h1>PUT /api/clientes/{id} : Actualizar cliente con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ClienteDetailDTO}.
     * 
     * Actualiza el cliente con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el cliente con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un cliente con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del cliente que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param cliente {@link ClienteDetailDTO} 
     * @return JSON {@link ClienteDetailDTO} -
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el cliente porque ya existe otro con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id, ClienteDetailDTO cliente) throws BusinessLogicException {
        ClienteEntity entity = cliente.toEntity();
        entity.setId(id);
        ClienteEntity oldEntity = logic.getCliente(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        //entity.setBooks(oldEntity.getBooks());
        return new ClienteDetailDTO(logic.updateCliente(entity));
    }
    
    /**
     * <h1>DELETE /api/clientes/{id} : Borrar cliente por id.</h1>
     * 
     * <pre>Borra cliente con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la ciudad correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de cliente que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteCliente(@PathParam("id") Long id) throws BusinessLogicException {
        // Void
        ClienteEntity entity = logic.getCliente(id);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        logic.deleteCliente(id);
    }
     
    @Path("{idCliente: \\d+}/carrito")
    public Class<CarritoResource> getClienteCarrito(@PathParam("idCliente") Long idCliente) {
        ClienteEntity entity = logic.getCliente(idCliente);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + idCliente + "/carrito no existe.", 404);
        }
        return CarritoResource.class;
    }
    
    
    
}
