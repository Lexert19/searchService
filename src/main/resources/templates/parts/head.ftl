<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SearchService</title>
    <script src="https://www.google.com/recaptcha/enterprise.js?render=${recaptchaKey}"></script>
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <script>
    function onSubmit(token) {
        document.getElementById("demo-form").submit();
    }
    </script>
</head>