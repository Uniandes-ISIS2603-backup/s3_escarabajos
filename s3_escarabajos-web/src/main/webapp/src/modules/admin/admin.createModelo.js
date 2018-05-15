(function (ng) {

    var mod = ng.module("adminModule");
    
    mod.constant("modeloContext","api/modelos");
    
    mod.controller('adminCreateCtrl',['$scope','$http','modeloContext', '$state',
        function($scope,$http,modeloContext, $state){
            $scope.data = {};            

            $scope.createModelo = function () {
                $http.post(modeloContext, $scope.data).then(function () {
                    $state.go('adminCategorias',{reload: true});
                });
            };
    }]);

})(window.angular);
