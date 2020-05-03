package com.sarfaraz.covid19_india;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void datediff() throws ParseException {
        final String lastUpdate = "02/05/2020 10:58:49";
        DateFormat format = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        Date d1 = format.parse(lastUpdate);
        Date d2 = new Date();
        long diff = d2.getTime() - d1.getTime();
        long diffSec = diff / 1000;
        long minutes = (diffSec / 60);
        long seconds = diffSec % 60;
        long hours = minutes / 60;
        System.out.println("The difference is " + hours + " hours and " + minutes % 60 + " minutes and " + seconds + " seconds.");
        System.out.println(String.format("%02d:%02d:%02d", hours, minutes % 60, seconds));

    }
}