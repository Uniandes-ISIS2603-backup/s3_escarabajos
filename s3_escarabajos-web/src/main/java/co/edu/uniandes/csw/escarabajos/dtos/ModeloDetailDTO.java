/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link ModeloDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "marca": String,
 *      "referencia": String,
 *      "calificacionMedia": double,
 *      "tipoModelo":String,
 *      "url": String,
 *      "precio"double,
 *      "items": [{@ItemDetailDTO}],
 *      "calficaciones": [{
 * @CalificacionDTO}]}
 * </pre> Por ejemplo un modelo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "marca": "BMXTREME",
 *      "referencia": "BMEXTREME-MTN-2017",
 *      "calificacionMedia": 4.50,
 *      "tipoModelo": "Accesorio"
 *      "items":  [{
 *      "type": "itemDetailDTO",
 *      "album": [],
 *      "categoria": "Deportiva",
 *      "color": "Verde",
 *      "id": 3,
 *      "modeloId": 3,
 *      "precio": 20000,
 *      "referencia": "SPORT-2017",
 *      "tipo": "Bicicleta"
 *   },
 *   {
 *       "type": "itemDetailDTO",
 *       "album": [],
 *       "color": "Verde",
 *       "id": 1,
 *       "modeloId": 1,
 *       "precio": 20000,
 *       "referencia": "BMEXTREME-MTN-2017",
 *       "tipo": "Accesorio"
 *   }],
 *      "calificaciones": [
 *          {
 *              "id": 1,
 *              "comentario": "Muy buen Casco",
 *              "puntaje": 4
 *          }
 *      ],
 *      "url": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAKvSURBVDjLpZJdSFNhGMcnIX4GhglGKHlRemHhYBoSiEkgZhgaIjqwNaqBGuxiF5FFhDOHzRmZlGBdhI5SopQInZOJtXkTKGYyN+fm5s7mOW7HtjHHhv17zynLqDDowMN7znOe/+/5egUABP9jewY4VlePOp3OG3a7/YnVaq32er37/hlgXlq65fF6wbIsb263G2azmZqdnU3fE/Bhbq7d7fEgGo0iEokgGAwiHA7D7/eDAFjjzEziXwEGo/Gu3eXixaFQiM/OMAzW19d5kNVmw3uTSfFHgMFgUFpIACfmgrmMnJj0zrfAGbOxAcP0tO83gHVgoI3S6xElgkAgAJ/Px4s9pJW1tTU4HA7YCJzzj01O4heAp7W1LTg0hNjUFLY6O7FpMICmaVAUBRdph2wBy8vLPJBsBi9HR5d+AKz19TK2vx8xQt1SqRBsacFnqRT04CDICrGysgKyQqwSITeHsfFxPNNqs3iAMTs7wdbUhBhxhpVKBMj7pkQCf10dmKoquNRqWCwWvJh4CsXji7iqOY8G5elwxfUTN3nAWE7OMbtcjujwMAIyGTYbG+GrrQVTWQlvWRmo4mJou67hzvAlvFnoxRylQ/dEE+q6j+Nk8yG14Hlm5pFFki3S1wdWLIavpgZ0RQW8paWgiopAE4C0/QxGPt7HyOIDbnBQ66+gWy/jAFuCntTUuNd5efOMXP4lpFCALi+Hp6QEbpEINAGwhYU41yrE24V+7H5G53s5wLcN9KSlHTSJRE5GLI6GGhpAE0CAVOAXCvEpPx+nmg9H7+mk6NBJeHHHuORnBTtr1KSkHBjIyHi1WFDAuoXCbVtu7va7rKyYNj39LAlUXlDnoUt3mc/Mndw3P4PdF+l2fHycJjFR9Cg5WfEwKalak5Cwf+cfCVYRC3Blfz9VnP8rovbZoQ8oWiIAAAAASUVORK5CYII=",
 *      "precio": 0.0
 *   }
 *
 * </pre>
 *
 * @author Andres
 */
public class ModeloDetailDTO extends ModeloDTO {

    /**
     * Lista que representa todos los items de un modelo
     */
    private List<ItemDetailDTO> items;

    /**
     * Lista que representa todas las calficaciones de un modelo
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * String que representa el url del item mas barato que no es usado.
     */
    private String url;

    /**
     * Double que representa el precio del item nuevo mas barato.
     */
    private Double precio;

    /**
     * Constructor por defecto
     */
    public ModeloDetailDTO() {
        //empty
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el
     * objeto
     */
    public ModeloDetailDTO(ModeloEntity entity) {
        super(entity);
        precio = Double.MAX_VALUE;
        url = "https://17a6ky3xia123toqte227ibf-wpengine.netdna-ssl.com/wp-content/uploads/2016/12/bike-home-template-optimized.jpg";
        if (entity != null && entity.getItems() != null) {
            items = new ArrayList<>();
            for (ItemEntity entityItem : entity.getItems()) {
                items.add(new ItemDetailDTO(entityItem, entity.getReferencia()));
                if (entityItem.getPrecio() < precio && entityItem.getAlbum() != null && !entityItem.getAlbum().isEmpty() && !(entityItem instanceof BicicletaUsadaEntity)) {
                    precio = entityItem.getPrecio();
                    url = entityItem.getAlbum().get(0);
                }
            }
        }
        verificarCalificaciones(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public ModeloEntity toEntity() {
        ModeloEntity modelo = super.toEntity();
        if (getItems() != null) {
            List<ItemEntity> itemsEntity = new ArrayList<>();
            for (ItemDTO dtoItem : getItems()) {
                itemsEntity.add(dtoItem.toEntity());
            }
            modelo.setItems(itemsEntity);
        }
        if (getCalificaciones() != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            modelo.setCalificaciones(calificacionesEntity);
        }

        return modelo;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the items
     */
    public List<ItemDetailDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemDetailDTO> items) {
        this.items = items;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
   /**
    *  Metodo que se encarga de verificar las calificaciones de un entity.
    * @param entity 
    */
    private void verificarCalificaciones(ModeloEntity entity) {
        if (entity != null && entity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }
    }

}
