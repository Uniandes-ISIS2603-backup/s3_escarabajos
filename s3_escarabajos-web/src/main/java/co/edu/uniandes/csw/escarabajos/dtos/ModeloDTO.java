/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;

/**
 * ModeloDTO Objeto de transferencia de datos del modelo de la bicicleta. Los
 * DTO contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "marca": String,
 *      "referencia": String,
 *      "calificacionMedia": double,
 *      "tipoModelo":String
 *
 *   }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "marca": "BMXTREME",
 *      "referencia": "BMEXTREME-MTN-2017",
 *      "calificacionMedia": 4.50,
 *      "tipoModelo": "Accesorio"
 *   }
 *
 * </pre>
 *
 * @author Andres
 */
public class ModeloDTO {

    /**
     * Atributo que modela el id unico del modelo
     */
    private Long id;
    /**
     * Atributo que modela la marca del modelo
     */
    private String marca;
    /**
     * Atributo que modela la referencia del modelo
     */
    private String referencia;

    /**
     * Atributo que modela la calificacionMedia del modelo
     */
    private Double calificacionMedia;
    /**
     * Atributo que modela la el tipo de modelo
     */
    private String tipoModelo;

    /**
     * Constructor por defecto
     */
    public ModeloDTO() {
        //empty
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param modelo: Es la entidad que se va a convertir a DTO
     */
    public ModeloDTO(ModeloEntity modelo) {
        if (modelo != null) {
            this.id = modelo.getId();
            this.marca = modelo.getMarca();
            this.referencia = modelo.getReferencia();
            this.calificacionMedia = modelo.getCalificacionMedia();
            this.tipoModelo = modelo.getTipoModelo();
        }
    }
    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

   
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ModeloEntity toEntity() {
        ModeloEntity entity = new ModeloEntity();
        entity.setId(this.getId());
        entity.setCalificacionMedia(this.getCalificacionMedia());
        entity.setMarca(this.getMarca());
        entity.setReferencia(this.getReferencia());
        entity.setTipoModelo(this.getTipoModelo());
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the calificacionMedia
     */
    public Double getCalificacionMedia() {
        return calificacionMedia;
    }

    /**
     * @param calificacionMedia the calificacionMedia to set
     */
    public void setCalificacionMedia(Double calificacionMedia) {
        this.calificacionMedia = calificacionMedia;
    }

    /**
     * @return the tipoModelo
     */
    public String getTipoModelo() {
        return tipoModelo;
    }

    /**
     * @param tipoModelo the tipoModelo to set
     */
    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }
}
