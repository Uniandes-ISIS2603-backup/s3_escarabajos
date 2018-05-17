/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.ListaDeseosPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class ListaDeseosLogic {
    
    /**
     * LOGGER de la clase ListaDeseosLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(ListaDeseosLogic.class.getName());
    
    /**
     * Constante para modelar que no existe una lista de deseos.
     */
    private static final String NOEXISTE = "No existe un listadeseos con el id";
    
    /**
     * Injecta la persistencia de lista de deseos.
     */
    @Inject
    private ListaDeseosPersistence persistence;
    
    /**
     * Injecta la logica de item.
     */
    @Inject
    private ItemLogic itemLogic;
    
    /**
     * Crea un carito. 
     * @param entity la lista de deseos que se quiere crear
     * @return la lista de deseos agregado
     * @throws BusinessLogicException  si ya existe el lista de deseos
     */
    public ListaDeseosEntity createListaDeseos(ListaDeseosEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación del listadeseos");
        ListaDeseosEntity rpta = persistence.create(entity);
        LOGGER.info("Termina proceso de creación de listadeseos");
        return rpta;
    }
    
    /**
     * Devuelve una lista de deseos especifica.
     * @param id el id de la lista de deseos a buscar
     * @return la lista de deseos encontrado
     * @throws BusinessLogicException por reglas de negocio
     */
    public ListaDeseosEntity findListaDeseos( Long id ) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el listadeseos con id={0}", id);
        ListaDeseosEntity listadeseos = persistence.find(id);
        if (listadeseos == null) {
            LOGGER.log(Level.SEVERE, "El listadeseos con el id {0} no existe", id);
            throw new BusinessLogicException();
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar listadeseos con id={0}", id);
        return listadeseos;
    }
    
    /**
     * Actualiza una lista de deseos
     * @param entity la lista de deseos con la informacion actualizada
     * @return la lista de deseos actualizada
     * @throws BusinessLogicException si no se puede actualizar
     */
    public ListaDeseosEntity updateListaDeseos( ListaDeseosEntity entity ) throws BusinessLogicException{
        
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException(NOEXISTE + entity.getId()+ "\"");
        }
        return persistence.update(entity);
    }
    
    /**
     * Elimina una lista de deseos
     * @param id el id de la lista de deseos que se desea borrar
     */
    public void deleteListaDeseos(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar listadeseos con id={0}", id);
        persistence.deleteListaDeseos(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar listadeseos con id={0}", id);
    }
    
    /**
     * Devuelve la lista de items de la lista de deseos
     * @param idListaDeseos el id de la lista de deseos a la que se le quieren consultar los items
     * @return la lista de items
     * @throws BusinessLogicException si no se puede colsultar
     */
    public List<ItemEntity> getItems( Long idListaDeseos ) throws BusinessLogicException{
        
        if (persistence.find(idListaDeseos) == null) {
            throw new BusinessLogicException(NOEXISTE + idListaDeseos + "\"");
        }
        
        ListaDeseosEntity listadeseos = persistence.find(idListaDeseos);
        
        return listadeseos.getItems();
    }
    
    /**
     * Agrega un item a la lista de items
     * @param idListaDeseos el id del carrito al que se le quiere agregar items
     * @param itemId el id del item que se desea agregar 
     * @return el item agregado
     * @throws BusinessLogicException si no se encuentra la lista de deseos o el item
     */
    public ItemEntity addItem( Long idListaDeseos, Long itemId ) throws BusinessLogicException{
                  
        ListaDeseosEntity listadeseos = persistence.find(idListaDeseos);
        
        if (listadeseos == null) {
            throw new BusinessLogicException(NOEXISTE + idListaDeseos + "\"");
        }
        
        if (listadeseos.getItems().contains(itemLogic.getItem(itemId))) {
            throw new BusinessLogicException("El listadeseos ya tiene este item");
        }
        
        ItemEntity item = itemLogic.getItem(itemId);
        
        if( item == null ) {
            
            throw new BusinessLogicException(NOEXISTE + itemId );
        }
        
        List<ItemEntity> rpta = listadeseos.getItems();
        
        rpta.add(item);
        
        listadeseos.setItems(rpta);
        
        listadeseos.setPrecioTotal(listadeseos.getPrecioTotal() + item.getPrecio());
              
        updateListaDeseos(listadeseos);
        
        return item;
    }
    
    /**
     * Elimina un item de la lista de items de la lista de deseos
     * @param idListaDeseos el id de la lista de deseos que se le quiere quitar un item
     * @param itemId el id del item que se desea quitar
     * @return el item eliminado
     * @throws BusinessLogicException  si no se encuentra la lista de deseos o el item
     */
    public ItemEntity removeItem( Long idListaDeseos, Long itemId ) throws BusinessLogicException{
        ListaDeseosEntity listadeseos = persistence.find(idListaDeseos);
        if (listadeseos == null) {
            throw new BusinessLogicException(NOEXISTE + idListaDeseos + "\"");
        }
        
        ItemEntity item = itemLogic.getItem(itemId);
        List<ItemEntity> rpta = listadeseos.getItems();
        rpta.remove(item);
        listadeseos.setItems(rpta);
        listadeseos.setPrecioTotal(listadeseos.getPrecioTotal() - item.getPrecio());
        updateListaDeseos(listadeseos);
        
        return item;
    }
    
    /**
     * Genera la factura con los items que tiene la lista de deseos y vacia la lista de deseos
     * @param id el id de la lista de deseos
     * @return la factura generada
     * @throws BusinessLogicException si la lista de deseos no tiene items 
     */
    public FacturaEntity crearFactura( Long id ) throws BusinessLogicException{
        ListaDeseosEntity listadeseos = persistence.find(id);
        if ( listadeseos== null) {
            throw new BusinessLogicException(NOEXISTE + id+ "\"");
        }
        if(listadeseos.getItems().isEmpty()) {
            throw new BusinessLogicException("No se puede generar factura porque el listadeseos aun no contiene items");
        }
        
        FacturaEntity factura = new FacturaEntity();
        
        actualizarPrecioTotal(id);
        
        listadeseos = persistence.find(id);
        
        factura.setDinero(listadeseos.getPrecioTotal());
        
        factura.setCliente(listadeseos.getCliente());
                
        listadeseos = persistence.find(id);
        
        listadeseos.setItems(new ArrayList<>());
        
        listadeseos.setPrecioTotal(0.0);
        
        persistence.update(listadeseos);
        
        return factura;
    }
    
    /**
     * Calcula el procio total de la lista de deseos y lo actualiza
     * @param id de la lista de deseos que se desea actualizar el precio
     */
    public void actualizarPrecioTotal( Long id ){
        
        ListaDeseosEntity listadeseos = persistence.find(id);
        
        listadeseos.setPrecioTotal(0.0);
        
        for(int i=0; i<listadeseos.getItems().size();i++){
            
            listadeseos.setPrecioTotal(listadeseos.getPrecioTotal()+ listadeseos.getItems().get(i).getPrecio());
        }
        
        persistence.update(listadeseos);
    }
    
    /**
     * Devuelve la lista de deseos perteneciente al cliente que entra por parametro
     * @param clienteId del cliente dueño de la lista de deseos
     */
    public ListaDeseosEntity getListaDeseosByClienteId( Long clienteId ){
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el listadeseos del cliente con id={0}", clienteId);
        ListaDeseosEntity listadeseos = persistence.findListaDeseosByClienteId(clienteId);
        if (listadeseos == null) {
            LOGGER.log(Level.SEVERE, "El cliente con id {0} aun no tiene un listadeseos ", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar listadeseos del cliente con id={0}", clienteId);
        return listadeseos;
    }
}
