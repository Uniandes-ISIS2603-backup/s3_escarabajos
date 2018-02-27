/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n.gaitan
 */
public class ReclamoDetailDTO extends ReclamoDTO
{
    
    private List<FotoDTO> album;
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
         if (entity.getAlbum()!= null) {
            album = new ArrayList<>();
            for (FotoEntity entityFoto : entity.getAlbum()) {
               album.add(new FotoDTO(entityFoto));
            }
        }
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    public ReclamoEntity toEntiy()
    {
        ReclamoEntity entity = super.toEntity();
        if (getAlbum()!= null) {
            List<FotoEntity> fotoEntity = new ArrayList<>();
            for (FotoDTO dtoFoto : getAlbum()) {
                fotoEntity.add(dtoFoto.toEntity());
            }
            entity.setAlbum(fotoEntity);
        }
        return entity;
    }

    /**
     * @return the album
     */
    public List<FotoDTO> getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(List<FotoDTO> album) {
        this.album = album;
    }
}
