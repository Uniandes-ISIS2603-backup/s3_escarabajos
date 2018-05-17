/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;

/**
 * Clase que extiende de {@link FacturaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la factura vaya a la documentacion de {@link FacturaDTO}
 *
 * Al serializarse como JSON esta clase implementa la siguiente factura: <br>
 * <pre>
 * {
 * "id": Long,
 * "dinero": Double
 * </pre> Por ejemplo una factura se representa asi:<br>
 *
 * <pre>
 *   {
 * "type": "clienteDetailDTO",
 * "id": "4444",
 * "dinero": "20000",
 * }
 * </pre>
 * @author jp.carreno
 */
public class FacturaDetailDTO extends FacturaDTO {

    /**
     * Modela el reclamo de una factura.
     */
    private ReclamoEntity reclamo;

    /**
     * Modela el medio de pago de una factura.
     */
    private MedioPagoEntity medioDePago;

    /**
     * Modela el cliente de una factura.
     */
    private ClienteEntity cliente;

    /**
     * Constructor por defecto
     */
    public FacturaDetailDTO() {
        //Empty
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de factura a partir de la cual se construye el
     * objeto
     */
    public FacturaDetailDTO(FacturaEntity entity) {
        super(entity);
        this.cliente = entity.getCliente();
        this.medioDePago = entity.getMedioDePago();
        this.reclamo = entity.getReclamo();
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity facturaE = super.toEntity();
        facturaE.setCliente(this.cliente);
        facturaE.setMedioDePago(this.getMedioDePago());
        facturaE.setReclamo(this.reclamo);
        return facturaE;
    }

    /**
     * Devuelve el reclamo.
     *
     * @return reclamo
     */
    public ReclamoEntity getReclamo() {
        return reclamo;
    }

    /**
     * Modifuca el reclamo.
     *
     * @param reclamo nuevo reclamo
     */
    public void setReclamo(ReclamoEntity reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * Devuelve el medio de pago.
     *
     * @return medioDePago
     */
    public MedioPagoEntity getMedioDePago() {
        return medioDePago;
    }

    /**
     * Modifica el medio de pago.
     *
     * @param medioDePago nuevo medio De Pago
     */
    public void setMedioDePago(MedioPagoEntity medioDePago) {
        this.medioDePago = medioDePago;
    }

    /**
     * Devuelve el cliente.
     *
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente.
     *
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
