package com.example.demo.controller;


import com.example.demo.constant.ReturnCode;
import com.example.demo.controller.bean.ResultDTO;
import com.example.demo.controller.bean.UserDTO;
import com.example.demo.controller.bean.UserLoginParamDTO;
import com.example.demo.controller.bean.UserParamDTO;
import com.example.demo.controller.bean.base.RestRequest;
import com.example.demo.controller.bean.base.RestResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.bean.UserBO;
import com.example.demo.utils.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("bk/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public RestResponse<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseFactory.createSuccessResponse(users);
    }

    @GetMapping("/search")
    public RestResponse<List<UserDTO>> findBySearch(@RequestParam(required = false) String param) {
        List<UserDTO> users = userService.findBySearch(param);
        return ResponseFactory.createSuccessResponse(users);

    }

    @PostMapping("/save")
    public RestResponse<ResultDTO> save(@Valid @RequestBody RestRequest<UserParamDTO> request) {
        UserParamDTO userParamDTO = request.getBody();
        if (Objects.isNull(userParamDTO.getId())) {
            userService.save(UserBO.createBy(userParamDTO));
        } else {
            userService.update(UserBO.createBy(userParamDTO));
        }


        ResultDTO dto = new ResultDTO();
        dto.setResultCode(ReturnCode.SUCCESS.getCode());
        dto.setResultDescription(ReturnCode.SUCCESS.getValue());


        return ResponseFactory.createSuccessResponse(dto);
    }


    @DeleteMapping("/{id}")
    public RestResponse<ResultDTO> delete(@PathVariable Integer id) {
        userService.delete(id);

        ResultDTO dto = new ResultDTO();
        dto.setResultCode(ReturnCode.SUCCESS.getCode());
        dto.setResultDescription(ReturnCode.SUCCESS.getValue());


        return ResponseFactory.createSuccessResponse(dto);
    }


    @PostMapping("/login")
    public RestResponse<UserDTO> login(@Valid @RequestBody RestRequest<UserLoginParamDTO> request) {
        UserLoginParamDTO userLoginParamDTO = request.getBody();
        UserDTO userDTO = userService.login(UserBO.createBy(userLoginParamDTO));
        return ResponseFactory.createSuccessResponse(userDTO);
    }

    @PostMapping("/register")
    public RestResponse<ResultDTO> register(@Valid @RequestBody RestRequest<UserParamDTO> request) {
        UserParamDTO userLoginParamDTO = request.getBody();
        userService.save(UserBO.createBy(userLoginParamDTO));
        ResultDTO dto = new ResultDTO();
        dto.setResultCode(ReturnCode.SUCCESS.getCode());
        dto.setResultDescription(ReturnCode.SUCCESS.getValue());
        return ResponseFactory.createSuccessResponse(dto);
    }

}
