package com.stys.platform.alerts;

import org.apache.commons.mail.Email;
import play.Plugin;

/** Base mailer plugin */
public interface MailerService {
    
    /** Send email */
    public void send(Email email);
}
