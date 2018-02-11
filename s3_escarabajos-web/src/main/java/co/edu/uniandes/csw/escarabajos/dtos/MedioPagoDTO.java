/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

/**
 *
 * @author Mateo
 */
public class MedioPagoDTO {
    
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
