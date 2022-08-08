package leoguedex.com.github.API_Pedido_Java.service.validation;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Cliente;
import leoguedex.com.github.API_Pedido_Java.domain.enums.TipoCliente;
import leoguedex.com.github.API_Pedido_Java.domain.repository.ClienteRepository;
import leoguedex.com.github.API_Pedido_Java.rest.dto.ClienteNewDto;
import leoguedex.com.github.API_Pedido_Java.rest.exception.FieldMessage;
import leoguedex.com.github.API_Pedido_Java.service.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if(clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Invalido."));
        }

        if(clienteNewDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Invalido."));
        }

        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());
        if (cliente != null){
            list.add(new FieldMessage("email", "E-mail já cadastrado"));
        }

        for (FieldMessage e: list){
            constraintValidatorContext
            .disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
            .addPropertyNode(e.getFieldName())
            .addConstraintViolation();
        }

        return list.isEmpty();
    }

}