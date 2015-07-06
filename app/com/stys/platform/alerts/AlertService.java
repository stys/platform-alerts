package com.stys.platform.alerts;

/** Generic abstract alerts service */
public interface AlertService<A> {

    /** Send alert */
    public void alert(A alert);
}
