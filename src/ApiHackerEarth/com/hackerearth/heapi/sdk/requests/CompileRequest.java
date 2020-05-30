/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.requests;

import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.BaseRequest;
import com.google.gson.Gson;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.CompileOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.CompileResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 *
 * @author Amal
 */
public class CompileRequest extends  BaseRequest {
     public static final String COMPILE_ENDPOINT = "https://api.hackerearth.com/v3/code/compile/";
    

    public CompileRequest(String clientSecret, CompileOptions params){
        super(clientSecret, params);
      
    }

    @Override
    public CompileResponse Execute(){
        Gson gson = new Gson();
        this.options.setClientSecret(this.clientSecret);
        List<NameValuePair> parameters = this.options.getURLParameters();
        String responseString = sendRequest(COMPILE_ENDPOINT, parameters);
        CompileResponse response = gson.fromJson(responseString, CompileResponse.class);
        return response;
    }
}
