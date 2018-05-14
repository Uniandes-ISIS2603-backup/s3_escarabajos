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
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeseosLogic.class.getName());
    
    private static final String NOEXISTE = "No existe un listadeseos con el id";
    
    @Inject
    private ListaDeseosPersistence persistence;
    
    @Inject
    private ItemLogic itemLogic;
    //DONE: Está variable no se está usando
    
    /**
     * Crea un carito 
     * @param entity el listadeseos que se quiere crear
     * @return el listadeseos agregado
     * @throws BusinessLogicException  si ya existe el listadeseos
     */
    public ListaDeseosEntity createListaDeseos(ListaDeseosEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación del listadeseos");
        // DONE: Esta verificación no está bien porque no se ha creado el listadeseos entonces no tiene aun un id. 
        
        ListaDeseosEntity rpta = persistence.create(entity);
        LOGGER.info("Termina proceso de creación de listadeseos");
        return rpta;
    }
    
    /**
     * encuentra un listadeseos
     * @param id el id del listadeseos a buscar
     * @return el listadeseos encontrado
     */
    public ListaDeseosEntity findListaDeseos( Long id ){
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el listadeseos con id={0}", id);
        ListaDeseosEntity listadeseos = persistence.find(id);
        if (listadeseos == null) {
            LOGGER.log(Level.SEVERE, "El listadeseos con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar listadeseos con id={0}", id);
        return listadeseos;
    }
    
    /**
     * actualiza un listadeseos
     * @param entity el listadeseos con la informacion actualizada
     * @return el listadeseos actualizado
     * @throws BusinessLogicException si no se puede actualizar
     */
    public ListaDeseosEntity updateListaDeseos( ListaDeseosEntity entity ) throws BusinessLogicException{
        
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException(NOEXISTE + entity.getId()+ "\"");
        }
        return persistence.update(entity);
    }
    
    /**
     * borra un listadeseos
     * @param id el id del listadeseos que se desea borrar
     */
    public void deleteListaDeseos(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar listadeseos con id={0}", id);
        persistence.deleteListaDeseos(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar listadeseos con id={0}", id);
    }
    
    /**
     * devuelve la lista de items del listadeseos
     * @param id el id del listadeseos al que se le quieren consultar los items
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
     * agrega un item a la lista de items
     * @param idListaDeseos el id del carito al que se le quiere agregar items
     * @param itemId el id del item que se desea agregar 
     * @return el item agregado
     * @throws BusinessLogicException si no se encuentra el listadeseos o el item
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
     * quita un item de la lista de items del listadeseos
     * @param idListaDeseos el id del listadeseos que se le quiere quitar un item
     * @param itemId el id del item que se desea quitar
     * @return el item eliminado
     * @throws BusinessLogicException  si no se encuentra el listadeseos o el item
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
     * genera la factura con los items que tiene el listadeseos y vacia el listadeseos
     * @param id el id del listadeseos
     * @return la factura generada
     * @throws BusinessLogicException si el listadeseos no tiene items 
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
     * calcula el procio total del listadeseos y lo actualiza
     * @param id del listadeseos que se desea actualizar el precio
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
     * retorna el listadeseos perteneciente al cliente que entra por parametro
     * @param id del cliente dueño del listadeseos
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
