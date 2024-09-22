<#include "/parts/head.ftl" />
<#include "/parts/header.ftl" />
<div class="container">
    <h1 class="text-center">Search Results</h1>
    <div class="row">
        <#if results?has_content>
            <#list results as result>
                <div class="col-md-4">
                    <div class="card result-card">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${result.title}
                            </h5>
                            <p class="card-text">
                                ${result.content}
                            </p>
                            <a href="" class="btn btn-primary">View More</a>
                        </div>
                    </div>
                </div>
            </#list>
            <#else>
                <div class="col-12">
                    <div class="alert alert-warning" role="alert">
                        No results found for your search.
                    </div>
                </div>
        </#if>
    </div>
</div>
<#include "/parts/head.ftl" />