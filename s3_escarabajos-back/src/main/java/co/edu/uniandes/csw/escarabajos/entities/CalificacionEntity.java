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
 * @author n.gaitan
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long id;

    public String comentario;
    
    public double puntaje;
    
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
       public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
}
