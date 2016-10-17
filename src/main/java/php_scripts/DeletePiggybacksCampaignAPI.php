<?php
/*Initiating username and password variables, generating current timestamp and hash*/
$username = "name";
$password = "password";
$timestamp  = round(microtime(true) * 1000);
$hash = md5(md5($password).$timestamp);
$id = number;

/*Specifying API call URL*/
$url = "https://nXXX.epom.com/rest-api/piggyback/campaign/$id.do";

/*Specifying request parameters*/
$post_data = array(
    "hash" => $hash,
    "timestamp" => $timestamp,
    "username" => $username,
    "id" => $id
);

/*Specifying curl options*/
$options = array(
    CURLOPT_URL            => $url . '?' . http_build_query($post_data),
    CURLOPT_SSL_VERIFYPEER => FALSE,
    CURLOPT_CUSTOMREQUEST => 'DELETE',
    CURLOPT_POSTFIELDS     => http_build_query($post_data),
    CURLOPT_RETURNTRANSFER => TRUE
);

/*Connection initiation*/
$curl = curl_init();

/*Applying curl options to our curl instance*/
curl_setopt_array($curl, $options);

/*Executing the call*/
$result = curl_exec($curl);

?>