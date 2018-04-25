(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogoContext','$state',
        function ($scope, $http, catalogoContext, $state) {
            $scope.adv = $state.params.adv;
            console.log($scope);
        }
    ]);
}
)(window.angular);