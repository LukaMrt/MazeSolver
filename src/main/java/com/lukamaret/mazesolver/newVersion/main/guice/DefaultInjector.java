package com.lukamaret.mazesolver.newVersion.main.guice;

import com.google.inject.AbstractModule;
import com.lukamaret.mazesolver.newVersion.infrastructure.FilePath;
import com.lukamaret.mazesolver.newVersion.infrastructure.MazeDimension;

public class DefaultInjector extends AbstractModule {

    private final MazeDimension mazeDimension;

    public DefaultInjector(MazeDimension mazeDimension) {
        this.mazeDimension = mazeDimension;
    }

    @Override
    protected void configure() {
        bind(MazeDimension.class).toInstance(mazeDimension);
        bind(FilePath.class).toInstance(new FilePath(mazeDimension.linesCount() + "x" + mazeDimension.columnsCount() + ".txt"));
    }

}
