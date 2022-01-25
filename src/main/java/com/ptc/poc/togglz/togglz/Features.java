package com.ptc.poc.togglz.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum Features implements Feature {

    @Label("Using special characters")
    @EnabledByDefault
    USING_SPECIAL_CHARACTERS;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
