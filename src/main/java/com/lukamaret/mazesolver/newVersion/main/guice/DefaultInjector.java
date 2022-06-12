package com.lukamaret.mazesolver.newVersion.main.guice;

import com.google.inject.AbstractModule;
import com.lukamaret.mazesolver.newVersion.infrastructure.FileName;

public class DefaultInjector extends AbstractModule {

    private final FileName fileName;

    public DefaultInjector(FileName fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void configure() {
        bind(FileName.class).toInstance(fileName);
    }

}
