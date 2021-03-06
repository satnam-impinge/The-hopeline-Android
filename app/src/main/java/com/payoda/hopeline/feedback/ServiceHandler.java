package com.payoda.hopeline.feedback;

import android.util.Log;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ServiceHandler
{

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler()
    {

    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
   /* public String makeServiceCall(String url, int method)
    {
        return this.makeServiceCall(url, method, null);
    }*/

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */

    @SuppressWarnings("deprecation")
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> entity)
    {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method==POST) {

                HttpPost httpPost = new HttpPost(url);
                // adding post params
                if (entity != null) {

                    Log.e("entity is","entity is"+entity);
                    httpPost.setEntity(new UrlEncodedFormEntity(entity));
                }
                httpResponse=httpClient.execute(httpPost);

            } else if(method==GET) {
                // appending params to url
               /* if (entity != null) {
                    String paramString = URLEncodedUtils.format(entity,"utf-8");
                    url += "?" + paramString;
                }*/
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}