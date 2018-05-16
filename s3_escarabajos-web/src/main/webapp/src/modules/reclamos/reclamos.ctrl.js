(function (ng) {
    var mod = ng.module("reclamosModule");
    mod.constant("reclamosContext", "api/clientes/reclamos");
    mod.controller('reclamosCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext + "/detail/" + sessionStorage.getItem("id")).then(function (response) {
                $scope.reclamosRecords = response.data;
            });
        }
    ]);
    mod.controller("reclamosDetailCtrl", ['$scope', 'reclamosContext', '$http', '$state',
        function ($scope, reclamosContext, $http, $state) {
            if ($state.params.reclamoId !== undefined && $state.params.reclamoId !== null) {
                $scope.data = {};

                $http.get(reclamosContext + "/" + $state.params.reclamoId).then(function (response) {
                    $scope.reclamoActual = response.data;
                });
                $scope.terminar = function () {
                    $http.put(reclamosContext + "/" + $state.params.reclamoId + "/finalizar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.renaudar = function () {
                    $http.put(reclamosContext + "/" + $state.params.reclamoId + "/renaudar").then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.updateMensaje = function () {

                    $http.put(reclamosContext + "/" + $state.params.reclamoId, $scope.data).then(function (response) {
                        $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true});
                    });
                };
            }
        }]);
    mod.controller('reclamoCreateCtrl', ['$scope', '$http', 'reclamosContext', '$state', '$rootScope',
        function ($scope, $http, reclamosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.facturaId = null;
            if ((sessionStorage.getItem("id") !== undefined) && (sessionStorage.getItem("id") !== null)) {

                $http.get('api/facturas/cliente/' + sessionStorage.getItem("id")).then(function (response) {
                    $scope.facturas2 = response.data;
                    console.log(response);
                });


            }
            ;
            $scope.createReclamo = function () {
                $http.post(reclamosContext + '/' + sessionStorage.getItem("id") + '/facturas/' + $scope.facturaId, $scope.data).then(function (response) {
                    $state.go('reclamosList', {reclamoId: response.data.id}, {reload: true});
                }, function (response) {
                    if (response.status !== 200)
                    {
                        $state.go('error');
                        console.log(response);
                        if (response.status === 412)
                        {
                            $scope.errorCode = "ERROR" 
                        } else
                        {
                             $scope.errorCode = "ERROR -" + response.status;
                             console.log($scope.errorCode);
                        }
                       $scope.errorMessage = response.statusText;
                    }
                });
            };
        }
    ]);
    mod.controller('reclamosAdminCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext).then(function (response) {
                $scope.reclamosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);