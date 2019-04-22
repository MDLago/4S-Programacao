/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.dominio;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author 5586658
 */
public class MailSender {
    public static boolean enviarEmail(String destinatario, String conteudo) throws MessagingException {
            Properties props = new Properties();
            String remetente = "";
            String senha = "";
            
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
 
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication(remetente, senha);
                             }
                        });
 
            /** Ativa Debug para sessão */
            session.setDebug(true);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente)); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                       .parse(destinatario);  

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(conteudo);//Assunto
            message.setText("Lembrete de tarefa: "+conteudo);

            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            return true;
 
      }
}
