/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author c.santacruza
 */
@Entity
public class BicicletaUsadaEntity extends BicicletaEntity implements Serializable {

    private String facturaOriginal;
    private String estado;
    private double precioDeReventa;
    private VendedorEntity vendedor;

    public VendedorEntity getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
    }
    
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioDeReventa() {
        return precioDeReventa;
    }

    public void setPrecioDeReventa(double precioDeReventa) {
        this.precioDeReventa = precioDeReventa;
    }

    public String getFacturaOriginal() {
        return facturaOriginal;
    }

    public void setFacturaOriginal(String facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }

}
