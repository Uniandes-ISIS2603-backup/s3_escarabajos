(function (ng) {

    var mod = ng.module("adminModule");
    
    mod.constant("adminContext","api/modelos");
    
    mod.controller('adminCtrl',['$scope','$http','adminContext', '$state',
        function($scope,$http,adminContext, $state){
            
            $scope.listaClientes ={};
            
            $http.get(adminContext).then(function(response){
                $scope.records = response.data;
            });
            
            this.deleteRecord = function (id) {
                $http.delete('http://localhost:8080/s3_escarabajos-web/api/modelos/'+ id);
                $state.reload('adminCategorias');
            };
     
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/modelos/'+ id);
            };

    }]);

})(window.angular);


