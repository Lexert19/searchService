<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SearchService</title>
    <link rel="stylesheet">
    <script src="https://www.google.com/recaptcha/enterprise.js?render=${recaptchaKey}"></script>
</head>

<body>
    <h1>
        <@translate f="main_header" fn="home" />
    </h1>
    <p></p>
    <form>
        <button class="g-recaptcha"
            data-sitekey="${recaptchaKey}"
            data-callback='onSubmit'
            data-action='submit'>Submit</button>
    </form>
</body>

</html>