/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;

/**
 * ItemDTO Objeto de transferencia de datos de Accesorios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precio": number,
 *      "album": {"url":string,"url":String}
 * 
 *   }
 * </pre>
 * Por ejemplo un item se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 123456,
 *      "precio": "30,000",
 *      "album": {"url":"url1","url":"url2"}
 *   }
 *
 * </pre>
 * @author Andres
 */
public class ItemDTO{
 
    private Long id;
    private double precio;
    private String[] album;
    /**
     * Constructor por defecto
     */
    public ItemDTO() {
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public ItemDTO(ItemEntity entity) {
       this.id = entity.getId();
       this.precio = entity.getPrecio();
       this.album = entity.getAlbum();
    }

      /**
     * Convertir DTO a Entity
     *
     * @param entity Es la entidad que se le van a asignarlos valores del DTO
     * @return Un Entity con los valores del DTO
     */
    public ItemEntity toEntity(ItemEntity entity) {
       entity.setId(this.id);
       entity.setPrecio(this.precio);
       entity.setAlbum(this.album);
        return entity;
    }
}