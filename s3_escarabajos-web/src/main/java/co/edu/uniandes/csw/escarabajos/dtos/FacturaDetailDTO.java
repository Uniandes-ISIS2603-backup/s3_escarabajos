/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;

/**
 *
 * @author jp.carreno
 */
public class FacturaDetailDTO extends FacturaDTO {

    /**
     * Constructor por defecto
     */
    public FacturaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicleta a partir de la cual se construye el objeto
     */
    public FacturaDetailDTO(FacturaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity biciE = super.toEntity();
        return biciE;
    }
    
}
