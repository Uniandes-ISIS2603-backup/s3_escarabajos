/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Andres
 */
@Entity
public class ModeloEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referencia;
    private String marca;
    private String tipoModelo;
    private double calificacionMedia;
    
    @PodamExclude
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private List<ItemEntity> items = new ArrayList<ItemEntity>();
    
   /* @PodamExclude
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
   */
    
    public ModeloEntity()
    {
        
    }
    /**
     * @return the tipoModelo
     */
    public String getTipoModelo() {
        return tipoModelo;
    }
    /**
     * @param tipoModelo the tipoModelo to set
     */
    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the calificacionMedia
     */
    public double getCalificacionMedia() {
        return calificacionMedia;
    }

    /**
     * @param calificacionMedia the calificacionMedia to set
     */
    public void setCalificacionMedia(double calificacionMedia) {
        this.calificacionMedia = calificacionMedia;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the items
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
