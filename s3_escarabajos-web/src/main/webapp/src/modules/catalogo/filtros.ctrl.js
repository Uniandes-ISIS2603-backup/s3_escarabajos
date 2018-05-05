(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('filtrosCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.pagina = $state.params.pagina;
            $scope.adv = $state.params.adv;
            $scope.filtros = $state.params.filtros;

            //Marcas
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
                for (var k = 0; k < $scope.marc.length; k++) {
                    if (k < 5) {
                        $scope.marcas.push($scope.marc[k]);
                    } else {
                        $scope.marcasCol.push($scope.marc[k]);
                    }
                }
            });

            //Categorias
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
                for (var l = 0; l < $scope.cat.length; l++) {
                    if (l < 5) {
                        $scope.categorias.push($scope.cat[l]);
                    } else {
                        $scope.categoriasCol.push($scope.cat[l]);
                    }
                }
            });

            //Colores
            catalogoFactory.getColores($state.params.tipo).then(function (response) {
                $scope.col = response.data;
                $scope.coloresCol = [];
                $scope.coloresNoAct = [];
                for (var j = 0; j < $scope.col.length; j++) {
                    var enc = false;
                    for (var i = 0; i < $scope.filtros.colores.length; i++) {
                        if ($scope.filtros.colores[i] === $scope.col[j].nombre) {
                            enc = true;
                        }
                    }
                    if (enc === false)
                    {
                        $scope.coloresNoAct.push($scope.col[j]);
                    }
                }
                for (var m = 0; m < $scope.coloresNoAct.length; m += 5) {
                    $scope.coloresCol.push($scope.coloresNoAct.slice(m, m + 5));
                }
            });

            //Calificacion!
            $(':radio').change(function () {
                $scope.filtros.calificacionMin = this.value;
                var adv = 'adv';
                if (!$scope.adv) {
                    adv = 'noAdv';
                }
                $state.go(adv, {
                    filtros: $scope.filtros,
                    pagina: $scope.pagina,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            });

            //Precio
            catalogoFactory.getPrecioMax($state.params.tipo).then(function (response) {
                $scope.max = parseFloat(response.data.nombre);
                $scope.min = 0;
                $scope.valMin = 0;
                if ($scope.filtros.precioMin > 0) {
                    $scope.valMin = Math.round($scope.filtros.precioMin / 1000);
                }
                $scope.valMax = Math.round($scope.max / 1000);
                if ($scope.filtros.precioMax < $scope.max) {
                    $scope.valMax = Math.round($scope.filtros.precioMax / 1000);
                }
                $scope.max = Math.round($scope.max / 1000);
                var handle1 = $("#custom-handle1");
                var handle2 = $("#custom-handle2");
                $("#slider-range").slider({
                    create: function () {
                        handle1.text($scope.valMin + "K");
                        handle2.text($scope.valMax + "K");
                    },
                    range: true,
                    min: $scope.min,
                    max: $scope.max,
                    values: [$scope.valMin, $scope.valMax],
                    step: 10,
                    slide: function (event, ui) {
                        handle1.text("$" + ui.values[0] + "K");
                        handle2.text("$" + ui.values[1] + "K");
                    },
                    change: function (event, ui) {
                        $scope.filtros.precioMin = ui.values[0] * 1000;
                        $scope.filtros.precioMax = ui.values[1] * 1000;
                        var adv = 'adv';
                        if (!$scope.adv) {
                            adv = 'noAdv';
                        }
                        $state.go(adv, {
                            filtros: $scope.filtros,
                            pagina: $scope.pagina,
                            tipo: $scope.tipo
                        }, {
                            reload: true
                        });
                    }
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
                var adv = 'adv';
                if (!$scope.adv) {
                    adv = 'noAdv';
                }
                $state.go(adv, {
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
                var adv = 'adv';
                if (!$scope.adv) {
                    adv = 'noAdv';
                }
                $state.go(adv, {
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
                for (var i = 0; i < $scope.filtros.colores.length; i++) {
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
                var adv = 'adv';
                if (!$scope.adv) {
                    adv = 'noAdv';
                }
                $state.go(adv, {
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