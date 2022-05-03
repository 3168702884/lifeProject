package com.example.demo.until;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Timer;
public class TimerUntil {
    private String shangpin_ID;
    public TimerUntil(String shangpin_ID){
        this.shangpin_ID=shangpin_ID;
    }
    public void TimerStart(){
        Timer timer = new Timer(shangpin_ID);
        timer.schedule(new TimerTaskUntil(shangpin_ID), 1000*60*60*47);
    }

}
