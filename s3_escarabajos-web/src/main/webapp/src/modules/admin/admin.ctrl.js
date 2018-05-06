(function (ng) {

    var mod = ng.module("adminModule");
    
    mod.constant("adminContext","api/clientes");
    
    mod.controller('adminCtrl',['$scope','$http','adminContext', '$state',
        function($scope,$http,adminContext, $state){
            
            $scope.listaClientes ={};
            
            $http.get(adminContext).then(function(response){
                $scope.records = response.data;
            });
            
            this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
                $state.reload('adminList');
            };
     
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
            };

    }]);

})(window.angular);


