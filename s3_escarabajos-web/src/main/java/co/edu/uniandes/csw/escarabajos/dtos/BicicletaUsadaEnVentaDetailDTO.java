/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;

/**
 * BicicletaUsadaEnVentaDetailDTO Objeto de transferencia de datos de
 * BicicletasUsadasEnVenta. Los DetailDTO contienen las represnetaciones de los JSON
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
public class BicicletaUsadaEnVentaDetailDTO extends BicicletaUsadaEnVentaDTO {

    private VendedorDTO vendedor;
    /**
     * Constructor por defecto
     */
    public BicicletaUsadaEnVentaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de bicicletaUsadaEnVenta a partir de la cual se
     * construye el objeto
     */
    public BicicletaUsadaEnVentaDetailDTO(BicicletaUsadaEntity entity) {
        super(entity);
        entity.setVendedor(this.getVendedor().toEntity());
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public BicicletaUsadaEntity toEntity() {
        BicicletaUsadaEntity biciUsadaE = super.toEntity();
        biciUsadaE.setVendedor(this.getVendedor().toEntity());
        return biciUsadaE;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    
}
