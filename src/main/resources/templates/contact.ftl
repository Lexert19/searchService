<#include "parts/head.ftl">


<body>
    <h1>Contact</h1>
    <p></p>
    <form action="" method="POST">
        <input type="mail" name="email" placeholder="email" required>
        <input type="text" name="firstName" placeholder="firstname" required>
        <input type="text" name="lastName" placeholder="lastName" required>
        <input type="text" name="content" placeholder="content" required>
        <input type="checkbox" name="termsAndConditions" placeholder="firstname" required>
        <input type="checkbox" name="privacyPolicy" required>
        <button class="g-recaptcha"
            data-sitekey="${recaptchaKey}"
            data-callback='onSubmit'
            data-action='submit' type="submit">Submit</button>
            <button type="submit">wyslij</button>
    </form>
</body>

</html>