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
 *
 * @author jp.carreno
 */
public class FacturaDetailDTO extends FacturaDTO {

    private ReclamoEntity reclamo;

    private MedioPagoEntity medioDePago;
    
    private ClienteEntity cliente;
    
    /**
     * Constructor por defecto
     */
    public FacturaDetailDTO() {
        //vacio
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de factura a partir de la cual se construye el objeto
     */
    //TODO: qué pasa si entity es null
    public FacturaDetailDTO(FacturaEntity entity) {
       super(entity);
       //TODO: entity podría ser null
       this.cliente = entity.getCliente();
       this.medioDePago = entity.getMedioDePago();
       this.reclamo = entity.getReclamo();
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
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
     * @return the reclamos
     */
    public ReclamoEntity getReclamo() {
        return reclamo;
    }

    /**
     * @param reclamo the reclamo to set
     */
    public void setReclamo(ReclamoEntity reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * @return the medioDePago
     */
    public MedioPagoEntity getMedioDePago() {
        return medioDePago;
    }

    /**
     * @param medioDePago the medioDePago to set
     */
    public void setMedioDePago(MedioPagoEntity medioDePago) {
        this.medioDePago = medioDePago;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
}
