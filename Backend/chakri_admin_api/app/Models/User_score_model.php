<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class User_score_model extends Model
{
    //
    //
    protected $table = "user_score_db";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'user_id',
        'qus_id',
        'score',
        'date',
        'mark_sheet'
    ];
}
