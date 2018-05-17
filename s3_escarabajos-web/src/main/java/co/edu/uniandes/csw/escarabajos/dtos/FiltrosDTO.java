/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import java.util.List;

/**
 * InfoDTO Objeto de transferencia de datos de los filtros. Los DTO contienen
 * las represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "marcas": [{@String}],
 *      "categorias": [{
 * @String}], "colores": [{
 * @String}], "precioMin": double, "precioMax": double, "calificacinoMin":
 * double }
 * </pre> Por ejemplo los filtros se representa asi:<br>
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
 *
 * @author Andres
 */
public class FiltrosDTO {

    /**
     * Modela las marcas.
     */
    private List<String> marcas;

    /**
     * Modela las categorias.
     */
    private List<String> categorias;

    /**
     * Modela los colores.
     */
    private List<String> colores;

    /**
     * Modela el precio minimo.
     */
    private Double precioMin;

    /**
     * Modela el precio maximo.
     */
    private Double precioMax;

    /**
     * Modela la calificacion minima.
     */
    private Double calificacionMin;

    /**
     * Constructor por defecto.
     */
    public FiltrosDTO() {
        //Empty
    }

    /**
     * Devuelve las marcas.
     *
     * @return marcas
     */
    public List<String> getMarcas() {
        return marcas;
    }

    /**
     * Modifica las marcas.
     *
     * @param marcas nuevas marcas
     */
    public void setMarcas(List<String> marcas) {
        this.marcas = marcas;
    }

    /**
     * Devuelve las categorias.
     *
     * @return categorias
     */
    public List<String> getCategorias() {
        return categorias;
    }

    /**
     * Modifica las categorias.
     *
     * @param categorias nuevas categorias
     */
    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    /**
     * Devuelve los colores.
     *
     * @return colores
     */
    public List<String> getColores() {
        return colores;
    }

    /**
     * Modifica los colores.
     *
     * @param colores nuevos colores
     */
    public void setColores(List<String> colores) {
        this.colores = colores;
    }

    /**
     * Devuelve el precio minimo.
     *
     * @return precioMin
     */
    public Double getPrecioMin() {
        return precioMin;
    }

    /**
     * Modifica el precio minimo.
     *
     * @param precioMin nuevo precio Minimo
     */
    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    /**
     * Devuelve el precio maximo.
     *
     * @return precioMax
     */
    public Double getPrecioMax() {
        return precioMax;
    }

    /**
     * Modifica el precio maximo.
     *
     * @param precioMax nuevo precio Maximo
     */
    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }

    /**
     * Devuelve la calificacion minima.
     *
     * @return calificacionMin
     */
    public Double getCalificacionMin() {
        return calificacionMin;
    }

    /**
     * Modifica la calificacion minima.
     *
     * @param calificacionMin nueva calificacionMin
     */
    public void setCalificacionMin(Double calificacionMin) {
        this.calificacionMin = calificacionMin;
    }

}
