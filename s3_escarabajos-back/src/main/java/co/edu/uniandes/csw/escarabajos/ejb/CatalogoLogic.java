package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CatalogoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres
 */
@Stateless
public class CatalogoLogic {

    @Inject
    private CatalogoPersistence persistence;

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
     * @param marcas marcas a filtrar.
     * @param categorias categorias a filtrar
     * @param colores colores a filtrar
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param pagina pagina a buscar
     * @param numModelos numero de modelos a filtrar.
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public List<ModeloEntity> getModelosBicicletasFiltrados(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, Integer pagina, Integer numModelos) throws BusinessLogicException {
        if (marcas != null && categorias != null && colores != null && precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return persistence.filtrarBicicletas(marcas, categorias, colores, precioMin, precioMax, calificacionMin, pagina, numModelos);
        } else {
            throw new BusinessLogicException("Los Filtros no estan en el formato adecuado!");
        }
    }

    /**
     * Metodo que se encarga de llamar el metodo de la persistencia que filtra
     * los modelos.
     *
     * @param marcas marcas a filtrar.
     * @param categorias categorias a filtrar
     * @param colores colores a filtrar
     * @param precioMin precioMin de los modelos a filtrar
     * @param precioMax precioMax de los modelos a filtrar
     * @param calificacionMin calificacion minima de los modelos a filtrar
     * @param pagina pagina a buscar
     * @param numModelos numero de modelos a filtrar.
     * @return lista de modeloEntity filtrados.
     * @throws BusinessLogicException si los filtros no estan en el formato
     * adecuado!
     */
    public List<ModeloEntity> getModelosAccesoriosFiltrados(List<String> marcas, List<String> categorias, List<String> colores, Double precioMin, Double precioMax, Double calificacionMin, Integer pagina, Integer numModelos) throws BusinessLogicException {
        if (marcas != null && categorias != null && colores != null && precioMin >= 0 && precioMax >= 0 && calificacionMin >= 0) {
            return persistence.filtrarAccesorios(marcas, categorias, colores, precioMin, precioMax, calificacionMin, pagina, numModelos);
        } else {
            throw new BusinessLogicException("Los Filtros no estan en el formato adecuado!");
        }
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
        if (marcas != null && categorias != null && colores != null && precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return persistence.contarBicicletasFiltradas(marcas, categorias, colores, precioMin, precioMax, calificacionMin);
        } else {
            throw new BusinessLogicException("Los Filtros no estan en el formato adecuado!");
        }
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
        if (marcas != null && categorias != null && colores != null && precioMin >= 0 && precioMax >= -1 && calificacionMin >= 0) {
            return persistence.contarAccesoriosFiltrados(marcas, categorias, colores, precioMin, precioMax, calificacionMin);
        } else {
            throw new BusinessLogicException("Los Filtros no estan en el formato adecuado!");
        }
    }

    /**
     * Metodo que se encarga de pedirle el precio al persistence.
     *
     * @return precio de la bicicleta mas cara de la aplicacion.
     */
    public Double getPrecioBicicletas() {
        return persistence.getPrecioBicicletas();
    }

    /**
     * Metodo que se encarga de pedirle el precio al persistence.
     *
     * @return precio del accesorios mas cara de la aplicacion.
     */
    public Double getPrecioAccesorios() {
        return persistence.getPrecioAccesorios();
    }

    /**
     * Metodo que se encarga de llamar a los metodos de busqueda de la
     * persistencia.
     *
     * @param busqueda string a buscar
     * @return modelos que cumplen con la busqueda.
     */
    public List<ModeloEntity> buscar(String busqueda) {
        List<ModeloEntity> lista = new ArrayList<>();
        String[] busquedas = busqueda.split(" ");
        for (String busqueda1 : busquedas) {
            List<ModeloEntity> temp = persistence.buscarAccesorios(busqueda1);
            for (ModeloEntity modeloEntity : temp) {
                boolean encontrado = false;
                for (int i = 0; i < lista.size() && !encontrado; i++) {
                    if (modeloEntity.getId().equals(lista.get(i).getId())) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    lista.add(modeloEntity);
                }
            }
            temp = persistence.buscarBicicletas(busqueda1);
            for (ModeloEntity modeloEntity : temp) {
                boolean encontrado = false;
                for (int i = 0; i < lista.size() && !encontrado; i++) {
                    if (modeloEntity.getId().equals(lista.get(i).getId())) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    lista.add(modeloEntity);
                }
            }
        }
        return lista;
    }
}
