package io.harshit.betterreadsweb;

import io.harshit.betterreadsweb.connection.DataStaxAstraProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Socket;
import java.security.cert.CertificateException;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BetterreadsWebApplication {

    public static void main(String[] args) {
        trustAllHosts();
        SpringApplication.run(BetterreadsWebApplication.class, args);
    }

    private static void trustAllHosts() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509ExtendedTrustManager() {

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string, Socket socket)
                        throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string, Socket socket)
                        throws CertificateException {

                }

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle)
                        throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle)
                        throws CertificateException {

                }

            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
