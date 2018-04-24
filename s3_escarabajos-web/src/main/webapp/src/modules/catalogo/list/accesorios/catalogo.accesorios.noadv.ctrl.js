(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoAccesoriosNoAdvCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        function ($scope, $http, catalogoContext, $state) {
            if (($state.params.paginaParam !== undefined) && ($state.params.filasParam !== undefined) && ($state.params.filtrosParam !== undefined)) {
                $scope.pagina = $state.params.paginaParam;
                $scope.filas = $state.params.filasParam;
                $scope.filtros = $state.params.filtrosParam;
                var marc = [];
                
                $http.get(catalogoContext + "/marcas/accesorios").then(function (response) {
                    marc = response.data;
                });

               // marc = [{"nombre": "AAAA"}, {"nombre": "BBBB"}, {"nombre": "CCCC"}, {"nombre": "DDDD"}, {"nombre": "EEEE"}, {"nombre": "FFFF"}, {"nombre": "GGGG"}, {"nombre": "HHHH"}, {"nombre": "IIII"}, {"nombre": "JJJJ"}];

                for (var j = 0; j < marc.length; j++) {
                    marc[j].activado = false;
                    for (var i = 0; i < $scope.filtros.marcas.length; i++) {
                        if ($scope.filtros.marcas[i] === marc[j].nombre) {
                            marc[j].activado = true;
                        }
                    }
                }
                var cat = [];
                $http.get(catalogoContext + "/categorias/accesorios").then(function (response) {
                    cat = response.data;
                });

                cat = [{"nombre": "KKKK"}, {"nombre": "LLLLL"}, {"nombre": "MMMM"}, {"nombre": "NNNN"}, {"nombre": "OOOO"}, {"nombre": "PPPPP"}, {"nombre": "QQQQ"}, {"nombre": "RRRR"}, {"nombre": "SSSS"}, {"nombre": "TTTT"}];

                for (var j = 0; j < cat.length; j++) {
                    cat[j].activado = false;
                    for (var i = 0; i < $scope.filtros.categorias.length; i++) {
                        if ($scope.filtros.categorias[i] === cat[j].nombre) {
                            cat[j].activado = true;
                        }
                    }
                }
                var col = [];
                $http.get(catalogoContext + "/colores/accesorios").then(function (response) {
                    col = response.data;
                });

                col = [{"nombre": "azul"}, {"nombre": "amarillo"}, {"nombre": "rojo"}, {"nombre": "verde"}, {"nombre": "morado"}, {"nombre": "naranja"}, {"nombre": "rosado"}, {"nombre": "negro"}, {"nombre": "blanco"}, {"nombre": "cafe"}];
                for (var j = 0; j < col.length; j++) {
                    col[j].activado = false;
                    for (var i = 0; i < $scope.filtros.colores.length; i++) {
                        if ($scope.filtros.colores[i] === col[j].nombre) {
                            col[j].activado = true;
                        }
                    }
                }
                var temp = [{"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.0, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}, {"marca": "123", "referencia": "abc-123", "calificacionMedia": 4.5, "url": "https://www.decathlon.es/media/836/8360662/big_35a2777897594375a1eb8324a42d1410.jpg", "precio": 8000}];

                $scope.modelos = [];
                for (var i = 0; i < temp.length; i += 4) {
                    $scope.modelos.push(temp.slice(i, i + 4));
                }
                $scope.marcasCol = [];
                $scope.marcas = [];
                for (var i = 0; i < marc.length; i++) {
                    if (i < 5) {
                        $scope.marcas.push(marc[i]);
                    } else {
                        $scope.marcasCol.push(marc[i]);
                    }
                }
                $scope.categoriasCol = [];
                $scope.categorias = [];
                for (var i = 0; i < cat.length; i++) {
                    if (i < 5) {
                        $scope.categorias.push(cat[i]);
                    } else {
                        $scope.categoriasCol.push(cat[i]);
                    }
                }
                $scope.coloresCol = [];
                $scope.colores = [];
                for (var i = 0; i < col.length; i++) {
                    if (i < 5) {
                        $scope.colores.push(col[i]);
                    } else {
                        $scope.coloresCol.push(col[i]);
                    }
                }
            }
        }
    ]);
}
)(window.angular);