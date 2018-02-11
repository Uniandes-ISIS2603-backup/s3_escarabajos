/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;


import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;

/**
 * BicicletaUsadaEnVentaDTO Objeto de transferencia de datos de BicicletasUsadasEnVenta. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name: string,
 *      "facturaOriginal": string,
 *      "precioVenta": double
 *   }
 * </pre>
 * Por ejemplo una bicicletaUsadaEnVenta se representa asi:<br>
 * <pre>
 * 
 *   {
 *      
 *      
 *   }
 *
 * </pre>
 * @author Andres
 */
public class BicicletaUsadaEnVentaDTO {
     //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Representa la factura original del vendedor. 
     */
    private String facturaOriginal;
    
    
    /**
     * Modela el precio que la bicicleta le costo a la tienda.
     */
    private double precioVenta;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    /**
     * Por defecto.
     */
    public BicicletaUsadaEnVentaDTO()
    {
        
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param bici: Es la entidad que se va a convertir a DTO
     */
    public BicicletaUsadaEnVentaDTO(BicicletaUsadaEntity bici) {
        this.facturaOriginal = bici.getFacturaOriginal();
        this.precioVenta = bici.getPrecioVenta();
    }
    
    //-----------------------------------------------------------
    // Getters & Setters
    //-----------------------------------------------------------

    public String getFacturaOriginal() {
        return facturaOriginal;
    }

    public void setFacturaOriginal(String facturaOriginal) {
        this.facturaOriginal = facturaOriginal;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BicicletaUsadaEntity toEntity() {
        BicicletaUsadaEntity entity = new BicicletaUsadaEntity();
        entity.setFacturaOriginal(this.facturaOriginal);
        entity.setPrecioVenta(this.precioVenta);
        
        return entity;
    }
    
}
