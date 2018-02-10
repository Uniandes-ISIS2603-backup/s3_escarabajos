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
 *      "categoria: string,
 *      "marca": string,
 *      "color: string,
 *      "precio": double,
 *      "usada: boolean
 *   }
 * </pre> Por ejemplo una bicicleta se representa asi:<br>
 *
 * <pre>
 *
 *   {
*       "id": 1,
 *      "categoria: BMX,
 *      "marca": We the People,
 *      "color: Negro,
 *      "precio": 3.499,
 *      "usada: false
 *   }
 *
 * </pre>
 *
 * @author c.santacruza
 */
public class BicicletaDTO {

    private Long id;
    private String categoria;
    private String marca;
    private Double precio;
    private String color;
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
        this.id = bici.getId();
        this.categoria = bici.getCategoria();
        this.marca = bici.getMarca();
        this.precio = bici.getPrecio();
        this.color = bici.getColor();
        this.usada = bici.getUsada();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
    public BicicletaEntity toEntity() {
        BicicletaEntity entity = new BicicletaEntity();
        entity.setId(this.id);
        entity.setCategoria(this.categoria);
        entity.setMarca(this.marca);
        entity.setPrecio(this.precio);
        entity.setColor(this.color);
        entity.setUsada(this.usada);

        return entity;
    }
}
