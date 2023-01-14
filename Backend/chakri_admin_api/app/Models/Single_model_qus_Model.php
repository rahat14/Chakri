<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Single_model_qus_Model extends Model
{
    //
    //
    protected $table = "model_question_db";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'qustion_id',
        'title',
        'option_one',
        'option_three',
        'option_four',
        'right_ans',
        'option_two',
        'option_five'
    ];
}
