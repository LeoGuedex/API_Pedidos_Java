package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Categoria;
import leoguedex.com.github.API_Pedido_Java.domain.entity.Produto;
import leoguedex.com.github.API_Pedido_Java.domain.repository.CategoriaRepository;
import leoguedex.com.github.API_Pedido_Java.domain.repository.ProdutoRepository;
import leoguedex.com.github.API_Pedido_Java.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Produto find(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(()->
                new ObjectNotFoundException("Obj não encontrado! Id: " + id + ", Tipo " + Produto.class.getName()));
    }

    public Page<Produto> search (
            String nome,
            List<Integer> ids,
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction
    ) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);
    }

}