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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.carreno
 */
@Entity
public class FacturaEntity implements Serializable {

    /**
     * Atributo que representa el id de la factura. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el dinero de la factura.
     */
    private Double dinero;

    /**
     * Atributo que representa el reclamo hecho en la factura.
     */
    @PodamExclude
    @OneToOne(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ReclamoEntity reclamo;

    /**
     * Atributo que representa el medio de pago con el que se pago la factura.
     */
    @PodamExclude
    @OneToOne(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private MedioPagoEntity medioDePago;

    /**
     * Atributo que representa el cliente de la factura.
     */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Atributo que representa los items de la factura.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "FACTURA_ITEMS", joinColumns = @JoinColumn(name = "FACTURA_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<ItemEntity> items = new ArrayList<>();

    /**
     * Devuelve el reclamo.
     *
     * @return reclamo
     */
    public ReclamoEntity getReclamo() {
        return reclamo;
    }

    /**
     * Modifica el reclamo.
     *
     * @param reclamo nuevo reclamo
     */
    public void setReclamo(ReclamoEntity reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * Devuelve el cliente.
     *
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente.
     *
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve el medio de pago.
     *
     * @return medioDePago
     */
    public MedioPagoEntity getMedioDePago() {
        return medioDePago;
    }

    /**
     * Modifica el medio de pago.
     *
     * @param medioDePago nuevo medio de pago
     */
    public void setMedioDePago(MedioPagoEntity medioDePago) {
        this.medioDePago = medioDePago;
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
     * Devuelve el id
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
        final FacturaEntity other = (FacturaEntity) obj;
        return Objects.equals(this.getId(), other.getId());
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
     * Devuelve el dinero.
     *
     * @return dinero
     */
    public Double getDinero() {
        return dinero;
    }

    /**
     * Modifica el dinero.
     *
     * @param dinero nuevo dinero
     */
    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }
}
