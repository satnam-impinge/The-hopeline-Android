package com.payoda.hopeline.webapi;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shivam on 11/3/16.
 */
public class WebApi {

    private static WebApi webApi;

    public static final WebApi getInstance() {
        if (webApi == null) {
            webApi = new WebApi();
        }
        return webApi;
    }

    public String webcall(Context context, String url) {
        StringBuffer response = new StringBuffer();
        try {
            Log.e("url","url=="+url);
            URL url_Connection = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url_Connection.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20000);
//            conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            int status = conn.getResponseCode();
            Log.e("status","status=="+status);
            InputStreamReader inputStreamReader = null;
//            int content_Length = conn.getContentLength();
            if (status == 200) {
                inputStreamReader = new InputStreamReader(conn.getInputStream());

            } else {
                inputStreamReader = new InputStreamReader(conn.getErrorStream());
            }
//            int b = inputStreamReader.read();
//
//            for (int a = 0; a < content_Length; a++) {
//                response.append((char) b);
//                b = inputStreamReader.read();
//            }

            BufferedReader reader=new BufferedReader(inputStreamReader);
            int line;
            while ((line=reader.read())!=-1)
            {
                response.append((char) line);
            }

            inputStreamReader.close();
            conn.disconnect();

            Log.e("response ", response.toString());
        } catch (Exception e) {
            Log.e("Error Message", response.toString());
        }
        return response.toString();
    }

}
