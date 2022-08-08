package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import leoguedex.com.github.API_Pedido_Java.domain.entity.ItemPedido;
import leoguedex.com.github.API_Pedido_Java.domain.entity.PagamentoComBoleto;
import leoguedex.com.github.API_Pedido_Java.domain.entity.Pedido;
import leoguedex.com.github.API_Pedido_Java.domain.enums.EstadoPagamento;
import leoguedex.com.github.API_Pedido_Java.domain.repository.ItemPedidoRepository;
import leoguedex.com.github.API_Pedido_Java.domain.repository.PagamentoRepository;
import leoguedex.com.github.API_Pedido_Java.domain.repository.PedidoRepository;
import leoguedex.com.github.API_Pedido_Java.exception.AuthorizationException;
import leoguedex.com.github.API_Pedido_Java.exception.ObjectNotFoundException;
import leoguedex.com.github.API_Pedido_Java.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! id: "
                + id + " tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.00);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());

        emailService.sendOrderConfirmationEmail(pedido);

//        emailService.sendOrderconfirmationHtmlEmail(pedido);

        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso Negado");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Cliente cliente = clienteService.find(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}