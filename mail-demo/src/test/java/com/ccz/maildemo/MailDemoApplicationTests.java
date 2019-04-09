package com.ccz.maildemo;

import com.ccz.maildemo.serivce.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailDemoApplicationTests {
	@Autowired
	private MailService mailService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMailHtml(){
		String html= "<!DOCTYPE html>\r\n" +
				"<html>\r\n" +
				"<head>\r\n" +
				"<meta charset=\"UTF-8\">\r\n" +
				"<title>Insert title here</title>\r\n" +
				"</head>\r\n" +
				"<body>\r\n" +
				"	<font color=\"red\">发送html</font>\r\n" +
				"</body>\r\n" +
				"</html>";
		mailService.sendEmailHTML("414172041@qq.com","静态html",html);

	}
	@Test
	public void testMailAttachment(){
		String html= "<!DOCTYPE html>\r\n" +
				"<html>\r\n" +
				"<head>\r\n" +
				"<meta charset=\"UTF-8\">\r\n" +
				"<title>Insert title here</title>\r\n" +
				"</head>\r\n" +
				"<body>\r\n" +
				"	<font color=\"red\">发送html</font>\r\n" +
				"</body>\r\n" +
				"</html>";
		mailService.sendAttachmentEmail("414172041@qq.com","静态html",html,"C:\\Users\\A\\Desktop\\test2.pdf");
	}

}
