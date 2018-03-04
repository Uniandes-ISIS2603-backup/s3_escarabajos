/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.*;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
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
public class CalificacionLogic {

    private final static Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;

    @Inject
    private ModeloLogic modeloLogic;

    @Inject
    private ClienteLogic clienteLogic;

    private boolean isInRange(double pValue) {
        return pValue >= 0 && pValue <= 5;
    }

    public CalificacionEntity crearCalificacion(CalificacionEntity cal, Long modeloId, Long clienteId) throws BusinessLogicException {
        if (!isInRange(cal.getPuntaje())) {
            throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if (cal.getComentario().isEmpty() || cal.getComentario() == null) {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        ModeloEntity model = modeloLogic.getModelo(modeloId);
        ClienteEntity cliente = clienteLogic.getCliente(clienteId);
        cal.setModelo(model);
        cal.setCliente(cliente);
        model.setCalificacionMedia(getCalificacionMedia(modeloId));
        return calificacionPersistence.create(cal);
    }

    public CalificacionEntity updateCalificacion(CalificacionEntity cal, Long modeloId, Long clienteId) throws BusinessLogicException {
        LOGGER.info("Inicia el proceso de actualizar una calificaciòn.");
        if (!isInRange(cal.getPuntaje())) {
            throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if (cal.getComentario().isEmpty() || cal.getComentario() == null) {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        LOGGER.info("Termina correctamente el proceso de actualizar una calificacion");
        ModeloEntity model = modeloLogic.getModelo(modeloId);
        ClienteEntity cliente = clienteLogic.getCliente(clienteId);
        cal.setModelo(model);
        cal.setCliente(cliente);
        model.setCalificacionMedia(getCalificacionMedia(modeloId));
        return calificacionPersistence.update(cal);
    }

    public CalificacionEntity find(Long id) {
        LOGGER.info("Inicia el proceso de buscar una calificaciòn.");
        CalificacionEntity answ = calificacionPersistence.find(id);
        LOGGER.info("Termina el proceso de buscar una calificaciòn.");
        return answ;
    }

    public void delete(CalificacionEntity ent) {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn.");
        calificacionPersistence.delete(ent);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn.");
    }

    public void delete(Long id) {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn por id.");
        calificacionPersistence.delete(id);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn por id.");
    }

    public List<CalificacionEntity> findAll() {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones.");
        List<CalificacionEntity> answ = calificacionPersistence.findAll();
        LOGGER.info("Termina el proceso de buscar todas las calificaciones.");
        return answ;
    }

    public List<CalificacionEntity> getCalificacionesPorModelo(Long modeloId) {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones del modelo " + modeloId);
        List<CalificacionEntity> cals = calificacionPersistence.getCalificacionesPorModelo(modeloId);
        LOGGER.info("Termina el proceso de buscar todas las calificaciones del modelo " + modeloId);
        return cals;
    }

    public List<CalificacionEntity> getCalificacionesPorCliente(Long clienteId) {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones del modelo " + clienteId);
        List<CalificacionEntity> cals = calificacionPersistence.getCalificacionesPorCliente(clienteId);
        LOGGER.info("Termina el proceso de buscar todas las calificaciones del modelo " + clienteId);
        return cals;
    }

    public List<CalificacionEntity> getCalificacionesPorClienteAndModelo(Long clienteId, Long modeloId) throws BusinessLogicException
    {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones del modelo " + modeloId + " y el cliente " + clienteId);
        List<CalificacionEntity> cals = calificacionPersistence.getCalificacionesPorClienteAndModelo(clienteId, modeloId);
        LOGGER.info("Termina el proceso de buscar todas las calificaciones del modelo " + modeloId);
        if(cals.isEmpty() || cals == null) throw new BusinessLogicException("No existe una calificación creada por el cliente " + 
                clienteId + " para el modelo " + modeloId);
        return cals;
    }

    public void removeCalificacion(Long calId, Long clienteId, Long modId) {
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        ModeloEntity m = modeloLogic.getModelo(clienteId);
        CalificacionEntity cal = find(calId);
        c.getCalificaciones().remove(cal);
        m.getCalificaciones().remove(cal);
        delete(cal);
    }

    public double getCalificacionMedia(Long modeloId) throws BusinessLogicException {
        List<CalificacionEntity> cals = getCalificacionesPorModelo(modeloId);
        if (cals == null || cals.isEmpty()) {
            return -1;
        }
        double answer = 0;
        for (int i = 0; i < cals.size(); i++) {
            answer += cals.get(i).getPuntaje();
        }
        return answer / cals.size();
    }
}
