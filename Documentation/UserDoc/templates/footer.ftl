		</div>
		<div id="push"></div>
    </div>

    <!-- ersetzt
    <div id="footer">
      <div class="container">
        <p class="muted credit">&copy; 2014 | Mixed with <a href="http://getbootstrap.com/">Bootstrap v3.1.1</a> | Baked with <a href="http://jbake.org">JBake ${version}</a></p>
      </div>
    </div> -->
    <div id="footer">
        <div class="container">
          <div>   &copy; Dr. Guido Rößling 1998-today | <a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>datenschutz.html">Datenschutzerklärung</a></p></div>
          </div>
         </div>
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<#if (content.rootpath)??>${content.rootpath}<#else></#if>js/jquery-1.11.1.min.js"></script>
    <script src="<#if (content.rootpath)??>${content.rootpath}<#else></#if>js/bootstrap.min.js"></script>
    <script src="<#if (content.rootpath)??>${content.rootpath}<#else></#if>js/prettify.js"></script>
    
  </body>
</html>