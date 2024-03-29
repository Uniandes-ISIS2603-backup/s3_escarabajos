/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;

/**
 * ListaDeseosDTO Objeto de transferencia de datos del . Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precioTotal": double
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
public class ListaDeseosDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Modela el id unico.
     */
    private Long id;
    /**
     * Modela el precio de todos los itemps a comprar.
     */
    private Double precioTotal = 0.0;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor por defecto.
     */
    public ListaDeseosDTO() {
        // Do nothing because of JPA
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param listadeseos Es la entidad que se va a convertir a DTO
     */
    public ListaDeseosDTO(ListaDeseosEntity listadeseos) {

        if (listadeseos != null) {
            this.id = listadeseos.getId();
            this.precioTotal = listadeseos.getPrecioTotal();
        }
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    /**
     * Devuelve el precio de todos los items
     *
     * @return
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Modifica el precio total
     *
     * @param precioTotal nuevo precio
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Devuelve el id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    /**
     * lo convierte en un ListaDeseosEntity
     *
     * @return
     */
    public ListaDeseosEntity toEntity() {

        ListaDeseosEntity entity = new ListaDeseosEntity();
        entity.setId(this.id);
        entity.setPrecioTotal(this.precioTotal);
        return entity;
    }
}
