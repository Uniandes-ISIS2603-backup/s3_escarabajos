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
    private String comentario;
    private double puntaje;
    
    public CalificacionDTO()
    {}
    
    public CalificacionDTO(CalificacionEntity cal)
    {
        comentario = cal.getComentario();
        puntaje = cal.getPuntaje();
    }
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
