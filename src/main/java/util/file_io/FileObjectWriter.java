package util.file_io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileObjectWriter<T> extends FileObjectOperator {

    private static final Logger log = LogManager.getLogger(FileObjectWriter.class);

    public FileObjectWriter(String file) {
        super(file);
    }

    public void write(T object){
        try(
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(file)
                )
        ) {
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            log.error("Error while writing to file", e);
        } catch (IOException e) {
            log.error("Error while writing to file", e);
        }
    }
}
