package gob.sct.medprev.msc;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.micper.ingsw.TEntorno;
import com.micper.ingsw.TParametro;

public class MailAvisoBiometric {

	public void enviaMailValidaBiometric(String mensaje) {
		TEntorno vEntorno = new TEntorno();
		TParametro vParametros = new TParametro("07");
		vEntorno.setNumModulo(07);
		vParametros = new TParametro(vEntorno.getNumModuloStr());

		String mailServer = vParametros.getPropEspecifica("mailServer");
		String fromEmail = vParametros.getPropEspecifica("mailFrom");
		String toEmail = "";
		String Subject = "";
		String Body = "";

		try {
			// toEmail = "csolanog@sct.gob.mx";
			Subject = ".: Cambio de validación biométrica :.";
			Body = mensaje;
			StringBuffer messageEnter = new StringBuffer(
					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>MEDPREV Handler</title><style type='text/css'>.cuerpo { font-family: 'Times New Roman', Times, serif; color:#515151; }</style></head><body><table width='800' border='0' cellspacing='0' cellpadding='0'><tr><td><img src='http://aplicaciones9.sct.gob.mx/imagenes/medprev/img/medprev/mail_header.png' width='801' height='86' /></td></tr><tr><td class='cuerpo'><br>Atención: <br><br>"
							+ Body
							+ ".<br><br>MEDPREV-UTIC.</td></tr></table></body></html>");
			try {
				Properties props = new Properties();
				props.put("mail.smtp.host", mailServer);
				Session s = Session.getInstance(props, null);
				MimeMessage message = new MimeMessage(s);
				InternetAddress from = new InternetAddress(fromEmail);
				message.setFrom(from);
				message.addRecipients(Message.RecipientType.TO,
						vParametros.getPropEspecifica("mailTo"));
				message.setSubject(Subject);
				String mt = "text/html";
				message.setDataHandler(new DataHandler(messageEnter.toString(),
						mt));
				Transport.send(message);
			} catch (NullPointerException n) {
				System.out.println(n.getMessage());
			} catch (Exception e1) {
				// System.out.println("error al enviar correo " + e1);
				System.out.println(e1);
			}
		} catch (Exception er) {
			System.out.println(er.getMessage());
		}
	}
}
