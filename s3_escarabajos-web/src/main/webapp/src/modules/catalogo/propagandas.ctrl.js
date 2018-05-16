(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('propagandasCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.filtros = $state.params.filtros;
            $scope.pagina = $state.params.pagina;
            $scope.tipo = $state.params.tipo;
            if ($scope.tipo !== null) {
                catalogoFactory.getPromociones($scope.tipo).then(function (response) {
                    $scope.promos = response.data;
                    $scope.promociones = [];
                    for (var i = 0; i < $scope.promos.length; i += 3) {
                        $scope.promociones.push($scope.promos.slice(i, i + 3));
                    }
                });
            }
        }
    ]);
}
)(window.angular);