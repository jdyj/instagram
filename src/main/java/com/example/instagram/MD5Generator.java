package com.example.instagram;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    private String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(input.getBytes("UTF-8"));
        byte[] md5Hash = md5.digest();
        StringBuilder hexMD5hash = new StringBuilder();
        for (byte hash : md5Hash) {
            String hexString = String.format("%02x", hash);
            hexMD5hash.append(hexString);
        }
        result = hexMD5hash.toString();
    }

    public String toString() {
        return result;
    }
}
