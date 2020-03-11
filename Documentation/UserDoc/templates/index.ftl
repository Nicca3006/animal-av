<#include "header.ftl">
	
	<#include "menu.ftl">

	<div class="page-header">
		<h1>Willkommen bei ANIMAL</h1>
	</div>
	<#list pages as page>
  		<#if (page.title == "index")>
  			<p>${page.body}</p>
  		</#if>
  	</#list>

	<hr />

	<#list posts as post>
  		<#if (post.status == "published")>
  			<a href="${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
  			<p>${post.date?string("dd MMMM yyyy")}</p>
  			<p>${post.body}</p>
  		</#if>
  	</#list>
	
	<hr />
	
	<p>Weitere Versionen befinden sich im <a href="${content.rootpath}${config.archive_file}">Archiv</a>.</p>

<#include "footer.ftl">