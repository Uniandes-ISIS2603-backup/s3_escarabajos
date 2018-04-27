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
            if ($state.params.reclamoId !== undefined && $state.params.reclamoId !== null) {

                $http.get(reclamosContext + "/" + $state.params.reclamoId).then(function (response) {
                    $scope.reclamoActual = response.data;
                });
                $scope.terminar = function () {
                    $http.put(reclamosContext + "/finalizar/" + $state.params.reclamoId).then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                 $scope.renaudar = function () {
                    $http.put(reclamosContext + "/renaudar/" + $state.params.reclamoId).then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
            }
        }]);
    mod.controller('reclamoCreateCtrl', ['$scope', '$http', 'reclamosContext', '$state', '$rootScope',
        function ($scope, $http, reclamosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.createReclamo = function () {
                $http.post(reclamosContext, $scope.data).then(function (response) {
                    $state.go('reclamosList', {reclamoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);