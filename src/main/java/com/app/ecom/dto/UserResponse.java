package com.app.ecom.dto;

import com.app.ecom.model.User_Role;
import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private User_Role role;
    private  AddressDto addressDto;
}
