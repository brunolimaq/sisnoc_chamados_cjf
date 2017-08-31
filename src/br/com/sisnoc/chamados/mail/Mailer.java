package br.com.sisnoc.chamados.mail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;
import br.com.sisnoc.chamados.modelo.Relatorios;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Component
public class Mailer {

		
		@Autowired
		private JavaMailSender mailSender;
		
		@Autowired
		private PainelPessoalEquipeDao equipeDao;
		
		@Autowired private ApplicationContext appContext;


		
		@Async
		public void enviar() {
			SimpleMailMessage mensagem = new SimpleMailMessage();
			mensagem.setFrom("bruno.queiroz@cjf.jus.br");
			mensagem.setTo("jayro.roeder@cjf.jus.br");
			mensagem.setSubject("Teste Sisnoc");
			mensagem.setText("Obrigado!!!");
			
		}	
		
		@Async
		public void enviarHTML(String titulo, String  relatorio, String OutputFilePDF) throws MessagingException, IOException, JRException {
			  
			 
			  
			  Properties props = new Properties();
			  props.put("mail.host","correio.cjf.local"); 
			  Session session = Session.getDefaultInstance(props);
			  MimeMessage message = new MimeMessage(session);
			  Address from = new InternetAddress("svc_sisnoc@cjf.jus.br");
			  Address to = new InternetAddress("sesser@cjf.jus.br");
			  Address to2 = new InternetAddress("sesinf@cjf.jus.br");
			  Address to3 = new InternetAddress("infra.algar@cjf.jus.br");
			  message.setFrom(from);
			  message.addRecipient(RecipientType.TO, to);
			  message.addRecipient(RecipientType.TO, to2);
			  message.addRecipient(RecipientType.TO, to3);
			  message.setSentDate(new Date());
			  message.setSubject(titulo);
			  
			  String htmlMessage = "<html>"
			  		+ "<head>"
			  		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
			  		+ "<link rel=\"stylesheet\" href=\"resources/css/bootstrap.min.css\">"
			  		+ "<title>Relatório</title>"
			  		+ "</head>"
			  		+ "<body>"
			  		+ "<blockquote class=\"blockquote text-left\">"
			  		+ "<p class=\"mb-0\">Relatório de chamados abertos a mais de 7 dias sem solução. Favor se atentar a data de <bold>última atualização.</bold></p>"
			  		+ "<footer class=\"blockquote-footer\">SISNOC - <cite title=\"Source Title\">Vai além</cite></footer>"
			  		+ "</blockquote>"
			  		+ "<figure class=\"figure\">"
			  		+ "<img src=\"http://sisnoc/chamados/resources/images/logo_algar.png\" class=\"figure-img img-fluid rounded\" alt=\"Algar Tech - Gente servindo Gente.\">"
			  		+ "</br>"
			  		+ "<figcaption class=\"figure-caption\">Algar Tech - Gente servindo Gente.</figcaption>"
			  		+ "</figure>"
			  		+ "</body"
			  		+ "</html>";
			  

			  Multipart multipart = new MimeMultipart();

			  MimeBodyPart attachment0 = new MimeBodyPart();
			  attachment0.setContent(htmlMessage,"text/html; charset=UTF-8");
			  multipart.addBodyPart(attachment0);
			  
//			  //arquivo que será anexado
//			  String pathname = "C:/Users/bruno.queiroz.CJF/git/sisnoc_chamados_cjf/WebContent/resources/logo_algar.png";//pode conter o caminho
//			  File file = new File(pathname);
//
			  
			  	
			    InputStream template = getClass().getResourceAsStream(relatorio);
			    JasperReport report = JasperCompileManager.compileReport(template);
			    Map<String, Object> teste = new HashMap<String,Object>();
			    
			    
			    JasperPrint print = JasperFillManager.fillReport(report,teste, equipeDao.getConnection());
			    File pdf = File.createTempFile(OutputFilePDF, ".pdf");
			    JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
			 
			    
			  MimeBodyPart attachment1 = new MimeBodyPart();  
			  attachment1.setDataHandler(new DataHandler(new FileDataSource(pdf)));
			  attachment1.setFileName(pdf.getName());
			  multipart.addBodyPart(attachment1);

			  //adicionando a multipart como conteudo da mensagem 
			  message.setContent(multipart);
			  
			  //enviando
    			mailSender.send(message);

			  System.out.println("E-mail enviado com sucesso!");
					
			
		}	


