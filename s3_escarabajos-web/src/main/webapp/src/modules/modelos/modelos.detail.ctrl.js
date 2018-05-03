(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    mod.controller("modeloDetailCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {

            $scope.imagenes = ["http://www.fyrdraca.co.uk/wp-content/uploads/2017/04/bicycle-city-street-wallpaper-53cb2e1d5128e.jpg", "data:image/jpeg;base64,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", "http://www.nationalgeographic.it/images/2017/02/17/121845769-5cd70c95-e40c-4fb6-99fa-955e334fa62b.jpg", "http://s1.1zoom.me/big0/177/Toys_Bicycle_446901.jpg", "http://www.fondosni.com/images/2013-04-28/Rueda%20de%20bicicleta-365091.jpg"];
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
            console.log($scope);
            $scope.imagen = $scope.actuales[0];
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;
                    if ($scope.modeloActual.items !== undefined && $scope.modeloActual.items !== null && $scope.modeloActual.items.length > 0) {
                        $scope.categoria = $scope.modeloActual.items[0].categoria;
                    }
                });
            }
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
                            if (!enc) {
                                enc = true;
                                $scope.fin = i - 1;
                            }
                        }
                    }
                }
                console.log($scope);
                console.log($scope.ini + "!!!" + $scope.fin);
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
                            if (!enc) {
                                enc = true;
                                $scope.fin = i - 1;
                            }
                        }
                    }
                }
                console.log($scope);
                console.log($scope.ini + "!!!" + $scope.fin);
            };


        }]);

})(window.angular);