(function (ng) {
    var mod = ng.module("AccesoriosMod");

    mod.constant("calificacionContext", "api/modelos");

    mod.controller("calificacionesListCtrl2", ['$scope', 'calificacionContext', '$http', '$state',
        function ($scope, calificacionContext, $http, $state) {
            $scope.data = {};
            console.log($state.params.accesorioId );
            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {
                
                
                $http.get(calificacionContext + '/' + $state.params.accesorioId + '/calificaciones').then(function (response) {
                    $scope.calificaciones = response.data;
                });
                $scope.createCalificacion = function ()
                {
                    $http.post(calificacionContext + '/' + $state.params.accesorioId + '/calificaciones',  $scope.data).then(
                            $state.go('^.accesorioDetail', {accesorioId: $state.params.accesorioId}, {reload: true}));
                };
            }
        }]);

})(window.angular);