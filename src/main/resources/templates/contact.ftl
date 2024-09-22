<#include "parts/head.ftl" />
<#include "/parts/header.ftl" />
<div class="container">
    <#-- <form action="" method="POST">
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
        </form> -->
        <section class="mb-5">
            <h2 class="text-center">Kontakt</h2>
            <form action="your-server-endpoint" method="POST" id="contactForm">
                <div class="form-group">
                    <label for="firstName">Imię</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Wpisz swoje imię" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Nazwisko</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Wpisz swoje nazwisko" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Wpisz swój email" required>
                </div>
                <div class="form-group">
                    <label for="content">Wiadomość</label>
                    <textarea class="form-control" id="content" name="content" rows="3" placeholder="Wpisz swoją wiadomość" required></textarea>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="termsAndConditions" name="termsAndConditions" required>
                    <label class="form-check-label" for="termsAndConditions">Akceptuję warunki</label>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="privacyPolicy" name="privacyPolicy" required>
                    <label class="form-check-label" for="privacyPolicy">Akceptuję politykę prywatności</label>
                </div>
                <button class="g-recaptcha btn btn-dark"
                    data-sitekey="${recaptchaKey}"
                    data-callback='onSubmit'
                    data-action='submit' type="submit">Nie jestem robotem</button>
                <button class="btn btn-dark" type="submit">Wyślij</button>
            </form>
        </section>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</div>
<#include "/parts/head.ftl" />