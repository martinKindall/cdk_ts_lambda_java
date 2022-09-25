package com.codigomorsa.app.modules;

import com.codigomorsa.app.services.TestVetDatabase;
import com.codigomorsa.app.services.VetDatabase;
import dagger.Binds;
import dagger.Module;

@Module
abstract public class TestVetModule {

    @Binds
    abstract VetDatabase vetDatabase(TestVetDatabase testVetDatabase);
}
