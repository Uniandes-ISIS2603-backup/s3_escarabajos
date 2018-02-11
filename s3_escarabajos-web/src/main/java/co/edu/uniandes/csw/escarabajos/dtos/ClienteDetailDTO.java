/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;

/**
 *
 * @author s.beltran
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    /**
     * Constructor por defecto
     */
    public ClienteDetailDTO() {
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity cityE = super.toEntity();
        return cityE;
    }
}
