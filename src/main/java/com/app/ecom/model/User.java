package com.app.ecom.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
