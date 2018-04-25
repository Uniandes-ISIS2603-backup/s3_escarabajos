(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('advCtrl', ['$scope', '$http', 'catalogoContext',
        function ($scope, $http, catalogoContext) {
            //Aqui se definen los chunks y eso
          var temp = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.0, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];

                $scope.modelos = [];
                for (var i = 0; i < temp.length; i += 3) {
                    $scope.modelos.push(temp.slice(i, i + 3));
                }
                $scope.cols = 4;
        }
    ]);
}
)(window.angular);