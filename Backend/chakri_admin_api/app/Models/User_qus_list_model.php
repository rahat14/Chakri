<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class User_qus_list_model extends Model
{
    //
    //
    protected $table = "user_qus_list";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'qus',
        'ans',
        'date'
    ];
}
