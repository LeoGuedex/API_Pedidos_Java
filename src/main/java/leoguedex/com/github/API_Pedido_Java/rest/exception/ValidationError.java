package leoguedex.com.github.API_Pedido_Java.rest.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private static final long serialVersionUID= 1L;

    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }


    public List<FieldMessage> getList() {
        return list;
    }

    public void addError(String fieldName, String message){
        this.list.add(new FieldMessage(fieldName, message));
    }

}