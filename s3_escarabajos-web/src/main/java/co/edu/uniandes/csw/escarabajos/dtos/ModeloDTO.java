/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;

/**
 * ModeloDTO Objeto de transferencia de datos del modelo de la bicicleta. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
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
 *      "marca": "BMXTREME",
 *      "referencia": "BMEXTREME-MTN-2017",
 *      "calificacionMedia": 4.52,
 *      "tipoModelo": "Accesorio"
 *   }
 *
 * </pre>
 *
 * @author Andres
 */
public class ModeloDTO
{
   //-----------------------------------------------------------
   // Atributos
   //-----------------------------------------------------------
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
    private double calificacionMedia;
    /**
     * Atributo que modela la el tipo de modelo
     */
    private String tipoModelo;
     
   //-----------------------------------------------------------
   // Constructores
   //-----------------------------------------------------------
    /**
     * Constructor por defecto
     */
    public ModeloDTO() 
    {

    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param modelo: Es la entidad que se va a convertir a DTO
     */
    public ModeloDTO(ModeloEntity modelo) 
    {
        this.id = modelo.getId();
        this.marca = modelo.getMarca();
        this.referencia = modelo.getReferencia();
        this.calificacionMedia = modelo.getCalificacionMedia();
        this.tipoModelo = modelo.getTipoModelo();
    }
   //-----------------------------------------------------------
   // Métodos
   //-----------------------------------------------------------
    
     public String getReferencia()
    {
        return referencia;
    }
    public String getMarca()
    {
        return marca;
    }
    public double getCalificacionMedia()
    {
        return calificacionMedia;
    }
    public void setReferencia(String referencia)
    {
        this.referencia = referencia;
    }
    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    public void setCalificacionMedia(double cal)
    {
        this.calificacionMedia = cal;
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
    
     /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ModeloEntity toEntity() {
        ModeloEntity entity = new ModeloEntity();
        entity.setId(this.id);
        entity.setCalificacionMedia(this.calificacionMedia);
        entity.setMarca(this.marca);
        entity.setReferencia(this.referencia);
        entity.setTipoModelo(this.getTipoModelo());
        return entity;
    }
}
