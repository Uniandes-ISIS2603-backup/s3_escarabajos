/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CalificacionPersistence;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author n.gaitan
 */
public class CalificacionLogic 
{
    
    private final static Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
   
    private boolean isInRange(double pValue)
    {
        return pValue >= 0 && pValue <= 5;
    }
    public CalificacionEntity crearCalificacion(CalificacionEntity cal)throws BusinessLogicException
    {
        if(isInRange(cal.getPuntaje()))
        {
           throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if(cal.getComentario().isEmpty() || cal.getComentario() == null)
        {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        return calificacionPersistence.create(cal);
    }
    public CalificacionEntity updateCalificacion(CalificacionEntity cal)throws BusinessLogicException
    {
         if(isInRange(cal.getPuntaje()))
        {
           throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if(cal.getComentario().isEmpty() || cal.getComentario() == null)
        {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        return calificacionPersistence.update(cal);
    }
}
