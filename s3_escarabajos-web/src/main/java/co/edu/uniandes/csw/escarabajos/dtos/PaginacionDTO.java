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
 * Clase que maneja la transferencia de datos de paginas.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "numero": int,
 *      "modelos": [{@ModeloDetailDTO}]
 * </pre> Por ejemplo una pagina se representa asi:<br>
 *
 * <pre>
 *  {
 *      "numero":200,
 *      "modelos":[{{
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
 *   }}]
 *  }
 *
 * </pre>
 *
 * @author Andres
 */
public class PaginacionDTO {

    /**
     * Lista que representa todos modelos de una pagina
     */
    private List<ModeloDetailDTO> modelos;

    /**
     * Integer que representa el numero total de modelos con ese filtro.
     */
    private Integer numero;

    /**
     * Constructor por defecto
     */
    public PaginacionDTO() {
        //empty
    }

    /**
     * Constructor para trasformar la lista y el numero a paginacion.
     *
     * @param modelos lista de modelos.
     * @param numero de modelos con esos filtros
     */
    public PaginacionDTO(List<ModeloDetailDTO> modelos, Integer numero) {
        this.modelos = modelos;
        this.numero = numero;
    }

    /**
     * @return the modelos
     */
    public List<ModeloDetailDTO> getModelos() {
        return modelos;
    }

    /**
     * @param modelos the modelos to set
     */
    public void setModelos(List<ModeloDetailDTO> modelos) {
        this.modelos = modelos;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
