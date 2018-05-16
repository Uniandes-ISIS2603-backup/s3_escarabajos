(function(ng){
    var mod = ng.module("moduloFacturas");
    
    mod.constant("facturaContext","api/facturas");
    
    mod.controller('facturaClienteCtrl',['$scope','$http','facturaContext',
        function($scope,$http,facturaContext){
            
            if ((sessionStorage.getItem("id") !== undefined)&& (sessionStorage.getItem("id") !== null)) {
                
                $http.get(facturaContext + '/cliente/' + sessionStorage.getItem("id")).then(function (response) {
                    $scope.facturas2 = response.data;
                });
            }
    }]);
})(window.angular)
