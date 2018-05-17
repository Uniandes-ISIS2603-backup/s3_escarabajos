/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.List;

/**
 * Clase que extiende de {@link CalificacionDTO} para manejar la transformacion
 * entre los objetos JSON y las Entidades de la base de datos.
 *
 * Al serializarse como JSON esta clase implementa el siguiente reclamo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "mensaje": String,
 *      "razon": double,
 *      "factura": {@link FacturaDTO},
 *      "album": [{@String}]
 *   }
 * </pre> Por ejemplo un reclamo se representa asi:<br>
 * <pre>
 * {
 *      "id": 1,
 *      "mensaje": "Color incorrecto",
 *      "razon": "Cambio de bicicleta",
 *      "factura": {},
 *      "album": []
 * }
 * </pre>
 */
public class ReclamoDetailDTO extends ReclamoDTO {

    /**
     * Modela la factura del reclamo.
     */
    private FacturaDTO factura;

    /**
     * Modela el album de reclamo.
     */
    private List<String> album;

    /**
     * Modela el cliente del reclamo.
     */
    private ClienteDTO cliente;

    /**
     * Constructor por defecto.
     */
    public ReclamoDetailDTO() {
        //empty
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el
     * objeto
     */
    public ReclamoDetailDTO(ReclamoEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getFactura() != null) {
                factura = new FacturaDTO(entity.getFactura());
            }
            if (entity.getCliente() != null) {
                cliente = new ClienteDTO(entity.getCliente());
            }
        }

    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    public ReclamoEntity toEntiy() {
        ReclamoEntity entity = super.toEntity();
        if (getFactura() != null) {
            entity.setFactura(factura.toEntity());
        }
        if (getCliente() != null) {
            entity.setCliente(cliente.toEntity());
        }
        return entity;
    }

    /**
     * Devuelve el album
     *
     * @return album
     */
    public List<String> getAlbum() {
        return album;
    }

    /**
     * Modifica el album
     *
     * @param album nuevo album
     */
    public void setAlbum(List<String> album) {
        this.album = album;
    }

    /**
     * Retorna la factura a la que hace referencia el reclamo
     *
     * @return la factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * Modifica la factura a la que hace referencia el reclamo
     *
     * @param factura la nueva factura
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * Devuelve el cliente asociado al reclamo
     *
     * @return cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente
     *
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

}
