//package com.sparta.plusweek.global.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//// 비밀번호 암호화 객체.
//// 환경변수로부터 salt 를 받아와 SHA-512로 암호화
//@Component
//public class PasswordEncoder {
//
//    @Value("${password-encoder.salt}")
//    private String salt;
//
//    public String encode(String rawPassword) {
//        try {
//            byte[] bytes = salting(rawPassword);
//            return getEncryptedString(bytes);
//        } catch (NoSuchAlgorithmException ignored) {
//        }
//        return rawPassword;
//    }
//
//    private byte[] salting(String rawPassword) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        md.update(salt.getBytes(StandardCharsets.UTF_8));
//        return md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
//    }
//
//    private String getEncryptedString(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte aByte : bytes) {
//            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
//        }
//        return sb.toString();
//    }
//}
