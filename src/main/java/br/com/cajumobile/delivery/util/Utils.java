package br.com.cajumobile.delivery.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String md5(String value) {
        String retorno = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes(), 0, value.length());
            BigInteger hash = new BigInteger(1, md.digest());
            retorno = String.format("%1$032x", hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return retorno;
    }

}
