package com.example.demo.controller.bean.base;

import lombok.Data;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;

@Data
public class RestRequest<Body> implements Serializable {
    private static final long serialVersionUID = 245071105410487890L;
    private RequestHeader header;
    private Body body;

    @Data
    public static class RequestHeader implements Serializable{
        private String apiCode;
        private String userId;
    }

}
