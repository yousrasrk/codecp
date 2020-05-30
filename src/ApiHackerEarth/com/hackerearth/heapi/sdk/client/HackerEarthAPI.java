/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ApiHackerEarth.com.hackerearth.heapi.sdk.client;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.CompileOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.RunOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.CompileRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.RunRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.CompileResponse;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.RunResponse;

/**
 *
 * @author Amal
 */
public class HackerEarthAPI {
    private final String mClientSecret;

    public HackerEarthAPI(String clientSecret){
        this.mClientSecret = clientSecret;
    }

    public CompileResponse Compile(CompileOptions options){

        CompileRequest request = new CompileRequest(mClientSecret, options);
        CompileResponse response = (CompileResponse)request.Execute();
        return response;
    }

    public RunResponse Run(RunOptions options){
        RunRequest request = new RunRequest(mClientSecret, options);
        RunResponse response = (RunResponse)request.Execute();
        return response;
    }
}
