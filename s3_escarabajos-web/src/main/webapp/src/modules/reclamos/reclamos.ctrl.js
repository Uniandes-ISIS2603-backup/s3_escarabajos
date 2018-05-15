(function (ng) {
    var mod = ng.module("reclamosModule");
    mod.constant("reclamosContext", "api/clientes");
    mod.controller('reclamosCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext + "/" + sessionStorage.getItem("id") + "/reclamos").then(function (response) {
                $scope.reclamosRecords = response.data;
            });
        }
    ]);
    mod.controller("reclamosDetailCtrl", ['$scope', 'reclamosContext', '$http', '$state',
        function ($scope, reclamosContext, $http, $state) {
            if ($state.params.reclamoId !== undefined && $state.params.reclamoId !== null) {
                $scope.data = {};

                $http.get(reclamosContext + "/reclamos/" + $state.params.reclamoId).then(function (response) {
                    $scope.reclamoActual = response.data;
                });
                $scope.terminar = function () {
                    $http.put(reclamosContext + "/reclamos/" + $state.params.reclamoId + "/finalizar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.renaudar = function () {
                    $http.put(reclamosContext + "/reclamos/" + $state.params.reclamoId + "/renaudar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.updateMensaje = function () {

                    $http.put(reclamosContext + "/reclamos/" + $state.params.reclamoId, $scope.data).then(function (response) {
                        $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true});
                    });
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
     mod.controller('reclamosAdminCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext + "/reclamos").then(function (response) {
                $scope.reclamosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);