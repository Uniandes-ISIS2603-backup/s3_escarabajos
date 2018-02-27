/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
/**
 *
 * @author n.gaitan
 */
public class CalificacionDTO 
{
    /**
     * Comentario dado por el usuario.
     */
    String comentario;
    /**
     * Puntaje dado por el usuario.
     */
    double puntaje;
    
    /**
     * Constructor por defecto
     */
    public CalificacionDTO()
    {}
    /**
     * Constructor que comienza a partir de una entity
     * @param cal entidad de la se saca la información
     */
    public CalificacionDTO(CalificacionEntity cal)
    {
        comentario = cal.getComentario();
        puntaje = cal.getPuntaje();
    }
    /**
     * Método para obtener el comentario
     * @return el comentario
     */
    public String getComentario()
    {
        return comentario;
    }
    public double getPuntaje()
    {
        return puntaje;
    }
    public void setPuntaje(double punt)
    {
        puntaje = punt;
    }
    public void setComentario(String com)
    {
        comentario = com;
    }
    public CalificacionEntity toEntity()
    {
        CalificacionEntity e = new CalificacionEntity();
        e.setComentario(comentario);
        e.setPuntaje(puntaje);
        return e;
    }
}
