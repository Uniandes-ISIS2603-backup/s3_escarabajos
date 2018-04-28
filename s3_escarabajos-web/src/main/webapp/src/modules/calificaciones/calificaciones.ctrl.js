(function (ng) {
    var mod = ng.module("moduloBicis");
    mod.constant("calificacionesContext", "api/calificaciones");
    mod.controller('calificacionCtrl', ['$scope', '$http', 'calificacionContext',
        function ($scope, $http, reclamosContext) {
            $http.get(calificacionesContext).then(function (response) {
                $scope.reclamosRecords = response.data;
                console.log(reclamosContext);
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
                    $http.put(reclamosContext + "/finalizar/" + $state.params.reclamoId).then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.renaudar = function () {
                    $http.put(reclamosContext + "/renaudar/" + $state.params.reclamoId).then(
                            $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true}));
                };
                $scope.updateMensaje = function () {

                    $http.put(reclamosContext + "/" + $state.params.reclamoId, $scope.data).then(function (response) {
                        $state.go('^.reclamosDetail', {reclamoId: $state.params.reclamoId}, {reload: true});
                    });
                };
            }
        }]);
}
)(window.angular);