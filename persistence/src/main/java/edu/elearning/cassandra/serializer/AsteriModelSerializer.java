package edu.elearning.cassandra.serializer;

import edu.elearning.se.AsteriModel;

import java.io.*;
import java.nio.ByteBuffer;

public class AsteriModelSerializer {

    public static ByteBuffer serialize(AsteriModel entity) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(entity);
            return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            //TODO : log it
        }

        return ByteBuffer.wrap(new byte[]{});
    }

    public static Object deserialize(ByteBuffer blob) {
        Object ob = null;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(blob.array())) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ob = objectInputStream.readObject();
        } catch (IOException e) {
            //TODO : log it
        } catch (ClassNotFoundException e) {

        }
        return ob;
    }
}
