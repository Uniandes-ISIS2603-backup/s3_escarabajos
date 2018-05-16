(function (ng) {
    var mod = ng.module("moduloMediosPago");
    
    mod.constant("mediosPagoContext","api/clientes");
    
    mod.controller('mediosPagoCtrl',['$scope','$http','mediosPagoContext',
        function($scope,$http,mediosPagoContext){
            
            if ((sessionStorage.getItem("id") !== undefined)&& (sessionStorage.getItem("id") !== null)) {
                
                $http.get(mediosPagoContext + '/' + sessionStorage.getItem("id") + '/mediospago').then(function (response) {
                    $scope.medios = response.data;
                });
            }
    }]);
})(window.angular)