/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.options;

import ApiHackerEarth.com.hackerearth.heapi.sdk.options.BaseOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.SupportedLanguages;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Amal
 */
public class CompileOptions extends BaseOptions {
     public CompileOptions(String sourceCode, SupportedLanguages language){
        super(sourceCode, language);
    }

    @Override
    public List<NameValuePair> getURLParameters(){
        List<NameValuePair> parameters = super.getURLParameters();
        return parameters;
    }
}
