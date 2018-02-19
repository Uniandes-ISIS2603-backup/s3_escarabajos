/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "items": [
 *      {"id": number,
 *      "categoria: string,
 *      "marca": string,
 *      "color: string,
 *      "precio": double,
 *      "usada: boolean}
 *      ]
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "items": [
 *      {"id": 1,
 *      "categoria: BMX,
 *      "marca": We the People,
 *      "color: Negro,
 *      "precio": 3.499,
 *      "usada: false}
 *      ]
 *   }
 *
 * </pre>
 *
 * @author Mateo
 */

public class CarritoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    
    
    /**
     * modela el precio de todos los itemps a comprar
     */
    private double precioTotal;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    
    /**
     * Constructor por defecto
     */
    public CarritoDTO() {
        
    }
    
        /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public CarritoDTO(CarritoEntity carrito) {
        this.precioTotal = carrito.getPrecioTotal();
    }
    

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    
    public CarritoEntity toEntity() {
        
        CarritoEntity entity = new CarritoEntity();
        entity.setPrecioTotal(this.precioTotal);
        return entity;
    }
    
    
}
