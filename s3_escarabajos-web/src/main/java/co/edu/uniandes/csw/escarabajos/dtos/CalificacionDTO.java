/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;

/**
 * CalificacionDTO Objeto de transferencia de datos del modelo de la bicicleta.
 * Los DTO contienen las representaciones de los JSON que se transfieren entre
 * el cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa la siguiente calificacion:
 * <br>
 * <pre>
 *   {
 *      "id": number,
 *      "comentario": String,
 *      "puntaje": double
 *
 *   }
 * </pre> Por ejemplo una calificacion se representa asi:<br>
 *
 * <pre>
 *
 *  {
 *      "id": 1,
 *      "comentario": "Lindo casco",
 *      "puntaje": 5
 *
 *   }
 *
 * </pre>
 *
 * @author n.gaitan
 */
public class CalificacionDTO {

    /**
     * Id de la calificacion
     */
    private Long id;

    /**
     * Comentario dado por el usuario.
     */
    private String comentario;
    /**
     * Puntaje dado por el usuario.
     */
    private Double puntaje;

    /**
     * Constructor por defecto
     */
    public CalificacionDTO() {
    }

    /**
     * Constructor que comienza a partir de una entity
     *
     * @param cal entidad de la se saca la información
     */
    public CalificacionDTO(CalificacionEntity cal) {
    //TODO: DONE cal podría ser null
        if (cal != null) {
            id = cal.getId();
            comentario = cal.getComentario();
            puntaje = cal.getPuntaje();
        }
    }

    /**
     * Método para obtener el id.
     *
     * @return el id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método para obtener el comentario
     *
     * @return el comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Metodo para obtener el puntaje
     *
     * @return el puntaje
     */
    public double getPuntaje() {
        return puntaje;
    }

    /**
     * Actualiza el puntaje
     *
     * @param punt nuevo puntaje
     */
    public void setPuntaje(double punt) {
        puntaje = punt;
    }

    /**
     * Actualiza el comentario
     *
     * @param com el nuevo comentario
     */
    public void setComentario(String com) {
        comentario = com;
    }

    /**
     * Convierte un DTO a un entity
     *
     * @return el nuevo entity
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity e = new CalificacionEntity();
        e.setComentario(comentario);
        e.setPuntaje(puntaje);
        e.setId(id);
        return e;
    }
}
