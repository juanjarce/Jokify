package co.edu.uniquindio.estr.jokify.serialization.threads;

import co.edu.uniquindio.estr.jokify.model.Store;

public class SaveBinaryResource extends Thread{
    @Override
    public void run() {
        Store.serializeBinary();
    }
}
