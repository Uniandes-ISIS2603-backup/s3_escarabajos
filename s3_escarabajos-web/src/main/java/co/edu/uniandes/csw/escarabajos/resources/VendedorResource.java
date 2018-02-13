/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ClienteDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.VendedorDTO;
import co.edu.uniandes.csw.escarabajos.dtos.VendedorDetailDTO;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author s.beltran
 */
public class VendedorResource {
    /**
     * <h1>POST /api/vendedores : Crear un vendedor.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link CityDetailDTO}.
     * 
     * Crea una nueva ciudad con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva ciudad .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la ciudad.
     * </code>
     * </pre>
     * @param vendedor {@link VendedorDetailDTO} - 
     * @return JSON {@link VendedorDetailDTO}  - 
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public VendedorDTO createVendedor(VendedorDetailDTO cliente) throws BusinessLogicException {
        return cliente;
    }
    /**
     * <h1>GET /api/clientes : Obtener todas las vendedores.</h1>
     * 
     * <pre>Busca y devuelve todas las vendedores que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las ciudades de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link VendedorDetailDTO} - 
     */
    @GET
    public List<VendedorDetailDTO> getVendedores() {
        return new ArrayList<VendedorDetailDTO>();
    }
    
     /**
     * <h1>GET /api/vendedor/{id} : Obtener vendedor por id.</h1>
     * 
     * <pre>Busca vendedor con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve cliente correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un cliente con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la vendedor que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link VendedorDetailDTO} - La ciudad buscada
     */
    @GET
    @Path("{id: \\d+}")
    public VendedorDetailDTO getVendedor(@PathParam("id") Long id) {
        return null;
    }
    
     /**
     * <h1>PUT /api/vendedores/{id} : Actualizar cliente con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CityDetailDTO}.
     * 
     * Actualiza la ciudad con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la ciudad con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la ciudad que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param cliente {@link ClienteDetailDTO} 
     * @return JSON {@link ClienteDetailDTO} -
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la ciudad porque ya existe una con ese nombre.
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
     * 200 OK Elimina la ciudad correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de vendedor que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteVendedor(@PathParam("id") Long id) {
        // Void
    }
}
