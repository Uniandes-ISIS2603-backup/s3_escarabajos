(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('propagandasCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.filtros = $state.params.filtros;
            $scope.pagina = $state.params.pagina;
            $scope.tipo = $state.params.tipo;
            if ($scope.tipo !== null) {
                $scope.promociones = catalogoFactory.getPromociones($scope.tipo);
            }
        }
    ]);
}
)(window.angular);