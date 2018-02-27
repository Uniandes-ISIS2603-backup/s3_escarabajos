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
import java.util.List;
import javax.ejb.Stateless;
/**
 *
 * @author n.gaitan
 */
@Stateless
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
        if(!isInRange(cal.getPuntaje()))
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
        LOGGER.info("Inicia el proceso de actualizar una calificaciòn.");
         if(isInRange(cal.getPuntaje()))
        {
           throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if(cal.getComentario().isEmpty() || cal.getComentario() == null)
        {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        LOGGER.info("Termina correctamente el proceso de actualizar una calificacion");
        return calificacionPersistence.update(cal);
    }
    public CalificacionEntity find(Long id)
    {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn.");
        CalificacionEntity answ = calificacionPersistence.find(id);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn.");
        return answ;
    }    
    public void delete(CalificacionEntity ent)
    {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn.");
        calificacionPersistence.delete(ent);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn.");
    }
    public void delete( Long id )
    {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn por id.");
        calificacionPersistence.delete(id);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn por id.");
    }
    public List<CalificacionEntity> findAll()
    {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn.");
        List<CalificacionEntity> answ = calificacionPersistence.findAll();
        LOGGER.info("Termina el proceso de eliminar una calificaciòn.");
        return answ;
    }
    
}
