/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CarritoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CarritoDTO}
 * @author Mateo
 */
public class CarritoDetailDTO extends CarritoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela los items que el cliente va a comprar
     */
    private List<ItemDTO> items;
    
    /**
     * modela el cliente dueño del carrito;
     */
    private ClienteDTO cliente;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    
    public CarritoDetailDTO() {
    }
    
    public CarritoDetailDTO( CarritoEntity entity ) {
        
        super(entity);
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
    
    public List<ItemDTO> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<ItemDTO> items) {
        this.items = items;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public CarritoEntity toEntity() {
        
        CarritoEntity entity = super.toEntity();
        
        if (getItems() != null) {
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
