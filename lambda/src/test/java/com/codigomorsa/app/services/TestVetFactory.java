package com.codigomorsa.app.services;

import com.codigomorsa.app.modules.TestVetModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = TestVetModule.class)
public interface TestVetFactory extends VetFactory {

    TestVetDatabase testDb();
}
