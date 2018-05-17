package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CatalogoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres
 */
@Stateless
public class CatalogoLogic {

    private static final Logger LOGGER = Logger.getLogger(CatalogoLogic.class.getName());
    private static final String ERROR = "Los Filtros no estan en el formato adecuado!";
    @Inject
    private CatalogoPersistence persistence;

    @Inject
    private ModeloLogic modeloLogic;

    @Inject
    private ItemLogic itemLogic;

    /**
     * Obtiene una colección de todas las marcas de accesorios en el sistema
     *
     * @return Colección de nombres de todas las marcas de accesorios en el
     * sistema
     *
     */
    public List<String> getMarcasAccesorios() {
        return persistence.findMarcas(ModeloLogic.ACCESORIO);
    }

    /**
     * Obtiene una colección de todas las categorias de accesorios en el sistema
     *
     * @return Colección de nombres de todas las categorias de accesorios en el
     * sistema
     *
     */
    public List<String> getCategoriasAccesorios() {
        return persistence.findCategorias(ModeloLogic.ACCESORIO);
    }

    /**
     * Obtiene una colección de todos los colores de accesorios en el sistema
     *
     * @return Colección de nombres de todas los colores de accesorios en el
     * sistema
     *
     */
    public List<String> getColoresAccesorios() {
        return persistence.findColores(ModeloLogic.ACCESORIO);
    }

    /**
     * Obtiene una colección de todas las marcas de bicicletas en el sistema
     *
     * @return Colección de nombres de todas las marcas de bicicletas en el
     * sistema
     *
     */
    public List<String> getMarcasBicicletas() {
        return persistence.findMarcas(ModeloLogic.BICICLETA);
    }

    /**
     * Obtiene una colección de todas las categorias de bicicletas en el sistema
     *
     * @return Colección de nombres de todas las categorias de bicicletas en el
     * sistema
     *
     */
    public List<String> getCategoriasBicicletas() {
        return persistence.findCategorias(ModeloLogic.BICICLETA);
    }

    /**
     * Obtiene una colección de todos los colores de bicicletas en el sistema
     *
     * @return Colección de nombres de todas los colores de bicicletas en el
     * sistema
     *
     */
    public List<String> getColoresBicicletas() {
        return persistence.findColores(ModeloLogic.BICICLETA);
    }

    /**
     * Metodo que se encarga de llamar el metodo de la persistencia que filtra
     * los modelos.
     *
     * @param filtros lista de marcas,categorias y colores
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param pagina pagina a buscar
     * @param numModelos numero de modelos a filtrar.
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public List<ModeloEntity> getModelosBicicletasFiltrados(List<List<String>> filtros, Double precioMin, Double precioMax, Double calificacionMin, Integer pagina, Integer numModelos) throws BusinessLogicException {
        if (precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {

            return filtrar(filtros, precioMin, precioMax, calificacionMin, pagina, numModelos, ModeloLogic.BICICLETA);

        }
        throw new BusinessLogicException(ERROR);

    }

    /**
     * Metodo que se encarga de llamar el metodo de la persistencia que filtra
     * los modelos.
     *
     * @param filtros lista de marcas,categorias y colores
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param pagina pagina a buscar
     * @param numModelos numero de modelos a filtrar.
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public List<ModeloEntity> getModelosAccesoriosFiltrados(List<List<String>> filtros, Double precioMin, Double precioMax, Double calificacionMin, Integer pagina, Integer numModelos) throws BusinessLogicException {
        if (precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return filtrar(filtros, precioMin, precioMax, calificacionMin, pagina, numModelos, ModeloLogic.ACCESORIO);

        }
        throw new BusinessLogicException(ERROR);

    }

    /**
     * Metodo que se encarga de llamar el metodo que cuenta el numero de modelos
     * de tipo bicicleta con los filtros datos. los modelos.
     *
     *
     * @param filtros lista de marcas,categorias y colores
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param tipo tipo a contar
     * @param pagina pagina a buscar
     * @param numModelos numero de modelos a filtrar.
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public List<ModeloEntity> filtrar(List<List<String>> filtros, Double precioMin, Double precioMax, Double calificacionMin, Integer pagina, Integer numModelos, String tipo) throws BusinessLogicException {
        if (filtros.get(0) != null && filtros.get(1) != null && filtros.get(2) != null) {
            return persistence.filtrar(filtros, precioMin, precioMax, calificacionMin, pagina, numModelos, tipo);
        }
        throw new BusinessLogicException(ERROR);
    }

    /**
     * Metodo que se encarga de llamar el metodo que cuenta el numero de modelos
     * de tipo bicicleta con los filtros datos. los modelos.
     *
     * @param marcas marcas a filtrar.
     * @param categorias categorias a filtrar
     * @param colores colores a filtrar
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public Integer getNumeroBicicletasConFiltros(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin) throws BusinessLogicException {
        if (precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return contarFiltrados(marcas, categorias, colores, precioMin, precioMax, calificacionMin, ModeloLogic.BICICLETA);

        }
        throw new BusinessLogicException(ERROR);
    }

    /**
     * Metodo que se encarga de llamar el metodo que cuenta el numero de modelos
     * de tipo bicicleta con los filtros datos. los modelos.
     *
     * @param marcas marcas a filtrar.
     * @param categorias categorias a filtrar
     * @param colores colores a filtrar
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param tipo tipo a contar
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public Integer contarFiltrados(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, String tipo) throws BusinessLogicException {
        if (marcas != null && categorias != null && colores != null) {
            return persistence.contarFiltrados(marcas, categorias, colores, precioMin, precioMax, calificacionMin, tipo);
        }
        throw new BusinessLogicException(ERROR);
    }

    /**
     * Metodo que se encarga de llamar el metodo que cuenta el numero de modelos
     * de tipo accesorios con los filtros datos. los modelos.
     *
     * @param marcas marcas a filtrar.
     * @param categorias categorias a filtrar
     * @param colores colores a filtrar
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @return numero de modelos de accesorios que cumplen con los filtros.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public Integer getNumeroAccesoriosConFiltros(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin) throws BusinessLogicException {
        if (precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return contarFiltrados(marcas, categorias, colores, precioMin, precioMax, calificacionMin, ModeloLogic.ACCESORIO);

        }
        throw new BusinessLogicException(ERROR);
    }

    /**
     * Metodo que se encarga de pedirle el precio al persistence.
     *
     * @return precio de la bicicleta mas cara de la aplicacion.
     */
    public Double getPrecioBicicletas() {
        return persistence.getPrecio(ModeloLogic.BICICLETA);
    }

