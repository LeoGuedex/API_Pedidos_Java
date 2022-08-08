package leoguedex.com.github.API_Pedido_Java.rest.dto;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 char")
    private String nome;

    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDto(Categoria categoria) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}