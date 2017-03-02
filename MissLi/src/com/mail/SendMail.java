package com.mail;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


/**
 * Title: 使用javamail发送邮件 Description: 演示如何使用javamail包发送电子邮件。这个实例可发送多附件
 * 
 * @version 1.0
 */
public class SendMail {
String to = "";// 收件人
String from = "";// 发件人
String host = "";// smtp主机
String username = "";
String password = "";
String filename = "";// 附件文件名
String subject = "";// 邮件主题
String content = "";// 邮件正文
Vector file = new Vector();// 附件文件集合


/**
* <br>
* 方法说明：默认构造器 <br>
* 输入参数： <br>
* 返回类型：
*/
public SendMail() {
}


/**
* <br>
* 方法说明：构造器，提供直接的参数传入 <br>
* 输入参数： <br>
* 返回类型：
*/
public SendMail(String to, String from, String smtpServer, String username, String password, String subject,
String content) {
this.to = to;
this.from = from;
this.host = smtpServer;
this.username = username;
this.password = password;
this.subject = subject;
this.content = content;
}


/**
* <br>
* 方法说明：设置邮件服务器地址 <br>
* 输入参数：String host 邮件服务器地址名称 <br>
* 返回类型：
*/
public void setHost(String host) {
this.host = host;
}


/**
* <br>
* 方法说明：设置登录服务器校验密码 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setPassWord(String pwd) {
this.password = pwd;
}


/**
* <br>
* 方法说明：设置登录服务器校验用户 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setUserName(String usn) {
this.username = usn;
}


/**
* <br>
* 方法说明：设置邮件发送目的邮箱 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setTo(String to) {
this.to = to;
}


/**
* <br>
* 方法说明：设置邮件发送源邮箱 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setFrom(String from) {
this.from = from;
}


/**
* <br>
* 方法说明：设置邮件主题 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setSubject(String subject) {
this.subject = subject;
}


/**
* <br>
* 方法说明：设置邮件内容 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setContent(String content) {
this.content = content;
}


/**
* <br>
* 方法说明：把主题转换为中文 <br>
* 输入参数：String strText <br>
* 返回类型：
*/
public String transferChinese(String strText) {
try {
strText = MimeUtility.encodeText(new String(strText.getBytes(), "GB2312"), "GB2312", "B");
} catch (Exception e) {
e.printStackTrace();
}
return strText;
}


/**
* <br>
* 方法说明：往附件组合中添加附件 <br>
* 输入参数： <br>
* 返回类型：
*/
public void attachfile(String fname) {
file.addElement(fname);
}


/**
* <br>
* 方法说明：发送邮件 <br>
* 输入参数： <br>
* 返回类型：boolean 成功为true，反之为false
*/
public boolean sendMail() {
// 构造mail session
Properties props = System.getProperties();
props.put("mail.smtp.host", host);
props.put("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, new Authenticator() {
public PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// 构造MimeMessage 并设定基本的值
MimeMessage msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = { new InternetAddress(to) };
msg.setRecipients(Message.RecipientType.TO, address);
subject = transferChinese(subject);
msg.setSubject(subject);
// 构造Multipart
Multipart mp = new MimeMultipart();
// 向Multipart添加正文
MimeBodyPart mbpContent = new MimeBodyPart();
mbpContent.setText(content);
// 向MimeMessage添加（Multipart代表正文）
mp.addBodyPart(mbpContent);
// 向Multipart添加附件
Enumeration efile = file.elements();
while (efile.hasMoreElements()) {
MimeBodyPart mbpFile = new MimeBodyPart();
filename = efile.nextElement().toString();
FileDataSource fds = new FileDataSource(filename);
mbpFile.setDataHandler(new DataHandler(fds));
mbpFile.setFileName(fds.getName());
// 向MimeMessage添加（Multipart代表附件）
mp.addBodyPart(mbpFile);
}
file.removeAllElements();
// 向Multipart添加MimeMessage
msg.setContent(mp);
msg.setSentDate(new Date());
// 发送邮件
Transport.send(msg);
System.out.println("...");
} catch (MessagingException mex) {
mex.printStackTrace();
Exception ex = null;
if ((ex = mex.getNextException()) != null) {
ex.printStackTrace();


}
System.out.println("" + mex.getMessage());
return false;
}
return true;
}


/**
* <br>
* 方法说明：主方法，用于测试 <br>
* 输入参数： <br>
* 返回类型：
*/
public void send(String email,String subject,String content){

	SendMail sendmail = new SendMail();
	sendmail.setHost("smtp.163.com");// smtp.mail.yahoo.com.cn/smtp.163.com
	sendmail.setUserName("congeler@163.com");// 您的邮箱用户名
	sendmail.setPassWord("159753123Qwe");// 您的邮箱密码
	sendmail.setTo(email);// 接收者
	sendmail.setFrom("congeler@163.com");// 发送者
	sendmail.setSubject(subject );
	sendmail.setContent(content);
	String str="螳螂是昆虫中体型偏大的，体长一般55到105毫米，非洲的螳螂是世界最大的，身体流线型，以绿色，褐色为主，也具有花斑的种类；标志性特征是有两把“大刀”，即前肢，上有一排坚硬的锯齿，大刀钩末端有长有攀爬的吸盘。头部呈扇形，较小；复眼突出，大而透亮以黄绿色为主，晚上在灯光下呈现黑色，单眼，在两眼之间有3个小点即单眼；触角细长；颈部可180度转动；咀嚼式口器，上颚强劲。前足腿节和胫节有利刺，胫节镰刀状，常向腿节折叠，形成可以捕捉猎物的前足；前翅轻柔，遮住身体全部为覆翅，后翅比前翅要薄，边缘透明色，中间成放射状的紫红色、伸展开呈现扇状，休息时收敛和前翅相合；腹部肥大。前足锋利发达善于捕捉，中、后足适于步行，但有时前足也会用来保持平衡，发育呈变态发育。";
	System.out.println(email+subject+"-------"+content+str);
	// sendmail.attachfile("D:\\config.ini");
	sendmail.sendMail();
	
}



public static void main(String[] args) {
System.out.println("...");
SendMail mail=new SendMail();
mail.send("893266122@qq.com","验证ing","localhost://");

}
}