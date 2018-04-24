(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoBicicletasNoAdvCtrl', ['$scope', '$http', 'catalogoContext',
        function ($scope, $http, catalogoContext) {
            $http.get('src/data/eventos.json').then(function (response) {
                $scope.modelosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);