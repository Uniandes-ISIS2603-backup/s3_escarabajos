/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
//TODO: Borrar lo que no se use
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CalificacionDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. 
 * 
  * Al serializarse como JSON esta clase implementa el siguiente reclamo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "mensaje": String,
 *      "razon": double,
 *      "factura": {@link FacturaDTO},
 *      "album": [{@link FotoDTO}]
 *   }
 * </pre> Por ejemplo un reclamo se representa asi:<br>
 * <pre>
 * {
 *      "id": 1,
 *      "mensaje": "Color incorrecto",
 *      "razon": "Cambio de bicicleta",
 *      "factura": {},
 *      "album": []
 * }
 * </pre>
 */
public class ReclamoDetailDTO extends ReclamoDTO {

    private FacturaDTO factura;

    private List<FotoDTO> album;

    /**
     * Constructor vacio
     */
    public ReclamoDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el
     * objeto
     */
    public ReclamoDetailDTO(ReclamoEntity entity) {
        super(entity);
        //TODO: entity podría ser null
        if (entity.getAlbum() != null) {
            album = new ArrayList<>();
            for (FotoEntity entityFoto : entity.getAlbum()) {
                album.add(new FotoDTO(entityFoto));
            }
        }
        if (entity.getFactura() != null) {
            factura = new FacturaDTO(entity.getFactura());
        }

    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    //TODO: falta @Override
    public ReclamoEntity toEntiy() {
        ReclamoEntity entity = super.toEntity();
        if (getAlbum() != null) {
            List<FotoEntity> fotoEntity = new ArrayList<>();
            for (FotoDTO dtoFoto : getAlbum()) {
                fotoEntity.add(dtoFoto.toEntity());
            }
            entity.setAlbum(fotoEntity);
        }
        if(getFactura() != null)
        {
            entity.setFactura(factura.toEntity());
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

    /**
     * Retorna la factura a la que hace referencia el reclamo
     *
     * @return la factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * Modifica la factura a la que hace referencia el reclamo
     * @param factura la nueva factura
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }
    

}
