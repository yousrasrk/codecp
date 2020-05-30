/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.BaseOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.BaseResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Amal
 */
public class BaseRequest {
    public final BaseOptions options;
    protected final String clientSecret;
    public final String USER_AGENT = "Mozilla/5.0";

    public BaseRequest(String clientSecret, BaseOptions options){
        this.clientSecret = clientSecret;
        this.options = options;
    }

    public BaseResponse Execute(){
        return null;
    }

    protected String sendRequest(final String endpoint, final List<NameValuePair> options){
        try {
            HttpPost httpPost = new HttpPost(endpoint);
            httpPost.setEntity(new UrlEncodedFormEntity(options));
            //httpPost.setHeader("Content-type", "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer resultBuffer = new StringBuffer();
            String line = "";
            while((line = rd.readLine()) != null){
                resultBuffer.append(line);
            }
            return resultBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Blocking IO.
    public BaseResponse waitForResult(){
        return null;
    }
}
