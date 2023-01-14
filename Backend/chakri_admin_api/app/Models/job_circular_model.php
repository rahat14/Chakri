<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class job_circular_model extends Model
{
    //
    //
    protected $table = "job_circular_db";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'title',
        'description',
        'image',
        'image2',
        'image3',
        'pdf',
        'cat_id',
        'sub_cat_id',
        'keywords',
        'date',
        'rank'


    ];
}
