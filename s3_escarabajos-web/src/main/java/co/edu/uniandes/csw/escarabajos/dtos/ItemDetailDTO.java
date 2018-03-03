/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;

//ESTA CLASE NO DEBERIA EXSISTIR YA QUE ITEM NO TIENE RELACIONES CON NADA.
/**
 * Clase que extiende de {@link ItemDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del item vaya a la documentacion de {@link ItemDTO}
 * @author Andres
 */
public class ItemDetailDTO extends ItemDTO {

    private String tipo;
    
    /**
     * Constructor por defecto
     */
    public ItemDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad del item a partir de la cual se construye el objeto
     */
    public ItemDetailDTO(ItemEntity entity) {
        super(entity);
    }

    @Override
    public ItemEntity toEntity()  {
        //ATENCION !!! --ESTE METODO NO SE PUEDE LLAMAR, Si quieren el toEntity() de un ITEM toca por las subclases para no perder informacion.
       return null;
    }
}

