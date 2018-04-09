(function(ng){
    var mod = ng.module("moduloBicis");
    
    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciCtrl',['$scope','$http','biciContext',
        function($scope,$http,biciContext){
            
            $scope.listaBicis ={};
            
            $http.get(biciContext).then(function(response){
                $scope.listaBicis = response.data;
            });
    }]);
})(window.angular)