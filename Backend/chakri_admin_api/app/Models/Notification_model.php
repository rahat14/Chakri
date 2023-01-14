<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Notification_model extends Model
{
    //
    //
    protected $table = "notification_db";
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'title',
        'description',
        'date'
    ];
}
