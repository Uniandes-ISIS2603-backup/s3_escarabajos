(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('filtrosCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.pagina = $state.params.pagina;
            $scope.filtros = $state.params.filtros;
            catalogoFactory.getMarcas($state.params.tipo).then(function (response) {
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


            $(':radio').change(function () {
                $scope.filtros.calificacionMin = this.value;
                $state.go('adv', {
                    filtros: $scope.filtros,
                    pagina: $scope.pagina,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            });

            $scope.cambiarMarca = function (marca) {
                var enc = false;
                var temp = [];
                for (var i = 0; i < $scope.filtros.marcas.length && !enc; i++) {

                    if ($scope.filtros.marcas[i] === marca) {
                        enc = true;
                    } else {
                        temp.push($scope.filtros.marcas[i]);
                    }
                }
                if (!enc) {
                    temp.push(marca);
                }
                $scope.filtros.marcas = temp;
                $state.go('adv', {
                    filtros: $scope.filtros,
                    pagina: $scope.pagina,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            };

            $scope.cambiarCategoria = function (categoria) {
                var enc = false;
                var temp = [];
                for (var i = 0; i < $scope.filtros.categorias.length && !enc; i++) {

                    if ($scope.filtros.categorias[i] === categoria) {
                        enc = true;
                    } else {
                        temp.push($scope.filtros.categorias[i]);
                    }
                }
                if (!enc) {
                    temp.push(categoria);
                }
                $scope.filtros.categorias = temp;
                $state.go('adv', {
                    filtros: $scope.filtros,
                    pagina: $scope.pagina,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            };

            $scope.cambiarColor = function (color) {
                var enc = false;
                var temp = [];
                for (var i = 0; i < $scope.filtros.colores.length && !enc; i++) {

                    if ($scope.filtros.colores[i] === color) {
                        enc = true;
                    } else {
                        temp.push($scope.filtros.colores[i]);
                    }
                }
                if (!enc) {
                    temp.push(color);
                }
                $scope.filtros.colores = temp;
                $state.go('adv', {
                    filtros: $scope.filtros,
                    pagina: $scope.pagina,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            };
        }
    ]);
}
)(window.angular);