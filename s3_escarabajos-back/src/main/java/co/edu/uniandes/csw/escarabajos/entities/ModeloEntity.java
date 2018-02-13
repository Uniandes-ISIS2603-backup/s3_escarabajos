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
 * @author n.gaitan
 */
@Entity
public class ModeloEntity extends BaseEntity implements Serializable
{
    private String referencia;
    private String marca;
    private double calificacionMedia;
    
    public String getReferencia()
    {
        return referencia;
    }
    public String getMarca()
    {
        return marca;
    }
    public double getCalificacionMedia()
    {
        return calificacionMedia;
    }
    public void setReferencia(String referencia)
    {
        this.referencia = referencia;
    }
    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    public void setCalificacionMedia(double cal)
    {
        this.calificacionMedia = cal;
    }
}
