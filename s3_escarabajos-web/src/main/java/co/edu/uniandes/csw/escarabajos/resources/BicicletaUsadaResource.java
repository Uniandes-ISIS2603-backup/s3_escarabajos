package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaUsadaDTO;
import co.edu.uniandes.csw.escarabajos.ejb.BicicletaUsadaLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
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
 * <pre>Clase que implementa el recurso "bicisUsadas".
 * URL: /api/clientes/{idCliente}/bicis
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "bicisUsadas".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author c.santacruza
 */
@Path("clientes/{idCliente: \\d+}/bicis")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BicicletaUsadaResource {

    /**
     * Inyecta la logica de bicicleta usada.
     */
    @Inject
    BicicletaUsadaLogic logic;

    /**
     * Inyecta la logica de modelo.
     */
    @Inject
    ModeloLogic modeloLogic;

    private List<BicicletaUsadaDTO> listEntity2DTO(List<BicicletaUsadaEntity> entityList) {
        List<BicicletaUsadaDTO> list = new ArrayList<>();
        for (BicicletaUsadaEntity entity : entityList) {
            list.add(new BicicletaUsadaDTO(entity));
        }
        return list;
    }

    /**
     * <h1>POST /api/clientes/{idCliente}/bicis : Crear una bicicleta
     * usada.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link BicicletaUsadaDTO}.
     *
     * Crea una nueva bicicleta usada con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva bicicleta usada .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la bicicleta usada.
     * </code>
     * </pre>
     *
     * @param idVendedor
     * @param bici {@link BicicletaUsadaDTO} - La bicicleta usada que se desea
     * guardar.
     * @return JSON {@link BicicletaUsadaDTO} - La bicicleta usada guardada con
     * el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la bicicleta usada.
     */
    @POST
    public BicicletaUsadaDTO createBicicletaUsada(@PathParam("idCliente") Long idVendedor, BicicletaUsadaDTO bici) throws BusinessLogicException {
        BicicletaUsadaEntity temp = logic.createBicicleta(idVendedor, bici.toEntity());
        return new BicicletaUsadaDTO((BicicletaUsadaEntity) modeloLogic.addItem(temp, temp.getModeloId()));
    }

    /**
     * <h1>GET /api/clientes/{idCliente}/bicis : Obtener todas las bicicletas
     * usadas.</h1>
     *
     * <pre>Busca y devuelve todas las bicicletas usadas que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las bicicletas usadas de la aplicacion.</code>
     * </pre>
     *
     * @param idVendedor
     * @return JSONArray {@link BicicletaUsadaDTO} - Las bicicletas usadas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @GET
    public List<BicicletaUsadaDTO> getBicicletaUsadasDelVendedor(@PathParam("idCliente") Long idVendedor) throws BusinessLogicException {
        return listEntity2DTO(logic.getBicicletasDelVendedor(idVendedor));
    }

    /**
     * <h1>GET /api/clientes/{idCliente}/bicis/{id} : Obtener bicicleta usada
     * por id.</h1>
     *
     * <pre>Busca la bicicleta usada con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la bicicleta usada correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una bicicleta usada con el id dado.
     * </code>
     * </pre>
     *
     * @param idVendedor
     * @param id Identificador de la bicicleta usada que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaUsadaDTO} - La bicicleta usada buscada
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public BicicletaUsadaDTO getBicicletaUsada(@PathParam("idCliente") Long idVendedor, @PathParam("id") Long id) throws BusinessLogicException, WebApplicationException {
        BicicletaUsadaEntity entity = logic.getBicicleta(idVendedor, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cliente/" + idVendedor + "/bicis/" + id + " no existe.", 404);
        }
        return new BicicletaUsadaDTO(entity);
    }

    /**
     * <h1>PUT /api/clientes/{idCliente}/bicis/{id} : Actualizar bicicleta usada
     * con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BicicletaUsadaDTO}.
     *
     * Actualiza la bicicleta usada con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la bicicleta usada con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta usada con el id dado.
     * </code>
     * </pre>
     *
     * @param idVendedor
     * @param id Identificador de la bicicleta usada que se desea
     * actualizar.Este debe ser una cadena de dígitos.
     * @param bici {@link BicicletaUsadaDTO} La bicicleta usada que se desea
     * guardar.
     * @return JSON {@link BicicletaUsadaDTO} - La bicicleta usada guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la bicicleta usada
     * porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public BicicletaUsadaDTO updateBicicletaUsada(@PathParam("idCliente") Long idVendedor, @PathParam("id") Long id, BicicletaUsadaDTO bici) throws BusinessLogicException {
        bici.setId(id);
        BicicletaUsadaEntity entity = logic.getBicicleta(idVendedor, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cliente/" + idVendedor + "/bicis/" + id + " no existe.", 404);
        }
        return new BicicletaUsadaDTO(logic.updateBicicleta(idVendedor, bici.toEntity()));
    }

    /**
     * <h1>DELETE /api/clientes/{idCliente}/bicis/{id} : Borrar bicicleta usada
     * por id.</h1>
     *
     * <pre>Borra la bicicleta usada con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la bicicleta usada correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta usada con el id dado.
     * </code>
     * </pre>
     *
     * @param idVendedor
     * @param id Identificador de la bicicleta usada que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBicicletaUsada(@PathParam("idCliente") Long idVendedor, @PathParam("id") Long id) throws BusinessLogicException {
        BicicletaUsadaEntity entity = logic.getBicicleta(idVendedor, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cliente/" + idVendedor + "/bicis/" + id + " no existe.", 404);
        }
        logic.deleteBicicleta(idVendedor, id);
    }

}
