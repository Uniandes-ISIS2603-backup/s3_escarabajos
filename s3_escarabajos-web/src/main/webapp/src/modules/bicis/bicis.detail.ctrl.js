(function (ng) {
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext", "api/bicis");

    mod.controller("biciDetailCtrl", ['$scope', 'biciContext', '$http', '$state',
        function ($scope, biciContext, $http, $state) {
            
            if ($state.params.biciId !== undefined && $state.params.biciId !== null) {
                
                $http.get(biciContext + '/' + $state.params.biciId).then(function (response) {
                    $scope.biciActual = response.data;
                });
            }
        }]);
    
})(window.angular);