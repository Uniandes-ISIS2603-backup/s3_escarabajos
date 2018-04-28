/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.util.List;

/**
 * InfoDTO Objeto de transferencia de datos de los filtros. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "marcas": [{@String}],
 *      "categorias": [{@String}],
 *      "colores": [{@String}],
 *      "precioMin": double,
 *      "precioMax": double,
 *      "calificacinoMin": double
 *   }
 * </pre>
 * Por ejemplo los filtros se representa asi:<br>
 * 
 * <pre>
 *   {
 *      "marcas": [{BMx}],
 *      "categorias": [{diversion}],
 *      "colores": [{rojo}],
 *      "precioMin": double,
 *      "precioMax": double,
 *      "calificacinoMin": double
 *   }
 * </pre>
 * @author Andres
 */
public class FiltrosDTO {
    
    private List<String> marcas;
    
    private List<String> categorias;
    
    private List<String> colores;
    
    private Double precioMin;
    
    private Double precioMax;
    
    private Double calificacionMin;
    
     /**
     * Constructor por defecto
     */
    public FiltrosDTO() {
    }

    /**
     * @return the marcas
     */
    public List<String> getMarcas() {
        return marcas;
    }

    /**
     * @param marcas the marcas to set
     */
    public void setMarcas(List<String> marcas) {
        this.marcas = marcas;
    }

    /**
     * @return the categorias
     */
    public List<String> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the colores
     */
    public List<String> getColores() {
        return colores;
    }

    /**
     * @param colores the colores to set
     */
    public void setColores(List<String> colores) {
        this.colores = colores;
    }

    /**
     * @return the precioMin
     */
    public Double getPrecioMin() {
        return precioMin;
    }

    /**
     * @param precioMin the precioMin to set
     */
    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    /**
     * @return the precioMax
     */
    public Double getPrecioMax() {
        return precioMax;
    }

    /**
     * @param precioMax the precioMax to set
     */
    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }

    /**
     * @return the calificacionMin
     */
    public Double getCalificacionMin() {
        return calificacionMin;
    }

    /**
     * @param calificacionMin the calificacionMin to set
     */
    public void setCalificacionMin(Double calificacionMin) {
        this.calificacionMin = calificacionMin;
    }

}
