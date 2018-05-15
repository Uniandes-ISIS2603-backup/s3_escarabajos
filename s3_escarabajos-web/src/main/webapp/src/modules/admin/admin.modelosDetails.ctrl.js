(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("modeloContext", "api/modelos");

    mod.controller("modelosDetailsCtrl", ['$scope','$http','modeloContext', '$state',
        function ($scope, $http, modeloContext, $state) {  
            
            if (($state.params.clienteId !== undefined)&& ($state.params.clienteId !== null)) {
                
                $http.get(modeloContext + '/' + $state.params.clienteId).then(function (response) {
                    $scope.modeloActual = response.data;
                });
            }
        }]);
    
})(window.angular);
