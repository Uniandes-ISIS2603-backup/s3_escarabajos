/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jp.carreno
 */
@Entity
public class TransferenciaEntity implements Serializable {

    @Id
    private Long id;
    private String dineroT;
    private String usuarioT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDinero() {
        return dineroT;
    }

    public String getUsuario() {
        return usuarioT;
    }

    public void setDinero(String dineroT) {
        this.dineroT = dineroT;
    }

    public void setUsuario(String usuarioT) {
        this.usuarioT = usuarioT;
    }

    
    
}
