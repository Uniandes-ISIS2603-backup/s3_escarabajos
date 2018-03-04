/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;

/**
 * BicicletaDTO Objeto de transferencia de datos de Bicicletas. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "categoria": string,
 *      "marca": string,
 *      "color": string,
 *      "precio": double,
 *      "usada": boolean,
 *      "album":[]String,
 *      
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "categoria": "Deportiva",
 *      "marca": "We the People",
 *      "color": "Negro",
 *      "precio": 3.499,
 *      "usada": false,
 *      "album" :[
 *                 
 *               ]
 *   }
 *
 * </pre>
 *
 * @author c.santacruza
 */
public class BicicletaDTO extends ItemDTO {

    private String categoria;
    private Boolean usada;

    /**
     * Constructor por defecto
     */
    public BicicletaDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public BicicletaDTO(BicicletaEntity bici) {
        super(bici);
        if (bici != null) {
            this.categoria = bici.getCategoria();
            this.usada = bici.getUsada();
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getUsada() {
        return usada;
    }

    public void setUsada(Boolean usada) {
        this.usada = usada;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public BicicletaEntity toEntity() {
        BicicletaEntity entity = new BicicletaEntity();
        super.toEntity(entity);
        entity.setCategoria(this.categoria);
        entity.setUsada(this.usada);

        return entity;
    }
}
