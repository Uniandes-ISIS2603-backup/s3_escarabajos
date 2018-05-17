/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;

/**
 * CarritoDTO Objeto de transferencia de datos del . Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "tipo": String
 *      ]
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "tipo": "Tarjeta de Credito"
 *   }
 *
 * </pre>
 *
 * @author jp.carreno
 */
public class MedioPagoDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private Long id;

    /**
     * Modela el tipo de medio de pago que uso el cliente.
     */
    private String tipo;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor por defecto.
     */
    public MedioPagoDTO() {
        //empty
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public MedioPagoDTO(MedioPagoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.tipo = entity.getTipo();
        }
    }

    /**
     * Devuelve el tipo
     *
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Convertir DTO a Entity
     *
     * @param entity Es la entidad que se le van a asignarlos valores del DTO
     * @return Un Entity con los valores del DTO
     */
    public MedioPagoEntity toEntity() {
        MedioPagoEntity entity = new MedioPagoEntity();
        entity.setId(this.id);
        entity.setTipo(this.tipo);
        return entity;
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
     * Modificar el id
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
