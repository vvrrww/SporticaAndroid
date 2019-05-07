package com.example.sportica;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChulaSSOHelper {

    private static final String DeeAppId = "th.ac.chula.eng.cp.mobile";
    private static final String DeeAppSecret = "0a455d685fdf2dec0790817aaaea5096ad4aed64c72b6290e9aef693aa7c61583763eddadb1f26627b39e7d5180069db91833ef18a5d2973f997e9a916023fec";

    public static String serviceValidation(String ticket){
        try {
            URL url=new URL("https://account.it.chula.ac.th/serviceValidation");

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("DeeAppId", DeeAppId);
            conn.setRequestProperty("DeeAppSecret", DeeAppSecret);
            conn.setRequestProperty("DeeTicket", ticket);
            conn.setUseCaches(false);
            conn.setDoInput(true);

            InputStream is = conn.getInputStream();
            String encoding = conn.getContentEncoding() == null ? "UTF-8"
                    : conn.getContentEncoding();
            String jsonResponse = IOUtils.toString(is, encoding);
            return jsonResponse;

        } catch (
        IOException e) {
            e.printStackTrace();
        }

            return null;
    }
}
