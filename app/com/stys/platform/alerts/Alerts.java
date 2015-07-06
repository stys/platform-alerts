package com.stys.platform.alerts;

import play.Application;
import play.Logger;
import play.Plugin;

import java.util.List;

/** Alerts API */
public class Alerts extends Plugin {

    /** Singleton */
    private static Alerts instance;
    
    /** Instance of application */
    private Application application;

    /** Plugin constructor */
    public Alerts(Application application) {
        if (null == Alerts.instance) {
            this.application = application;
            Alerts.instance = this;
            Logger.info("Picked {}", Alerts.class.getSimpleName());
        } else {
            Logger.warn("Attempt to reinitialize singleton class {}", Alerts.class.getSimpleName());
        }
    }
    
    @Override
    public void onStart() {
        /* Additional configuration here */
    }
    
    /** Issue alert of specified class */
    public static <S> S getAlertService(Class<S> service) {
        return instance.application.plugin(service);
    }

}
