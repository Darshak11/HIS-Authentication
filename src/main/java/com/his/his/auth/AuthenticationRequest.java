package com.his.his.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest
{
    private String uuid;
    private String password;

    public String getUuid(){
        return this.uuid;
    }
}
    
