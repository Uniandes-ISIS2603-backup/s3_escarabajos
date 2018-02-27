/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ReclamoPersistence;
import java.util.logging.Logger;
import javax.inject.Inject;
import java.util.List;
import javax.ejb.Stateless;
/**
 *
 * @author n.gaitan
 */
@Stateless
public class ReclamoLogic 
{
     private final static Logger LOGGER = Logger.getLogger(ReclamoLogic.class.getName());
     
     @Inject
     private ReclamoPersistence reclamoPersistence;
     
     private boolean emptyList(List<FotoEntity> lista)
     {
         return lista.isEmpty();
     }
     public ReclamoEntity createReclamo(ReclamoEntity ent) throws BusinessLogicException   
     {
       LOGGER.info("Iniciando el proceso de crear un reclamo.");
       if(ent.getMensaje().isEmpty())
       {
           throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
       }
       if(emptyList(ent.getAlbum()))
       {
           throw new BusinessLogicException("Debe ingresar al menos 1 foto con evidencia.");
       }
       if(ent.getRazon().isEmpty())
       {
           throw new BusinessLogicException("Por favor ingrese el motivo de su reclamo");
       }
       reclamoPersistence.create(ent);
       LOGGER.info("Finalizando el proceso de crear un reclamo");
       return ent;  
     }
      public ReclamoEntity updateReclamo(ReclamoEntity ent) throws BusinessLogicException   
     {
       LOGGER.info("Iniciando el proceso de actualizar un reclamo.");
       if(ent.getMensaje().isEmpty())
       {
           throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
       }
       if(emptyList(ent.getAlbum()))
       {
           throw new BusinessLogicException("Debe ingresar al menos 1 foto con evidencia.");
       }
       if(ent.getRazon().isEmpty())
       {
           throw new BusinessLogicException("Por favor ingrese el motivo de su reclamo");
       }
       reclamoPersistence.update(ent);
       LOGGER.info("Finalizando el proceso de actualizar un reclamo");
       return ent;  
     }
    public ReclamoEntity find(Long id)
    {
        LOGGER.info("Inicia el proceso de buscar un reclamo.");
        ReclamoEntity answ = reclamoPersistence.find(id);
        LOGGER.info("Termina el proceso de buscar un reclamo.");
        return answ;
    }    
    public void delete(ReclamoEntity ent)
    {
        LOGGER.info("Inicia el proceso de eliminar un reclamo.");
        reclamoPersistence.delete(ent);
        LOGGER.info("Termina el proceso de eliminar un reclamo.");
    }
    public void delete( Long id )
    {
        LOGGER.info("Inicia el proceso de eliminar un reclamo por id.");
        reclamoPersistence.delete(id);
        LOGGER.info("Termina el proceso de eliminar un reclamo por id.");
    }
    public List<ReclamoEntity> findAll()
    {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos.");
        List<ReclamoEntity> answ = reclamoPersistence.findAll();
        LOGGER.info("Termina el proceso de mostrar todos los reclamos.");
        return answ;
    }
}
