/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.TransaccionEntity;
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
public class TransaccionDetailDTO extends TransaccionDTO {

    /**
     * Constructor por defecto
     */
    public TransaccionDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicleta a partir de la cual se construye el objeto
     */
    public TransaccionDetailDTO(TransaccionEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public TransaccionEntity toEntity() {
        TransaccionEntity biciE = super.toEntity();
        return biciE;
    }
    
}
