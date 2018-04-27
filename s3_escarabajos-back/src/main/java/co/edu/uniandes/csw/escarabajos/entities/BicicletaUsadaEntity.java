/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author c.santacruza
 */
@Entity
public class BicicletaUsadaEntity extends BicicletaEntity implements Serializable {
    
    /**
     * Atribuot q representa la factura orginal de la bicicleta.
     */
    private String facturaOriginal;
    /**
     * Atributo que representa el estado de la solicitud de compra de la bicicleta.
     */
    private String estado;
    
    /**
     * Atributo que representa al vendedor de esta bicicleta.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;
    
    /**
     * Devuelve el vendedor.
     * @return vendedor
     */
    public ClienteEntity getCliente() {
        return cliente;
    }
    /**
     * Modifica el vendedor.
     * @param cliente nuevo vendedor
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Devuelve el estado
     * @return estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * Modific el estado
     * @param estado nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Devielve la factura original
     * @return factura origianl
     */
    public String getFacturaOriginal() {
        return facturaOriginal;
    }
    /**
     * Modifica la factura original.
     * @param facturaOriginal  nueva factura originial
     */
    public void setFacturaOriginal(String facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }
}
