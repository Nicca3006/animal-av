	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>index.html">ANIMAL</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>index.html">Home</a></li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>installation.html">Installation <br> ANIMAL</a></li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>schnellstart.html">Schnellstart <br> ANIMAL</a></li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>vols.html">Listen bauen <br> mit VOLS</a></li>
            <li class="dropdown">
                          <a href="" class="dropdown-toggle" data-toggle="dropdown">Tutorials<b class="caret"></b></a>
                          <ul class="dropdown-menu">
                            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>tutorials.html">Überblick</a></li>
                            <li class="divider"></li>
                            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>userGuide.html">ANIMAL Tutorial</a></li>
                            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>animalScript.html">ANIMALScriptTutorial</a></li>
                           </ul>
                           </li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>about.html">Über uns</a></li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>datenschutz.html">Datenschutzerklärung</a></li>

            <!-- wieder einkommentieren falls erforderlich
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>sitemap.xml">Sitemap</a></li>
            <li><a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>${config.feed_file}">Downloads</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dokumentation<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>-->
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    <div class="container">