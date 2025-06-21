package org.mosesidowu.empirezone.utils;

import java.util.HashMap;
import java.util.Map;

public class CountryMapper {

    private static final Map<String, String> countryMap = new HashMap<>();

    static {
        countryMap.put("nigeria", "NG");
        countryMap.put("ghana", "GH");
        countryMap.put("kenya", "KE");
        countryMap.put("south africa", "ZA");
        countryMap.put("united states", "US");
        countryMap.put("canada", "CA");
        countryMap.put("united kingdom", "GB");
        countryMap.put("germany", "DE");
        countryMap.put("france", "FR");
        countryMap.put("italy", "IT");
        countryMap.put("spain", "ES");
        countryMap.put("brazil", "BR");
        countryMap.put("argentina", "AR");
        countryMap.put("mexico", "MX");
        countryMap.put("china", "CN");
        countryMap.put("japan", "JP");
        countryMap.put("india", "IN");
        countryMap.put("australia", "AU");
        countryMap.put("new zealand", "NZ");
        countryMap.put("egypt", "EG");
        countryMap.put("uganda", "UG");
        countryMap.put("tanzania", "TZ");
        countryMap.put("rwanda", "RW");
        countryMap.put("cameroon", "CM");
        countryMap.put("zimbabwe", "ZW");
        countryMap.put("ethiopia", "ET");
        countryMap.put("morocco", "MA");
        countryMap.put("senegal", "SN");
        countryMap.put("zambia", "ZM");
        countryMap.put("sudan", "SD");
        countryMap.put("liberia", "LR");
        countryMap.put("sierra leone", "SL");
        countryMap.put("ivory coast", "CI");
        countryMap.put("democratic republic of the congo", "CD");
        countryMap.put("congo", "CG");
    }

    public static String toCountryCode(String locationInput) {
        if (locationInput == null) return null;
        return countryMap.get(locationInput.trim().toLowerCase());
    }
}
