(function(ng){
    var mod = ng.module("moduloBicisUsadas");
    
    mod.constant("biciUsadaContext","api/vendedores");
    
    mod.controller('biciUsadaCtrl',['$scope','$http','biciUsadaContext','$state',
        function($scope,$http,biciUsadaContext,$state){
            
            $scope.listaBicisUsadas ={};
            
            //el get deberia tener lo siguiente: biciUsadaContext + '/'+ $state.params.vendedorId + '/bicis'
            //pero como no esta completa la parte de vendedor lo puse para un vendedor especifico para mostrar 
            //que si sirve la union del back con el front
            $http.get(biciUsadaContext + '/1/bicis').then(function(response){
                $scope.listaBicisUsadas = response.data;
            });
    }]);
})(window.angular)