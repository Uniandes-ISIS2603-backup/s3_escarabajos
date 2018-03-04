/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class ItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double precio;
    
    private String color;
    
    @PodamExclude
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FotoEntity> album = new ArrayList<>();
    
    private Long modeloId;
    
    
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
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the album
     */
    public List<FotoEntity> getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(List<FotoEntity> album) {
        this.album = album;
    }

    /**
     * @return the modeloId
     */
    public long getModeloId() {
        return modeloId;
    }

    /**
     * @param modeloId the modeloId to set
     */
    public void setModeloId(long modeloId) {
        this.modeloId = modeloId;
    }
 
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemEntity other = (ItemEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
}
