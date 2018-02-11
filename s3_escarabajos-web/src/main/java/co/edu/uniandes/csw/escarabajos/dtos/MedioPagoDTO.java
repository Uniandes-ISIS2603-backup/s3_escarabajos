/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

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
 * @author Mateo
 */
public class MedioPagoDTO {
    
    //-----------------------------------------------------------
    // Conctantes
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
    
    /**
     * modela el precio total de la transaccion
     */
    private double dineroTransaccion;
    /**
     *  modela el numero de tarjeta de credito del cliente que realizo la transaccion
     */
    private int numeroTarjeta;
    
    /**
     *  modela el tipo de medio de pago que uso el cliente
     */
    private String tipo;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    
    public MedioPagoDTO(double dineroTransaccion, int numeroTarjeta, String tipo) {
        this.dineroTransaccion = dineroTransaccion;
        this.numeroTarjeta = numeroTarjeta;
        this.tipo = tipo;
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    
    public double getDineroTransaccion() {
        return dineroTransaccion;
    }

    public void setDineroTransaccion(double dineroTransaccion) {
        this.dineroTransaccion = dineroTransaccion;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
