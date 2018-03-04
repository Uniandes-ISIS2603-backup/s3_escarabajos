/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;

/**
 * BicicletaUsadaDetailDTO Objeto de transferencia de datos de Bicicletas usadas. Los DetailDTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "categoria: string,
 *      "marca": string,
 *      "color: string,
 *      "precio": double,
 *      "usada: boolean,
 *      "album": []String,
 *      "modelo":{      
 *                 "id": number,
 *                 "marca": String,
 *                 "referencia": String,
 *                 "calificacionMedia": double,
 *                 "tipoModelo":String
 *               }
 *      }
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "categoria": "BMX",
 *      "marca": "We the People",
 *      "color": "Negro",
 *      "precio": 3.499,
 *      "usada": true,
 *      "facturaOriginal" : "https://cloud10.todocoleccion.online/facturas-antiguas/tc/2010/05/15/19344998.jpg",
 *      "album" :[
 *                 
 *               ],
 *      "modelo" : {
 *                  "id": 1,
 *                  "marca": "BMXTREME",
 *                  "referencia": "BMEXTREME-MTN-2017",
 *                  "calificacionMedia": 4.50,
 *                  "tipoModelo": "Bicicleta"  
 *                 }
 *   }
 *
 * </pre>
 *
 * @author c.santacruza
 */
public class BicicletaUsadaDetailDTO extends BicicletaDTO {

    /*
    * Relación a un modelo
     */
    private ModeloDTO modelo;
    
    /**
     * Constructor por defecto
     */
    public BicicletaUsadaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicleta a partir de la cual se construye el
     * objeto
     */
    public BicicletaUsadaDetailDTO(BicicletaUsadaEntity entity) {
        super(entity);
       /* if (entity.getModelo()!= null) {
            this.modelo = new ModeloDTO(entity.getModelo());
        } else {
            entity.setModelo(null);
        }*/ 
       //ESTO NO HACE FALTA ITEM YA LO HACE
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public BicicletaUsadaEntity toEntity() {
        BicicletaUsadaEntity biciE = (BicicletaUsadaEntity) super.toEntity();
       /* if (this.getModelo() != null) {
            biciE.setModelo(this.getModelo().toEntity());
        }*/
        return biciE;
    }

    /**
     * @return the modelo
     */
    public ModeloDTO getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ModeloDTO modelo) {
        this.modelo = modelo;
    }
}
