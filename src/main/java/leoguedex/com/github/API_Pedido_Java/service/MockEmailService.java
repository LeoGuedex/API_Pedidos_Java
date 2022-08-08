package leoguedex.com.github.API_Pedido_Java.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Simulando Envio de Email....");
        LOG.info(mailMessage.toString());
        LOG.info("E-mail enviado com sucesso!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mailMessage) {
        LOG.info("Simulando Envio de Email....");
        LOG.info(mailMessage.toString());
        LOG.info("E-mail enviado com sucesso!");
    }

}