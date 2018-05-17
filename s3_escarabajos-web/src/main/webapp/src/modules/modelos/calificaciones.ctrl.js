(function (ng) {
    var mod = ng.module("modelosModule");


    mod.controller("calificacionesListCtrl", ['$scope', '$rootScope', '$http', '$state',
        function ($scope, $rootScope, $http, $state) {
            $scope.data = {};
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                try {
                    $http.get('api/modelos/' + $state.params.modeloId + '/clientes/calificaciones', ).then(function (response) {
                        $scope.calificaciones = response.data;
                    }, function (response) {
                        $rootScope.showError(response);
                    });
                    $scope.createCalificacion = function ()
                    {
                        $http.post('api/modelos/' + $state.params.modeloId + '/clientes/' + sessionStorage.getItem("id") + '/calificaciones', $scope.data).then(function (response) {
                            $state.go('modeloDetail', {modeloId: $state.params.modeloId}, {reload: true})
                        }, function (response) {
                            $rootScope.showError(response);
                        });
                    };
                } catch (e) {
                    alert(e);
                }
            }
        }]);

})(window.angular);