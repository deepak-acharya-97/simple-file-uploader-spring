<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Upload file here</h2>
  <form action="/upload" method="post" enctype="multipart/form-data" >
  <div>
  <select class="form-control" id="fileExtension" name="fileExtension">
      <option value="png">png</option>
      <option value="jpeg">jpeg</option>
      
    </select>
    <label for="image_uploads">Choose images to upload (PNG, JPG)</label>
    <input type="file" id="image_uploads" name="image_uploads" accept=".jpg, .jpeg, .png" >
  </div>
  <div>
    <button type=submit>Submit</button>
  </div>
</form>
</div>

</body>
</html>
