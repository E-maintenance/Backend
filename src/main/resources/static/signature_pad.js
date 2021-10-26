<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://intridea.github.io/sketch.js/lib/sketch.min.js" type="text/javascript"></script>
<script type="text/javascript">
   $(function () {
       $('#colors_sketch').sketch();
       $(".tools a").eq(0).attr("style", "color:#000");
       $(".tools a").click(function () {
           $(".tools a").removeAttr("style");
           $(this).attr("style", "color:#000");
       });
   });
</script>