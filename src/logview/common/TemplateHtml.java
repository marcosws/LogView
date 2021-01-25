package logview.common;

public class TemplateHtml {
	
	public static final String TEMPLATE_HTML = 
			"<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"	<head>\r\n" + 
			"		<meta charset=\"UTF-8\">\r\n" + 
			"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
			"		<title>LogView - Report</title>\r\n" + 
			"		<style>\r\n" + 
			"			html{font-family: Arial;}\r\n" + 
			"			.title{color:blue;}\r\n" + 
			"			.fail{color:red;}\r\n" + 
			"			.pass{color:green;}\r\n" + 
			"			.info{color:black;}\r\n" + 
			"			#content{margin-left: 20px;}\r\n" + 
			"		</style>\r\n" + 
			"	</head>\r\n" + 
			"	<body>\r\n" + 
			"		<div id=\"content\">\r\n" + 
			"			<h1>LogView - Report</h1>\r\n" + 
			"			{LINE}" + 
			"		</div>\r\n" + 
			"	</body>\r\n" + 
			"</html>";
	
	public static final String FAILED_LOG = "<p class=\"fail\">[FAILED] - {LOG}</p>";
	public static final String PASSED_LOG = "<p class=\"pass\">[PASSED] - {LOG}</p>";
	public static final String TITLE_LOG = "<p class=\"title\">[TITLE] - {LOG}</p>";
	public static final String INFO_LOG = "<p class=\"info\">[INFORMATION] - {LOG}</p>";

}
