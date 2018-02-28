/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double dinero;
    @PodamExclude
    @OneToMany(mappedBy = "Factura", cascade = CascadeType.ALL)
    private List<ReclamoEntity> reclamos;
    
    @PodamExclude
    @OneToOne(mappedBy = "Factura")
    private MedioPagoEntity medioDePago;
    
    @PodamExclude
    @OneToOne(mappedBy = "Factura")
    private CarritoEntity carrito;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * @return the reclamos
     */
    public List<ReclamoEntity> getReclamos() {
        return reclamos;
    }

    /**
     * @param reclamos the reclamos to set
     */
    public void setReclamos(List<ReclamoEntity> reclamos) {
        this.reclamos = reclamos;
    }

    
    /**
     * @return the carrito
     */
    public CarritoEntity getCarrito() {
        return carrito;
    }

    /**
     * @param carrito the carrito to set
     */
    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the medioDePago
     */
    public MedioPagoEntity getMedioDePago() {
        return medioDePago;
    }

    /**
     * @param medioDePago the medioDePago to set
     */
    public void setMedioDePago(MedioPagoEntity medioDePago) {
        this.medioDePago = medioDePago;
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

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * @return the dinero
     */
    public Double getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(Double dinero) {
        this.dinero = dinero;
    }
}
