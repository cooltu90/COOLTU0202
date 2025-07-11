package com.codingtu.cooltu.lib4j.net;

import java.io.InputStream;
import java.net.HttpURLConnection;

public class HttpInfo {

    public HttpURLConnection conn;
    public InputStream in;

    public HttpInfo() {
    }

    public void close() {
        try {
            if (this.in != null) {
                this.in.close();
            }

            this.in = null;
        } catch (Exception var2) {
        }

        if (this.conn != null) {
            this.conn.disconnect();
        }

        this.conn = null;
    }

}
