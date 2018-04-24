(function(ng){
    var mod = ng.module("moduloFacturas");
    
    mod.constant("facturaContext","api/facturas");
    
    mod.controller('facturaCtrl',['$scope','$http','facturaContext',
        function($scope,$http,facturaContext){
            
            $scope.facturas ={};
            
            $http.get(facturaContext).then(function(response){
                $scope.facturas = response.data;
            });
    }]);
})(window.angular)
