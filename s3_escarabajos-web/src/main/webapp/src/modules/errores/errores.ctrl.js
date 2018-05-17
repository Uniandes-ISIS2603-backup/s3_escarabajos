(function (ng) {

    var mod = ng.module("errorMod");

    mod.controller('errorCtrl', ['$scope', '$state',
        function ($scope, $state) {
            document.getElementById("errorCode").innerHTML = $state.params.status;
            document.getElementById("errorMessage").innerHTML = $state.params.mensaje;
        }]);

})(window.angular);

