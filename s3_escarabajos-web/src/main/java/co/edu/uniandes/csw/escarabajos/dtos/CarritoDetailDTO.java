/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CarritoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CarritoDTO}
 * @author Mateo
 */
public class CarritoDetailDTO extends CarritoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela los items que el cliente va a comprar
     */
    private List<ItemDTO> items;
    
    /**
     * modela el cliente dueño del carrito;
     */
    private ClienteDTO cliente;
    
    /**
     * modela la factura que genera el carrito cuando se va a comprar.
     */
    private FacturaDTO factura;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public CarritoDetailDTO() {
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    public List<ItemDTO> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<ItemDTO> items) {
        this.items = items;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public FacturaDTO getFactura() {
        return factura;
    }

    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

}
