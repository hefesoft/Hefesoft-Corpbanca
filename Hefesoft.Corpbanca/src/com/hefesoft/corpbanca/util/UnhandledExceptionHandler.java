package com.hefesoft.corpbanca.util;

import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

public class UnhandledExceptionHandler implements UncaughtExceptionHandler {
 
    private UncaughtExceptionHandler _defaultUncaughtExceptionHandler;
 
    public UnhandledExceptionHandler() {
        _defaultUncaughtExceptionHandler = Thread
                .getDefaultUncaughtExceptionHandler();
    }
 
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        
        GMailSender mailsender = new GMailSender("hefesoft20101207@gmail.com", "Hefesoft1234!");

        String[] toArr = { "hefesoft20101207@gmail.com", "futbolito152@gmail.com" };
        mailsender.set_to(toArr);
        mailsender.set_from("hefesoft20101207@gmail.com");
        mailsender.set_subject("Error en corpbanca movil.");
        mailsender.setBody(ex.getMessage());
        
        Log.e("MailApp", "Could not send email", ex);
        
        try {

            if (mailsender.send()) {
         
            } else {
                
            }
        } catch (Exception e) {
            
            Log.e("MailApp", "Could not send email", e);
        }
        
        _defaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        
    }
 
}