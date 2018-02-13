/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
/**
 *
 * @author n.gaitan
 */
public class CalificacionDetailDTO extends CalificacionDTO
{
    public CalificacionDetailDTO(CalificacionEntity entity) 
    {
        super(entity);
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    public CalificacionEntity toEntiy()
    {
        return super.toEntity();
    }
}
