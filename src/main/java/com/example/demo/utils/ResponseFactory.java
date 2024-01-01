package com.example.demo.utils;

import com.example.demo.constant.ReturnCode;
import com.example.demo.controller.bean.ResultDTO;
import com.example.demo.controller.bean.base.RestResponse;

public class ResponseFactory {



    public static <Body> RestResponse<Body> createResponse(String code, String desc, Body body) {
        RestResponse<Body> restResponse = new RestResponse<>();

        RestResponse.ResponseHeader header = new RestResponse.ResponseHeader();
        header.setResultCode(code);
        header.setResultDescription(desc);
        restResponse.setHeader(header);
        restResponse.setBody(body);

        return restResponse;

    }


    public static <Body> RestResponse<Body> createSuccessResponse(Body body) {
        ReturnCode returnCode = ReturnCode.SUCCESS;
        RestResponse<Body> restResponse = createResponse(returnCode.getCode(), returnCode.getValue(), body);

        return restResponse;
    }


    public static RestResponse<ResultDTO> createErrorResponse(String code, String desc) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultCode(code);
        resultDTO.setResultDescription(desc);

        RestResponse<ResultDTO> response = createResponse(code, desc, resultDTO);

        return response;

    }
}
