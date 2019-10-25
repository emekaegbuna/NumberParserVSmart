package com.example.numberparservsmart;

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
        String prefixU;
        Boolean exist;

        // check dialled number is valid
        if(dialledNumber.length() < 10)
            return "Invalid length of dialed number";

        int startPosU = userNumber.length() - 10;

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
            prefixU = userNumber.substring(1, startPosU);
            res = getUserPrefix(prefixU);
            if(!res.equals("N/A"))
                return res + numberNoPrefix;

            return "Unable to find matching CountryCode with Prefix of users number";
        }
    }

    // takes prefix and returns true if within callingCodes Map
    private Boolean checkCountryCode(int prefix){
        return callingCodes.containsValue(prefix);
    }

    private String getCountryCodeKey(String prefix){
        int prefixU = Integer.parseInt(prefix);
        if (callingCodes.containsValue(prefixU)){
            for (Map.Entry<String, Integer> entry : callingCodes.entrySet()){
                if (entry.getValue().equals(prefixU)){
                    return entry.getKey();
                }
            }
        }
        return "N/A";
    }

    private String getUserPrefix(String prefixU){

        String userCountry = getCountryCodeKey(prefixU);

        if (prefixes.containsKey(userCountry)){
            for (Map.Entry<String, String> entry : prefixes.entrySet()){
                if (entry.getKey().equals(userCountry) && entry.getValue().equals("0")){
                    return "+" + prefixU;
                }
            }
        }

        return "N/A";
    }


}

