package com.greenGekko.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class JUser {

    @JsonProperty("email")
    public final String email;
    @JsonProperty("userPassword")
    public final String userPassword;
    @JsonProperty("uuid")
    public final String uuid;
    @JsonProperty("rolesSet")
    public final Set<JRole> rolesSet;

    @Builder(toBuilder = true)
    @NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
    @AllArgsConstructor
    @Data
    public static class JRole {

        @JsonProperty("id")
        public final int id;
        @JsonProperty("role")
        public final String role;

    }
}
