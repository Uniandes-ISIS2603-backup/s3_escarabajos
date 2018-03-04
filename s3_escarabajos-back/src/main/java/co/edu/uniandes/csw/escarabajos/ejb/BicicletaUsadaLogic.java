///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.escarabajos.ejb;
//
//import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
//import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.escarabajos.persistence.BicicletaUsadaPersistence;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//
///**
// *
// * @author c.santacruza
// */
//@Stateless
//public class BicicletaUsadaLogic {
//
//    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());
//
//    @Inject
//    private BicicletaUsadaPersistence persistence;
//    @Inject
//    private ItemLogic logicItem;
//    @Inject
//    private VendedorLogic logicVendedor;
//
//    /**
//     * Devuelve todos los bicicleta usadas usadas que hay en la base de datos.
//     *
//     * @return Lista de entidades de tipo bicicleta usada.
//     */
//    public List<BicicletaUsadaEntity> getBicicletasDelVendedor(Long id) {
//        LOGGER.info("Inicia proceso de consultar todos los bicicleta usadas usadas");
//        List<BicicletaUsadaEntity> bicis = persistence.findAllBicis(id);
//        LOGGER.info("Termina proceso de consultar todos los bicicleta usadas usadas");
//        return bicis;
//    }
//
//    /**
//     * Busca un bicicleta usada por ID
//     *
//     * @param id El id del bicicleta usada a buscar
//     * @return El bicicleta usada encontrado, null si no lo encuentra.
//     */
//    public BicicletaUsadaEntity getBicicleta(Long id) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar bicicleta usada con id={0}", id);
//        BicicletaUsadaEntity bici = persistence.find(id);
//        if (bici == null) {
//            LOGGER.log(Level.SEVERE, "El bicicleta usada con el id {0} no existe", id);
//        }
//        LOGGER.log(Level.INFO, "Termina proceso de consultar bicicleta usada con id={0}", id);
//        return bici;
//    }
//
//    /**
//     * Guardar una nueva bicicleta usada
//     *
//     * @param entity La entidad de tipo bicicleta usada del nuevo bicicleta
//     * usada a persistir.
//     * @return La entidad luego de persistirla
//     * @throws BusinessLogicException Si el atributo usada es false o si no esta
//     * asociada a una categoria o si no esta asociada a un modelo
//     */
//    public BicicletaUsadaEntity createBicicleta(BicicletaUsadaEntity entity) throws BusinessLogicException {
//        LOGGER.info("Inicia proceso de creación de bicicleta usada");
//        logic.verificarItem(entity);
//        if (entity.getUsada() != false) {
//            throw new BusinessLogicException("La bicicleta usada debe ser nueva");
//        }
//        if (entity.getCategoria() == null || entity.getCategoria().equals("")) {
//            throw new BusinessLogicException("La bicicleta usada debe estar asociada a una categoria valida");
//        }
//        if (!entity.getEstado().equals("En proceso")) {
//            throw new BusinessLogicException("La bicicleta usada debe tener estado inicial En proceso");
//        }
//        if (logic2.getVendedor(entity.getVendedor().getId()) == null) {
//            throw new BusinessLogicException("La bicicleta usada debe tener un vendedor existente");
//        }
//        persistence.create(entity);
//        LOGGER.info("Termina proceso de creación de bicicleta usada");
//        return entity;
//    }
//
//    /**
//     * Actualizar un bicicleta usada por ID
//     *
//     * @param id El ID del bicicleta usada a actualizar
//     * @param entity La entidad del bicicleta usada con los cambios deseados
//     * @return La entidad del bicicleta usada luego de actualizarla
//     * @throws BusinessLogicException Si el atributo usada es false o si no esta
//     * asociada a una categoria o si no esta asociada a un modelo
//     */
//    public BicicletaUsadaEntity updateBicicleta(Long id, BicicletaUsadaEntity entity) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Inicia proceso de actualizar bicicleta usada con id={0}", id);
//        logic.verificarItem(entity);
//        if (entity.getUsada() != true) {
//            throw new BusinessLogicException("La bicicleta usada debe ser nueva");
//        }
//        if (entity.getCategoria() == null || entity.getCategoria().equals(" ")) {
//            throw new BusinessLogicException("La bicicleta usada debe estar asociada a una categoria valida");
//        }
//        if (!entity.getEstado().equals("En proceso")) {
//            throw new BusinessLogicException("La bicicleta usada debe tener estado inicial En proceso");
//        }
//        if (logic2.getVendedor(entity.getVendedor().getId()) == null) {
//            throw new BusinessLogicException("La bicicleta usada debe tener un vendedor existente");
//        }
//        BicicletaUsadaEntity newEntity = persistence.update(entity);
//        LOGGER.log(Level.INFO, "Termina proceso de actualizar bicicleta usada con id={0}", entity.getId());
//        return newEntity;
//    }
//
//    /**
//     * Eliminar un bicicleta usada por ID
//     *
//     * @param id El ID de la bicicleta usada a eliminar
//     */
//    public void deleteBicicleta(Long id) {
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar bicicleta usada con id={0}", id);
//        persistence.delete(id);
//        LOGGER.log(Level.INFO, "Termina proceso de borrar bicicleta usada con id={0}", id);
//    }
//}
