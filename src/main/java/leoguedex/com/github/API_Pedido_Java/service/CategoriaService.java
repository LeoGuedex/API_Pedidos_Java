package leoguedex.com.github.API_Pedido_Java.service;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Categoria;
import leoguedex.com.github.API_Pedido_Java.domain.repository.CategoriaRepository;
import leoguedex.com.github.API_Pedido_Java.exception.DataIntegratyException;
import leoguedex.com.github.API_Pedido_Java.exception.ObjectNotFoundException;
import leoguedex.com.github.API_Pedido_Java.rest.dto.CategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(()-> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id + ", tipo: "
                + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria Insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Page<Categoria> findPage(Integer page, Integer linerPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linerPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria update (Categoria categoria){
        find(categoria.getId()); // evitando de 2 usarem ao msm tempo
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id){
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegratyException("Não é possivel excluir uma categoria que possui produtos");
        }
    }

    public Categoria fromDto(CategoriaDto categoriaDto){
        return new Categoria(categoriaDto.getId(), categoriaDto.getNome());

    }

}