/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.util.ArrayList;

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
