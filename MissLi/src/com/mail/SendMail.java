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
 * Title: ʹ��javamail�����ʼ� Description: ��ʾ���ʹ��javamail�����͵����ʼ������ʵ���ɷ��Ͷ฽��
 * 
 * @version 1.0
 */
public class SendMail {
String to = "";// �ռ���
String from = "";// ������
String host = "";// smtp����
String username = "";
String password = "";
String filename = "";// �����ļ���
String subject = "";// �ʼ�����
String content = "";// �ʼ�����
Vector file = new Vector();// �����ļ�����


/**
* <br>
* ����˵����Ĭ�Ϲ����� <br>
* ��������� <br>
* �������ͣ�
*/
public SendMail() {
}


/**
* <br>
* ����˵�������������ṩֱ�ӵĲ������� <br>
* ��������� <br>
* �������ͣ�
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
* ����˵���������ʼ���������ַ <br>
* ���������String host �ʼ���������ַ���� <br>
* �������ͣ�
*/
public void setHost(String host) {
this.host = host;
}


/**
* <br>
* ����˵�������õ�¼������У������ <br>
* ��������� <br>
* �������ͣ�
*/
public void setPassWord(String pwd) {
this.password = pwd;
}


/**
* <br>
* ����˵�������õ�¼������У���û� <br>
* ��������� <br>
* �������ͣ�
*/
public void setUserName(String usn) {
this.username = usn;
}


/**
* <br>
* ����˵���������ʼ�����Ŀ������ <br>
* ��������� <br>
* �������ͣ�
*/
public void setTo(String to) {
this.to = to;
}


/**
* <br>
* ����˵���������ʼ�����Դ���� <br>
* ��������� <br>
* �������ͣ�
*/
public void setFrom(String from) {
this.from = from;
}


/**
* <br>
* ����˵���������ʼ����� <br>
* ��������� <br>
* �������ͣ�
*/
public void setSubject(String subject) {
this.subject = subject;
}


/**
* <br>
* ����˵���������ʼ����� <br>
* ��������� <br>
* �������ͣ�
*/
public void setContent(String content) {
this.content = content;
}


/**
* <br>
* ����˵����������ת��Ϊ���� <br>
* ���������String strText <br>
* �������ͣ�
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
* ����˵�����������������Ӹ��� <br>
* ��������� <br>
* �������ͣ�
*/
public void attachfile(String fname) {
file.addElement(fname);
}


/**
* <br>
* ����˵���������ʼ� <br>
* ��������� <br>
* �������ͣ�boolean �ɹ�Ϊtrue����֮Ϊfalse
*/
public boolean sendMail() {
// ����mail session
Properties props = System.getProperties();
props.put("mail.smtp.host", host);
props.put("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, new Authenticator() {
public PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// ����MimeMessage ���趨������ֵ
MimeMessage msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = { new InternetAddress(to) };
msg.setRecipients(Message.RecipientType.TO, address);
subject = transferChinese(subject);
msg.setSubject(subject);
// ����Multipart
Multipart mp = new MimeMultipart();
// ��Multipart�������
MimeBodyPart mbpContent = new MimeBodyPart();
mbpContent.setText(content);
// ��MimeMessage��ӣ�Multipart�������ģ�
mp.addBodyPart(mbpContent);
// ��Multipart��Ӹ���
Enumeration efile = file.elements();
while (efile.hasMoreElements()) {
MimeBodyPart mbpFile = new MimeBodyPart();
filename = efile.nextElement().toString();
FileDataSource fds = new FileDataSource(filename);
mbpFile.setDataHandler(new DataHandler(fds));
mbpFile.setFileName(fds.getName());
// ��MimeMessage��ӣ�Multipart��������
mp.addBodyPart(mbpFile);
}
file.removeAllElements();
// ��Multipart���MimeMessage
msg.setContent(mp);
msg.setSentDate(new Date());
// �����ʼ�
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
* ����˵���������������ڲ��� <br>
* ��������� <br>
* �������ͣ�
*/
public static void main(String[] args) {
System.out.println("...");
SendMail sendmail = new SendMail();
sendmail.setHost("smtp.163.com");// smtp.mail.yahoo.com.cn/smtp.163.com
sendmail.setUserName("congeler@163.com");// ���������û���
sendmail.setPassWord("159753123Qwe");// ������������
sendmail.setTo("893266122@1qq.com");// ������
sendmail.setFrom("congeler@163.com");// ������
sendmail.setSubject("���123" );
sendmail.setContent("�Ұ���3333");
// sendmail.attachfile("D:\\config.ini");
sendmail.sendMail();
System.out.println("end");
}
}