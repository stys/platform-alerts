package com.stys.platform.alerts;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import play.Application;
import play.Configuration;
import play.Plugin;
import play.libs.Akka;
import scala.concurrent.duration.FiniteDuration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/** Mailer plugin */
public class MainMailerService extends Plugin implements MailerService {

    protected static final String MAILER_KEY = "com.stys.platform.alerts.mailer";
    private static final String HOST_KEY = "host";
    private static final String PORT_KEY = "port";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";
    private static final String SSL_KEY = "ssl";
    private static final String FROM_KEY = "from";
    
    /** SMTP host */
    private String _host;
    
    /** SMTP port */
    private Integer _port;
    
    /** SMTP user */
    private String _user;
    
    /** SMTP password */
    private String _password;

    /** SMTP ssl */
    private Boolean _ssl;
    
    /** Sender email */
    private String _from;

    /** Conventional plugin constructor */
    @Inject
    public MainMailerService(Application application) {

        // Read configuration
        Configuration conf = application.configuration().getConfig(MAILER_KEY);
        this._host = conf.getString(HOST_KEY);
        this._port = conf.getInt(PORT_KEY);
        this._user = conf.getString(USER_KEY);
        this._password = conf.getString(PASSWORD_KEY);
        this._ssl = conf.getBoolean(SSL_KEY);
        this._from = conf.getString(FROM_KEY);
        
        // Log plugin startup
        play.Logger.info("Picked {}", this.getClass().getSimpleName());
        
    }

    /** Send email using connection settings */
    @Override
    public void send(final Email email){
        
        // Fix connection settings
        email.setHostName(this._host);
        email.setSmtpPort(this._port);
        email.setAuthentication(this._user, this._password);
        email.setSSLOnConnect(this._ssl);
        email.setCharset(EmailConstants.UTF_8);
        
        try {
            email.setFrom(this._from);
        } catch (EmailException ex) {
            play.Logger.error("Error sending email", ex);
        }
        
        // Async send
         Akka.system().scheduler().scheduleOnce(
            new FiniteDuration(1L, TimeUnit.SECONDS),
            new Runnable() {
                @Override
                public void run() {
                    try {
                        email.send();
                    } catch (EmailException ex) {
                        play.Logger.error("Error sending email", ex);
                    }
                }
            },
            Akka.system().dispatcher()
         );
        
    }

}
