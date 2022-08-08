package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import leoguedex.com.github.API_Pedido_Java.domain.repository.ClienteRepository;
import leoguedex.com.github.API_Pedido_Java.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente==null){
            throw  new UsernameNotFoundException(email);
        }
    return new UserSS(
            cliente.getId(),
            cliente.getEmail(),
            cliente.getSenha(),
            cliente.getPerfis());
    }

}