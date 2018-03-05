/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;




import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;

/**
 *
 * @author jp.carreno
 */
public class FacturaDTO{

    private Long id;
    private Double dinero;

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Constructor por defecto
     */
    public FacturaDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param factura: Es la entidad que se va a convertir a DTO
     */
    public FacturaDTO(FacturaEntity factura) {
        this.dinero = factura.getDinero();
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
