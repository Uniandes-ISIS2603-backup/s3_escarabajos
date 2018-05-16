(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('advCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.filtros = $state.params.filtros;
            $scope.pagina = $state.params.pagina;
            $scope.tipo = $state.params.tipo;

            catalogoFactory.getModelos($scope.tipo, $scope.filtros, $scope.pagina, 9).then(function (response) {
                $scope.mods = response.data.modelos;
                $scope.pags = Math.ceil(response.data.numero / 9);
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 4) {
                    $scope.modelos.push($scope.mods.slice(i, i + 4));
                }
                $scope.cols = 3;

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

            $scope.prev = function () {
                if ($scope.pagina !== 1) {
                    $state.go('adv', {
                        filtros: $scope.filtros,
                        pagina: $scope.pagina - 1,
                        tipo: $scope.tipo
                    }, {
                        reload: true
                    });
                }
            };
            $scope.next = function () {
                if ($scope.pagina !== $scope.pags) {
                    $state.go('adv', {
                        filtros: $scope.filtros,
                        pagina: $scope.pagina + 1,
                        tipo: $scope.tipo
                    }, {
                        reload: true
                    });
                }
            };


            $scope.irPagina = function (temp) {
                $state.go('adv', {
                    filtros: $scope.filtros,
                    pagina: temp,
                    tipo: $scope.tipo
                }, {
                    reload: true
                });
            };

        }

    ]);
}
)(window.angular);
