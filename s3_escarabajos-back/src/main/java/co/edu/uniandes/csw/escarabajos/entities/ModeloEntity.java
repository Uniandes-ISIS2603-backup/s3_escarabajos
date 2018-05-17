/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andres
 */
@Entity
public class ModeloEntity implements Serializable {

    /**
     * Atributo que representa el id del modelo. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa la referencia del modelo.
     */
    private String referencia;

    /**
     * Atributo que representa la marca del modelo.
     */
    private String marca;

    /**
     * Atributo que representa el tipo del modelo.
     */
    private String tipoModelo;

    /**
     * Atributo que representa la calificacion media del modelo.
     */
    private Double calificacionMedia;

    /**
     * Atributo que representa la fecha de creacion del modelo.
     */
    @Temporal(TemporalType.DATE)
    private Date creacion;

    /**
     * Atributo que representa los items que pertenecen al modelo.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mod_id")
    private List<ItemEntity> items = new ArrayList<>();

    /**
     * Atributo que representa las calificaciones hechas al modelo.
     */
    @PodamExclude
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private List<CalificacionEntity> calificaciones = new ArrayList<>();

    /**
     * Constructor vacio
     */
    public ModeloEntity() {
        //Constructor vacio
    }

    /**
     * Devuelve el tipo.
     *
     * @return tipo
     */
    public String getTipoModelo() {
        return tipoModelo;
    }

    /**
     * Modifica el tipo.
     *
     * @param tipoModelo nuevo tipo
     */
    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }

    /**
     * Devuelve la referencia.
     *
     * @return referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Modifica la referencia.
     *
     * @param referencia nueva referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Devuelve la marca.
     *
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Modifica la marca.
     *
     * @param marca nueva marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Devuelve la calificacion media.
     *
     * @return calificacionMedia
     */
    public double getCalificacionMedia() {
        return calificacionMedia;
    }

    /**
     * Modifica la calificacion media
     *
     * @param calificacionMedia nueva calificacion media
     */
    public void setCalificacionMedia(double calificacionMedia) {
        this.calificacionMedia = calificacionMedia;
    }

    /**
     * Devuelve el id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve las calificaciones.
     *
     * @return calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones.
     *
     * @param calificaciones nuevas calificaciones
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Devuelve los items.
     *
     * @return items
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * Modifica los items.
     *
     * @param items nuevos items
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    /**
     * Compara si dos objetos son iguales.
     *
     * @param obj objeto a comparar
     * @return boolean Si son iguales retorna true. De lo contrario retorna
     * false
     */
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
        final ModeloEntity other = (ModeloEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Metodo predeterminado hashCode.
     *
     * @return el hashCode creado
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * Devuelve la fecha de creacion.
     *
     * @return creacion
     */
    public Date getCreacion() {
        return creacion;
    }

    /**
     * Modifica la fecha de creacion.
     *
     * @param creacion nueva fecha de creacion
     */
    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

}
