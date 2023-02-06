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
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">course Details</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form method="post" enctype="multipart/form-data">

                <div class="card-body">

                  <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <input type="text" class="form-control" name = "course_name" id="exampleInputEmail1" placeholder="Enter your name" required >
                  </div>


                  <div class="form-group">
                            <!-- form-group Starts -->

                            <label class="col-md-3 control-label">Course Description : </label>

                            <div class="col-md-12">



                                <textarea name="course_details" class="form-control" rows="8" cols="8" placeholder="Write The Description"></textarea>

                            </div>

                        </div>
                        <!-- form-group Ends -->



                
                  
                  <div class="form-group">
              <!-- form-group Starts -->

              <label for="exampleInputFile"> Course Video 1 (<50mb) </label>

              <div class="input-group">
                <div class="custom-file">

                  <input type="file" name="course_video_1" class="custom-file-input" id="exampleInputFile">
                  <label class="custom-file-label" for="exampleInputFile">Choose Video</label>

                </div>


              </div>

             </div>


                <div class="form-group">
              <!-- form-group Starts -->

              <label for="exampleInputFile"> Course Video 2 (<50mb) </label>

              <div class="input-group">
                <div class="custom-file">

                  <input type="file" name="course_video_2" class="custom-file-input" id="exampleInputFile">
                  <label class="custom-file-label" for="exampleInputFile">Choose Video</label>

                </div>


              </div>
                </div><!-- form-group Ends -->




                <div class="form-group">
              <!-- form-group Starts -->

              <label for="exampleInputFile"> Course Cover Image : </label>

              <div class="input-group">
                <div class="custom-file">

                  <input type="file" name="cover_image" class="custom-file-input" id="exampleInputFile">
                  <label class="custom-file-label" for="exampleInputFile">Choose image</label>

                </div>
              </div>
                </div><!-- form-group Ends -->


                <div class="form-group">
              <!-- form-group Starts -->

              <label for="exampleInputFile1"> Course Thumbnail Image : </label>

              <div class="input-group">
                <div class="custom-file">

                  <input type="file" name="thumbnail_image" class="custom-file-input" id="exampleInputFile">
                  <label class="custom-file-label" for="exampleInputFile">Choose image</label>

                </div>
              </div>
                </div><!-- form-group Ends -->



                <div class="form-group">
                    <label for="exampleInputEmail1">Course Price (BDT)</label>
                    <input type="number" class="form-control" name = "course_price"  placeholder="Course Price">
                  </div>



                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-success">Submit</button>
                </div>
              </form>
            </div>
            <!-- /.card -->


            <?php

    if (isset($_POST['submit'])) {

        $course_title = $_POST['course_name'];

        $course_desc = $_POST['course_details'];

        $course_price = $_POST['course_price'];




        if ($_FILES['course_video_1']['size'] == 0 && $_FILES['course_video_1']['error'] == 4) {


            //empty 



        }else {


          $course_video_link1 = $_FILES['course_video_1']['name'];

          $tmp_image = $_FILES['course_video_1']['tmp_name'];
  
          move_uploaded_file($tmp_image, "all_images/$course_cover_image");
        
        
        }

  
  
        if ($_FILES['course_video_2']['size'] == 0 && $_FILES['course_video_2']['error'] == 4) {


            //empty 

            

        }else {

          $course_video_link2 = $_FILES['course_video_2']['name'];

          $tmp_image = $_FILES['course_video_2']['tmp_name'];

         move_uploaded_file($tmp_image, "all_images/$course_cover_image");

        }


        

        if ($_FILES['cover_image']['size'] == 0 && $_FILES['cover_image']['error'] == 4) {


          //empty 

          

      }else {

        $course_cover_image = $_FILES['cover_image']['name'];

        $tmp_image = $_FILES['cover_image']['tmp_name'];

        move_uploaded_file($tmp_image, "all_images/$course_cover_image");



      }



      if ($_FILES['thumbnail_image']['size'] == 0 && $_FILES['thumbnail_image']['error'] == 4) {


        //empty 

        

    }else {


      
      $course_thumbnail_image = $_FILES['thumbnail_image']['name'];

      $tmp_image = $_FILES['thumbnail_image']['tmp_name'];

      move_uploaded_file($tmp_image, "all_images/$course_thumbnail_image");

    }
        
       

        $insert_store = "insert into store (store_title,store_image,store_desc,merchant_id) values ('$store_title','$store_image','$store_desc' , '$store_merchant_id')";

        $run_store = mysqli_query($con, $insert_store);

        if ($run_store) {

            echo "<script>alert('New store  Has Been Inserted')</script>";

            echo "<script>window.open('index.php?view_store','_self')</script>";
        }
    }

    ?>





            <?php }?>