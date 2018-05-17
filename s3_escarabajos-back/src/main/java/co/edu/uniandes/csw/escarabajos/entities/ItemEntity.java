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
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andres
 */
@Entity
public class ItemEntity implements Serializable {

    /**
     * Atributo que representa el id de un item. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el precio de un item.
     */
    private Double precio;

    /**
     * Atributo que representa el color de un item.
     */
    private String color;

    /**
     * Atributo que representa el album de fotos de un item.
     */
    @PodamExclude
    @ElementCollection
    @CollectionTable(name = "ITEM_ALBUM")
    @Lob
    private List<String> album = new ArrayList<>();

    /**
     * Atributo que representa el modelo de un item.
     */
    private Long modeloId;

    /**
     * Atributo que representa la categoria a la que pertenece el item.
     */
    private String categoria;

    /**
     * Atributo que representa si el item esta disponible para compra.
     */
    private Boolean disponible;

    /**
     * Atributo que representa el multiplicador del precio.
     */
    private Double multiplicador;

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
     * Devuelve el precio.
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.setPrecio((Double) precio);
    }

    /**
     * Devuelve el modelo.
     *
     * @return modeloId
     */
    public long getModeloId() {
        return modeloId;
    }

    /**
     * Modifica el modelo
     *
     * @param modeloId nuevo modelo
     */
    public void setModeloId(long modeloId) {
        this.setModeloId((Long) modeloId);
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
        final ItemEntity other = (ItemEntity) obj;
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
     * Devuelve el color.
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Modifica el color.
     *
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Devuelve la categoria.
     *
     * @return categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modifica la categoria.
     *
     * @param categoria nueva categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Modifica el precio.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Modifica el modelo.
     *
     * @param modeloId nuevo modelo
     */
    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    /**
     * Devuelve si esta disponible.
     *
     * @return disponible
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /**
     * Modifica si esta disponible.
     *
     * @param disponible nuevo valor de disponible
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Devuelve el album de fotos.
     *
     * @return album
     */
    public List<String> getAlbum() {
        return album;
    }

    /**
     * Modifica el album de fotos.
     *
     * @param album nuevo album de fotos
     */
    public void setAlbum(List<String> album) {
        this.album = album;
    }

    /**
     * Devuelve el multiplicador.
     *
     * @return multiplicador
     */
    public Double getMultiplicador() {
        return multiplicador;
    }

    /**
     * Modifica el multiplicador.
     *
     * @param multiplicador nuevo multiplicador
     */
    public void setMultiplicador(Double multiplicador) {
        this.multiplicador = multiplicador;
    }

}
