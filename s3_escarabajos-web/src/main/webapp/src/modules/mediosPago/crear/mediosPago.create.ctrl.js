(function (ng) {
    var mod = ng.module("moduloMediosPago");
    
    mod.constant("mediosPagoContext","api/clientes");
    
    mod.controller('mediosPagoCreateCtrl',['$scope','$http','mediosPagoContext','$state',
        function($scope,$http,mediosPagoContext,$state){

            $scope.data = {};
            
            $scope.createMediosP = function () {
                var input = {};
                
                input.id = 9999;
                
                input.tipo = $scope.data.tipo;
                
                $http.post(mediosPagoContext + '/' + sessionStorage.getItem("id") + '/mediospago', input).then(function (response) {
                    $state.go('mediosPagoList', {biciUsadaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);