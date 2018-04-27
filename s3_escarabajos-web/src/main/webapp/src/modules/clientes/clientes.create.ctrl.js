(function (ng) {

    var mod = ng.module("clientesModule");
    
    mod.constant("clientesContext","api/clientes");
    
    mod.controller('clientesCreateCtrl',['$scope','$http','clientesContext', '$state',
        function($scope,$http,clientesContext, $state){
            $scope.data = {};            
            
            $scope.createCliente = function () {
                $http.post(clientesContext, $scope.data).then(function (response) {
                    $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                });
            };
    }]);

})(window.angular);

