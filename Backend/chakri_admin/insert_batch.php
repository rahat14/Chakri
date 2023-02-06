<?php


if (!isset($_SESSION['admin_email'])) {

    echo "<script>window.open('login.php','_self')</script>";
} else {
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<!-- Main content -->
<section class="content">
      <div class="container-fluid">
        <div class="row d-flex justify-content-center">
          <!-- left column -->
          <div class="col-md-6">
            <!-- general form elements -->
            <div class="card card-danger">
              <div class="card-header">
                <h3 class="card-title">Batch</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form>

                <div class="card-body">

                <div class="form-group">
                    <label for="exampleInputEmail1">Id</label>
                    <input type="number" name="student_id" class="form-control" id="" placeholder="Enter your id">
                </div>

                  <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <input type="text" name="course_name" class="form-control" id="" placeholder="Enter your name">
                  </div>
                  <div class="form-group">
                    <label for="phoneNumber">Code</label>
                    <input type="number" name="code" class="form-control" id="" placeholder="Enter your code number">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Course Id</label>
                    <input type="number" name="course_id" class="form-control" id="" placeholder="Enter your id">
                  </div>

                  <div class="form-group">
                    <label for="">Start Date</label>
                    <input type="date" name="course_start_Date" class="form-control" id="" placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="">End Date</label>
                    <input type="date" name="course_End_Date" class="form-control" id="" placeholder="">
                  </div>
                  <div class="form-group">
                    <label for="">Max Quota</label>
                    <input type="number" name="max_quota"  class="form-control" id="" placeholder="">
                  </div>
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-danger">Submit</button>
                </div>
              </form>
            </div>
            <!-- /.card -->


    

 <?php
if (isset($_POST['submit'])) {

  $student_id = $_POST['student_id'];

  $course_name = $_POST['course_name'];

  $code = $_POST['code'];

  $course_id = $_POST['course_id'];

  $course_start_Date = $_POST['course_start_Date'];

  $course_End_Date = $_POST['course_End_Date'];


}
?>


