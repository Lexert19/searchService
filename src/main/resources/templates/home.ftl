<#include "parts/head.ftl">

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