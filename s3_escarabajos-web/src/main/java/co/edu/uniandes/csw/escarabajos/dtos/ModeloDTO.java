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
 *      "calificacionMedia": double
 *      ]
 *   }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "marca": "BMXTREME",
 *      "referencia": "BMEXTREME-MTN-2017",
 *      "calificacionMedia": 4.52
 *   }
 *
 * </pre>
 *
 * @author n.gaitan
 */
public class ModeloDTO
{
   //-----------------------------------------------------------
   // Atributos
   //-----------------------------------------------------------
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
     * Constructor por defecto
     */
     
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
        this.marca = modelo.getMarca();
        this.referencia = modelo.getReferencia();
        this.calificacionMedia = modelo.getCalificacionMedia();
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
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ModeloEntity toEntity() {
        ModeloEntity entity = new ModeloEntity();
        entity.setCalificacionMedia(this.calificacionMedia);
        entity.setMarca(this.marca);
        entity.setReferencia(this.referencia);
        return entity;
    }
}
