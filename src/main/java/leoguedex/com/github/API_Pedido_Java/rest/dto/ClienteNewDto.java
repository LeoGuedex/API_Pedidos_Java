package leoguedex.com.github.API_Pedido_Java.rest.dto;

import leoguedex.com.github.API_Pedido_Java.service.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClienteNewDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5, max = 120, message = "Tamanho deve ser entre 5 e 120")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String cpfCnpj;

    private Integer tipo;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String senha;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String logradouro;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String cep;


    private Integer cidadeId;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    public ClienteNewDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}