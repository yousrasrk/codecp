/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiHackerEarth.com.hackerearth.heapi.sdk.options;

/**
 *
 * @author Amal
 */
public enum SupportedLanguages {
    CPP("CPP"),
    JAVA("JAVA"),
    PYTHON("PYTHON"),
    C("C"),
    JAVASCRIPT("JAVASCRIPT"),
    HASKELL("HASKELL"),
    PERL("PERL"),
    PHP("PHP"),
    RUBY("RUBY"),
    CLOJURE("CLOJURE"),
    CSHARP("CSHARP"),
    CPP11("CPP11");

    private String value;

    private SupportedLanguages(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
