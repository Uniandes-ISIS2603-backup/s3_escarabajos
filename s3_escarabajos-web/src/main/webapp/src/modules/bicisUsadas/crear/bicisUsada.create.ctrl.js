(function (ng) {
    var mod = ng.module("moduloBicisUsadas");
    
    mod.constant("biciUsadaContext","api/clientes");
    
    mod.controller('biciUsadaCreateCtrl',['$scope','$http','biciUsadaContext','$state',
        function($scope,$http,biciUsadaContext,$state){

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createBiciUsada
             * @methodOf bicisUsada.controller:biciUsadaCreateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la bicicleta usada.
             * @param {Object} biciUsada Objeto con la nueva de la bicicleta usada.
             */
            
              
              
            $scope.createBiciUsada = function () {
                 var input = {};
                
                input.id = 7777;
                
                input.precio = parseInt($scope.data.precio);
                
                input.modeloId = parseInt($scope.data.modeloId);
                
                input.color = $scope.data.color;
                
                input.categoria = $scope.data.categoria;
                
                input.album  = [];
                
                input.usada = true;
                
                input.facturaOriginal  = $scope.data.facturaOriginal;
                
                  //Debia ser: biciUsadaContext + '/' + $state.params.vendedorId + '/bicis' pero aun no esta funcionando vendedor completamente.
                //por ende lo hago con un vendedor especifico.
                $http.post(biciUsadaContext + '/1/bicis' , input).then(function (response) {
                    $state.go('bicisUsadaList', {biciUsadaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);