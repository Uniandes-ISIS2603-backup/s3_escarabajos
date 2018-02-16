/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;

/**
 *
 * @author c.santacruza
 */
public class BicicletaDetailDTO extends BicicletaDTO {

    /**
     * Constructor por defecto
     */
    public BicicletaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicleta a partir de la cual se construye el
     * objeto
     */
    public BicicletaDetailDTO(BicicletaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public BicicletaEntity toEntity() {
        BicicletaEntity biciE = super.toEntity();
        return biciE;
    }
}
