package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import leoguedex.com.github.API_Pedido_Java.domain.repository.ClienteRepository;
import leoguedex.com.github.API_Pedido_Java.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente==null){
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();

        cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPassword(cliente, newPass);
    }

    private String newPassword() {

        char[] vet = new char[10];
        for (int i=0; i < 10; i++ ){
            vet[i] = randonChar();
        }
        return new String(vet);
    }

    private char randonChar() {
        int opt = rand.nextInt(3);

        if(opt == 0){
            return (char)(rand.nextInt(10) + 48);
        } else if(opt == 1){
            return (char) (rand.nextInt(26) + 65);
        } else {
            return (char) (rand.nextInt(26) + 97);
        }
    }

}