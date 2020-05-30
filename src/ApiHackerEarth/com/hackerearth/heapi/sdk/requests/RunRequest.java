/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.requests;

import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.BaseRequest;
import com.google.gson.Gson;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.RunOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.RunResponse;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 *
 * @author Amal
 */
public class RunRequest  extends BaseRequest{
     public static final String RUN_ENDPOINT = "https://api.hackerearth.com/v3/code/run/";

    public RunRequest(String clientSecret, RunOptions params){
        super(clientSecret, params);
    }

    @Override
    public RunResponse Execute(){
        Gson gson = new Gson();
        this.options.setClientSecret(this.clientSecret);
        List<NameValuePair> options = this.options.getURLParameters();
        String responseString = sendRequest(RUN_ENDPOINT, options);
        RunResponse response = gson.fromJson(responseString, RunResponse.class);
        return response;
    }
}
