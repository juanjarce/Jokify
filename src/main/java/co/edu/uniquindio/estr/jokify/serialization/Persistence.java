package co.edu.uniquindio.estr.jokify.serialization;

import co.edu.uniquindio.estr.jokify.model.Store;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistence {

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Methods for binary Serialization ---------------------------------------------------------------------------------------------------------------------------------------------

    // saveBinaryResource()
    public static void serializeBinary(String filePath, Store myStore) {
        try {
            ObjectOutputStream writingToFile = new ObjectOutputStream(new FileOutputStream(filePath));
            writingToFile.writeObject(myStore);
            writingToFile.close();
        } catch (Exception ignored) {
        }
    }

    // loadBinaryResource()
    public static Store deserializeBinary(String filePath) {
        Store myStore = null;
        try {
            ObjectInputStream readingFile = new ObjectInputStream(new FileInputStream(filePath));
            myStore = (Store) readingFile.readObject();
            readingFile.close();
        } catch (Exception ignored) {
        }
        return myStore;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
