<HTML>
<HEAD>
<TITLE>TTT</TITLE>
<meta charset="UTF-8">
<script language="javascript" src="CheckActivX.js"></script>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
</object> 
</HEAD>
<style id="sty1">
table {
    font-size: 8pt;
    font-weight: normal;
    color: #000000 ;
    text-decoration: none;
	
}
td {
    font-size: 8pt;
    font-weight: normal;
    color: #000000 ;
    text-decoration: none;
}
.printfont{
    
    font-size: 8pt;
    font-weight: normal;
    color: #000000 ;
    text-decoration: none;
	
    
}
</style>
<BODY>
<div id='div1'>
 <p><input type="button" value="打印预览" name="B3"  onclick="previewmytable()">
<table id="tb" style="line-height:14px;letter-spacing:0px">

      <tr><td  style="line-height:24px;font-size:22;font-weight: bold;font-family:仿宋;align="center">&nbsp; &nbsp;POS签购单 &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</td></tr>  
    <tr><td>持卡人存根&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;请妥善保管</td></tr>  
    <tr><td>--------------------------------------------------------------------------</td></tr>  
    <tr><td><span style="letter-spacing:3;">商户名称：#{shmc}</span></td></tr>
    <tr><td><span style="letter-spacing:1">商户编号：#{shbh}&nbsp; 操作员:&nbsp;#{czy}<span></td></tr>
    <tr><td>终端编号：#{zdbh}  清算日期：#{qsrq}</td></tr>
    <tr><td>卡  号：</td></tr> 
	<tr><td style="font-size:16;">#{kh}    B</td></tr>
	<tr><td>消费：</td></tr> 
	<tr><td><span style="letter-spacing:-0.1em">发卡行：#{fkh}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;收单行：#{sdh}</td></tr>
	<tr><td>有效期：#{yxq}</td></tr>
	<tr><td>批次号：#{pch}     &nbsp; &nbsp; &nbsp;凭证号：#{pzh}</td></tr>
	<tr><td>日期：#{rq}  &nbsp; &nbsp;时间：#{sk}</td></tr>
	<tr><td>授权号：#{sqh}     &nbsp; &nbsp; &nbsp;参考号：#{ckh}</td></tr>
	<tr><td>金额：<span style="font-size:16"> RMB #{amount}</span></tr>
	<tr><td>备注：#{bak}</td></tr>
	<tr><td>ARQC:#{arqc}</td></tr>
	<tr><td>AID:#{aid}</td></tr>
	<tr><td>TUR:#{tur} TSI:#{tsi}   ATC:#{atc}</td></tr>
	<tr><td>应用标签：#{yybq} 首选名称：#{ssmc}</td></tr>
	<tr><td>-------------------------------------------------------------</td></tr> 
	<tr><td>      本人确认以上交易同意记入本卡账户</td></tr> 
	<tr><td>BOOO-MPI4-015  5.4.1         #{finalDate}</td></tr>
	<tr><td>-------------------------------------------------------------</td></tr> 
	<tr><td>       想要您的签购单上发布消息吗？</td></tr> 
	

     
</table>  
</div>

<script  type="text/javascript">
        function previewmytable() {
           LODOP.PRINT_INIT("打印插件功能演示_Lodop功能_自定义纸张");
		LODOP.SET_PRINT_PAGESIZE(1,700,1180,"");
		 //LODOP.SET_PRINT_STYLE("FontName","文鼎报宋简"); 
		 //LODOP.SET_PRINT_STYLE("FontSize",16);
		// LODOP.ADD_PRINT_TEXT(5,180,431,20,"            中国建议银行        ");
		//LODOP.SET_PRINT_STYLEA(0,"ItemLetterSpacing", "-5")
		//LODOP.SET_PRINT_STYLEA(0,"LetterSpacing", "-5")
		    var strbodystyle = " <html xmlns='http://www.w3.org/1999/xhtml' lang='zh-CN'><head> <style>" + document.getElementById("sty1").innerHTML + "</style>";
        
		  var strformhtml = strbodystyle+"<body><table style='line-height:12px;letter-spacing:0px'>" + document.getElementById("tb").innerHTML+ "</table></body></html>";
		//alert( strformhtml);
           //报表标题 
		       
	            LODOP.ADD_PRINT_HTM(5, -5,1250, 1100,strformhtml); 
           

	           LODOP.PREVIEW(); 
        };
       
</script>
</BODY>
</HTML>
