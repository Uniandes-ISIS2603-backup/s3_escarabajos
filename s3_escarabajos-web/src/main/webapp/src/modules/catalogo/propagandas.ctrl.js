(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('propagandasCtrl', ['$scope', '$http', 'catalogoContext',
        function ($scope, $http, catalogoContext) {
            var promos = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];
            $scope.promociones = [];
            for (var i = 0; i < promos.length; i += 3) {
                $scope.promociones.push(promos.slice(i, i + 3));
            }
        }
    ]);
}
)(window.angular);