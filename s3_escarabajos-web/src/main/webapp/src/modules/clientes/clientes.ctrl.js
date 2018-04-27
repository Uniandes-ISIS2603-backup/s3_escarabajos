(function (ng) {

    var mod = ng.module("clientesModule");
    
    mod.constant("clientesContext","api/clientes");
    
    mod.controller('clientesCtrl',['$scope','$http','clientesContext', '$state',
        function($scope,$http,clientesContext, $state){
            
            $scope.listaClientes ={};
            
            $http.get(clientesContext).then(function(response){
                $scope.records = response.data;
            });
            
            this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
                $state.reload('clientesList');
            };
     
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
            };
    }]);

})(window.angular);

