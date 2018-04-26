(function (ng) {
    var mod = ng.module("moduloBicisUsadas");

    mod.constant("biciUsadaContext", "api/vendedores");

    mod.controller("biciUsadaDetailCtrl", ['$scope', 'biciUsadaContext', '$http', '$state',
        function ($scope, biciUsadaContext, $http, $state) {
            
            if ($state.params.biciUsadaId !== undefined && $state.params.biciUsadaId !== null) {
                
            //el get deberia tener lo siguiente: biciUsadaContext + '/' + $state.params.vendedorId + '/bicis/' + $state.params.biciUsadaId
            //pero como no esta completa la parte de vendedor lo puse para un vendedor especifico para mostrar 
            //que si sirve la union del back con el front
                $http.get(biciUsadaContext + '/1/bicis/' + $state.params.biciUsadaId).then(function (response) {
                    console.log(response.data.facturaOriginal);
                    $scope.biciUsadaActual = response.data;
                    
                });
            }
        }]);
    
})(window.angular);