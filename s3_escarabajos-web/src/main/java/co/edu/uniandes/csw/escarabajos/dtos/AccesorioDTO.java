/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.*;


/**
 * AccesorioDTO Objeto de transferencia de datos de Accesorios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precio": number,
 *      "album": {"url":string,"url":String}
 *      "tipoAcessorio": string,
 *      "descripcion": string
 *   }
 * </pre>
 * Por ejemplo un accesorio se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 123456,
 *      "precio": "30,000",
 *      "album": {"url":"url1","url":"url2"}
 *      "tipoAcessorio": "Casco",
 *      "descripcion": "Casco super resistente color verde"
 *   }
 *
 * </pre>
 * @author Andres
 */
public class AccesorioDTO extends ItemDTO {
 
    private String tipoAccesorio;
    private String descripcion;
    /**
     * Constructor por defecto
     */
    public AccesorioDTO() {
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public AccesorioDTO(AccesorioEntity entity) {
       this.tipoAccesorio = entity.getTipoAcessorio();
       this.descripcion = entity.getDescripcion();
       
    }

    /**
     * @return the tipoAccesorio
     */
    public String getTipoAccesorio() {
        return tipoAccesorio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
      /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public AccesorioEntity toEntity() {
        AccesorioEntity entity = new AccesorioEntity();
        super.toEntity(entity);         //Recoge la informacion que esta en la super clase y la agrega al entity
        entity.setTipoAcessorio(this.tipoAccesorio);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
}
