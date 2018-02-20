/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;

/**
 *
 * @author n.gaitan
 */
public class ReclamoDetailDTO extends ReclamoDTO
{
    /**
     * Constructor vacio
     */
    public ReclamoDetailDTO(){}
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el objeto
     */
    public ReclamoDetailDTO(ReclamoEntity entity) 
    {
        super(entity);
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    public ReclamoEntity toEntiy()
    {
        return super.toEntity();
    }
}
