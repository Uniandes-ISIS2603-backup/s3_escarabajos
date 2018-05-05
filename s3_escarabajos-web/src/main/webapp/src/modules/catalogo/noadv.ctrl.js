(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('noAdvCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.filtros = $state.params.filtros;
            $scope.pagina = $state.params.pagina;
            $scope.tipo = $state.params.tipo;

            catalogoFactory.getModelos($scope.tipo, $scope.filtros, $scope.pagina, 12).then(function (response) {
                $scope.mods = response.data.modelos;
                $scope.pags = Math.ceil(response.data.numero / 9);
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 4) {
                    $scope.modelos.push($scope.mods.slice(i, i + 4));
                }
                $scope.cols = 3;

                $scope.paginas = [];
                for (var i = 1; i <= $scope.pags; i++) {
                    $scope.paginas.push(i);
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
                    $state.go('noAdv', {
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
                    $state.go('noAdv', {
                        filtros: $scope.filtros,
                        pagina: $scope.pagina + 1,
                        tipo: $scope.tipo
                    }, {
                        reload: true
                    });
                }
            };


        }

    ]);
}
)(window.angular);