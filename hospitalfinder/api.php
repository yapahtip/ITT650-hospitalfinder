<?php
//simple api for database insertion
// Citation

/***
  Ismail, M. H. (2021, July 11). Android: Building a Simple RESTful Client-Server Application. Coding Malaya. https://youtu.be/LjWyxm5HPK8
***/

require_once('db.php');

 if (!isset($_POST['name']) && !isset($_POST['email']) && !isset($_POST['address']) && !isset($_POST['coords']) ){
   die("Error incomplete HTTP request");

 }

 if (strlen($_POST['name']) < 3  || strlen($_POST['email'])<5 || strlen($_POST['address'])<1 || strlen($_POST['coords'])<1) {

   die("Error");

 }


//filter all inputs for securing
$POSTV = filter_input_array(INPUT_POST,
    ['name' => FILTER_SANITIZE_STRING,
     'email' => FILTER_SANITIZE_STRING,
     'address' => FILTER_SANITIZE_STRING,
     'coords' => FILTER_SANITIZE_STRING,
    ]
);
$name = $POSTV['name'];
$email = $POSTV['email'];
$address = $POSTV['address'];
$addr = $_SERVER['REMOTE_ADDR'];
$coords = $POSTV['coords'];
$agent = $_SERVER['HTTP_USER_AGENT'];

$query= "INSERT INTO user (id, name, email, address, ip_address, user_agent, coords)
VALUES (NULL,'$name','$email', '$address', '$addr','$agent', '$coords')";

$result=mysqli_query($link, $query);

if (!$result) {

  echo mysqli_error($link);

} else {

echo "User Check In Feedback Posted";
}
 ?>
