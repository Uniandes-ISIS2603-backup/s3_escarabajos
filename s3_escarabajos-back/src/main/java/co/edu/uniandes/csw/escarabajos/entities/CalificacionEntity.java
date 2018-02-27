/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 *
 * @author n.gaitan
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    
    private String comentario;
    
    private double puntaje;
    
    @ManyToOne( cascade = CascadeType.PERSIST )
    private ModeloEntity modelo;
    
        
   @ManyToOne( cascade = CascadeType.PERSIST )
    private ClienteEntity cliente;
    
    public String getComentario()
    {
        return comentario;
    }
    public double getPuntaje()
    {
        return puntaje;
    }
    public void setPuntaje(double punt)
    {
        puntaje = punt;
    }
    public void setComentario(String com)
    {
        comentario = com;
    }

    public ModeloEntity getModelo() {
        return modelo;
    }

    public void setModelo(ModeloEntity modelo) {
        this.modelo = modelo;
    }

    public ClienteEntity getClientes() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
}
