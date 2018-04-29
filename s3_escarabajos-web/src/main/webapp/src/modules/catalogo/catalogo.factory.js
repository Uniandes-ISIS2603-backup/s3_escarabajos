(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.factory('catalogoFactory', ['$http', 'catalogoContext', function ($http, catalogoContext) {
            var dataFactory = {};
            dataFactory.getPromociones = function () {
                var promos = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];
                var promociones = [];
                for (var i = 0; i < promos.length; i += 3) {
                    promociones.push(promos.slice(i, i + 3));
                }
                return promociones;
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

            dataFactory.getModelos = function (tipo, filtros, pagina, numeroModelos) {
                var temp = angular.toJson(filtros);
                temp.precioMin = parseFloat(filtros.precioMin);
                temp.precioMax = parseFloat(filtros.precioMax);
                temp.calificacionMin = parseFloat(filtros.calificacionMin)
                return $http.post(catalogoContext + '/modelos/' + tipo + '/' + pagina + '/' + numeroModelos, temp);
            };
            

            return dataFactory;
        }]);
}
)(window.angular);