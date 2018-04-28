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
                    'detailView': {
                        templateUrl: basePath + 'bicis.detail.html',
                        controller: 'biciDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'gaitanView': {
                        templateUrl: 'src/modules/calificaciones/calificaciones.list.html'
                    }
                }
            }).state('biciCreate', {
                url: '/create',
                parent: 'bicis',
                views: {
                    'listView': {
                        templateUrl: basePath + '/crear/bicis.create.html',
                        controller: 'biciCreateCtrl'
                    }
                }
            }).state('biciDelete', {
                parent: 'bicis',
                params: {
                    biciId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicis.list.html',
                        controller: 'biciDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular)