/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;

/**
 * Clase que extiende de {@link CalificacionDTO} para manejar la transformacion
 * entre los objetos JSON y las Entidades de la base de datos.
 *
 * Al serializarse como JSON esta clase implementa la siguiente calificacion:
 * <br>
 * <pre>
 *   {
 *      "id": number,
 *      "comentario": String,
 *      "puntaje": double,
 *      "cliente": {@link ClienteDTO},
 *      "modelo": {@link ModeloDTO}
 *   }
 * </pre> Por ejemplo una calificacion se representa asi:<br>
 * <pre>
 * {
 *      "id": 1,
 *      "comentario": "Lindo casco",
 *      "puntaje": 3.44,
 *      "cliente":
 *      {
 *          "id": 1,
 * "nombre": "Ariel",
 * "correo": "asnar0@discuz.net",
 * "usuario": "␣",
 * "cedula": "8643803030"
 *      },
 *      "modelo":
 *      {
 *          "id": 1,
 *          "marca": "BMXTREME",
 *          "referencia": "BMEXTREME-MTN-2017",
 *          "calificacionMedia": 4.50,
 *          "tipoModelo": "Accesorio"
 *      }
 * }
 * </pre>
 */
public class CalificacionDetailDTO extends CalificacionDTO {

    /**
     * El cliente que hace la calificacion
     */
    private ClienteDTO cliente;
    /**
     * El modelo que es calificado
     */
    private ModeloDTO modelo;

    /**
     * Constructor por defecto
     */
    public CalificacionDetailDTO() {
        //Empty
    }

    ;
    /**
     * Constructor a partir de una entity
     * @param entity entidad que contiene la informacion
     */
    public CalificacionDetailDTO(CalificacionEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getCliente() != null) {
                this.cliente = new ClienteDTO(entity.getCliente());
            } else {
                cliente = null;
            }
            if (entity.getModelo() != null) {
                this.modelo = new ModeloDTO(entity.getModelo());
            } else {
                modelo = null;
            }
        }

    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    public CalificacionEntity toEntiy() {
        CalificacionEntity ent = super.toEntity();
        if (this.modelo != null) {
            ent.setModelo(this.modelo.toEntity());
        }
        if (this.cliente != null) {
            ent.setCliente(this.cliente.toEntity());
        }
        return ent;
    }

    /**
     * Retorna el cliente de la calificacion
     *
     * @return el cliente de la calificacion
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Cambia el cliente de la calificacion
     *
     * @param cliente el nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna el modelo de la calificacion
     *
     * @return el modelo de la calificacion
     */
    public ModeloDTO getModelo() {
        return modelo;
    }

    /**
     * Cambia el modelo de la calificacion
     *
     * @param modelo el nuevo modelo de la calificacion
     */
    public void setModelo(ModeloDTO modelo) {
        this.modelo = modelo;
    }

}
