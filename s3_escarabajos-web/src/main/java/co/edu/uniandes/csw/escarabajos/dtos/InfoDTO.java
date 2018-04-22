/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

/**
 * InfoDTO Objeto de transferencia de datos de los filtros. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "nombre": String
 *   }
 * </pre>
 * Por ejemplo un item se representa asi:<br>
 * 
 * <pre>
 *   {
 *      "nombre":"Rojo"
 *   }
 * </pre>
 * @author Andres
 */
public class InfoDTO {
    
    private String nombre;
    
     /**
     * Constructor por defecto
     */
    public InfoDTO() {
    }
    public InfoDTO(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
