/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

/**
 * ItemDTO Objeto de transferencia de datos de los items del carrito .Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "cantidad": number
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "cantidad": 2
 *   }
 *
 * </pre>
 **/
public class ItemDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela cuantos items de este tipo estan en el carrito
     */
    private Integer cantidad;
 
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    /**
     * Constructor por defecto
     */
    public ItemDTO(){
        
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
