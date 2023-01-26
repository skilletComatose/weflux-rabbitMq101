package co.com.bancolombia.model.globalresponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public  class GlobalResponse<T> {
    int code;
    String msg;
    T data;
}
