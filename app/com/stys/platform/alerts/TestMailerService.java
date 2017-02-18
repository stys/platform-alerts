package com.stys.platform.alerts;

import org.apache.commons.mail.EmailException;
import play.Application;
import org.apache.commons.mail.Email;
import play.Configuration;
import play.Logger;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;

/** Test mailer service sends all emails to a test email receiver */
public class TestMailerService extends MainMailerService {
    
    private Application application;
    
    private static final String TO_KEY = "test.to";

    private InternetAddress _receiver;

    @Inject
    public TestMailerService(Application application) {
        super(application);
        this.application = application;
        Logger.info("Picked {}", TestMailerService.class.getSimpleName());
    }
    
    @Override
    public void onStart() {
        /* Additional configuration here */
        Configuration conf = this.application.configuration().getConfig(MAILER_KEY);
        String email = conf.getString(TO_KEY);
        try {
            this._receiver = new InternetAddress(email);
        } catch (AddressException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void send(Email email) {
        try {
            email.setTo(Arrays.asList(this._receiver));
            super.send(email);
        } catch (EmailException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
