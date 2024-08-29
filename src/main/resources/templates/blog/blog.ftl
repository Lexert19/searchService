<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SearchService</title>
    <link rel="stylesheet">
</head>

<body>
    <h1>Blog</h1>
    <#if posts?size==0>
        <p>No posts available.</p>
        <#else>
            <ul>
                <#list posts as post>
                    <li>
                        <h2>
                            ${post.title}
                        </h2> 
                        <p>
                            ${post.content}
                        </p> 
                    </li>
                </#list>
            </ul>
    </#if>
</body>

</html>