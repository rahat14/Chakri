<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Comment_model extends Model
{
    //
    //
    public $timestamps = false; 
    protected $table = "comment_db";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'post_id',
        'user_id',
        'content',
        'date',
        'post_type'
    ];
}
