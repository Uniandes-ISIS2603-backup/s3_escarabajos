/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
/**
 * Clase que extiende de {@link ModeloDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. 
 * @author n.gaitan
 */
public class ModeloDetailDTO extends ModeloDTO
{
    /**
     * Constructor por defecto
     */
    public ModeloDetailDTO()
    {
    }
        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el objeto
     */
    public ModeloDetailDTO(ModeloEntity entity) {
        super(entity);
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    public ModeloEntity toEntiy()
    {
        return super.toEntity();
    }
    
}
