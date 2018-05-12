(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    mod.controller("modeloDetailCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;
                    $scope.tipo = $scope.modeloActual.tipoModelo;
                    $scope.items = $scope.modeloActual.items;
                    $scope.itemActual = null; //Si no encuentra ninguno deberia mandar un oops!!!!
                    $scope.precio = Number.MAX_VALUE;
                    $scope.precioUsadas = Number.MAX_VALUE;
                    $scope.colores = [];
                    $scope.usadas = [];
                    $scope.cargarImagenes = function (item) {
                        $scope.imagenes = item.album;
                        $scope.actuales = [];
                        var enc = false;
                        $scope.ini = 0;
                        $scope.fin = 3;
                        for (var i = 0; i < 4; i++) {
                            if ($scope.imagenes[i] !== undefined && $scope.imagenes[i] !== null) {
                                $scope.actuales[i] = $scope.imagenes[i];
                            } else {
                                $scope.actuales[null];
                                if (!enc) {
                                    enc = true;
                                    $scope.fin = i - 1;
                                }
                            }
                        }
                        $scope.imagen = $scope.actuales[0];
                        if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                            $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                                $scope.modeloActual = response.data;
                                if ($scope.modeloActual.items !== undefined && $scope.modeloActual.items !== null && $scope.modeloActual.items.length > 0) {
                                    $scope.categoria = $scope.modeloActual.items[0].categoria;
                                }
                            });
                        }
                    };
                    $scope.mostrarImagen = function (url) {
                        $scope.imagen = url;
                    };
                    $scope.nextImagen = function () {
                        if ($scope.fin !== $scope.imagenes.length - 1) {
                            $scope.ini++;
                            $scope.fin++;
                            var enc = false;
                            for (var i = 0; i < 4; i++) {
                                if ($scope.imagenes[i] !== undefined && $scope.imagenes[$scope.ini + i] !== null) {
                                    $scope.actuales[i] = $scope.imagenes[$scope.ini + i];
                                } else {
                                    $scope.actuales[null];
                                }
                            }
                            if (!enc) {
                                enc = true;
                                $scope.fin = i - 1;
                            }
                        }
                    };
                    $scope.prevImagen = function () {
                        if ($scope.ini !== 0) {
                            $scope.ini--;
                            $scope.fin--;
                            var enc = false;
                            for (var i = 0; i < 4; i++) {
                                if ($scope.imagenes[i] !== undefined && $scope.imagenes[$scope.ini + i] !== null) {
                                    $scope.actuales[i] = $scope.imagenes[$scope.ini + i];
                                } else {
                                    $scope.actuales[null];
                                }
                            }
                            if (!enc) {
                                enc = true;
                                $scope.fin = i - 1;
                            }
                        }
                    };
                    for (var i = 0; i < $scope.items.length; i++) {
                        if ($scope.items[i].disponible && $scope.items[i].precio < $scope.precio) {
                            $scope.itemActual = $scope.items[i];
                            $scope.precio = $scope.items[i].precio;
                        }
                        if ($scope.items[i].disponible && $scope.tipo === 'Bicicleta' && $scope.items[i].tipo === 'BicicletaUsada') {
                            $scope.usadas.push($scope.items[i]);
                            if ($scope.precioUsadas > $scope.items[i].precio) {
                                $scope.precioUsadas = $scope.items[i].precio;
                            }
                        }
                    }
                    for (var i = 0; i < $scope.items.length; i++) {
                        var enc = false;
                        for (var j = 0; j < $scope.colores.length && !enc; j++) {
                            if ($scope.items[i].color === $scope.colores[j]) {
                                enc = true;
                            }
                        }
                        if (!enc && $scope.items[i].color !== $scope.itemActual.color) {
                            $scope.colores.push($scope.items[i].color);
                        }
                    }
                    $scope.cargarImagenes($scope.itemActual);
                    $scope.cambiarColor = function (color) {
                        for (var i = 0; i < $scope.items.length; i++) {
                            if ($scope.items[i].disponible && $scope.items[i].color === color) {
                                $scope.colores.push($scope.itemActual.color);
                                for (var h = 0; h < $scope.colores.length; h++) {
                                    if ($scope.colores[h] === color) {
                                        $scope.colores.splice(h, 1);
                                    }
                                }
                                $scope.itemActual = $scope.items[i];
                                $scope.cargarImagenes($scope.itemActual);
                            }
                        }

                    };
                });
            }


        }]);
})(window.angular);