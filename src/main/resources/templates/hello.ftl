<@common.page title='Hello'>
    <h1>Hello ${name}!</h1>
    <form action="<@spring.url '/' />">
        <input name="name" value="${name}"/>
        <input type="submit" value="Say hello"/>
    </form>
</@common.page>