/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;


//ESTA CLASE NO DEBERIA EXSISTIR YA QUE ACCESORIO NO TIENE RELACIONES CON NADA.
/**
 * Clase que extiende de {@link AccesorioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del accesorio vaya a la documentacion de {@link AccesorioDTO}
 * @author Andres
 */
public class AccesorioDetailDTO extends AccesorioDTO {

    /**
     * Constructor por defecto
     */
    public AccesorioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad del accesorio a partir de la cual se construye el objeto
     */
    public AccesorioDetailDTO(AccesorioEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public AccesorioEntity toEntity() {
        AccesorioEntity accE = super.toEntity();
        return accE;
    }

}