		@Async
		public void enviarVolumetria(String titulo, String  relatorio, String OutputFilePDF) throws MessagingException, IOException, JRException {
			  
			 
			  
			  Properties props = new Properties();
			  props.put("mail.host","correio.cjf.local"); 
			  Session session = Session.getDefaultInstance(props);
			  MimeMessage message = new MimeMessage(session);
			  Address from = new InternetAddress("svc_sisnoc@cjf.jus.br");
			  Address to = new InternetAddress("sesser@cjf.jus.br");
			  Address to2 = new InternetAddress("sesinf@cjf.jus.br");
			  Address to3 = new InternetAddress("infra.algar@cjf.jus.br");
			  message.setFrom(from);
			  message.addRecipient(RecipientType.TO, to);
			  message.addRecipient(RecipientType.TO, to2);
			  message.addRecipient(RecipientType.TO, to3);
			  message.setSentDate(new Date());
			  message.setSubject(titulo);
			  
			  String htmlMessage = "<html>"
			  		+ "<head>"
			  		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
			  		+ "<link rel=\"stylesheet\" href=\"resources/css/bootstrap.min.css\">"
			  		+ "<title>Relatório</title>"
			  		+ "</head>"
			  		+ "<body>"
			  		+ "<blockquote class=\"blockquote text-left\">"
			  		+ "<p class=\"mb-0\">Volumetria de requisições por Semana.</bold></p>"
			  		+ "<footer class=\"blockquote-footer\">SISNOC - <cite title=\"Source Title\">Vai além</cite></footer>"
			  		+ "</blockquote>"
			  		+ "<figure class=\"figure\">"
			  		+ "<img src=\"http://sisnoc/chamados/resources/images/logo_algar.png\" class=\"figure-img img-fluid rounded\" alt=\"Algar Tech - Gente servindo Gente.\">"
			  		+ "</br>"
			  		+ "<figcaption class=\"figure-caption\">Algar Tech - Gente servindo Gente.</figcaption>"
			  		+ "</figure>"
			  		+ "</body>"
			  		+ "</html>";
			  

			  Multipart multipart = new MimeMultipart();

			  MimeBodyPart attachment0 = new MimeBodyPart();
			  attachment0.setContent(htmlMessage,"text/html; charset=UTF-8");
			  multipart.addBodyPart(attachment0);
			  
//			  //arquivo que será anexado
//			  String pathname = "C:/Users/bruno.queiroz.CJF/git/sisnoc_chamados_cjf/WebContent/resources/logo_algar.png";//pode conter o caminho
//			  File file = new File(pathname);
//
			  
			  	
			    InputStream template = getClass().getResourceAsStream(relatorio);
			    JasperReport report = JasperCompileManager.compileReport(template);
			    Map<String, Object> teste = new HashMap<String,Object>();
			    
			    
			    JasperPrint print = JasperFillManager.fillReport(report,teste, equipeDao.getConnection());
			    File pdf = File.createTempFile(OutputFilePDF, ".pdf");
			    JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
			 
			    
			  MimeBodyPart attachment1 = new MimeBodyPart();  
			  attachment1.setDataHandler(new DataHandler(new FileDataSource(pdf)));
			  attachment1.setFileName(pdf.getName());
			  multipart.addBodyPart(attachment1);

			  //adicionando a multipart como conteudo da mensagem 
			  message.setContent(multipart);
			  
			  //enviando
    			mailSender.send(message);

			  System.out.println("E-mail enviado com sucesso!");
					
			
		}	


}
