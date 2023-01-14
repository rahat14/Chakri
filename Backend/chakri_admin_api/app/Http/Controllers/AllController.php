<?php

namespace App\Http\Controllers;

use App\Models\Comment_model;
use App\Models\job_circular_model;
use App\Models\Job_prep_model;
use App\Models\Model_qus_model;
use App\Models\Notification_model;
use App\Models\Single_model_qus_Model;
use App\Models\User_model;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class AllController extends Controller
{
    //  job circular section 

    public function  getJobCirculerFillered($cat_id ){

       $allFillteredCirculars =  job_circular_model::query()
            ->where('cat_id', $cat_id ) 
            ->paginate(15);


        return response()->json($allFillteredCirculars, 200);
   
    }
    public function  getJobCirculerBestFillered()
    {
        //sleep(3);
        $allFillteredBEstCirculars =  job_circular_model::query()
        ->where('sub_cat_id', 1)
        ->paginate(20);


        return response()->json($allFillteredBEstCirculars, 200);
    }


    public function  getJobCirculerRanked()
    {
        //sleep(3);
        $allFillteredBEstCirculars =  job_circular_model::query()
            ->orderby('rank','DESC')
            ->paginate(20);


        return response()->json($allFillteredBEstCirculars, 200);
    }




    public function  getJobPrepFillered($cat_id, $sub_cat_id){

        $allFillteredPrep =  Job_prep_model::query()
        ->where('cat_id', $cat_id)
        ->where('sub_cat_id', $sub_cat_id)
        ->paginate(20);


        return response()->json($allFillteredPrep, 200);
    }

    // model qus 

    public function  WholemodelQus($cat_id)
    {

        $allFillteredWholeQus =  Model_qus_model::query()
        ->where('cat_id', $cat_id)
        ->where('type', 0)
        ->paginate(20);


        return response()->json($allFillteredWholeQus, 200);
    }


    public function  SubjectiveModelQus($cat_id, $sub_cat_id)
    {

        $allFillteredsubQus =  Model_qus_model::query()
            ->where('cat_id', $cat_id)
            ->where('sub_cat_id', $sub_cat_id)
            ->paginate(20);


        return response()->json($allFillteredsubQus, 200);
    }

    public function  getModelQustionByid($id)
    {

        $allQus =  Model_qus_model::query()
            ->where('id', $id)
            ->get();


        return response()->json($allQus, 200);
    }
    public function  getModelQustionListByid($id)
    {

        $allQusList =  Single_model_qus_Model::query()
            ->where('qustion_id', $id)
            ->get();


        return response()->json($allQusList, 200);
    }
    public function  loadACommentByPostID($pid)
    {

        $allCommentList =  Comment_model::query()
        ->where('post_id', $pid)
            ->orderBy('id', 'DESC') 
            ->paginate(15);


        return response()->json($allCommentList, 200);
    }
    public function  loadACommentByUserID($uid)
    {

        $allCommentList =  Comment_model::query()
            ->where('user_id', $uid)

            ->get();


        return response()->json($allCommentList, 200);
    }
    public function  loadAllNotificaiton()
    {

        $allNotification =  Notification_model::query()
            ->orderBy('id', 'DESC') 
            ->paginate(20);


        return response()->json($allNotification, 200);
    }

    // login methd 
    public function checkPhonne($ph)
    {

        $cout =  User_model::query()
        ->where('phone', $ph)
        ->count();

        if (number_format($cout) == 0
        ) {
            return response()->json([
                'msg' => 'OK',
                'error' =>  false
            ], 200);
        } else {

            return response()->json([
                'msg' => 'Same Phone Already Exist',
                'error' =>  true
            ], 200);
        }
    }

    //login method 
    public function isUserLogin(Request $request)
    {
        $email = $request->phone;
        $pass = $request->password;

        $cout =    User_model::query()
        ->where('phone', $email)
        ->where('password', $pass)
        ->count();




        if ($cout == "1") {

            return response()->json([
                'msg' => 'login successful',
                'error' => false,
                'details' => User_model::query()
                ->where('phone', $email)
                ->where('password', $pass)
                ->get()

            ], 200);
        } else {

            return response()->json([
                    'msg' => 'login error',
                    'error' => true,
                    'details' => []

                ], 200);
        }
    }

    // all posting method here 

    public function CreateComment(Request $request){
        $single_comment = new Comment_model();
        Log::info(print_r($request, true));
      
       
 
 ;        // mapping the input 
        $single_comment ->post_id = $request->input('post_type') ;
        $single_comment->post_type = $request->input('post_type');
        $single_comment->content = $request->input('content');
        $single_comment->user_id = $request->input('user_id');
        $single_comment->date = $request->input('date'); 
        
        $single_comment->save() ;

        return response()->json([
            'msg' => 'comment added',
            'error' =>  false
        ], 200); 
            

    }

    public function CreateUser(Request $request){
        $single_user = new User_model();


        $single_user->full_name = $request->full_name;
        $single_user->password = $request->password;
        $single_user->adress = $request->adress;
        $single_user->is_verified = $request->is_verified;
        $single_user->is_ban = $request->is_ban;
        $single_user->phone = $request->phone;
        $single_user->user_name = $request->user_name;
        $single_user->date = $request->date;

        $single_user->save();

        return response()->json([
            'msg' => 'User Added',
            'error' =>  false
        ], 200); 


    }
    
    



}
