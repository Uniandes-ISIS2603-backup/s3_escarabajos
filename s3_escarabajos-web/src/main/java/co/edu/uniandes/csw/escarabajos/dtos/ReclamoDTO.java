/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;

/**
 * ReclamoDTO Objeto de transferencia de datos del modelo de la bicicleta. Los
 * DTO contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente reclamo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "mensaje": String,
 *      "razon": String,
 *      "enProceso" : Boolean
 *
 *   }
 * </pre> Por ejemplo un reclamo se representa asi:<br>
 *
 * <pre>
 *
 *  {
 *      "id": 1,
 *      "mensaje": "Rueda pinchada",
 *      "razon": "Garantía",
 *      "enProceso": false
 *
 *   }
 *
 * </pre>
 *
 * @author n.gaitan
 */
public class ReclamoDTO {

    /**
     * El id del reclamo.
     */
    private Long id;
    /**
     * El mensaje del reclamo.
     */
    private String mensaje;
    /**
     * El motivo del reclamo.
     */
    private String razon;
    /**
     * Indica si el reclamo ya terminó o si sigue en proceso.
     */
    private boolean enProceso;

    /**
     * Constructor por defecto.
     */
    public ReclamoDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param recl: Es la entidad que se va a convertir a DTO
     */
    public ReclamoDTO(ReclamoEntity recl) {
        if (recl != null) {
            this.id = recl.getId();
            this.mensaje = recl.getMensaje();
            this.razon = recl.getRazon();
            this.enProceso = recl.isEnProceso();
        }

    }

    /**
     * Retorna el mensaje que explica el reclamo
     *
     * @return el mensaje que explica el reclamo
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Cambia el mensaje que explica el reclamo
     *
     * @param msj el nuevo mensaje del reclamo
     */
    public void setMensaje(String msj) {
        mensaje = msj;
    }

    /**
     * Retorna la razon del reclamo
     *
     * @return la razon del reclamo
     */
    public String getRazon() {
        return razon;
    }

    /**
     * Modifica la razon del reclamo
     *
     * @param raz la nueva razon del reclamo
     */
    public void setRazon(String raz) {
        razon = raz;
    }

    /**
     * Método que retorna el id del reclamo
     *
     * @return el id del reclamo
     */
    public Long getId() {
        return id;
    }

    /**
     * Actualiza el id del reclamo
     *
     * @param id el nuveo id del relcamo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que define si un reclamo está en proceso.
     *
     * @return true en caso de estar en proceso false en el caso contrario
     */
    public boolean isEnProceso() {
        return enProceso;
    }

    /**
     * Cambia el estado de enProceso
     *
     * @param enProceso el cambio que se quiere hacer
     */
    public void setEnProceso(boolean enProceso) {
        this.enProceso = enProceso;
    }

    /**
     * Convierte de DTO a entity
     *
     * @return la entidad creada a partir del DTO
     */
    public ReclamoEntity toEntity() {
        ReclamoEntity entity = new ReclamoEntity();
        entity.setId(this.id);
        entity.setMensaje(this.mensaje);
        entity.setRazon(this.razon);
        if (!this.enProceso) {
            entity.terminar();
        } else {
            entity.renaudar();
        }
        return entity;
    }
}
