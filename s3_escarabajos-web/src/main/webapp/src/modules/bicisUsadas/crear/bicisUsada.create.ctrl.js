(function (ng) {
    var mod = ng.module("moduloBicisUsadas");
    
    mod.constant("biciUsadaContext","api/vendedores");
    
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
                $http.post(biciUsadaContext + '/' + $state.params.vendedorId + '/bicis' , $scope.data).then(function (response) {
                    $state.go('bicisUsadaList', {biciUsadaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);