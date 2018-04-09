(function (ng) {
    var mod = ng.module("moduloBicis", ['ui.router']);

    mod.constant("biciContext", "api/bicis");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/bicis/';

            $urlRouterProvider.otherwise("/bicis/list");

            $stateProvider.state('bicis', {
                url: '/bicis',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bicis.html',
                        controller: 'biciCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicisList', {
                url: '/list',
                parent: 'bicis',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicis.list.html'
                    }
                }
            }).state('biciDetail', {
                url: '/{biciId:int}/detail',
                parent: 'bicis',
                param: {
                    biciId: null
                },
                views: {
                    'listView':{
                      templateUrl: basePath + 'bici.list.html',
                      controller : 'biciDetailCtrl',
                      controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'bici.detail.html',
                        controller: 'biciDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular)