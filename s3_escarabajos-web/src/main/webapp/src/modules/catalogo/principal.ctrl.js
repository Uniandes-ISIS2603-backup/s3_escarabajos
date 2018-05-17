(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('principalCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.pagina = $state.params.pagina;
            $scope.tipo = null;
            catalogoFactory.getPrincipal($scope.pagina, 18).then(function (response) {
                $scope.mods = response.data.modelos;
                $scope.pags = Math.ceil(response.data.numero / 18);
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 6) {
                    $scope.modelos.push($scope.mods.slice(i, i + 6));
                }
                $scope.cols = 2;

                $scope.paginas = [];
                for (var j = 1; j <= $scope.pags; j++) {
                    $scope.paginas.push(j);
                }
            });

            $scope.ir = function (id) {
                $state.go('modeloDetail', {
                    modeloId: id
                }, {
                    reload: true
                });
            };

            $scope.irPagina = function (temp) {
                $state.go('principal', {
                    filtros: null,
                    pagina: temp,
                    tipo: null
                }, {
                    reload: true
                });
            };

            $scope.prev = function () {
                if ($scope.pagina !== 1) {
                    $state.go('principal', {
                        filtros: null,
                        pagina: $scope.pagina - 1,
                        tipo: null
                    }, {
                        reload: true
                    });
                }
            };
            $scope.next = function () {
                if ($scope.pagina !== $scope.pags) {
                    $state.go('principal', {
                        filtros: null,
                        pagina: $scope.pagina + 1,
                        tipo: null
                    }, {
                        reload: true
                    });
                }
            };


        }

    ]);
}
)(window.angular);