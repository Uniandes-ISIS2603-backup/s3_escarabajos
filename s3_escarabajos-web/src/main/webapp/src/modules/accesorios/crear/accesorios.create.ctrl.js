(function (ng) {
    var mod = ng.module("AccesoriosMod");
    
    mod.constant("accesorioContext","api/accesorios");
    
    mod.controller('accesorioCreateCtrl',['$scope','$http','accesorioContext','$state',
        function($scope,$http,accesorioContext,$state){

            $scope.data = {};

            

            /**
             * @ngdoc function
             * @name createAccesorio
             * @methodOf accesorios.controller:accesorioCreateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el accesorio.
             * @param {Object} accesorio Objeto con la nueva de la accesorio.
             */
            $scope.createAccesorio = function () {
                
                console.log($scope.data);
                
                var input = {};
                
                input.id = 0;
                
                input.precio = parseInt($scope.data.precio);
                
                input.modeloId = parseInt($scope.data.modeloId);
                
                input.color = $scope.color;
                
                input.album = [];
                
                input.tipoAccesorio = $scope.tipoAccesorio;
                
                input.descripcion = $scope.descripcion;
                
                console.log(input);
                
                $http.post(accesorioContext, input).then(function (response) {
                    
                    $state.go('accesoriosList', {accesorioId: response.data.id}, {reload: true});

                });
            };
        }
    ]);
}
)(window.angular);