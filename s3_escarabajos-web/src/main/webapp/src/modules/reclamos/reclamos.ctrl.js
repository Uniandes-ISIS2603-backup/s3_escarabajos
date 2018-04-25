(function (ng) {
    var mod = ng.module("reclamosModule");
    mod.constant("reclamosContext", "api/reclamos");
    mod.controller('reclamosCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext).then(function (response) {
                $scope.reclamosRecords = response.data;
                console.log(reclamosContext);
            });
        }
    ]);
    mod.controller("reclamosDetailCtrl", ['$scope', 'reclamosContext', '$http', '$state',
        function ($scope, reclamosContext, $http, $state) {
            console.log(1);

            if ($state.params.reclamoId !== undefined && $state.params.reclamoId !== null) {

                $http.get(reclamosContext + "/" + $state.params.reclamoId).then(function (response) {
                    $scope.reclamoActual = response.data;
                });
            }
        }]);
}
)(window.angular);