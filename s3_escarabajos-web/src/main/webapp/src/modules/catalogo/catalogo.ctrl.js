(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.controller('catalogoCtrl', ['$scope','$state',
        function ($scope, $state) {
            $scope.adv = $state.params.adv;
            $scope.tipo = $state.params.tipo;
        }
    ]);
}
)(window.angular);