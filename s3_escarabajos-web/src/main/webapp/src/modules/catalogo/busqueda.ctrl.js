(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('busquedaCtrl', ['$scope', '$state', 'catalogoFactory',
        function ($scope, $state, catalogoFactory) {
            $scope.tipo = "catalogo";
            $scope.pagina = null;
            $scope.busqueda = $state.params.busqueda;
            catalogoFactory.getBusqueda($scope.busqueda).then(function (response) {
                $scope.mods = response.data;
                $scope.modelos = [];
                for (var i = 0; i < $scope.mods.length; i += 5) {
                    $scope.modelos.push($scope.mods.slice(i, i + 5));
                }
                $scope.cols = 3;
            });

            $scope.ir = function (id) {
                $state.go('modeloDetail', {
                    modeloId: id
                }, {
                    reload: true
                });
            };
        }

    ]);
}
)(window.angular);