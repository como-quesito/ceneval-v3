/**
 * Created by campitos on 4/08/15.
 */
angular.module('hello',['ngRoute','ngResource','ngFileUpload'])
    .config(function($routeProvider,$httpProvider){
        $routeProvider.when('/',{
            templateUrl:'home.html',
            controller:'home'
        }).when('/ingresar',{
            templateUrl:'ingresar.html',
            controller:'navigation'
        }).when('/guias',{
            templateUrl:'guias.html',
            controller:'guias',

        }). when('/reactivos',{
            templateUrl:'reactivos.html',
            controller:'reactivos'
        }). when('/profesores',{
            templateUrl:'profesores.html',
            controller:'profesores'
        }).
         otherwise('/');
        $httpProvider.defaults.headers.common['X-Requested-With']='XMLHttpRequest';
    })
.controller('home', function($scope, $http){
        $scope.mensaje='';
        $http.get('recurso').success(function(data) {
            $scope.saludo = data;
            console.log('Ya ha sido wacheado por hoy');
        })


    })
.controller('navigation',function($rootScope,$scope,$http,$location,$route){
        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };
        $rootScope.temita;
        var authenticate=function(credentials, callback){
            var headers=credentials ? { authorization:"Basic "
            +btoa(credentials.username+ ":"+credentials.password)
            }:{};
            $http.get('user',{headers:headers}).success(function(data){
                if(data.name){
                    $rootScope.authenticated=true;
                    //El siguiente es para que sin ahcer click aparezca el menu en los reactivos
                    $rootScope.props = [{label: "1.", tema:"Introduccion"},{label: "2.", tema:"bases"},{label: "3.", tema:"conclusiones"}];

                    console.log("el nombre es este:"+credentials.username);
                    if(data.name==='campitos'){
                        $rootScope.supercan=true;
                    }else{
                        $rootScope.supercan=false;
                    }

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
                    $location.path("/ingresar");
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
    }).controller('reactivos',function($scope,Upload, $timeout,$rootScope, $http,$resource,$log){
        $scope.hola="hola desde los reactivos";

        $scope.uploadFiles = function(file) {
            $scope.f = file;
            if (file && !file.$error) {
                file.upload = Upload.upload({
                   // url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                    url:'cargar-mongo1',
                    file: file
                });

                file.upload.then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.errorMsg = response.status + ': ' + response.data;
                });

                file.upload.progress(function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                        evt.loaded / evt.total));
                });
            }
        }

        /*
        var Reactivo=$resource('reactivo/:id',{id:'@id'},{crear:{method:'POST'},
            actualizar:{method:'PUT'}, borrar:{method:'DELETE'}});


        $rootScope.setSelected = function(prop){
            $rootScope.selectedprop = prop;
            console.log(prop.label);
        };
        $rootScope.props = [{label: "1.", tema:"Introduccion"},{label: "2.", tema:"bases"},{label: "3.", tema:"conclusiones"}];

        $rootScope.agarrar=function(){

        }


        //GET Todos

            $scope.reactivo=Reactivo.query(function(){
                console.log($scope.reactivo.length);
            })


        //Get por id
        $scope.buscarPorId=function(){

            $scope.usu=Usuario.get({id:1

            }, function(){
                console.log("Usuario obtenido:"+$scope.usu.nombre);
            });
        }


//UPDATE
        $scope.actualizarUsuario=function(){
            console.log("antes del evento update");
            var usuario = new Usuario();
            usuario.login = $scope.login;
            usuario.password = $scope.password;
            usuario.email =$scope.email;
            usuario.$actualizar(function (data) {
                console.log(data.nombre);
            });
        }

        //DELETE
        $scope.borrarUsuario=function(){
            var usuario=new Usuario();
            usuario.id=5;
            usuario.$borrar();
            console.log("si se borro");
        }

        //POST
        $scope.guardarUsuario=function() {

            console.log("antes del evento");
            var usuario = new Usuario();
            usuario.login = $scope.login;
            usuario.password = $scope.password;
            usuario.email =$scope.email;

            usuario.$crear(function (data) {
                console.log(data.login);
            });






        };// Termina boton guardarUsuario()
*/

    }).controller("guias",function($scope){

    }).controller('ctrlGuias',function($scope,$http){
        $scope.mensajeGuias='Bienvenido a las guias!!';
        console.log('holaaaaaaaaa');


    }).controller('profesores',function($scope,$http,$resource){
         console.log('EStas en el controlador de profesores');
        var Profesor=$resource('profesor/:id',{id:'@id'},{crear:{method:'POST'},
            actualizar:{method:'PUT'}, borrar:{method:'DELETE'}});

        //POST
        $scope.guardarProfesor=function() {

            console.log("antes del evento");
            var profesor = new Profesor();
            profesor.nombre = $scope.nombre;
            profesor.paterno = $scope.paterno;
            profesor.password =$scope.password;

            profesor.$crear(function (data) {
                console.log(data.nombre);
                $scope.nombre='';
                $scope.paterno='';
                $scope.password='';
            });

            //CARGAR IMAGEN DE REACTIVO







        }//termina boton guardar profesor


    });

