/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.responses;

import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.BaseResponse;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.Errors;

@Generated("org.jsonschema2pojo")
/**
 *
 * @author Amal
 */
public class CompileResponse extends BaseResponse{
    @Expose
    private Errors errors;
    @Expose
    private String id;
    @SerializedName("code_id")
    @Expose
    private String codeId;
    @Expose
    private String message;
    @SerializedName("compile_status")
    @Expose
    private String compileStatus;
    @SerializedName("web_link")
    @Expose
    private String webLink;

    /**
     *
     * @return
     * The errors
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     *
     * @param errors
     * The errors
     */
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The codeId
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     *
     * @param codeId
     * The code_id
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The compileStatus
     */
    public String getCompileStatus() {
        return compileStatus;
    }

    /**
     *
     * @param compileStatus
     * The compile_status
     */
    public void setCompileStatus(String compileStatus) {
        this.compileStatus = compileStatus;
    }

    /**
     *
     * @return
     * The webLink
     */
    public String getWebLink() {
        return webLink;
    }

    /**
     *
     * @param webLink
     * The web_link
     */
    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
}
