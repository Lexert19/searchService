<#include "/parts/head.ftl" />
<#include "/parts/header.ftl" />
<div class="container">
    <div class="row">
        <h1>
            <@translate f="main_header" fn="home" />
        </h1>
        <section class="mb-5">
            <h2 class="text-center">O Nas</h2>
            <p class="text-center">Jesteśmy firmą, która dostarcza najlepsze usługi w branży. Naszym celem jest zadowolenie klienta.</p>
        </section>
        <section class="mb-5">
            <h2 class="text-center">Nasze Usługi</h2>
            <div class="row">
                <div class="col-md-4 text-center">
                    <div class="service-box">
                        <h3>Usługa 1</h3>
                        <p>Opis usługi 1.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-box">
                        <h3>Usługa 2</h3>
                        <p>Opis usługi 2.</p>
                    </div>
                </div>
                <div class="col-md-4 text-center">
                    <div class="service-box">
                        <h3>Usługa 3</h3>
                        <p>Opis usługi 3.</p>
                    </div>
                </div>
            </div>
        </section>
        <section class="mb-5">
            <h2 class="text-center">Kontakt</h2>
            <form>
                <div class="form-group">
                    <label for="name">Imię i Nazwisko</label>
                    <input type="text" class="form-control" id="name" placeholder="Wpisz swoje imię i nazwisko">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Wpisz swój email">
                </div>
                <div class="form-group">
                    <label for="message">Wiadomość</label>
                    <textarea class="form-control" id="message" rows="3" placeholder="Wpisz swoją wiadomość"></textarea>
                </div>
                <button type="submit" class="btn btn-dark">Wyślij</button>
            </form>
        </section>
    </div>
</div>
<#include "/parts/head.ftl" />