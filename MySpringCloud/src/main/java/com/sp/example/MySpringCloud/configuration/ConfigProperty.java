package com.sp.example.MySpringCloud.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class ConfigProperty {
    private int minium;
    private int maximum;

    public ConfigProperty() {
    }

    public ConfigProperty(int minium, int maximum) {
        this.minium = minium;
        this.maximum = maximum;
    }

    public int getMinium() {
        return minium;
    }

    public void setMinium(int minium) {
        this.minium = minium;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
