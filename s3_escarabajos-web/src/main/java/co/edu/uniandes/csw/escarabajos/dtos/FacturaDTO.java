/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;

/**
 * FacturaDTO Objeto de transferencia de datos de Facturas. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "dinero": double
 *
 *   }
 * </pre> Por ejemplo una factura se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123456,
 *      "dinero": 30000,
 *   }
 *
 * </pre>
 *
 * @author jp.carreno
 */
public class FacturaDTO {

    /**
     * Modela el id de factura.
     */
    private Long id;

    /**
     * Modela el dinero de la factura.
     */
    private Double dinero;

    /**
     * Devuelve el dinero.
     *
     * @return dinero
     */
    public double getDinero() {
        return dinero;
    }

    /**
     * Modifica el dinero.
     *
     * @param dinero nuevo dinero
     */
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    /**
     * Devuelve el id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Constructor por defecto
     */
    public FacturaDTO() {
        //Empty
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param factura: Es la entidad que se va a convertir a DTO
     */
    public FacturaDTO(FacturaEntity factura) {
        this.dinero = factura.getDinero();
        this.id = factura.getId();
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        entity.setDinero(this.dinero);
        return entity;
    }

}
