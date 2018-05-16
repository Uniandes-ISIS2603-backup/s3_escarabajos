(function (ng) {

    var mod = ng.module("adminModule");
    
    mod.constant("modeloContext","api/modelos");
    
    mod.controller('adminCreateCtrl',['$scope','$http','modeloContext', '$state',
        function($scope,$http,modeloContext, $state){
            $scope.modeloActual = {};         
            $scope.createModelo = function () {
                
                var input = {};
                input.calificacionMedia = 0;
                input.id = 0;
                input.tipoModelo = $scope.modeloActual.tipoModelo;
                input.referencia = $scope.modeloActual.referencia;
                input.marca = $scope.modeloActual.marca;
                
                console.log.data;
                $http.post(modeloContext, input).then(function () {
                    $state.go('adminCategorias',{reload: true});
                });
            };
    }]);

})(window.angular);
