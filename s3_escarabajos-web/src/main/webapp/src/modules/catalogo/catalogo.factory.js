(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.factory('catalogoFactory', ['$http', 'catalogoContext', function ($http, catalogoContext) {
            var dataFactory = {};
            dataFactory.getPromociones = function (tipo) {
                return $http.get(catalogoContext+"/promociones/"+tipo);
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
            return dataFactory;
        }]);
}
)(window.angular);