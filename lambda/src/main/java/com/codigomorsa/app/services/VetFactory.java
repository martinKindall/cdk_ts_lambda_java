package com.codigomorsa.app.services;

import com.codigomorsa.app.modules.VetModule;
import dagger.Component;

@Component(modules = VetModule.class)
public interface VetFactory {
    Vet init();
}
