<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class User_model extends Model
{
    //
    //
    
    protected $table = "user_db";
    protected $primaryKey = 'id';
    public $timestamps = false; 
    protected $fillable = [
        'id',
        'full_name',
        'password',
        'adress',
        'is_verified',
        'is_ban',
        'phone',
        'user_name',
        'date',
       

    ];
}