    /**
     * Metodo que se encarga de pedirle el precio al persistence.
     *
     * @return precio del accesorios mas cara de la aplicacion.
     */
    public Double getPrecioAccesorios() {
        return persistence.getPrecio(ModeloLogic.ACCESORIO);
    }

    /**
     * Metodo que se encarga de llamar a los metodos de busqueda de la
     * persistencia.
     *
     * @param busqueda string a buscar
     * @return modelos que cumplen con la busqueda.
     */
    public List<ModeloEntity> buscar(String busqueda) {
        return persistence.buscar(busqueda);
    }

    /**
     * Metodo que se encarga de crear una propaganda!
     *
     * @param idModelo del modelo a promocionar
     * @param multiplicador del modelo
     * @return modelo actualizado.
     * @throws BusinessLogicException
     */
    public ModeloEntity crearPropaganda(Long idModelo, Double multiplicador) throws BusinessLogicException {
        ModeloEntity modelo = modeloLogic.getModelo(idModelo);
        if (modelo == null) {
            throw new BusinessLogicException(ModeloLogic.MODELONOEXISTE);
        }
        for (ItemEntity item : modelo.getItems()) {
            itemLogic.multiplicarItem(multiplicador, item.getId());
        }
        return modeloLogic.getModelo(idModelo);
    }

    /**
     * Metodo que se encarga de retornar las propaganads de un tipo de la
     * aplicacion.
     *
     * @param tipo de propagandas a buscar
     * @return lista de modelos en propaganda
     */
    public List<ModeloEntity> getPropagandas(String tipo) {
        return persistence.getPropagandas(tipo);
    }

    public List<ModeloEntity> getModelosPaginacion(Integer pagina, Integer numModelos) {
        return modeloLogic.getModelos(pagina, numModelos);
    }

    /**
     * Metodo que se encarga de borrar todos los modelos de una marca
     * @param marca a borrar
     * @throws BusinessLogicException si falla la busqueda
     */
    public void deleteMarca(String marca) throws BusinessLogicException {
        LOGGER.info("@@@@@");
        List<List<String>> filtros = new ArrayList<>();
        ArrayList<String> marcas = new ArrayList<>();
        marcas.add(marca);
        filtros.add(marcas);
        filtros.add(new ArrayList<>());
        filtros.add(new ArrayList<>());
        for (ModeloEntity modelo : getModelosAccesoriosFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE)) {
            modeloLogic.deleteModelo(modelo.getId());
        }
        for (ModeloEntity modelo : getModelosBicicletasFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE)) {
            modeloLogic.deleteModelo(modelo.getId());
        }
    }

    /**
     * Metodo que se encarga de borrar los modelos que tienen la categoria ingresada por parametro
     * @param categoria
     * @throws BusinessLogicException 
     */
    public void deleteCategoria(String categoria) throws BusinessLogicException {
        List<List<String>> filtros = new ArrayList<>();
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add(categoria);
        filtros.add(new ArrayList<>());
        filtros.add(categorias);
        filtros.add(new ArrayList<>());
        for (ModeloEntity modelo : getModelosAccesoriosFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE)) {
            modeloLogic.deleteModelo(modelo.getId());
        }
        for (ModeloEntity modelo : getModelosBicicletasFiltrados(filtros, 0.0, Double.MAX_VALUE, 0.0, 1, Integer.MAX_VALUE)) {
            modeloLogic.deleteModelo(modelo.getId());
        }
    }
}
