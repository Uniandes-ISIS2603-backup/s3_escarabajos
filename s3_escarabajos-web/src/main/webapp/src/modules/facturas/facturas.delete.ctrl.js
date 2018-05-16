(function (ng) {
    var mod = ng.module("moduloFacturas");

     mod.constant("facturaContext","api/facturas");
    
    mod.controller('facturaDeleteCtrl',['$scope','$http','facturaContext','$state',
        function($scope,$http,facturaContext,$state){

            $http.delete(facturaContext + '/'+ $state.params.facturaId).then(function (response) {
                $state.go('facturasList',{facturaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);