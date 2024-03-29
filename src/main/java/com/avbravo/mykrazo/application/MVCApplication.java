package com.avbravo.mykrazo.application;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.krazo.Properties;

@ApplicationPath("app")
public class MVCApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> defaultExtension = new HashMap<>();
        defaultExtension.put(Properties.DEFAULT_VIEW_FILE_EXTENSION, "jsp");
        return defaultExtension;
    }
}