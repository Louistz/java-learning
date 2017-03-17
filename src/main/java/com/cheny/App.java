package com.cheny;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ddd();
    }


    public static void test(){
    }

    public static  void ddd(){
        String urls = "http://kyfw.12306.cn/otn/leftTicket/queryA?leftTicketDTO.train_date=2017-02-02&leftTicketDTO.from_station=SMF&leftTicketDTO.to_station=NJH&purpose_codes=ADULT";

        HttpURLConnection conn = null;
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
                public X509Certificate[] getAcceptedIssuers(){return null;}
                public void checkClientTrusted(X509Certificate[] certs, String authType){}
                public void checkServerTrusted(X509Certificate[] certs, String authType){}
            }};

            // Install the all-trusting trust manager

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(urls);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
