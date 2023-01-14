<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->group(['prefix' => 'api/v1'], function () use ($router) {
    // job prep fillters getJobPrepFillered
    $router->get('job-perp/fillter/{cat_id}/{sub_cat_id}', 'AllController@getJobPrepFillered');
    // job circulars fillter
    $router->get('job-circular/fillter/{cat_id}', 'AllController@getJobCirculerFillered');
    // best 2000 circular 
    $router->get('job-circular/best', 'AllController@getJobCirculerBestFillered');

    //job circular ranked 
    $router->get('job-circular/by-rank', 'AllController@getJobCirculerRanked');

    //  whole model qus 
    $router->get('model-qus/whole/{cat_id}', 'AllController@WholemodelQus');
    // subjective 
    $router->get('model-qus/subjective/{cat_id}/{sub_cat_id}', 'AllController@SubjectiveModelQus');
    // get single model qus 
    $router->get('model-qus/{id}', 'AllController@getModelQustionByid');
     // get single model qus list via his model qus id 
    $router->get('model-qus/qus-list/{id}', 'AllController@getModelQustionListByid');
    // all comment via post id 
    $router->get('comments/{pid}', 'AllController@loadACommentByPostID');
   // all comment via user id 
    $router->get('comments/user/{uid}', 'AllController@loadACommentByUserID');
    //all notificaiton 
    $router->get('notifications', 'AllController@loadAllNotificaiton');
    // check phon 
    $router->get('check/phone/{ph}', 'AllController@checkPhonne');
    $router->post('login', 'AllController@isUserLogin');
  
});


$router->group(['prefix' => 'api/v1/create'], function () use ($router) {

    //create comment 
    $router->post('comment', 'AllController@CreateComment');
    //Create User
    $router->post('user', 'AllController@CreateUser');
});




//php -S localhost:8000 -t public
//php -S 192.168.1.214:8000 -t public for andoroid