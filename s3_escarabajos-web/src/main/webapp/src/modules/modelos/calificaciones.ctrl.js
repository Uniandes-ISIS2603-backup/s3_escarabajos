(function (ng) {
    var mod = ng.module("modelosModule");

    mod.constant("calificacionContext", "api/modelos");

    mod.controller("calificacionesListCtrl", ['$scope', 'calificacionContext', '$http', '$state',
        function ($scope, calificacionContext, $http, $state) {
            $scope.data = {};
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {

                $http.get(calificacionContext + '/' + $state.params.modeloId + '/calificaciones').then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    $http.post(calificacionContext + '/' + $state.params.modeloId + '/calificaciones',  $scope.data).then(
                            $state.go('modeloDetail', {modeloId: $state.params.modeloId}, {reload: true}));
                };
            }
        }]);

})(window.angular);