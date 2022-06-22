/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.util;

import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Fathullah
 */
public class BasicHeader {

    public static String createBasicToken(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return new String(encodedAuth);
    }

}
