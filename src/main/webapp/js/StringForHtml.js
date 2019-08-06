/**
 * Created by admin on 2016/9/9.
 */
$(document).ready(function(){
    $("table tbody tr td").each(function(){
        var txt = $(this).context.innerHTML;
        //console.info($(this).context.innerHTML);
        var regx=new RegExp("&lt;br&gt;","g");
        if(txt!="" && txt!=undefined){
            txt = txt.replace(regx,"<br/>");
            $(this).context.innerHTML = txt;
        }
    })
})