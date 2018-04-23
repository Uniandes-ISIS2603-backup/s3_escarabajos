(function (ng) {
    var mod = ng.module("AccesoriosMod");

    mod.constant("accesorioContext", "api/accesorios");

    mod.controller('accesorioDeleteCtrl', ['$scope', '$http', 'accesorioContext', '$state',
        function ($scope, $http, accesorioContext, $state) {

            var id = $state.params.itemId;
            
            console.log(id);
            
            $http.delete(accesorioContext + "/" + id).then(function (response) {
                $state.go('accesoriosList',{itemId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);