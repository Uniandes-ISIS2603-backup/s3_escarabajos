/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n.gaitan
 */
public class ReclamoDTO 
{
    Long id;
    List<String> pics;
    String mensaje;
    String razon;
   
    /**
     * Constructor por defecto
     */
    public ReclamoDTO()
    {
        pics = new ArrayList<String>();
    }
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param recl: Es la entidad que se va a convertir a DTO
     */
    public ReclamoDTO(ReclamoEntity recl) {
        this.id = recl.getId();
        this.pics = recl.getPics();
        this.mensaje = recl.getMensaje();
        this.razon = recl.getRazon();
    }
    public String getMensaje()
    {
        return mensaje;
    }
    public void setMensaje(String msj)
    {
        mensaje = msj;
    }
     public String getRazon()
    {
        return razon;
    }
    public void setRazon(String raz)
    {
        razon = raz;
    }
     public Long getId()
    {
        return id;
    }
    public List<String> getPics()
    {
        return pics;
    }
    public void setPics(List<String> pics)
    {
        this.pics = pics;
    }
    public ReclamoEntity toEntity()
    {
        ReclamoEntity entity = new ReclamoEntity();
        entity.setMensaje(this.mensaje);
        entity.setPics(this.pics);
        entity.setId(this.id);
        entity.setRazon(this.razon);
        return entity;
    }
}
