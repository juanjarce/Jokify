package co.edu.uniquindio.estr.jokify.serialization.threads;

import co.edu.uniquindio.estr.jokify.model.Store;

public class LoadXMLResource extends Thread{
    @Override
    public void run() {
        Store.deserializeXML();
    }
}
