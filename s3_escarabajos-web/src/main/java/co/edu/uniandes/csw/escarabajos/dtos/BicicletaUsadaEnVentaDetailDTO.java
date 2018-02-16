/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;

/**
 * Clase que extiende de {@link BicicletaUsadaEnVentaDTO} para manejar la
 * transformacion entre los objetos JSON y las Entidades de la base de datos.
 *
 * @author Andres
 */
public class BicicletaUsadaEnVentaDetailDTO extends BicicletaUsadaEnVentaDTO {

    /**
     * Constructor por defecto
     */
    public BicicletaUsadaEnVentaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicletaUsadaEnVenta a partir de la cual se
     * construye el objeto
     */
    public BicicletaUsadaEnVentaDetailDTO(BicicletaUsadaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public BicicletaUsadaEntity toEntity() {
        BicicletaUsadaEntity biciUsadaE = super.toEntity();
        return biciUsadaE;
    }

}
