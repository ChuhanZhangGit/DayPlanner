package com.example.testjava;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("asdfas");

        LocalTime start = LocalTime.of( 8,30,0) ;
        LocalTime stop = LocalTime.of( 17 , 0 , 0 ) ;

        List<LocalTime> LocalTimes = new ArrayList<>();
        LocalTime LocalTime = start;
        while ( LocalTime.isBefore( stop ) ) {
            LocalTimes.add( LocalTime );
            // Set up the next loop.
            LocalTime = LocalTime.plusMinutes(30 );
        }

        for (LocalTime date : LocalTimes) {
            System.out.println(date.toString());
        }
    }
}
