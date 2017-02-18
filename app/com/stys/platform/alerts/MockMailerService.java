package com.stys.platform.alerts;

import org.apache.commons.mail.Email;
import play.Application;
import play.Plugin;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;

/**
 * Maock mailer plugin
 */
public class MockMailerService extends Plugin implements MailerService {

    /** Conventional plugin constructor */
    @Inject
    public MockMailerService(Application application) {
        // Log plugin startup
        play.Logger.info("Picked {}", this.getClass().getSimpleName());
    }

    /** Send email using connection settings */
    @Override
    public void send(final Email email){
        
        // Write to log
        play.Logger.info(email.getSubject());
        for (InternetAddress a : email.getToAddresses()) {
            play.Logger.info(a.getAddress());
        }
        play.Logger.info(email.toString());
        
    }

}
