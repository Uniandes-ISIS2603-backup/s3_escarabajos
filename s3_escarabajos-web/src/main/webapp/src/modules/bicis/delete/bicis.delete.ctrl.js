(function (ng) {
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciDeleteCtrl',['$scope','$http','biciContext','$state',
        function($scope,$http,biciContext,$state){

            $http.delete(biciContext + "/" + $state.params.biciId).then(function (response) {
                $state.go('bicisList',{biciId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);