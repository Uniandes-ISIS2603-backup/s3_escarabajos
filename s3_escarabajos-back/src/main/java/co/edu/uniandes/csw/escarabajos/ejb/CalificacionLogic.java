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

    /**
     * LOGGER de la clase CalificacionLogic.
     */
    private final static Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    /**
     * Inyecta la persistencia de calificacion.
     */
    @Inject
    private CalificacionPersistence calificacionPersistence;

    /**
     * Inyecta la logica de modelo.
     */
    @Inject
    private ModeloLogic modeloLogic;

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    private ClienteLogic clienteLogic;

    /**
     * Determmina si la calificacion esta en el rango de 0 a 5.
     *
     * @param pValue valor de calificacion
     * @return si esta en rango retorna true. De lo contrario retorna false
     */
    private boolean isInRange(double pValue) {
        return pValue >= 0 && pValue <= 5;
    }

    /**
     * Crea una calificacion asociada a un modelo y un cliente.
     *
     * @param cal calificacion a crear
     * @param modeloId modelo espcifico
     * @param clienteId cliente especifico
     * @return calificacion creada
     * @throws BusinessLogicException por reglas de negocio
     */
    public CalificacionEntity crearCalificacion(CalificacionEntity cal, Long modeloId, Long clienteId) throws BusinessLogicException {
        LOGGER.info("[LOGIC] Comienza el proceso de crear una calificación y conectarla con el cliente " + clienteId + " y el modelo " + modeloId);
        if (!isInRange(cal.getPuntaje())) {
            LOGGER.info("[ERROR] El puntaje no está en rango");
            throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if (cal.getComentario() == null || cal.getComentario().isEmpty()) {
            LOGGER.info("[ERROR] El comentario está vacío");
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        if (clienteId != null) {
            if (!getCalificacionesPorClienteAndModelo(clienteId, modeloId).isEmpty()) {
                LOGGER.info("[ERROR] Usted ya ha calificado a este modelo");
                throw new BusinessLogicException("Usted ya ha calificado a este modelo");
            }
        }
        // TODO: DONE QUitar las líneas que no sirven
        ModeloEntity model = modeloLogic.getModelo(modeloId);
        LOGGER.info(model.getMarca());
        ClienteEntity cliente = clienteLogic.getCliente(clienteId);
        cal.setModelo(model);
        cal.setCliente(cliente);
        CalificacionEntity nueva = calificacionPersistence.create(cal);
        LOGGER.info("[LOGIC] Termina el proceso de crear una calificación y conectarla con el cliente " + clienteId + " y el modelo " + modeloId + ", su id es " + nueva.getId());
        model.setCalificacionMedia(getCalificacionMedia(modeloId));
        modeloLogic.updateModelo(modeloId, model);
        return nueva;
    }

    /**
     * Actualiza una calificacion especifica.
     *
     * @param cal calificacion especifica
     * @param modeloId modelo especifico
     * @param clienteId cliente especifico
     * @return calificacion actualizada
     * @throws BusinessLogicException por reglas de negocio
     */
    public CalificacionEntity updateCalificacion(CalificacionEntity cal, Long modeloId, Long clienteId) throws BusinessLogicException {
        LOGGER.info("Inicia el proceso de actualizar una calificaciòn.");
        if (!isInRange(cal.getPuntaje())) {
            throw new BusinessLogicException("El puntaje debe ser un valor entre 0 y 5");
        }
        if (cal.getComentario() == null || cal.getComentario().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese un comentario");
        }
        LOGGER.info("Termina correctamente el proceso de actualizar una calificacion");
        ModeloEntity model = modeloLogic.getModelo(modeloId);

        ClienteEntity cliente = clienteLogic.getCliente(clienteId);

        cal.setModelo(model);
        cal.setCliente(cliente);
        CalificacionEntity nueva = calificacionPersistence.update(cal);
        model.getCalificaciones().add(nueva);
        modeloLogic.updateModelo(model.getId(), model);
        cliente.getCalificaciones().add(cal);
        clienteLogic.updateCliente(cliente);
        model.setCalificacionMedia(getCalificacionMedia(modeloId));
        return nueva;
    }

    /**
     * Devuelve una calificacion especifica.
     *
     * @param id calificacion buscada
     * @return calificacion encontrada
     */
    public CalificacionEntity find(Long id) {
        LOGGER.info("Inicia el proceso de buscar una calificaciòn.");
        CalificacionEntity answ = calificacionPersistence.find(id);
        LOGGER.info("Termina el proceso de buscar una calificaciòn.");
        return answ;
    }

    /**
     * Elimina una calificacion especifica.
     *
     * @param ent calificacion que se va a eliminar
     */
    public void delete(CalificacionEntity ent) {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn.");
        calificacionPersistence.delete(ent);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn.");
    }

    /**
     * Elimina una calificacion especifica.
     *
     * @param id calificacion que se va a eliminar
     */
    public void delete(Long id) {
        LOGGER.info("Inicia el proceso de eliminar una calificaciòn por id.");
        calificacionPersistence.delete(id);
        LOGGER.info("Termina el proceso de eliminar una calificaciòn por id.");
    }

    /**
     * Devuelve todas las calificaciones en la base de datos.
     *
     * @return lista de calificaciones
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones.");
        List<CalificacionEntity> answ = calificacionPersistence.findAll();
        LOGGER.info("Termina el proceso de buscar todas las calificaciones.");
        return answ;
    }

    /**
     * Devuelve las calificaciones de un modelo especifico.
     *
     * @param modeloId modelo especifico
     * @return lista de calificaciones de un modelo
     */
    public List<CalificacionEntity> getCalificacionesPorModelo(Long modeloId) {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones del modelo " + modeloId);
        List<CalificacionEntity> cals = calificacionPersistence.getCalificacionesPorModelo(modeloId);
        LOGGER.info("Termina el proceso de buscar todas las calificaciones del modelo " + modeloId);
        return cals;
    }

    /**
     * Devuelve las calificaciones de un modelo especifico y un cliente
     * especifico.
     *
     * @param clienteId cliente especifico
     * @param modeloId modelo especifico
     * @return lista de calificaciones
     * @throws BusinessLogicException por reglas de negocio
     */
    public List<CalificacionEntity> getCalificacionesPorClienteAndModelo(Long clienteId, Long modeloId) throws BusinessLogicException {
        LOGGER.info("Inicia el proceso de buscar todas las calificaciones del modelo " + modeloId + " y el cliente " + clienteId);
        List<CalificacionEntity> cals = calificacionPersistence.getCalificacionesPorClienteAndModelo(clienteId, modeloId);
        LOGGER.info("Termina el proceso de buscar todas las calificaciones del modelo " + modeloId + " y el cliente " + clienteId);
        return cals;
    }

    /**
     * Elimina una calificacion especifica de un modelo y cliente especificos
     *
     * @param calId calificacion que se va a eliminar
     * @param clienteId cliente especifico
     * @param modId modelo especifico
     */
    public void removeCalificacion(Long calId, Long clienteId, Long modId) {
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        ModeloEntity m = modeloLogic.getModelo(clienteId);
        CalificacionEntity cal = find(calId);
        c.getCalificaciones().remove(cal);
        m.getCalificaciones().remove(cal);
        delete(cal);
    }

    /**
     * Devuelve la calificacion media de un modelo especifico.
     *
     * @param modeloId modelo especifico
     * @return calificacion media
     * @throws BusinessLogicException por reglas de negocio
     */
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
