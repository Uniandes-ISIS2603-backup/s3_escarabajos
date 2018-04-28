(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('advCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.filtros = $state.params.filtros;
            $scope.pagina = $state.params.pagina;
            $scope.tipo = $state.params.tipo;

            catalogoFactory.getModelos($scope.tipo, $scope.filtros).then(function (response) {
                $scope.mods = response.data;
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 3) {
                    $scope.modelos.push($scope.mods.slice(i, i + 3));
                }
                $scope.cols = 4;
            });
        }

    ]);
}
)(window.angular);