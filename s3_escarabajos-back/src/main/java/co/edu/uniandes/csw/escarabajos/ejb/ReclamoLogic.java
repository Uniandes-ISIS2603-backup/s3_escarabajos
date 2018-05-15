/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
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

    @Inject
    private ClienteLogic clienteLogic; 
    public ReclamoEntity createReclamo(ReclamoEntity ent, Long facturaId, Long clienteId) throws BusinessLogicException {
        LOGGER.info("Iniciando el proceso de crear un reclamo.");
        if (ent.getMensaje() == null || ent.getMensaje().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese los detalles del reclamo.");
        }
        if (ent.getRazon() == null || ent.getRazon().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese el motivo de su reclamo");
        }
        FacturaEntity f = facturaLogic.getFactura(facturaId);
        ClienteEntity c = clienteLogic.getCliente(clienteId);
        
        ent.setCliente(c);
        ent.setFactura(f);
        
        ReclamoEntity nuevo = reclamoPersistence.create(ent);
        LOGGER.info("Finalizando el proceso de crear un reclamo");
        return nuevo;
    }
    public ReclamoEntity find(Long id) {
        LOGGER.info("Inicia el proceso de buscar un calificaciòn.");
        ReclamoEntity answ = reclamoPersistence.find(id);
        LOGGER.info("Termina el proceso de buscar un calificaciòn.");
        return answ;
    }

    public List<ReclamoEntity> findAll() {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos.");
        List<ReclamoEntity> answ = reclamoPersistence.findAll();
        LOGGER.info("Termina el proceso de mostrar todos los reclamos.");
        return answ;
    }

    public void terminarReclamo(Long id) {
        ReclamoEntity temp = find(id);
        temp.terminar();
        reclamoPersistence.update(temp);
    }

    /**
     *
     * @param id
     */
    public void renaudarReclamo(Long id) {
        ReclamoEntity temp = find(id);
        temp.renaudar();
        reclamoPersistence.update(temp);
    }

    public ReclamoEntity getReclamoPorfactura(Long facturaId) {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de una factura.");
        ReclamoEntity answ = reclamoPersistence.getReclamoPorFactura(facturaId);
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de una factura.");
        return answ;
    }

    public ReclamoEntity updateMensajeReclamo(ReclamoEntity ent, Long reclId) throws BusinessLogicException {
        LOGGER.info("Iniciando el proceso de actualizar un reclamo.");
        if (ent.getMensaje() == null || ent.getMensaje().isEmpty()) {
            throw new BusinessLogicException("Por favor ingrese el mensaje.");
        }
        ReclamoEntity actualizado = reclamoPersistence.find(reclId);
        actualizado.setMensaje(actualizado.getMensaje() + "@@@@@@@" + ent.getMensaje());
        actualizado = reclamoPersistence.update(actualizado);
        LOGGER.info("Finalizando el proceso de actualizar un reclamo");
        return actualizado;
    }
    public List<ReclamoEntity> getReclamoByCliente(Long clienteId) {
        LOGGER.info("Inicia el proceso de mostrar todos los reclamos de un cliente.");
        List<ReclamoEntity> answ = reclamoPersistence.getReclamoPorCliente(clienteId);
        LOGGER.info("Termina el proceso de mostrar todos los reclamos de un cliente.");
        return answ;
    }
}
