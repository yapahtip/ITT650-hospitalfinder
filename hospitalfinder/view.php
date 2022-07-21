<?php
//simple script to view comments
// Citation

/***
  Ismail, M. H. (2021, July 11). Android: Building a Simple RESTful Client-Server Application. Coding Malaya. https://youtu.be/LjWyxm5HPK8
***/

require_once('db.php');

//select all row from table checkin
$query = "SELECT * FROM user ORDER BY date DESC";
$result=mysqli_query($link,$query);
?>
<!doctype html>
<html>
<head>
  <title>Hospital Finder</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<!--<style>
body {
  background-color: #33FFAA;
}
</style>-->
<body>

<h1><center>Hospital Finder User Information</center></h1>
<ol>
<?php foreach ($result as $row) {

?>
<li>
    <ul>
        <li><small>Id: <?=$row['id']?></small></li>
        <li><small>Name: <?=$row['name']?></small></li>
        <li><small>Email: <?=$row['email']?></small></li>
        <li><small>Date: <?=$row['date']?></small></li>
        <li><small>User Agent: <?=$row['user_agent']?></small></li>
        <li><small>Address: <?=$row['address']?></small></li>
        <li><small>Coordinates: <?=$row['coords']?></small></li>
        <!--<li><a href="mailto:<?=$row['email']?>"">
        <?=$row['name']?></a> gives feedback.. <br />-->

</ul><br />
</li>
<?php } ?>
</ol>
</body>
</html>
