(function (ng) {
    var mod = ng.module("moduloBicis");
    
    mod.constant("biciContext","api/bicis");
    
    mod.controller('biciCreateCtrl',['$scope','$http','biciContext','$state',
        function($scope,$http,biciContext,$state){

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createBici
             * @methodOf bicis.controller:biciCreateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la bicicleta.
             * @param {Object} bici Objeto con la nueva de la bicicleta.
             */
            $scope.createBici = function () {
                $http.post(biciContext, $scope.data).then(function (response) {
                    $state.go('bicisList', {biciId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);