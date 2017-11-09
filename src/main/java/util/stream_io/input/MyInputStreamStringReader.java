package util.stream_io.input;

import java.io.InputStream;

public class MyInputStreamStringReader extends MyInputStreamReader {
    public MyInputStreamStringReader(InputStream input) {
        super(input);
    }

    public String read() {
        return getScanner().next();
    }
}
