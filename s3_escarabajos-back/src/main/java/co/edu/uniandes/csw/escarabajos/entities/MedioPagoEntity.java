/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.Objects;
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
     * Constante que modela el tipo de pago por pse.
     */
    public final static String TIPO_PSE = "PSE";

    /**
     * Constante que modela el tipo de pago por paypal.
     */
    public final static String TIPO_PAYPAL = "PayPal";

    /**
     * Constante que modela el tipo de pago por tarjeta de credito.
     */
    public final static String TIPO_TARJETA_CREDITO = "Tarjeta de Credito";

    /**
     * Constante que modela el tipo de pago por tarjeta debito.
     */
    public final static String TIPO_TARJETA_DEBITO = "Tarjeta Debito";

    /**
     * Atributo que representa el id del medio de pago. id autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el tipo del medio de pago.
     */
    private String tipo;

    /**
     * Atributo que representa el cliente del medio de pago.
     */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Atributo que representa la factura del medio de pago.
     */
    @PodamExclude
    @OneToOne
    private FacturaEntity factura;

    /**
     * Devuelve el tipo.
     *
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        if (tipo.equalsIgnoreCase(TIPO_PAYPAL)) {
            this.tipo = TIPO_PAYPAL;
        } else if (tipo.equalsIgnoreCase(TIPO_PSE)) {
            this.tipo = TIPO_PSE;
        } else if (tipo.equalsIgnoreCase(TIPO_TARJETA_CREDITO)) {
            this.tipo = TIPO_TARJETA_CREDITO;
        } else if (tipo.equalsIgnoreCase(TIPO_TARJETA_DEBITO)) {
            this.tipo = TIPO_TARJETA_DEBITO;
        }
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
     * Devuelve la factura.
     *
     * @return factura
     */
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
     * Modifica la factura.
     *
     * @param factura nueva factura
     */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
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
        final MedioPagoEntity other = (MedioPagoEntity) obj;
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
