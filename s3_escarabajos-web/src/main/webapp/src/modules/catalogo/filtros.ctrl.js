(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('filtrosCtrl', ['$scope', '$http', 'catalogoContext',
        function ($scope, $http, catalogoContext) {
//           $http.get(catalogoContext + '/marcas/accesorios').then(function (response) {
//                    $scope.marc = response.data;
//                    for (var j = 0; j < $scope.marc.length; j++) {
//                        $scope.marc[j].activado = false;
//                        for (var i = 0; i < $scope.filtros.marcas.length; i++) {
//                            if ($scope.filtros.marcas[i] === $scope.marc[j].nombre) {
//                                $scope.marc[j].activado = true;
//                            }
//                        }
//                    }
//                    $scope.marcasCol = [];
//                    $scope.marcas = [];
//                    for (var i = 0; i < $scope.marc.length; i++) {
//                        if (i < 5) {
//                            $scope.marcas.push($scope.marc[i]);
//                        } else {
//                            $scope.marcasCol.push($scope.marc[i]);
//                        }
//                    }
//                });
//                $http.get(catalogoContext + "/categorias/accesorios").then(function (response) {
//                    $scope.cat = response.data;
//                    for (var j = 0; j < $scope.cat.length; j++) {
//                        $scope.cat[j].activado = false;
//                        for (var i = 0; i < $scope.filtros.categorias.length; i++) {
//                            if ($scope.filtros.categorias[i] === $scope.cat[j].nombre) {
//                                $scope.cat[j].activado = true;
//                            }
//                        }
//                    }
//                    $scope.categoriasCol = [];
//                    $scope.categorias = [];
//                    for (var i = 0; i < $scope.cat.length; i++) {
//                        if (i < 5) {
//                            $scope.categorias.push($scope.cat[i]);
//                        } else {
//                            $scope.categoriasCol.push($scope.cat[i]);
//                        }
//                    }
//                });
//                $http.get(catalogoContext + "/colores/accesorios").then(function (response) {
//                    $scope.col = response.data;
//                    for (var j = 0; j < $scope.col.length; j++) {
//                        $scope.col[j].activado = false;
//                        for (var i = 0; i < $scope.filtros.colores.length; i++) {
//                            if ($scope.filtros.colores[i] === $scope.col[j].nombre) {
//                                $scope.col[j].activado = true;
//                            }
//                        }
//                    }
//                    $scope.coloresCol = [];
//                    $scope.colores = [];
//                    for (var i = 0; i < $scope.col.length; i++) {
//                        if (i < 5) {
//                            $scope.colores.push($scope.col[i]);
//                        } else {
//                            $scope.coloresCol.push($scope.col[i]);
//                        }
//                    }
//                });
            $scope.marcas = [];
            $scope.colores = [];
            $scope.categorias = [];
            console.log("!!!!");
        }
    ]);
}
)(window.angular);