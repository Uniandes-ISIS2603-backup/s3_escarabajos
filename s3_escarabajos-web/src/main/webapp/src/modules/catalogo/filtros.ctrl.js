(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('filtrosCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.pagina = $state.params.pagina;
            $scope.filtros = $state.params.filtros;
            var test = [{"nombre": "AAAA"}, {"nombre": "BBBB"}, {"nombre": "CCCC"}, {"nombre": "DDDD"}, {"nombre": "EEEE"}, {"nombre": "FFFF"}, {"nombre": "GGGG"}, {"nombre": "HHHH"}, {"nombre": "IIII"}, {"nombre": "JJJJ"}];

           //catalogoFactory.getMarcas($state.params.tipo).then(function (response) {
                $scope.marc = [{"nombre": "AAAA"}, {"nombre": "BBBB"}, {"nombre": "CCCC"}, {"nombre": "DDDD"}, {"nombre": "EEEE"}, {"nombre": "FFFF"}, {"nombre": "GGGG"}, {"nombre": "HHHH"}, {"nombre": "IIII"}, {"nombre": "JJJJ"}];//response.data;
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
           // });

            catalogoFactory.getCategorias($state.params.tipo).then(function (response) {
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

            catalogoFactory.getColores($state.params.tipo).then(function (response) {
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
        }
    ]);
}
)(window.angular);