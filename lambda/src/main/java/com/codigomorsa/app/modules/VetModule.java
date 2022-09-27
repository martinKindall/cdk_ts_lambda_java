package com.codigomorsa.app.modules;

import com.codigomorsa.app.services.MyVetDatabase;
import com.codigomorsa.app.services.QueueSender;
import com.codigomorsa.app.services.SqsService;
import com.codigomorsa.app.services.VetDatabase;
import dagger.Binds;
import dagger.Module;

@Module
abstract public class VetModule {

    @Binds
    abstract VetDatabase vetDatabase(MyVetDatabase myVetDatabase);

    @Binds
    abstract QueueSender queue(SqsService sqsService);
}
