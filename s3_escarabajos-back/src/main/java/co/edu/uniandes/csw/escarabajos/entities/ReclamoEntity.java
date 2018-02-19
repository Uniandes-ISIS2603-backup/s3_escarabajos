/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

/**
 *
 * @author n.gaitan
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReclamoEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<String> pics;
    private String mensaje;
    private String razon;
    
    public String getMensaje()
    {
        return mensaje;
    }
    public void setMensaje(String msj)
    {
        mensaje = msj;
    }
     public String getRazon()
    {
        return razon;
    }
    public void setRazon(String raz)
    {
        razon = raz;
    }
     public Integer getId()
    {
        return id;
    }
     public void setId(Integer id)
     {
         this.id = id;
     }
    public List<String> getPics()
    {
        return pics;
    }
    public void setPics(List<String> pics)
    {
        this.pics = pics;
    }
     
}