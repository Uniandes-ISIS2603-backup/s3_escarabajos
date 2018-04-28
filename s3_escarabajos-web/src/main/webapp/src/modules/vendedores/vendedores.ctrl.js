(function (ng) {

    var mod = ng.module("vendedoresModule");
    
    mod.constant("vendedoresContext","api/clientes/vendedores");

    mod.controller("vendedoresCtrl", ['$scope', '$http', 'vendedoresContext', 
        function ($scope, $http, vendedoresContext) {
    
            
            $scope.listaVendedores ={};
            
            $http.get(vendedoresContext).then(function(response){
                $scope.records = response.data;
            });
     
            this.getRecord = function (id) {
                $http.get('http://localhost:8080/s3_escarabajos-web/api/clientes/'+ id);
            };

        }]);
})(window.angular);

