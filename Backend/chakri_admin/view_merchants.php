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
                <!-- breadcrumb Starts  --->

                <li class="active">

                    </i> Dashboard / View Merchants

                </li>

            </ol><!-- breadcrumb Ends  --->

        </div><!-- col-lg-12 Ends -->

    </div><!-- 1 row Ends -->



    <section class="content">
        <div class="container-fluid">

            <div class="row">
                <!-- 2 row Starts -->

                <div class="col-12">
                    <!-- col-lg-12 Starts -->

                    <div class="card">
                        <!-- panel panel-default Starts -->

                        <div class="card-header">
                            <!-- panel-heading Starts -->

                            <h1 class="card-title">
                                <!-- panel-title Starts -->

                                <b>&nbsp&nbspView Merchants</b>

                            </h1><!-- panel-title Ends -->

                        </div><!-- panel-heading Ends -->



                        <div class="card-body">
                            <!-- panel-body Starts -->

                            <div class="table-responsive">
                                <!-- table-responsive Starts -->

                                <table id="example2" class="table table-bordered table-hover">
                                    <!-- table table-bordered table-hover table-striped Starts -->

                                    <thead>
                                        <!-- thead Starts -->

                                        <tr>

                                            <th>id:</th>
                                            <th>Marchants Name:</th>
                                            <!-- <th>Invoice No:</th> -->
                                            <!-- <th>Product Title:</th> -->
                                            <!-- <th>Product Qty:</th> -->
                                            <!-- <th>Product Size:</th> -->
                                            <th>Marchants Email:</th>
                                            <th>Phone:</th>
                                            <!-- <th>Order Status:</th> -->
                                            <th>View Details:</th>
                                            <!-- <th>Delete Order:</th> -->


                                        </tr>

                                    </thead><!-- thead Ends -->


                                    <tbody>
                                        <!-- tbody Starts -->

                                        <?php

                                        $i = 0;

                                        $get_orders = "select * from marchants order by id DESC ";

                                        $run_orders = mysqli_query($con, $get_orders);

                                        while ($row_orders = mysqli_fetch_array($run_orders)) {

                                            $order_id = $row_orders['id'];

                                            // $c_id = $row_orders['customer_id'];

                                            // $invoice_no = $row_orders['invoice_no'];

                                            //  $product_id = $row_orders['product_id'];

                                            // $qty = $row_orders['qty'];

                                            // $size = $row_orders['size'];
                                            $name   = $row_orders['name'];
                                            $email  =  $row_orders['email'];
                                            $phone = $row_orders['phone'];

                                            // $order_status = $row_orders['order_status'];
                                            // $order_comment = $row_orders['order_comment'];
                                            // $payment_type = $row_orders['payment_type'];
                                            // $delivery_zone = $row_orders['delivery_zone'];
                                            // $delivery_address = $row_orders['delivery_address'];
                                            // $trans_id = $row_orders['trans_id'];

                                            //   $get_products = "select * from products where product_id='$product_id'";

                                            //  $run_products = mysqli_query($con, $get_products);

                                            //    $row_products = mysqli_fetch_array($run_products);

                                            // $product_title = $row_products['product_title'];

                                            $i++;

                                        ?>

                                            <tr>

                                                <td><?php echo $order_id; ?></td>

                                                <td>
                                                    <?php echo $name; ?>
                                                </td>

                                                <td>
                                                    <?php


                                                    echo $email;

                                                    ?>
                                                </td>

                                                <td><?php echo $phone; ?></td>

                                                <!-- <td><?php echo $order_status; ?> </td> -->

                                                <td>

                                                    <button class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#myModal-<?php echo $order_id; ?>">

                                                        View Details

                                                    </button>
                                                   
                                                </td>

                                                <!-- <td>

                                                    <button class="btn btn-block btn-outline-danger" onclick="window.location.href='index.php?order_delete=<?php echo $order_id; ?>'">

                                                        <i class="fa fa-trash-o"></i> Delete

                                                    </button>

                                                </td> -->



                                            </tr>

                                        <?php } ?>

                                    </tbody><!-- tbody Ends -->

                                </table><!-- table table-bordered table-hover table-striped Ends -->

                            </div><!-- table-responsive Ends -->

                        </div><!-- panel-body Ends -->

                    </div><!-- panel panel-default Ends -->

                </div><!-- col-lg-12 Ends -->

            </div><!-- 2 row Ends -->



        </div>

    </section>







<?php } ?>