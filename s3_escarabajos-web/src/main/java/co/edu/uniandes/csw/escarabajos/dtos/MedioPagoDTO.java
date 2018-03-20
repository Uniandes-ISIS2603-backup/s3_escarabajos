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
 *      "dineroTransaccion": double,
 *      "numeroTarjeta": int,
 *      "tipo": String
 *      ]
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "dineroTransaccion": 100000,
 *      "numeroTarjeta": 1018505033,
 *      "tipo": "Tarjeta de Credito"
 *   }
 *
 * </pre>
 *
 * @author jp.carreno
 */
public class MedioPagoDTO {
    
    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    
    /**
     * modela el tipo de pago por pse
     */
    public final static String TIPO_PSE = "pse";
    
    /**
     * modela el tipo de pago por paypal
     */
    public final static String TIPO_PAYPAL = "paypal";
    
    /**
     * modela el tipo de pago por tarjeta de credito
     */
    public final static String TIPO_TARJETA_CREDITO = "tarjeta de credito";
    
    /**
     * modela el tipo de pago por tarjeta debito
     */
    public final static String TIPO_TARJETA_DEBITO = "tarjeta debito";
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    //TODO: no puede ser long debe ser Long. arreglar también set/get
    private long id;
    /**
     *  modela el numero de tarjeta de credito del cliente que realizo la transaccion
     */
   // private int numeroTarjeta;
    
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

    MedioPagoDTO(MedioPagoEntity entity) {
        //TODO: entity podría ser null
        this.id = entity.getId();
       // this.numeroTarjeta = entity.getNumeroTarjeta();
        this.tipo = entity.getTipo();
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Falta hacerlo TODO
    MedioPagoEntity toEntity() {
        MedioPagoEntity entity = new MedioPagoEntity();
        return entity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
