/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
//DONE: borrar lo que no se necesite
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "preciTotal": double
 *      
 *   }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 3,
 *      "precioTotal": 0.0
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
     * modela el id unico
     */
    private Long id;
    /**
     * modela el precio de todos los itemps a comprar
     */
    private Double precioTotal=0.0;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    
    /**
     * Constructor por defecto
     */
    public CarritoDTO() {
        
    }
    //TODO: actualizar la documentación
        /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public CarritoDTO(CarritoEntity carrito) {
        //TODO: carrito podría ser null
        this.id = carrito.getId();
        this.precioTotal = carrito.getPrecioTotal();
    }
    

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    
    /**
     * retorna el precio de todos los items
     * @return 
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }
    /**
     * asigna el precio total
     * @param precioTotal 
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    
    /**
     * lo convierte en un CarritoEntity
     * @return 
     */
    public CarritoEntity toEntity() {
        
        CarritoEntity entity = new CarritoEntity();
        entity.setId(this.id);
        entity.setPrecioTotal(this.precioTotal);
        return entity;
    }
}
