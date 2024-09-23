<#include "/parts/head.ftl" />
<#include "/parts/header.ftl" />
<div class="container">
    <h2 class="text-center">Logowanie</h2>
     <#if error?exists>
        <div class="alert alert-danger">
            ${error}
        </div>
    </#if>
    <form action="" method="POST">
        <div class="form-group">
            <label for="username">Nazwa użytkownika</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Hasło</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-secondary btn-block">Zaloguj się</button>
    </form>
    <div class="text-center mt-3">
        <a href="/auth/register" class="btn btn-secondary">Zarejestruj się</a>
    </div>
</div>
<#include "/parts/head.ftl" />