/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.carreno
 */
@Entity
public class MedioPagoEntity implements Serializable {
   
    /**
     * modela el tipo de pago por pse
     */
    public final static String TIPO_PSE = "pse";
    
    /**
     * modela el tipo de pago por paypal
     */
    public final static String TIPO_PAYPAL = "paypal";
    
    /**
     * modela el tipo de pago por tarjeta de credito
     */
    public final static String TIPO_TARJETA_CREDITO = "tarjeta de credito";
    
    /**
     * modela el tipo de pago por tarjeta debito
     */
    public final static String TIPO_TARJETA_DEBITO = "tarjeta debito";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tipo;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @OneToOne
    private FacturaEntity factura;

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the factura
     */
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
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
        final MedioPagoEntity other = (MedioPagoEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
