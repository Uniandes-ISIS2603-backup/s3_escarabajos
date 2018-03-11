/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.VendedorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * VendedorDTO Objeto de transferencia de datos del . Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
	"nombre":String,
	"direccion":String,
	"telefono":String,
        "id": Long,
        "bicicletasUsadas": []
	
    }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
	"nombre":"pibe",
	"direccion":"Cra 57 b 121",
	"telefono":"5885845",
        "id": Long,
        "bicicletasUsadas": []
	
    }
 *
 * </pre>
 *
 * @author s.beltran
 */
public class VendedorDetailDTO extends VendedorDTO{
    
    
    private List<BicicletaUsadaDTO> bicicletasUsadas;
     
    //TODO poner todo lo que tiene podamexclude
    public VendedorDetailDTO(){
        
    }
    
    public VendedorDetailDTO (VendedorEntity entity){
        super(entity);
        if (entity.getBicicletasUsadas()!= null) {
           bicicletasUsadas = new ArrayList<>();
            for (BicicletaUsadaEntity entityBicicletaUsada: entity.getBicicletasUsadas()) {
                bicicletasUsadas.add(new BicicletaUsadaDTO(entityBicicletaUsada));
            }
        }
    }
    
    @Override
    public VendedorEntity toEntity(){
        VendedorEntity vendedorE = new VendedorEntity();
        
         if (getBicicletasUsadas() != null) {
            List<BicicletaUsadaEntity> bicicletasUsadasEntity = new ArrayList<>();
            for (BicicletaUsadaDTO dtoBicicletaUsada : getBicicletasUsadas()) {
               bicicletasUsadasEntity.add(dtoBicicletaUsada.toEntity());
            }
            vendedorE.setBicicletasUsadas(bicicletasUsadasEntity);
        }
        
        return vendedorE;
    }

    /**
     * @return the bicicletasUsadas
     */
    public List<BicicletaUsadaDTO> getBicicletasUsadas() {
        return bicicletasUsadas;
    }

    /**
     * @param bicicletasUsadas the bicicletasUsadas to set
     */
    public void setBicicletasUsadas(List<BicicletaUsadaDTO> bicicletasUsadas) {
        this.bicicletasUsadas = bicicletasUsadas;
    }
}
