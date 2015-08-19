/**
 * Created by campitos on 4/08/15.
 */
angular.module('hello',['ngRoute'])
    .config(function($routeProvider,$httpProvider){
        $routeProvider.when('/',{
            templateUrl:'home.html',
            controller:'home'
        }).when('/login',{
            templateUrl:'login.html',
            controller:'navigation'
        }).when('/guias',{
            templateUrl:'guias.html',
            controller:'guias',

        }). when('/reactivos',{
            templateUrl:'reactivos.html',
            controller:'reactivos'
        }).
         otherwise('/');
        $httpProvider.defaults.headers.common["X-Requested-With"]='XMLHttpRequest';
    })
.controller('home', function($scope, $http){
        $scope.mensaje='';
        $http.get('/recurso/').success(function(data) {
            $scope.saludo = data;
            console.log('Ya ha sido wacheado por hoy');
        })


    })
.controller('navigation',function($rootScope,$scope,$http,$location){

        var authenticate=function(credentials, callback){
            var headers=credentials ? { authorization:"Basic "
            +btoa(credentials.username+ ":"+credentials.password)
            }:{};
            $http.get('user',{headers:headers}).success(function(data){
                if(data.name){
                    $rootScope.authenticated=true;
                }else{
                    $rootScope.authenticated=false;
                }
                callback && callback();
            }).error(function(){
                $rootScope.authenticated=false;
                callback && callback();
            });
        }
        authenticate();
        $scope.credentials={};
        $scope.login=function(){
            authenticate($scope.credentials,function(){
                if($rootScope.authenticated){
                    $location.path("/")
                    $scope.error=false;
                }else{
                    $location.path("/login");
                    $scope.error=true;
                }
            })
        }

        $scope.logout=function(){
            $http.post('logout',{}).success(function(){
                $rootScope.authenticated=false;
                $location.path("/");
            }).error(function(data){
                $rootScope.authenticated=false;
            });
        }
        $scope.cambiarAGuias=function(){
            $scope.mensaje="guias.html";
            console.log("hhhhhhhaaaaddfdffdfa");
        }


    })
    .controller('nada',function($scope){
        $scope.mensaje="";
        $scope.cambiarAGuias=function(){
            $scope.mensaje="guias.html";
            console.log("hhhhhhhaaaaddfdffdfa");
        }
    }).controller('guias',function($scope){

    }).controller('reactivos',function($scope){

    });

