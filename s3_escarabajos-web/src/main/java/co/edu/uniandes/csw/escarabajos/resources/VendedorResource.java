/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.VendedorDTO;
import co.edu.uniandes.csw.escarabajos.dtos.VendedorDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.VendedorLogic;
import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
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

/**
 * <pre>Clase que implementa el recurso "vendedores".
 * URL: /api/vendedores
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "vendedores".</i>
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
@Path("vendedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VendedorResource {
     @Inject
    VendedorLogic logic;
    /**
     * <h1>POST /api/vendedores : Crear un vendedor.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link VendedorDetailDTO}.
     * 
     * Crea un nuevo vendedor con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo vendedor .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el vendedor.
     * </code>
     * </pre>
     * @param vendedor {@link VendedorDetailDTO} - El vendedor que se va guardar.
     * @return JSON {@link VendedorDetailDTO}  - el vendedor guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el vendedor.
     */
    @POST
    public VendedorDTO createVendedor(VendedorDetailDTO vendedor) throws BusinessLogicException {
        return new VendedorDTO(logic.createVendedor(vendedor.toEntity()));
    }
    //TODO ALGO CON RESOURCE
    /**
     * <h1>GET /api/vendedores : Obtener todas las vendedores.</h1>
     * 
     * <pre>Busca y devuelve todas las vendedores que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las vendedores de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link VendedorDetailDTO} - 
     */
    @GET
    public List<VendedorDetailDTO> getVendedores() {
       return listEntity2DTO(logic.getVendedores());
    }
    
     private List<VendedorDetailDTO> listEntity2DTO(List<VendedorEntity> entityList) {
        List<VendedorDetailDTO> list = new ArrayList<>();
        for (VendedorEntity entity : entityList) {
            list.add(new VendedorDetailDTO(entity));
        }
        return list;
    }
    
     /**
     * <h1>GET /api/vendedores/{id} : Obtener vendedor por id.</h1>
     * 
     * <pre>Busca vendedor con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve vendedor correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un vendedor con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del vendedor que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link VendedorDetailDTO} - el vendedor buscado
     */
    @GET
    @Path("{id: \\d+}")
    public VendedorDetailDTO getVendedor(@PathParam("id") Long id) {
        VendedorEntity entity = logic.getVendedor(id);
        if (entity == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        return new VendedorDetailDTO(entity);
    }
    
     /**
     * <h1>PUT /api/vendedores/{id} : Actualizar vendedor con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link VendedorDetailDTO}.
     * 
     * Actualiza el vendedor con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el vendedor con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un vendedor con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del vendedor que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param cliente {@link VendedorDetailDTO} 
     * @return JSON {@link VendedorDetailDTO} -
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el vendedor porque ya existe uno con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public VendedorDetailDTO updateVendedor(@PathParam("id") Long id, VendedorDetailDTO cliente) throws BusinessLogicException {
        return cliente;
    }
    
    /**
     * <h1>DELETE /api/vendedores/{id} : Borrar vendedor por id.</h1>
     * 
     * <pre>Borra vendedor con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el vendedor correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un vendedor con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de vendedor que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteVendedor(@PathParam("id") Long id) throws BusinessLogicException {
        // Void
        logic.deleteVendedor(id);
    }
}
