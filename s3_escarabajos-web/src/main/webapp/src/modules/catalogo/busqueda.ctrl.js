(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('busquedaCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.tipo = null;
            $scope.busqueda  = $state.params.busqueda;
            catalogoFactory.getPrincipal($scope.busqueda).then(function (response) {
                $scope.mods = response.data.modelos;
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 5) {
                    $scope.modelos.push($scope.mods.slice(i, i + 5));
                }
                $scope.cols = 3;
            });
        }

    ]);
}
)(window.angular);