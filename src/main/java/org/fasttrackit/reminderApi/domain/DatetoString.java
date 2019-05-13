package org.fasttrackit.reminderApi.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetoString {

    public Date converter(String stringDate) throws ParseException {
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date = index.parse(stringDate);
        return date;
    }
}
