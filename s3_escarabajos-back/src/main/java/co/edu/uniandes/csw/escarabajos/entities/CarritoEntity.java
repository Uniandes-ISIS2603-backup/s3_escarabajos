/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Mateo
 */
@Entity
public class CarritoEntity implements Serializable{
    
    //----------------------------------------------------
    // Atributos
    //----------------------------------------------------
    
    @Id
    private Long idCliente;
    
    private Double precioTotal;
    
    @OneToMany
    private ArrayList<ItemEntity> items;
    
    @OneToOne
    private ClienteEntity cliente;
    
    @OneToOne
    private FacturaEntity factura;    
    
    //----------------------------------------------------
    // Getters y Setters
    //----------------------------------------------------

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long id) {
        this.idCliente = id;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
        public ArrayList<ItemEntity> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemEntity> items) {
        this.items = items;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
    
    
    
}
