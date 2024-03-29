/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.List;

/**
 * ItemDTO Objeto de transferencia de datos de Accesorios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precio": double,
 *      "modeloId": number,
 *      "color": String,
 *      "categoria": String,
 *      "album": [{@String}]
 *   }
 * </pre> Por ejemplo un item se representa asi:<br>
 *
 * <pre>
 *   {
 *      "id": 1,
 *      "precio": "221340.91",
 *      "modeloId": 1,
 *      "color" Rojo,
 *      "categoria" deportiva,
 *      "album": [
 *          {
 *              "id": 1,
 *              "url": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAKvSURBVDjLpZJdSFNhGMcnIX4GhglGKHlRemHhYBoSiEkgZhgaIjqwNaqBGuxiF5FFhDOHzRmZlGBdhI5SopQInZOJtXkTKGYyN+fm5s7mOW7HtjHHhv17zynLqDDowMN7znOe/+/5egUABP9jewY4VlePOp3OG3a7/YnVaq32er37/hlgXlq65fF6wbIsb263G2azmZqdnU3fE/Bhbq7d7fEgGo0iEokgGAwiHA7D7/eDAFjjzEziXwEGo/Gu3eXixaFQiM/OMAzW19d5kNVmw3uTSfFHgMFgUFpIACfmgrmMnJj0zrfAGbOxAcP0tO83gHVgoI3S6xElgkAgAJ/Px4s9pJW1tTU4HA7YCJzzj01O4heAp7W1LTg0hNjUFLY6O7FpMICmaVAUBRdph2wBy8vLPJBsBi9HR5d+AKz19TK2vx8xQt1SqRBsacFnqRT04CDICrGysgKyQqwSITeHsfFxPNNqs3iAMTs7wdbUhBhxhpVKBMj7pkQCf10dmKoquNRqWCwWvJh4CsXji7iqOY8G5elwxfUTN3nAWE7OMbtcjujwMAIyGTYbG+GrrQVTWQlvWRmo4mJou67hzvAlvFnoxRylQ/dEE+q6j+Nk8yG14Hlm5pFFki3S1wdWLIavpgZ0RQW8paWgiopAE4C0/QxGPt7HyOIDbnBQ66+gWy/jAFuCntTUuNd5efOMXP4lpFCALi+Hp6QEbpEINAGwhYU41yrE24V+7H5G53s5wLcN9KSlHTSJRE5GLI6GGhpAE0CAVOAXCvEpPx+nmg9H7+mk6NBJeHHHuORnBTtr1KSkHBjIyHi1WFDAuoXCbVtu7va7rKyYNj39LAlUXlDnoUt3mc/Mndw3P4PdF+l2fHycJjFR9Cg5WfEwKalak5Cwf+cfCVYRC3Blfz9VnP8rovbZoQ8oWiIAAAAASUVORK5CYII=",
 *              "descripcion": "Lever"
 *          },
 *          {
 *              "id": 2,
 *              "url": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAJFSURBVDjLpZNfaM0BFMc/v7s/pv25tnsXlrzo2nav3d3y4CaelqLw4l1JEVaUl1GeUHtQXpiSUvKqZFFWJtFajO62K/KwlXQn7syfe+3PPX883AkNKefl1KnzOed8zzmBu/M/Vvm74OnMiayZJlTNVfXO2fT5nX8ChJYm9zRhJFrrWok1xAJRTf+tgyWAU6neDwuyUCx5ieJCEREZ+xsgcHfOjJ50M0XV0LL39sa2QEwYnRr7JKKqqiER4cru641LNFBT1tfGMDfMHccCNcMd4s3xsLribjyeePp7EVUVdcPcyBVe83HuI+KCuRMKKjBz1oVjiMgfAKJk81kaqsKsrG3h/dc86loex+dRUwQlUhdhz7VdLiKIKLcPDATBz3dwbPCgx5vjZKczqBnmirihrjRUhVlTvxYzxzEGRx5w99Bg8MsdiCjqimjZ62KymmIz87x5+YLZ2SLF+QJuxR8jHL13wEWUFTUrUDNKXiprYoqYUZ13ossr2Lh1E2uaYtx/fpPh7EPS3S3nQt8rJ1a2syq8isnPE8SbkiSakiQiKTqiKWSqSKqtEw0pnau3oUGJdMdmgCOVACURBCXz7hkbop1MvJ0kl59CVYmGo8x8zlMV1LGjfT8Ax7su0z/eB9yqqQSQkqBmJCJJRI1cfoobe/sDgO2XurxQmOZ5bojR3CN6tl2ld2AfNRXLAObKABGevBpBVFlc0dwPYcWorw2Gx4aCzckt9I/3UR1U8ijzAOBi8K/vnO5u6QUOA/XAF6Bv+EKu5xvVXGolRpHH+AAAAABJRU5ErkJggg==",
 *              "descripcion": "Cayford"
 *          }
 *      ]
 *   }
 * </pre>
 *
 * @author Andres
 */
public abstract class ItemDTO {

    /**
     * Modela el id del item.
     */
    private Long id;

    /**
     * Modela el precio del item.
     */
    private double precio;

    /**
     * Modela el modelo del item.
     */
    private Long modeloId;

    /**
     * Modela el color del item.
     */
    private String color;

    /**
     * Modela la categoria del item.
     */
    private String categoria;

    /**
     * Modela el album del item.
     */
    private List<String> album;

    /**
     * Constructor por defecto.
     */
    public ItemDTO() {
        //empty
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public ItemDTO(ItemEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.precio = entity.getPrecio();
            this.modeloId = entity.getModeloId();
            this.color = entity.getColor();
            this.categoria = entity.getCategoria();
            this.album = entity.getAlbum();
        }
    }

    /**
     * Abstract
     *
     * @return
     */
    public abstract ItemEntity toEntity();

    /**
     * Convertir DTO a Entity
     *
     * @param entity Es la entidad que se le van a asignarlos valores del DTO
     * @return Un Entity con los valores del DTO
     */
    public ItemEntity toEntity(ItemEntity entity) {
        entity.setId(this.getId());
        entity.setPrecio(this.getPrecio());
        entity.setModeloId(this.getModeloId());
        entity.setColor(this.getColor());
        entity.setCategoria(this.getCategoria());
        entity.setAlbum(this.getAlbum());
        return entity;
    }

    /**
     * Devuelve el id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     *
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el precio.
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio.
     *
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve el album.
     *
     * @return album
     */
    public List<String> getAlbum() {
        return album;
    }

    /**
     * Modifica el album.
     *
     * @param album nuevo album
     */
    public void setAlbum(List<String> album) {
        this.album = album;
    }

    /**
     * Devuelve el modelo.
     *
     * @return modeloId
     */
    public long getModeloId() {
        return modeloId;
    }

    /**
     * Modifica el modelo.
     *
     * @param modeloId nuevo modeloId
     */
    public void setModeloId(long modeloId) {
        this.modeloId = modeloId;
    }

    /**
     * Devuelve el color.
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Modifica el color.
     *
     * @param color nuevo color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Devolver la categoria.
     *
     * @return categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modificar la categoria.
     *
     * @param categoria nueva categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
