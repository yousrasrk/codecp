/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.exceptions;
/**
 *
 * @author Amal
 */
public class InvalidParameterException extends Exception{
    private String mMessage = "Invalid Parameter found.";

    public InvalidParameterException(String message){
        super(message);
        this.mMessage = message;
    }

    @Override
    public String toString(){
        return mMessage;
    }

    public InvalidParameterException(Throwable cause){
        super(cause);
    }

    @Override
    public String getMessage(){
        return mMessage;
    }
    
}
