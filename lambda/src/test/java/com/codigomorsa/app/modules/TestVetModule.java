package com.codigomorsa.app.modules;

import com.codigomorsa.app.services.*;
import dagger.Binds;
import dagger.Module;

@Module
abstract public class TestVetModule {

    @Binds
    abstract VetDatabase vetDatabase(TestVetDatabase testVetDatabase);

    @Binds
    abstract QueueSender queue(MockSqsService mockSqsService);
}
