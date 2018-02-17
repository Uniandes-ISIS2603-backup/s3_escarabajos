/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

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
     * @param vendedor {@link VendedorDetailDTO} - 
     * @return JSON {@link VendedorDetailDTO}  - 
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el vendedor.
     */
    @POST
    public VendedorDTO createVendedor(VendedorDetailDTO vendedor) throws BusinessLogicException {
        return vendedor;
    }
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
        return new ArrayList<VendedorDetailDTO>();
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
        return null;
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
     public void deleteVendedor(@PathParam("id") Long id) {
        // Void
    }
}
