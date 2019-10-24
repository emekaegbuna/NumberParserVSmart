package com.example.numberparservsmart;

import java.util.HashMap;
import java.util.Map;


public class NumberParser {
    private Map<String, Integer> callingCodes;
    private Map<String, String> prefixes;


    public NumberParser(Map<String, Integer> callingCodes, Map<String, String> prefixes) {
        this.callingCodes = callingCodes;
        this.prefixes = prefixes;
    }


    // parse dialled number and return international format
    public String parse(String dialledNumber, String userNumber) {
        String prefix, res;
        Boolean exist;

        // check dialled number is valid
        if(dialledNumber.length() < 10)
            return "Invalid length of dialed number";

        int startPos = dialledNumber.length() - 10;
        String numberNoPrefix = dialledNumber.substring(startPos);

        // check if first index value of dialled number contains +,
        if(dialledNumber.substring(0, 1).equals("+")){
            prefix = dialledNumber.substring(1, startPos);
            if(prefix.length() >= 1 && prefix.length() <= 4){
                if(prefix.substring(0, 1).equals("0"))
                    return "Calling code cannot start with 0";

                int p = Integer.parseInt(prefix);
                exist = checkCountryCode(p);
                if(exist)
                    return dialledNumber;

                return "Non existing CollingCode with input dialled number";
            }

            return "Calling code has to be between 1 and 4 digits long";
        }
        else{
            prefix = dialledNumber.substring(0, startPos);
            res = checkPrefix(prefix);
            if(!res.equals("N/A"))
                return res + numberNoPrefix;

            return "Unable to find matching CountryCode with Prefix of dialled number";
        }
    }

    private Boolean checkCountryCode(int prefix){
        return callingCodes.containsValue(prefix);
    }

    private String checkPrefix(String prefix){
        System.out.println("prefix: " + prefix);
        if(prefixes.containsValue(prefix)){
            for (Map.Entry<String,String> entry : prefixes.entrySet()){
                if(entry.getValue().equals(prefix))
                    prefix = entry.getKey();
            }
            int intlCode = callingCodes.get(prefix);

            return "+" + intlCode;
        }
        return "N/A";
    }


}
