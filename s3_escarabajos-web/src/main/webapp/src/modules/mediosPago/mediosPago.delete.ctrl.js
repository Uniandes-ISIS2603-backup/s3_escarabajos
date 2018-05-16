(function (ng) {
    var mod = ng.module("moduloMediosPago");

    
    mod.controller('mediosPagoDeleteCtrl',['$scope','$http','mediosPagoContext','$state',
        function($scope,$http,mediosPagoContext,$state){

            $http.delete('api/mediospago/'+ $state.params.medioPagoId).then(function (response) {
                $state.go('mediosPagoList',{medioPagoId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);