/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.AccesorioDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ListaDeseosDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ListaDeseosDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ListaDeseosLogic;
import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
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

/**
 * <pre>Clase que implementa el recurso "listadeseos".
 * URL: /api/listadeseoss
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "listadeseos".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author Mateo
 * @version 1.0
 */
@Path("listadeseoss")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ListaDeseosResource {
    
    //DONE: No entiendo para qué sirve esta clase. Cuántos listadeseoss hay?
    //esta clase era solo para el ciclo 1 par mostrar que listadeseos funcionaba bien sin depender del cliente, la que se va a usar es la otro
    @Inject
    ListaDeseosLogic logic;

    /**
     * <h1>POST /api/listadeseoss : Crear un listadeseos.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link AccesorioDTO}.
     * 
     * Crea una nuevo listadeseos vacio
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo listadeseos.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el listadeseos.
     * </code>
     * </pre>
     * @param listadeseos {@link ListaDeseosDTO} - el listadeseos que se desea guardar.
     * @return JSON {@link ListaDeseosDTO}  - el listadeseos guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el listadeseos.
     */
    
    @POST
    public ListaDeseosDTO createListaDeseos( ListaDeseosDTO listadeseos ) throws BusinessLogicException {
        
        ListaDeseosEntity temp = logic.createListaDeseos(listadeseos.toEntity());
        return new ListaDeseosDTO(temp);
    }

    /**
     * <h1>GET /api/listadeseoss/{id} : Obtener el listadeseos por id.</h1>
     * 
     * <pre>Busca el accesorio con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el listadeseos correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un listadeseos con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del listadeseos que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ListaDeseosDTO} - El listadeseos buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ListaDeseosDetailDTO getListaDeseos(@PathParam("id") Long id) {
        return new ListaDeseosDetailDTO( logic.findListaDeseos(id) );
    }
    
    /**
     * <h1>PUT /api/listadeseoss/{id} : Actualizar el listadeseos con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ListaDeseosDTO}.
     * 
     * Actualiza el listadeseos con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el listadeseos con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un listadeseos con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del listadeseos que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param listadeseos {@link ListaDeseosDTO} - El listadeseos que se desea guardar.
     * @return JSON {@link ListaDeseosDTO} - El listadeseos guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el listadeseos.
     */
    @PUT
    @Path("{id: \\d+}")
    public ListaDeseosDTO updateListaDeseos(@PathParam("id") Long id, ListaDeseosDTO listadeseos) throws BusinessLogicException {
        return new ListaDeseosDTO( logic.updateListaDeseos(listadeseos.toEntity()) );
    }
    
    /**
     *No existe metodo delete listadeseos porque un listadeseos nunca debe ser borrado 
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteAccesorio(@PathParam("id") Long id) throws BusinessLogicException {
    }
}
