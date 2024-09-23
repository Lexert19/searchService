<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">SearchService</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/">Strona główna</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/">O nas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/contact">Kontakt</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/blog">Blog</a> 
                    </li>
                    <#if isLoggedIn>
                        <li class="nav-item">
                            <a class="nav-link" href="/panel">Panel</a> 
                        </li>
                        <#if userRole  == "user_admin">
                            <li class="nav-item">
                                <a class="nav-link" href="/admin">Panel Admina</a> 
                            </li>
                        </#if>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">Logowanie</a>
                        </li>
                    </#if>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Wyszukaj" aria-label="Wyszukaj">
                    <button class="btn btn-outline-light" type="submit">Wyszukaj</button>
                </form>
            </div>
        </div>
    </nav>
</header>
