package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import leoguedex.com.github.API_Pedido_Java.domain.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderconfirmationHtmlEmail(Pedido pedido);

    void sendHtmlEmail(MimeMessage mailMessage);

    void sendNewPassword(Cliente cliente, String newPass);

}