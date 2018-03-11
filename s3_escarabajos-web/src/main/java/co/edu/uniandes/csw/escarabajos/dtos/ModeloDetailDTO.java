/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
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
 *      "tipoModelo":String
 *      "items": [{@ItemDTO}],
 *      "calficaciones": [{@CalificacionDTO}]
 *      
 *   }
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
 *      "items":  [
 *          {
 *              "id": 1,
 *              "precio": "221340.91",
 *              "modeloId": 1,
 *              "color": rojo,
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
 *      "calificaciones": [
 *          {
 *              "id": 1,
 *              "comentario": "Muy buen Casco",
 *              "puntaje": 4       
 *          }
 *      ]
 *   }
 *
 * </pre>
 *
 * @author Andres
 */
public class ModeloDetailDTO extends ModeloDTO
{
    /**
     * Lista que representa todos los items de un modelo
     */
    private List<ItemDTO> items;
    
    /**
     * Lista que representa todas las calficaciones de un modelo
     */
    private List<CalificacionDTO> calificaciones;
    
    /**
     * Constructor por defecto
     */
    public ModeloDetailDTO()
    {
        //empty
    }
        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el objeto
     */
    public ModeloDetailDTO(ModeloEntity entity) {
        super(entity);
        if (entity.getItems()!= null) {
            items = new ArrayList<>();
            for (ItemEntity entityItem: entity.getItems()) {
                if (entityItem instanceof AccesorioEntity) {
                   items.add(new AccesorioDTO((AccesorioEntity)entityItem)); 
                }
                else if (entityItem instanceof BicicletaEntity) {
                   items.add(new BicicletaDTO((BicicletaEntity)entityItem)); 
                }
            }
        }
        if (entity.getCalificaciones()!= null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public ModeloEntity toEntity()
    {
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
    public List<ItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    
    
}
