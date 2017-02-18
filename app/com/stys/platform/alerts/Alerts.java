package com.stys.platform.alerts;

import play.Application;
import play.Logger;
import play.Play;
import play.Plugin;

import javax.inject.Inject;

/** Alerts API */
public class Alerts extends Plugin {

    /** Instance of application */
    private Application application;

    /** Plugin constructor */
    @Inject
    public Alerts(Application application) {
        this.application = application;
        Logger.info("Picked {}", Alerts.class.getSimpleName());
    }
    
    @Override
    public void onStart() {
        /* Additional configuration here */
    }
    
    /** Issue alert of specified class */
    public static <S> S getAlertService(Class<S> service) {
        return Play.application().plugin(service);
    }

}
