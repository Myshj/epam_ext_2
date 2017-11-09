package util.stream_io.input;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Base class for all stream readers.
 */
public abstract class MyInputStreamReader {

    /**
     * Scanner to read elements with.
     */
    private Scanner scanner;

    /**
     * Parametrized constructor.
     * @param input Stream to read elements from.
     */
    public MyInputStreamReader(InputStream input) {
        scanner = new Scanner(input).useDelimiter("\n");
    }

    /**
     * Returns scanner to read elements with.
     * @return Scanner to read elements with.
     */
    public Scanner getScanner() {
        return scanner;
    }
}
