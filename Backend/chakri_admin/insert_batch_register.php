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
          <div class="col-md-4">
            <!-- general form elements -->
            <div class="card card-dark">
              <div class="card-header">
                <h3 class="card-title">Batch Register Details</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form>

                <div class="card-body">

                <div class="form-group">
                    <label for="exampleInputEmail1">Id</label>
                    <input type="number" name="id" class="form-control" id="id" placeholder="Enter your id">
                  </div>
                  <div class="form-group">
                    <label for="">Student Id</label>
                    <input type="number" name="student_id" class="form-control" id="Student Id" placeholder="Enter your student id">
                  </div>
                  <div class="form-group">
                    <label for="">Batch Id</label>
                    <input type="number" name="batch_id" class="form-control" id="Batch Id" placeholder="Enter your Batch id">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Is paid</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            Yes
                        </label>
                        </div>
                        <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                        <label class="form-check-label" for="flexRadioDefault2">
                            No
                        </label>
                        </div>
                        </div>
                        <div class="form-group">
                          <label for="">Course Id</label>
                          <input type="number" name="course_id" class="form-control" id="Course id" placeholder="Enter your Course id">
                        </div>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Payment Date</label>
                          <input type="Date" name="payment_date" class="form-control" id="payment Date" placeholder="Enter your payment Date ">
                        </div>
                        <div class="form-group">
                          <label for="exampleInputEmail1">Amount</label>
                          <input type="number" name="amount" class="form-control" id="exampleInputEmail1" placeholder=" ">
                        </div>
                       
                        <div class="form-group">
                            <label class="col-md-3 control-label">Comment:</label>
                            <div class="col-md-12">
                                <textarea name="comment" class="form-control" rows="8" cols="8" placeholder="Write The Description"></textarea>
                            </div>

                        </div>


                        <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                          <label class="form-check-label"   for="exampleCheck1">Check me out</label>
                        </div>
                      </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-dark">Submit</button>
                </div>
              </form>
            </div>
            <!-- /.card -->



<?php 
if (isset($_POST['submit'])) {

  $id = $_POST['id'];

  $student_id = $_POST['student_id'];

  $batch_id = $_POST['batch_id'];

  $code = $_POST['code'];

  $course_id = $_POST['course_id'];

  $payment_date = $_POST['payment_date'];

  $amount = $_POST['amount'];

  $comment = $_POST['comment'];




} ?>