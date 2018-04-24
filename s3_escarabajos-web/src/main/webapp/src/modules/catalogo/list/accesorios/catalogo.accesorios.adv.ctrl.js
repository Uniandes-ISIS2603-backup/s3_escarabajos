(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoAccesoriosAdvCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        function ($scope, $http, catalogoContext, $state) {
            if (($state.params.paginaParam !== undefined) && ($state.params.filasParam !== undefined) && ($state.params.filtrosParam !== undefined)) {
                $scope.pagina = $state.params.paginaParam;
                $scope.filas = $state.params.filasParam;
                $scope.filtros = $state.params.filtrosParam;

                $http.get(catalogoContext + '/marcas/accesorios').then(function (response) {
                    $scope.marc = response.data;
                    for (var j = 0; j < $scope.marc.length; j++) {
                        $scope.marc[j].activado = false;
                        for (var i = 0; i < $scope.filtros.marcas.length; i++) {
                            if ($scope.filtros.marcas[i] === $scope.marc[j].nombre) {
                                $scope.marc[j].activado = true;
                            }
                        }
                    }
                    $scope.marcasCol = [];
                    $scope.marcas = [];
                    for (var i = 0; i < $scope.marc.length; i++) {
                        if (i < 5) {
                            $scope.marcas.push($scope.marc[i]);
                        } else {
                            $scope.marcasCol.push($scope.marc[i]);
                        }
                    }
                });
                $http.get(catalogoContext + "/categorias/accesorios").then(function (response) {
                    $scope.cat = response.data;
                    for (var j = 0; j < $scope.cat.length; j++) {
                        $scope.cat[j].activado = false;
                        for (var i = 0; i < $scope.filtros.categorias.length; i++) {
                            if ($scope.filtros.categorias[i] === $scope.cat[j].nombre) {
                                $scope.cat[j].activado = true;
                            }
                        }
                    }
                    $scope.categoriasCol = [];
                    $scope.categorias = [];
                    for (var i = 0; i < $scope.cat.length; i++) {
                        if (i < 5) {
                            $scope.categorias.push($scope.cat[i]);
                        } else {
                            $scope.categoriasCol.push($scope.cat[i]);
                        }
                    }
                });
                $http.get(catalogoContext + "/colores/accesorios").then(function (response) {
                    $scope.col = response.data;
                    for (var j = 0; j < $scope.col.length; j++) {
                        $scope.col[j].activado = false;
                        for (var i = 0; i < $scope.filtros.colores.length; i++) {
                            if ($scope.filtros.colores[i] === $scope.col[j].nombre) {
                                $scope.col[j].activado = true;
                            }
                        }
                    }
                    $scope.coloresCol = [];
                    $scope.colores = [];
                    for (var i = 0; i < $scope.col.length; i++) {
                        if (i < 5) {
                            $scope.colores.push($scope.col[i]);
                        } else {
                            $scope.coloresCol.push($scope.col[i]);
                        }
                    }
                });



                var promos = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];
                $scope.promociones = [];

                for (var i = 0; i < promos.length; i += 3) {
                    $scope.promociones.push(promos.slice(i, i + 3));
                }


                var temp = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.0, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];

                $scope.modelos = [];
                for (var i = 0; i < temp.length; i += 3) {
                    $scope.modelos.push(temp.slice(i, i + 3));
                }




            }
        }
    ]);
}
)(window.angular);