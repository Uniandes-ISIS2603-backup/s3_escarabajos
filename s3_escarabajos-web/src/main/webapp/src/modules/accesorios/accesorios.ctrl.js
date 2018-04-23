(function(ng){
    var mod = ng.module("AccesoriosMod");
    
    mod.constant("accesorioContext","api/accesorios");
    
    mod.controller('accesorioCtrl',['$scope','$http','accesorioContext',
        function($scope,$http,accesorioContext){
            
            $scope.listaAccesorios ={};
            
            $http.get(accesorioContext).then(function(response){
                $scope.listaAccesorio = response.data;
            });
    }]);
})(window.angular)