(function (ng) {
    var mod = ng.module("reclamosModule");
    mod.constant("reclamosContext", "api/reclamos");
    mod.controller('reclamosCtrl', ['$scope', '$http', 'reclamosContext',
        function ($scope, $http, reclamosContext) {
            $http.get(reclamosContext).then(function (response) {
                $scope.reclamosRecords = response.data;
                console.log(reclamosContext);
            });
        }
    ]);
}
)(window.angular);