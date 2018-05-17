(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    /**
     * @ngdoc controller
     * @name modelo.controller:modeloDetailCtrl
     * @description
     * Definición del controlador de Angular del módulo modelos. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Modelos en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    mod.controller("modeloDetailCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                /**
                 * @ngdoc function
                 * @name getModelo
                 * @methodOf modelos.controller:modeloDetailCtrl
                 * @description
                 * Esta función utiliza el protocolo HTTP para obtener el recurso 
                 * donde se encuentran los modelos en formato JSON. El recurso
                 * puede ser un archivo o un API Rest. La función se ejecuta
                 * automáticamente cuando el controlador es accedido desde el
                 * navegador.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los modelos o API donde se puede consultar. Se utiliza el
                 * contexto definido anteriormente.
                 */
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
                            /**
                             * @ngdoc function
                             * @name getModelo
                             * @methodOf modelos.controller:modeloDetailCtrl
                             * @description
                             * Esta función utiliza el protocolo HTTP para obtener el recurso 
                             * donde se encuentran los modelos en formato JSON. El recurso
                             * puede ser un archivo o un API Rest. La función se ejecuta
                             * automáticamente cuando el controlador es accedido desde el
                             * navegador.
                             * @param {String} URL Dirección donde se encuentra el recurso
                             * de los modelos o API donde se puede consultar. Se utiliza el
                             * contexto definido anteriormente.
                             */
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

                                $scope.fin = i - 1;
                            }
                        }
                    };
                    for (var u = 0; u < $scope.items.length; u++) {
                        if ($scope.items[u].disponible && $scope.items[u].precio < $scope.precio) {
                            $scope.itemActual = $scope.items[u];
                            $scope.precio = $scope.items[u].precio;
                        }
                        if ($scope.items[u].disponible && $scope.tipo === 'Bicicleta' && $scope.items[u].tipo === 'BicicletaUsada') {
                            $scope.usadas.push($scope.items[u]);
                            if ($scope.precioUsadas > $scope.items[u].precio) {
                                $scope.precioUsadas = $scope.items[u].precio;
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