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
                for (var i = 0; i < $scope.mods.length; i += 3) {
                    $scope.modelos.push($scope.mods.slice(i, i + 3));
                }
                $scope.cols = 4;

                $scope.paginas = [];
                for (var j = 1; j <= $scope.pags; j++) {
                    $scope.paginas.push(j);
                }
            });
            /**
             * Va el detalle del modelo que se quiere ver
             * @param id del modelo que se quiere ver
             */
            $scope.ir = function (id) {
                $state.go('modeloDetail', {
                    modeloId: id
                }, {
                    reload: true
                });
            };
            /**
             * Va a la página anterior
             */
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
            /**
             * Va a la siguiente página
             */
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

            /**
             * Va a una página dada por parametro
             * @param temp la págnia indicada por parametro
             */
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
