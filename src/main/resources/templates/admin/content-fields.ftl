<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SearchService</title>
    <link rel="stylesheet">
</head>
<body>
    <h1>content fields</h1>
    <table>
    <thead>
        <tr>
            <th>Key</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody>
        <#list fields?keys as key>
            <tr>
                <td>${key}</td>
                <td>${fields[key]}</td>
            </tr>
        </#list>
    </tbody>
</table>
</body>
</html>