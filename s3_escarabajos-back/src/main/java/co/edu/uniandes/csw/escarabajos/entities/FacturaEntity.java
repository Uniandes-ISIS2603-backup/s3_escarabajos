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
public class FacturaEntity extends BaseEntity implements Serializable {

    
    private double dineroT;
    private String usuarioT;

    public double getDinero() {
        return dineroT;
    }

    public String getUsuario() {
        return usuarioT;
    }

    public void setDinero(double dineroT) {
        this.dineroT = dineroT;
    }

    public void setUsuario(String usuarioT) {
        this.usuarioT = usuarioT;
    }

    
    
}
