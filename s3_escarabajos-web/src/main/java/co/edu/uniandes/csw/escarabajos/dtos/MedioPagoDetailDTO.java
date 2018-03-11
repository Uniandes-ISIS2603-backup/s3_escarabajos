/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;

/**
 * Clase que extiende de {@link MedioPagoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link MedioPagoDTO}
 * @author jp.carreno
 */
public class MedioPagoDetailDTO extends MedioPagoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private ClienteEntity cliente;
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public MedioPagoDetailDTO(){
        //vacio
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de medio de pago a partir de la cual se construye el objeto
     */
    public MedioPagoDetailDTO(MedioPagoEntity entity) {
        super(entity);
       this.cliente = entity.getCliente();
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public MedioPagoEntity toEntity() {
        MedioPagoEntity medioPagoE = super.toEntity();
        medioPagoE.setCliente(this.cliente);
        return medioPagoE;
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
