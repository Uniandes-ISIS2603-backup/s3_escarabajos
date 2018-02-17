/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jp.carreno
 */
@Entity
public class MedioPagoEntity extends BaseEntity implements Serializable {
   
    private double dineroT;
    
    private int numeroTarjeta;
    
    private String tipo;

    public double getDineroT() {
        return dineroT;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDineroT(double dineroT) {
        this.dineroT = dineroT;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
  
    
}
