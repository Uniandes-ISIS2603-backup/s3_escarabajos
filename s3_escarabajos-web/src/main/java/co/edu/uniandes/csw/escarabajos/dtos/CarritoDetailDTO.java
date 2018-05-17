/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CarritoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CarritoDTO} Al
 * serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * * <pre>
 *   {
 *      "precioTotal": double,
 *      "items": [{@ItemDTO}],
 *      "cliente": {
 * @ClienteDTO} }
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
public class CarritoDetailDTO extends CarritoDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Modela los items que el cliente va a comprar.
     */
    private List<ItemDTO> items = new ArrayList<>();

    /**
     * Modela el cliente dueño del carrito.
     */
    private ClienteDTO cliente;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructo por defecto.
     */
    public CarritoDetailDTO() {
        super();
    }

    /**
     * Crea un carrito de la nada y le asigna un cliente que llega por
     * parametro.
     */
    public CarritoDetailDTO(ClienteDTO cliente) {

        super();
        this.items = new ArrayList<>();
        this.cliente = cliente;
    }

    /**
     * Crea un CarritoDTO a partir de un Entity.
     *
     * @param entity
     */
    public CarritoDetailDTO(CarritoEntity entity) {

        super(entity);

        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        } else {
            entity.setCliente(null);
        }
        if (entity.getItems() != null) {
            items = new ArrayList<>();
            for (ItemEntity itemEntity : entity.getItems()) {
                if (itemEntity instanceof AccesorioEntity) {
                    items.add(new AccesorioDTO((AccesorioEntity) itemEntity));
                } else if (itemEntity instanceof BicicletaEntity) {
                    items.add(new BicicletaDTO((BicicletaEntity) itemEntity));
                }
            }
        }
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    /**
     * Devuelve los items.
     *
     * @return items
     */
    public List<ItemDTO> getItems() {
        return items;
    }

    /**
     * Modifica los items.
     *
     * @param items nuevos items
     */
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    /**
     * Devuelve el cliente.
     *
     * @return cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente.
     *
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Lo convierte en un CarritoENity
     *
     * @return
     */
    @Override
    public CarritoEntity toEntity() {

        CarritoEntity entity = super.toEntity();

        if (getItems().isEmpty()) {
            List<ItemEntity> itemsEntities = new ArrayList<>();
            for (ItemDTO itemDto : getItems()) {
                itemsEntities.add(itemDto.toEntity());
            }
            entity.setItems(itemsEntities);
        }
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }

        return entity;
    }
}
