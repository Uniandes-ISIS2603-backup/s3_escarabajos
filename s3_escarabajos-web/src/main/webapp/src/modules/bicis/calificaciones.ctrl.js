(function (ng) {
    var mod = ng.module("moduloBicis");

    mod.constant("calificacionContext", "api/modelos");

    mod.controller("calificacionesListCtrl", ['$scope', 'calificacionContext', '$http', '$state',
        function ($scope, calificacionContext, $http, $state) {
            $scope.data = {};
            if ($state.params.biciId !== undefined && $state.params.biciId !== null) {

                $http.get(calificacionContext + '/' + $state.params.biciId + '/calificaciones').then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    $http.post(calificacionContext + '/' + $state.params.biciId + '/calificaciones',  $scope.data).then(
                            $state.go('^.biciDetail', {biciId: $state.params.biciId}, {reload: true}));
                };
            }
        }]);

})(window.angular);