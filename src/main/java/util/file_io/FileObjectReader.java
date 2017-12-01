package util.file_io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileObjectReader<T> extends FileObjectOperator {
    private static final Logger log = LogManager.getLogger(FileObjectReader.class);
    public FileObjectReader(String file) {
        super(file);
    }

    public T read() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
        )) {
            return (T) ois.readObject();
        } catch (FileNotFoundException e) {
            log.error("Error while reading from file", e);
        } catch (IOException e) {
            log.error("Error while reading from file", e);
        } catch (ClassNotFoundException e) {
            log.error("Error while reading from file", e);
        }
        return null;
    }
}
