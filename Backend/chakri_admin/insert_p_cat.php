<?php

if (!isset($_SESSION['admin_email'])) {

    echo "<script>window.open('login.php','_self')</script>";
} else {


?>

    <div class="row">
        <!-- 1 row Starts -->

        <div class="col-lg-12">
            <!-- col-lg-12 Starts -->

            <ol class="breadcrumb">
                <!-- breadcrumb Starts -->

                <li>

                    <i class="fa fa-dashboard"></i> Dashboard / Insert Products Category

                </li>

            </ol><!-- breadcrumb Ends -->

        </div><!-- col-lg-12 Ends -->

    </div><!-- 1 row Ends -->

    <div class="row justify-content-center align-items-center h-100 ">
        <!-- 2 row Starts -->

        <div class="col-lg-8">
            <!-- col-lg-12 Starts -->

            <div class="card card-outline card-info">
                <!-- panel panel-default Starts -->

                <div class="card-header">
                    <!-- panel-heading Starts -->

                    <h3 class="card-title">
                        <!-- panel-title Starts -->

                       Insert Product Category

                    </h3><!-- panel-title Ends -->


                </div><!-- panel-heading Ends -->


                <div class="card-body">
                    <!-- panel-body Starts -->

                    <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">
                        <!-- form-horizontal Starts -->

                        <div class="form-group">
                            <!-- form-group Starts -->

                            <label class="col-md-6 control-label">Product Category Title</label>

                            <div class="col-md-12">

                                <input type="text" placeholder="Category Name" name="p_cat_title" class="form-control" required>

                            </div>

                        </div><!-- form-group Ends -->




                        <div class="form-group">
                            <!-- form-group Starts -->

                            <label class="col-md-3 control-label"></label>

                            <div class="col-md-2">

                                <input type="submit" name="submit" value="Add Category" class="btn btn-primary form-control">

                            </div>

                        </div><!-- form-group Ends -->

                    </form><!-- form-horizontal Ends -->

                </div><!-- panel-body Ends -->


            </div><!-- panel panel-default Ends -->

        </div><!-- col-lg-12 Ends -->

    </div><!-- 2 row Ends -->

    <?php

    if (isset($_POST['submit'])) {

        $p_cat_title = $_POST['p_cat_title'];

    

        $insert_p_cat = "insert into job_prep_sub_category(name , image ,parent_category_id ) values ('$p_cat_title' , 'NULL' , 5 )";

        $run_p_cat = mysqli_query($con, $insert_p_cat);

        if ($run_p_cat) {

            echo "<script>alert('New Category Has been Inserted')</script>";

            echo "<script>window.open('index.php?insert_p_cat','_self')</script>";
        }
    }



    ?>


<?php } ?>