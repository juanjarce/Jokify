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
    // Methods for serialization in XML files---------------------------------------------------------------------------------------------------------------------------------------

    // saveXMLResource()
    public static void serializeXML(String filePath, Store myStore) {
        try {
            XMLEncoder xmlEncoder;

            xmlEncoder = new XMLEncoder(new FileOutputStream(filePath));
            xmlEncoder.writeObject(myStore);
            xmlEncoder.close();
        } catch (Exception ignored) {
        }
    }

    // loadXMLResource()
    public static Store deserializeXML(String filePath) {
        XMLDecoder xmlDecoder;
        Store xmlObject = null;
        try {
            xmlDecoder = new XMLDecoder(new FileInputStream(filePath));
            xmlObject = (Store) xmlDecoder.readObject();
            xmlDecoder.close();
        } catch (Exception ignored) {
        }
        return xmlObject;
    }


}
