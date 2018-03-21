/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
//DONE: Borrar lo que no se use
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CarritoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CarritoDTO}
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
 *              "album": [
 *                  {
 *                      "id": 1,
 *                      "url": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAKvSURBVDjLpZJdSFNhGMcnIX4GhglGKHlRemHhYBoSiEkgZhgaIjqwNaqBGuxiF5FFhDOHzRmZlGBdhI5SopQInZOJtXkTKGYyN+fm5s7mOW7HtjHHhv17zynLqDDowMN7znOe/+/5egUABP9jewY4VlePOp3OG3a7/YnVaq32er37/hlgXlq65fF6wbIsb263G2azmZqdnU3fE/Bhbq7d7fEgGo0iEokgGAwiHA7D7/eDAFjjzEziXwEGo/Gu3eXixaFQiM/OMAzW19d5kNVmw3uTSfFHgMFgUFpIACfmgrmMnJj0zrfAGbOxAcP0tO83gHVgoI3S6xElgkAgAJ/Px4s9pJW1tTU4HA7YCJzzj01O4heAp7W1LTg0hNjUFLY6O7FpMICmaVAUBRdph2wBy8vLPJBsBi9HR5d+AKz19TK2vx8xQt1SqRBsacFnqRT04CDICrGysgKyQqwSITeHsfFxPNNqs3iAMTs7wdbUhBhxhpVKBMj7pkQCf10dmKoquNRqWCwWvJh4CsXji7iqOY8G5elwxfUTN3nAWE7OMbtcjujwMAIyGTYbG+GrrQVTWQlvWRmo4mJou67hzvAlvFnoxRylQ/dEE+q6j+Nk8yG14Hlm5pFFki3S1wdWLIavpgZ0RQW8paWgiopAE4C0/QxGPt7HyOIDbnBQ66+gWy/jAFuCntTUuNd5efOMXP4lpFCALi+Hp6QEbpEINAGwhYU41yrE24V+7H5G53s5wLcN9KSlHTSJRE5GLI6GGhpAE0CAVOAXCvEpPx+nmg9H7+mk6NBJeHHHuORnBTtr1KSkHBjIyHi1WFDAuoXCbVtu7va7rKyYNj39LAlUXlDnoUt3mc/Mndw3P4PdF+l2fHycJjFR9Cg5WfEwKalak5Cwf+cfCVYRC3Blfz9VnP8rovbZoQ8oWiIAAAAASUVORK5CYII=",
 *                      "descripcion": "Lever"
 *                  },
 *                  {
 *                      "id": 2,
 *                      "url": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAJFSURBVDjLpZNfaM0BFMc/v7s/pv25tnsXlrzo2nav3d3y4CaelqLw4l1JEVaUl1GeUHtQXpiSUvKqZFFWJtFajO62K/KwlXQn7syfe+3PPX883AkNKefl1KnzOed8zzmBu/M/Vvm74OnMiayZJlTNVfXO2fT5nX8ChJYm9zRhJFrrWok1xAJRTf+tgyWAU6neDwuyUCx5ieJCEREZ+xsgcHfOjJ50M0XV0LL39sa2QEwYnRr7JKKqqiER4cru641LNFBT1tfGMDfMHccCNcMd4s3xsLribjyeePp7EVUVdcPcyBVe83HuI+KCuRMKKjBz1oVjiMgfAKJk81kaqsKsrG3h/dc86loex+dRUwQlUhdhz7VdLiKIKLcPDATBz3dwbPCgx5vjZKczqBnmirihrjRUhVlTvxYzxzEGRx5w99Bg8MsdiCjqimjZ62KymmIz87x5+YLZ2SLF+QJuxR8jHL13wEWUFTUrUDNKXiprYoqYUZ13ossr2Lh1E2uaYtx/fpPh7EPS3S3nQt8rJ1a2syq8isnPE8SbkiSakiQiKTqiKWSqSKqtEw0pnau3oUGJdMdmgCOVACURBCXz7hkbop1MvJ0kl59CVYmGo8x8zlMV1LGjfT8Ax7su0z/eB9yqqQSQkqBmJCJJRI1cfoobe/sDgO2XurxQmOZ5bojR3CN6tl2ld2AfNRXLAObKABGevBpBVFlc0dwPYcWorw2Gx4aCzckt9I/3UR1U8ijzAOBi8K/vnO5u6QUOA/XAF6Bv+EKu5xvVXGolRpHH+AAAAABJRU5ErkJggg==",
 *                      "descripcion": "Cayford"
 *                  }
 *              ]
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
     * modela los items que el cliente va a comprar
     */
    private List<ItemDTO> items = new ArrayList<>();
    
    /**
     * modela el cliente dueño del carrito;
     */
    private ClienteDTO cliente;
    
    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    
    /**
     * Constructo por defecto
     */
    public CarritoDetailDTO() {
    }
    
    /**
     * Crea un carrito de la nada y le asigna un cliente que llega por parametro
     */
    //TODO: Revisar este método. cuándo se usaría?
    public CarritoDetailDTO( ClienteDTO cliente ){
        
        super();
        this.items = new ArrayList<ItemDTO>();
        this.cliente = cliente;
    }
    
    /**
     * Crea un CarritoDTO a partir de un Entity
     * @param entity 
     */
    public CarritoDetailDTO( CarritoEntity entity ) {
        
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
     * Lo convierte en un CarritoENity
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
        if (this.getCliente()!= null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        
        return entity;
    }
}
