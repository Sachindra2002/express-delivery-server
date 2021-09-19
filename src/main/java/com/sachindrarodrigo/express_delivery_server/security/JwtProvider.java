package com.sachindrarodrigo.express_delivery_server.security;

import com.fasterxml.classmate.AnnotationOverrides;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    //Asymmetric encryption mechanism is used to sign JWT web token using Java Keystore (private key => sign token and public key => decode token)
    //RSA encryption algorithm used

    @PostConstruct
    public void init(){
        try{
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/expressdelivery.jks");
            keyStore.load(resourceAsStream, "sachindra".toCharArray());

        }catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e){
            throw new RuntimeException("Something went wrong while loading keystore");
        }
    }

    //Sign token with private key
    public String generateToken(Authentication authentication) {
        User principle = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principle.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .compact();
    }

    private PrivateKey getPrivateKey(){
        try{
            return (PrivateKey) keyStore.getKey("expressdelivery", "sachindra".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException("Exception occurred while retrieving public key from keystore ");
        }
    }

    //Decode token with public key
    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
        return true;
    }

    private PublicKey getPublicKey(){
        try{
            return keyStore.getCertificate("expressdelivery").getPublicKey();
        }catch (KeyStoreException e){
            throw new RuntimeException("Exception occurred while retrieving public key from keystore");
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


}
