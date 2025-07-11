package com.codingtu.cooltu.lib4j.net;

import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimpleDownloadTool {

    private static final long SLEEP_TIME = 5;

    public static List<String> readUrl(String url) {
        List<String> lines = new ArrayList<String>();
        while (CountTool.count(lines) <= 0) {
            HttpInfo hi = null;
            BufferedReader br = null;
            try {
                System.out.println("===================================");
                System.out.println("正在读取:" + url);
                hi = getHttpInfo(url, false);
                br = new BufferedReader(new InputStreamReader(hi.in));
                String line = null;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                hi.close();
            } catch (Exception e) {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e1) {
                    }
                }
                if (hi != null)
                    hi.close();
                System.out.println("读取失败，等待" + SLEEP_TIME + "秒重试:" + url);
                lines.clear();
                try {
                    Thread.sleep(SLEEP_TIME * 1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("读取成功:" + url);
        System.out.println("===================================");
        return lines;
    }

    public static boolean download(String downloadUrl, String path) {
        File file = new File(path);
        File tempFile = new File(path + ".temp");
        if (file.exists()) {
            return true;
        }

        OutputStream outputStream = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        byte[] bytes = new byte[40960];

        int retryCount = 3;
        while (retryCount >= 0) {
            try {
                httpURLConnection = (HttpURLConnection) new URL(downloadUrl).openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setDoInput(true);
                inputStream = httpURLConnection.getInputStream();
                outputStream = new FileOutputStream(tempFile);
                int len;
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.flush();
                retryCount = -100;
            } catch (Exception e) {
                retryCount--;
                e.printStackTrace();
                System.out.println("下载失败：" + file.getName());
            } finally {
                try {
                    if (outputStream != null)
                        outputStream.close();
                    if (inputStream != null)
                        inputStream.close();
                    if (httpURLConnection != null)
                        httpURLConnection.disconnect();
                } catch (Exception e) {
                }
            }

            if (retryCount >= 0) {
                try {
                    Thread.sleep((3 - retryCount) * 2000);
                } catch (InterruptedException e1) {
                }
            }
        }

        if (retryCount == -100) {
            tempFile.renameTo(file);
        }
        return retryCount == -100;
    }

    private static HttpInfo getHttpInfo(String url, boolean useGzip) throws Exception {
        HttpInfo hi = new HttpInfo();
        hi.conn = (HttpURLConnection) new URL(url).openConnection();
        hi.conn.setRequestProperty("Accept", "*/*");
        hi.conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
        hi.conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        if (useGzip)
            hi.conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        hi.conn.setRequestProperty("Connection", "keep-alive");
        int responseCode = hi.conn.getResponseCode();
        if (200 <= responseCode && responseCode <= 299) {
            hi.in = hi.conn.getInputStream();
        } else {
            hi.in = hi.conn.getErrorStream();
        }
        return hi;
    }
}
