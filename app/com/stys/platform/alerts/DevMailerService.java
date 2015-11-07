package com.stys.platform.alerts;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import play.Application;
import play.Configuration;
import play.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;

/**
 * Created by Dasha on 11/7/2015.
 */
public class DevMailerService extends MainMailerService {

    private Application application;

    private static final String TO_KEY = "test.to";

    private InternetAddress _receiver;

    public DevMailerService(Application application) {
        super(application);
        this.application = application;
        Logger.info("Picked {}", DevMailerService.class.getSimpleName());
    }

    @Override
    public void onStart() {
        if(application.isDev()) {
            /* Additional configuration here */
            Configuration conf = this.application.configuration().getConfig(MAILER_KEY);
            String email = conf.getString(TO_KEY);
            try {
                this._receiver = new InternetAddress(email);
            } catch (AddressException ex) {
                throw new RuntimeException(ex);
            }
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
