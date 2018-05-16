(function (ng) {
    var mod = ng.module("moduloMediosPago", ['ui.router']);

    mod.constant("mediosPagoContext", "api/clientes");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/mediosPago/';


            $stateProvider.state('mediosPago', {
                url: '/cliente',
                views: {
                    'mainView': {
                         templateUrl: basePath + 'mediosPago.list.html',
                        controller: 'mediosPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular)