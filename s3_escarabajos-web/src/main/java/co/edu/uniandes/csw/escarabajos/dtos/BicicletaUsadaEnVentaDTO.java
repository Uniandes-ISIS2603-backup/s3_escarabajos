/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;

/**
 * BicicletaUsadaEnVentaDTO Objeto de transferencia de datos de
 * BicicletasUsadasEnVenta. Los DTO contienen las represnetaciones de los JSON
 * que se transfieren entre el cliente y el servidor.
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
 *      "facturaOriginal" : string,
 *      "precioVenta" : double
 *   }
 * </pre> Por ejemplo una bicicletaUsadaEnVenta se representa asi:<br>
 * <pre>
 *   {
 *      "id": 1,
 *      "categoria: BMX,
 *      "marca": We the People,
 *      "color: Negro,
 *      "precio": 2.50,
 *      "usada: true
 *      "facturaOriginal" : "https://cloud10.todocoleccion.online/facturas-antiguas/tc/2010/05/15/19344998.jpg"
 *   }
 *
 * </pre>
 *
 * @author c.santacruza
 */
public class BicicletaUsadaEnVentaDTO extends BicicletaDTO {

    /**
     * Representa la imagen de la factura original del vendedor.
     */
    private String facturaOriginal;

    /**
     * Por defecto.
     */
    public BicicletaUsadaEnVentaDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public BicicletaUsadaEnVentaDTO(BicicletaUsadaEntity bici) {
        this.facturaOriginal = bici.getFacturaOriginal();
    }

    
    public String getFacturaOriginal() {
        return facturaOriginal;
    }

    public void setFacturaOriginal(String facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BicicletaUsadaEntity toEntity() {
        BicicletaUsadaEntity entity = new BicicletaUsadaEntity();
        entity.setFacturaOriginal(this.facturaOriginal);

        return entity;
    }

}
