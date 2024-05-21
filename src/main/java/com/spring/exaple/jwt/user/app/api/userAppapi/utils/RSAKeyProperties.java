package com.spring.exaple.jwt.user.app.api.userAppapi.utils;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Data
public class RSAKeyProperties {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAKeyProperties(){
        KeyPair pair=KeyGeneratorUtility.generateRsakey();
        this.publicKey=(RSAPublicKey) pair.getPublic();
        this.privateKey=(RSAPrivateKey) pair.getPrivate();
    }
}
