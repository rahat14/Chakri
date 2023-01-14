<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Model_qus_model extends Model
{
    //
    //
    protected $table = "model_qus_list";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'title',
        'description',
        'cat_id',
        'sub_cat_id',
        'date',
        'type'
    ];
}
