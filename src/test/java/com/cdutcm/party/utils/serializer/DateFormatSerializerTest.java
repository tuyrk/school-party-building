package com.cdutcm.party.utils.serializer;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateFormatSerializerTest {

    @Test
    public void dateFormat() {
        Date date = new Date();
        String formatDate = DateFormat.getDateInstance(DateFormat.YEAR_FIELD).format(date);
        System.out.println("formatDate = " + formatDate);
    }

}