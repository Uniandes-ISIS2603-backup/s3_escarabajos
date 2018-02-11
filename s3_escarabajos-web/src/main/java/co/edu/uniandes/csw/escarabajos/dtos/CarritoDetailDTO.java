/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.util.ArrayList;

/**
 *
 * @author Mateo
 */
public class CarritoDetailDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela los items que el cliente va a comprar
     */
    private ArrayList<BicicletaDTO> items;
    
    /**
     * modela el cliente dueo del carrito;
     * @param items
     */
    //private ClienteDTO cliente;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public CarritoDetailDTO(ArrayList<BicicletaDTO> items) {
        this.items = items;
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    
    public ArrayList<BicicletaDTO> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<BicicletaDTO> items) {
        this.items = items;
    }

}
