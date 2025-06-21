package org.mosesidowu.empirezone.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.mosesidowu.empirezone.EmpireZoneAppApplication;
import org.mosesidowu.empirezone.exception.EmpireZoneAppException;
import org.mosesidowu.empirezone.exception.InvalidPhoneNumberException;

public class PhoneNumberValidator {

    public static String validatePhone(String rawPhone, String countryCode) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(rawPhone, countryCode.toUpperCase());

            if (!phoneUtil.isValidNumber(number)) {
                throw new InvalidPhoneNumberException("Invalid phone number for country: " + countryCode);
            }

            // Normalize to international E.164 format: +2348012345678
            return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (EmpireZoneAppException | NumberParseException e) {
            throw new InvalidPhoneNumberException("Invalid phone number format: " + e.getMessage());
        }
    }
}
