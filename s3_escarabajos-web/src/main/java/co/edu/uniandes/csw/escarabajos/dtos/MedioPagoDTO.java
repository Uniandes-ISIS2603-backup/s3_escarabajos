/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tipo": String
 *      ]
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "tipo": "Tarjeta de Credito"
 *   }
 *
 * </pre>
 *
 * @author jp.carreno
 */
public class MedioPagoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    

    private Long id;
    
    /**
     *  modela el tipo de medio de pago que uso el cliente
     */
    private String tipo;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    
    public MedioPagoDTO() {
        
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------

    public MedioPagoDTO(MedioPagoEntity entity) {
        if(entity!=null){
            this.id = entity.getId();
            this.tipo = entity.getTipo();
        }
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MedioPagoEntity toEntity() {
        MedioPagoEntity entity = new MedioPagoEntity();
        entity.setId(this.id);
        entity.setTipo(this.tipo);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
