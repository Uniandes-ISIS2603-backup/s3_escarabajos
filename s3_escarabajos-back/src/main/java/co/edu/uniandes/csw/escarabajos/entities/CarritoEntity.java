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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mateo
 */
@Entity
public class CarritoEntity implements Serializable {

    //----------------------------------------------------
    // Atributos
    //----------------------------------------------------
    /**
     * Atributo que representa el id del carrito. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el precio total del carrito. inicializado en 0
     */
    private Double precioTotal = 0.0;

    /**
     * Atributo que representa la lista de items del carrito.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "CAR_ITEMS", joinColumns = @JoinColumn(name = "CAR_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<ItemEntity> items = new ArrayList<>();

    /**
     * Atributo que representa el cliente dueño del carrito.
     */
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    //----------------------------------------------------
    // Getters y Setters
    //----------------------------------------------------
    /**
     * Devuelve el precio total del carrito.
     *
     * @return el precio total del carrito
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Modifica el precio total.
     *
     * @param precioTotal el nuevo precioTotal
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Devuelve la lista de items del carrito.
     *
     * @return la lista de items del carrito
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * Modifica la lista de items del carrito.
     *
     * @param items la nueva lista de items
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    /**
     * Devuelve el cliente dueño del carrito.
     *
     * @return el cliente dueño del carrito
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente del carrito
     *
     * @param cliente el nuevo cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve el id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
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
        final CarritoEntity other = (CarritoEntity) obj;
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

}
