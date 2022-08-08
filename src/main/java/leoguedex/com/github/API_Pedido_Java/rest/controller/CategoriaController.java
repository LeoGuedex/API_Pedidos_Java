package leoguedex.com.github.API_Pedido_Java.rest.controller;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Categoria;
import leoguedex.com.github.API_Pedido_Java.rest.dto.CategoriaDto;
import leoguedex.com.github.API_Pedido_Java.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){

        Categoria categoria = categoriaService.find(id);
    return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDto>> findAll(){
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDto> listDto = list.stream()
                .map(categoria -> new CategoriaDto(categoria))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDto> listDto = list.map(categoria -> new CategoriaDto(categoria));
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto categoriaDto){
        Categoria categoria = categoriaService.fromDto(categoriaDto);
        categoria = categoriaService.Insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDto categoriaDto){
        Categoria categoria = categoriaService.fromDto(categoriaDto);
        categoria.setId(id);
        categoria = categoriaService.update(categoria);
    return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}