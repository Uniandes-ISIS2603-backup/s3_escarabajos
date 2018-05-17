(function (ng) {
    /**
     * @ngdoc factory
     * @name error.factory:errorFactory
     * @description
     * Define el objeto factory que se encarga de relacionar todos los cotrollers del front con el back 
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.factory('catalogoFactory', ['$http', 'catalogoContext', function ($http, catalogoContext) {
            var dataFactory = {};
            dataFactory.getPromociones = function (tipo) {
                return $http.get(catalogoContext + "/promociones/" + tipo);
            };

            dataFactory.getMarcas = function (tipo) {
                return $http.get(catalogoContext + '/marcas/' + tipo);
            };
            dataFactory.getCategorias = function (tipo) {
                return $http.get(catalogoContext + '/categorias/' + tipo);
            };
            dataFactory.getColores = function (tipo) {
                return $http.get(catalogoContext + '/colores/' + tipo);
            };
            dataFactory.getPrecioMax = function (tipo) {
                return $http.get(catalogoContext + '/precio/' + tipo);
            };
            dataFactory.getModelos = function (tipo, filtros, pagina, numeroModelos) {
                var temp = angular.toJson(filtros);
                temp.precioMin = parseFloat(filtros.precioMin);
                temp.precioMax = parseFloat(filtros.precioMax);
                temp.calificacionMin = parseFloat(filtros.calificacionMin);
                return $http.post(catalogoContext + '/modelos/' + tipo + '/' + pagina + '/' + numeroModelos, temp);
            };

            dataFactory.getPrincipal = function (pagina, numeroModelos) {
                return $http.get(catalogoContext + '/modelos/' + pagina + '/' + numeroModelos);
            };

            dataFactory.getBusqueda = function (busqueda) {
                return $http.get(catalogoContext + '/buscar/' + busqueda);
            };
            return dataFactory;
        }]);
}
)(window.angular);