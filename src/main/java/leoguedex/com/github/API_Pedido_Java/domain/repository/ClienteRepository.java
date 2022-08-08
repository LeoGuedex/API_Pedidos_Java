package leoguedex.com.github.API_Pedido_Java.domain.repository;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

        @Transactional(readOnly = true)
        Cliente findByEmail(String email);

}