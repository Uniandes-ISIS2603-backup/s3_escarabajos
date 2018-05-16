(function (ng) {
    var mod = ng.module("modelosModule");


    mod.controller("calificacionesListCtrl", ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            $scope.data = {};
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                try {
                    $http.get('api/modelos/' + $state.params.modeloId + '/clientes/calificaciones', ).then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    $http.post('api/modelos/' + $state.params.modeloId + '/clientes/' + sessionStorage.getItem("id") +'/calificaciones',  $scope.data).then(
                            $state.go('modeloDetail', {modeloId: $state.params.modeloId}, {reload: true}));
                };
                } catch (e) {
                    alert(e);
                }
            }
        }]);

})(window.angular);