/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.options;

import ApiHackerEarth.com.hackerearth.heapi.sdk.options.CompileOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.SupportedLanguages;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 *
 * @author Amal
 */
public class RunOptions extends CompileOptions {
    public static final String RUN_TIME_UPPER_LIMIT = "5";

    public static final String MEMORY_UPPER_LIMIT = "262144";
    @SerializedName("compiled")
    @Expose
    public String compiled;
    

    @SerializedName("time_limit")
    @Expose
    public String timeLimit;
    
    @SerializedName("input")
    @Expose
    public final String input;
    

    @SerializedName("memory_limit")
    @Expose
    public String memoryLimit;

    @SerializedName("html")
    @Expose
    public String html;

    public int save = 1;

    public RunOptions(String sourceCode,String inputCode, SupportedLanguages Language){
        super(sourceCode, Language);
        this.input= inputCode;
    }

    public String getTimeLimit() {
        if(timeLimit == null){
            return RUN_TIME_UPPER_LIMIT;
        }
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getCompiled() {
        if(compiled == null){
            return "0";
        }
        return compiled;
    }

    public void setCompiled(String compiled) {
        this.compiled = compiled;
    }

    public String getMemoryLimit() {
        if(memoryLimit == null){
            return MEMORY_UPPER_LIMIT;
        }
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getHtml() {
        if(html == null){
            return "0";
        }
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getInput() {
        return input;
    }
    

    @Override
    public List<NameValuePair> getURLParameters(){
        List<NameValuePair> parameters = super.getURLParameters();
        parameters.add(new BasicNameValuePair("html", getHtml()));
        parameters.add(new BasicNameValuePair("compiled", getCompiled()));
        parameters.add(new BasicNameValuePair("memory_limit", getMemoryLimit()));
        parameters.add(new BasicNameValuePair("time_limit", getTimeLimit()));
        parameters.add(new BasicNameValuePair("input", getInput()));
        return parameters;
    }
}
