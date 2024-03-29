/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;

/**
 * Clase que extiende de {@link ItemDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del item vaya a la documentacion de {@link ItemDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precio": number,
 *      "modeloId": number,
 *      "color": color,
 *      "categoria": String,
 *      "album": [{@String}],
 *      "tipo": String,
 *      "referencia": String,
 *      "disponible": Boolean,
 *      "multiplicador": Double
 *   }
 * </pre> Por ejemplo un item se representa asi:<br>
 *
 * <pre>
 *   {
 *      "id": 1,
 *      "precio": "221340.91",
 *      "modeloId": 1,
 *      "color": rojo,
 *      "categoria": "casco",
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
 *      ],
 *      "tipo": "Accesorio",
 *      "referencia": "1234-abcd",
 *      "disponible": true,
 *      "multiplicador":1.0
 *
 *   }
 * </pre>
 *
 * @author Andres
 */
public class ItemDetailDTO extends ItemDTO {

    /**
     * Modela el tipo de item.
     */
    private String tipo;

    /**
     * Modela la referencia de item.
     */
    private String referencia;

    /**
     * Modela si el item esta disponible.
     */
    private Boolean disponible;

    /**
     * Modela el multiplicador de item.
     */
    private Double multiplicador;

    /**
     * Constructor por defecto.
     */
    public ItemDetailDTO() {
        //Empty
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad del item a partir de la cual se construye el
     * objeto
     * @param referencia la referencia del modelo al que le pertenece el item.
     */
    public ItemDetailDTO(ItemEntity entity, String referencia) {
        super(entity);
        if (entity != null) {
            if (entity instanceof AccesorioEntity) {
                this.tipo = ModeloLogic.ACCESORIO;
            } else if (entity instanceof BicicletaEntity) {
                if (entity instanceof BicicletaUsadaEntity) {
                    this.tipo = ModeloLogic.BICICLETAUSADA;
                } else {
                    this.tipo = ModeloLogic.BICICLETA;
                }
            }
            this.referencia = referencia;
            this.disponible = entity.getDisponible();
            this.multiplicador = entity.getMultiplicador();
        }
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public ItemEntity toEntity() {
        //ATENCION !!! --ESTE METODO NO SE PUEDE LLAMAR, Si quieren el toEntity() de un ITEM toca por las subclases para no perder informacion.
        return null;
    }

    /**
     * Devuelve el tipo.
     *
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo.
     *
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la referencia.
     *
     * @return referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Modifica la referencia.
     *
     * @param referencia nueva referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Devuelve si esta disponible.
     *
     * @return disponible
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /**
     * Modifica si esta disponible.
     *
     * @param disponible nuevo disponible
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Devuelve el multiplicador.
     *
     * @return multiplicador
     */
    public Double getMultiplicador() {
        return multiplicador;
    }

    /**
     * Modifica el multiplicador.
     *
     * @param multiplicador nuevo multiplicador
     */
    public void setMultiplicador(Double multiplicador) {
        this.multiplicador = multiplicador;
    }
}
