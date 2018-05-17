/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;

/**
 * Clase que extiende de {@link PromocionDetailDTO} para manejar la
 * transformacion entre los objetos JSON y las Entidades de la base de datos.
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
 *      "precio":double,
 *      "nuevo":boolean,
 *      "descuento": int,
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
 *      "precio": 0.0,
 *      "nuevo" true,
 *      "descuento" 10
 *   }
 *
 * </pre>
 *
 * @author Andres
 */
public class PromocionDetailDTO extends ModeloDetailDTO {

    /**
     * Atributo que representa el porcentaje de descuento de un modelo
     */
    private Integer descuento;

    /**
     * Constructor por defecto
     */
    public PromocionDetailDTO() {
        //empty
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el
     * objeto
     */
    public PromocionDetailDTO(ModeloEntity entity) {
        super(entity);
        descuento = 0;
        if (entity != null) {
            double min = Double.MAX_VALUE;
            for (ItemEntity item : entity.getItems()) {     
                if (item.getMultiplicador().toString().equals("1.0") && item.getMultiplicador() <min) {
                    min = item.getMultiplicador();
                }
            }
            if (min <1) {
                descuento = (int) ((1.0 - min) * 100);
            }
        }

    }

    /**
     * @return the descuento
     */
    public Integer getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

}
