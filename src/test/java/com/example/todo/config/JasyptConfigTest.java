package com.example.todo.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;


class JasyptConfigTest {
    @Test
    void StringEncryptor() {
        String url = "jdbc:log4jdbc:postgresql://127.0.0.1:5432/testdb";
        String username = "admin";
        String password = "pass";

        System.out.println(jasyptEncoding(url));
        System.out.println(jasyptEncoding(username));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        String key = "gdh-password";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(value);
    }
}