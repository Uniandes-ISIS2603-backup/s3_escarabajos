(function (ng) {
    var mod = ng.module("moduloBicis");
    
    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciCreateCtrl',['$scope','$http','biciContext','$state',
        function($scope,$http,biciContext,$state){

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createBici
             * @methodOf bicis.controller:biciCreateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la bicicleta.
             * @param {Object} bici Objeto con la nueva de la bicicleta.
             */
            $scope.createBici = function () {
                console.log($scope.data)
                var input = {};
                
                input.id = 9999;
                
                input.precio = parseInt($scope.data.precio);
                
                input.modeloId = parseInt($scope.data.modeloId);
                
                input.color = $scope.data.color;
                
                input.categoria = $scope.data.categoria;
                
                input.album  = [];
                
                input.usada = false;
                
                console.log(input)
                $http.post(biciContext, input).then(function (response) {
                    $state.go('bicisList', {biciId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);