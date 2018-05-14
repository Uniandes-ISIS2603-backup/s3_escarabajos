/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
//DONE: Borrar lo que no se use
import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link ListaDeseosDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link ListaDeseosDTO}
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * * <pre>
 *   {
 *      "precioTotal": double,
 *      "items": [{@ItemDTO}],
 *      "cliente": {@ClienteDTO}
 *   }
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "precioTotal": 0.0,
 *      "items": [
 *          {
 *              "id": 1,
 *              "precio": "221340.91",
 *              "modeloId": 1,
 *              "color": "rojo",
 *              "album": []
 *          }
 *      ],
 *      "cliente":{ "id": 1, "nombre": "Ariel", "correo": "asnar0@discuz.net", "usuario": "␣", "cedula": "8643803030"}
 *   }
 *
 * </pre>
 *
 * @author Mateo
 */
public class ListaDeseosDetailDTO extends ListaDeseosDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela los items que el cliente va a comprar
     */
    private List<ItemDTO> items = new ArrayList<>();
    
    /**
     * modela el cliente dueño del listadeseos;
     */
    private ClienteDTO cliente;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    
    /**
     * Constructo por defecto
     */
    public ListaDeseosDetailDTO() {
        super();
    }
    
    /**
     * Crea un listadeseos de la nada y le asigna un cliente que llega por parametro
     */
    //TODO: Revisar este método. cuándo se usaría?
    public ListaDeseosDetailDTO( ClienteDTO cliente ){
        
        super();
        this.items = new ArrayList<ItemDTO>();
        this.cliente = cliente;
    }
    
    /**
     * Crea un ListaDeseosDTO a partir de un Entity
     * @param entity 
     */
    public ListaDeseosDetailDTO( ListaDeseosEntity entity ) {
        
        super(entity);
       //TODO: entity podría ser null
        if (entity.getCliente()!= null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        } else {
            entity.setCliente(null);
        }
        if (entity.getItems()!= null) {
            items = new ArrayList<ItemDTO>();
            for (ItemEntity itemEntity : entity.getItems()) {
                if (itemEntity instanceof AccesorioEntity) {
                   items.add(new AccesorioDTO((AccesorioEntity)itemEntity)); 
                }
                else if (itemEntity instanceof BicicletaEntity) {
                   items.add(new BicicletaDTO((BicicletaEntity)itemEntity)); 
                }
            }
        }
    }
    
    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    /**
     * retorna los items
     * @return 
     */
    public List<ItemDTO> getItems() {
        return items;
    }
    
    /**
     * asigna los items
     * @param items 
     */
    public void setItems(ArrayList<ItemDTO> items) {
        this.items = items;
    }

    /**
     * retorna el cliente
     * @return 
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * asigna el cliente
     * @param cliente 
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Lo convierte en un ListaDeseosENity
     * @return 
     */
    @Override
    public ListaDeseosEntity toEntity() {
        
        ListaDeseosEntity entity = super.toEntity();
        
        if (getItems().isEmpty()) {
            List<ItemEntity> itemsEntities = new ArrayList<>();
            for (ItemDTO itemDto : getItems()) {
                itemsEntities.add(itemDto.toEntity());
            }
            entity.setItems(itemsEntities);
        }
        if (this.getCliente()!= null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        
        return entity;
    }
}