/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.TransferenciaEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jp.carreno
 */
@Entity
public class TransferenciaDetailDTO extends TransferenciaDTO {

    /**
     * Constructor por defecto
     */
    public TransferenciaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicleta a partir de la cual se construye el objeto
     */
    public TransferenciaDetailDTO(TransferenciaEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public TransferenciaEntity toEntity() {
        TransferenciaEntity biciE = super.toEntity();
        return biciE;
    }
    
}
