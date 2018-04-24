(function (ng) {
    var mod = ng.module("moduloBicisUsadas");

     mod.constant("biciUsadaContext","api/vendedores");
    
    mod.controller('biciUsadaDeleteCtrl',['$scope','$http','biciUsadaContext','$state',
        function($scope,$http,biciUsadaContext,$state){

            $http.delete(biciUsadaContext + "/1/bicis/" + $state.params.biciUsadaId).then(function (response) {
                $state.go('bicisUsadaList',{biciUsadaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);