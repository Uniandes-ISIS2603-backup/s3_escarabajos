/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ReclamoPersistence;
import java.util.logging.Logger;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;

/**
 *
 * @author n.gaitan
 */
@Stateless
public class ReclamoLogic {

    private final static Logger LOGGER = Logger.getLogger(ReclamoLogic.class.getName());

    @Inject
    private ReclamoPersistence reclamoPersistence;

    @Inject
    private FacturaLogic facturaLogic;

    private boolean emptyList(List<FotoEntity> lista) {
        return lista.isEmpty();
    }

    public ReclamoEntity createReclamo(ReclamoEntity ent, Long facturaId) throws BusinessLogicException {
        LOGGER.info("Iniciando el proceso de crear un reclamo.");
        if (ent.getMensaje() == null || ent.getMensaje().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
        }
        // if(emptyList(ent.getAlbum()))
        //{
        //  throw new BusinessLogicException("Debe ingresar al menos 1 foto con evidencia.");
        //}
        if (ent.getRazon() == null || ent.getRazon().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese el motivo de su reclamo");
        }
        // FacturaEntity f = facturaLogic.getFactura(facturaId);

        ReclamoEntity nuevo = reclamoPersistence.create(ent);
        // ent.setFactura(f);
        // f.setReclamo(nuevo);
        LOGGER.info("Finalizando el proceso de crear un reclamo");
        return nuevo;
    }

    public ReclamoEntity updateReclamo(ReclamoEntity ent, Long facturaId) throws BusinessLogicException {
        LOGGER.info("Iniciando el proceso de actualizar un reclamo.");
        if (ent == null || ent.getMensaje().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
        }
        // if (emptyList(ent.getAlbum())) {
        //   throw new BusinessLogicException("Debe ingresar al menos 1 foto con evidencia.");
        //FacturaEntity f = facturaLogic.getFactura(facturaId);
        //  ent.setFactura(f);
        ReclamoEntity actualizado = reclamoPersistence.find(ent.getId());
        actualizado = reclamoPersistence.update(actualizado);
        //f.setReclamo(actualizado);
        LOGGER.info("Finalizando el proceso de actualizar un reclamo");
        return actualizado;
    }

    public ReclamoEntity find(Long id) {
        LOGGER.info("Inicia el proceso de buscar un calificaciòn.");
        ReclamoEntity answ = reclamoPersistence.find(id);
        LOGGER.info("Termina el proceso de buscar un calificaciòn.");
        return answ;
    }

    public void delete(ReclamoEntity ent) {
        LOGGER.info("Inicia el proceso de eliminar un reclamo.");
        reclamoPersistence.delete(ent);
        LOGGER.info("Termina el proceso de eliminar un reclamo.");
    }

    public void delete(Long id) {
        LOGGER.info("Inicia el proceso de eliminar un reclamo por id.");
        reclamoPersistence.delete(id);
        LOGGER.info("Termina el proceso de eliminar un reclamo por id.");
    }

    public List<ReclamoEntity> findAll() {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos.");
        List<ReclamoEntity> answ = reclamoPersistence.findAll();
        LOGGER.info("Termina el proceso de mostrar todos los reclamos.");
        return answ;
    }

    public List<ReclamoEntity> findByFactura(Long facturaId) {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de una factura.");
        List<ReclamoEntity> answ = reclamoPersistence.getReclamosByFactura(facturaId);
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de una factura.");
        return answ;
    }

    public List<ReclamoEntity> getReclamosEnProceso() {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de una factura.");
        List<ReclamoEntity> answ = reclamoPersistence.getReclamosEnProceso();
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de una factura.");
        return answ;
    }

    public List<ReclamoEntity> getReclamosTerminados() {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de una factura.");
        List<ReclamoEntity> answ = reclamoPersistence.getReclamosTerminados();
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de una factura.");
        return answ;
    }

    public void terminarReclamo(Long id) {
        ReclamoEntity temp = find(id);
        temp.terminar();
        reclamoPersistence.update(temp);
    }

    public void renaudarReclamo(Long id){
        ReclamoEntity temp = find(id);
        temp.renaudar();
        reclamoPersistence.update(temp);
    }

    public List<ReclamoEntity> getReclamoPorfactura(Long facturaId) {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de una factura.");
        List<ReclamoEntity> answ = reclamoPersistence.getReclamoPorFactura(facturaId);
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de una factura.");
        return answ;
    }

    public void deleteReclamoByFacturaId(Long facturaId, Long reclamoId) throws BusinessLogicException {
        ReclamoEntity e1 = find(reclamoId);
        ReclamoEntity e2 = findByFactura(facturaId).get(0);
        if (!Objects.equals(e1.getId(), e2.getId())) {
            throw new BusinessLogicException("El reclamo no pertenece a la factura.");
        }
        facturaLogic.getFactura(facturaId).setReclamo(null);
        delete(reclamoId);
    }

    public ReclamoEntity updateMensajeReclamo(ReclamoEntity ent, Long facturaId) throws BusinessLogicException {
        LOGGER.info("Iniciando el proceso de actualizar un reclamo.");
        if (ent.getMensaje() == null || ent.getMensaje().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
        }
        // if (emptyList(ent.getAlbum())) {
        //   throw new BusinessLogicException("Debe ingresar al menos 1 foto con evidencia.");
        //}

        //FacturaEntity f = facturaLogic.getFactura(facturaId);
        //  ent.setFactura(f);
        ReclamoEntity actualizado = reclamoPersistence.find(ent.getId());
        actualizado.setMensaje(actualizado.getMensaje() + "@@@@@@@" + ent.getMensaje());
        actualizado = reclamoPersistence.update(actualizado);
        //f.setReclamo(actualizado);
        LOGGER.info("Finalizando el proceso de actualizar un reclamo");
        return actualizado;
    }
}
