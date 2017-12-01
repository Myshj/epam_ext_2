package util.stream_operators.input;

import util.stream_io.input.MyInputStreamStringReader;

import java.io.InputStream;

/**
 * Base class for all objects that use Input streams as data/command source.
 */
public abstract class InputStreamOperator {
    /**
     * Stream to read data/commands from.
     */
    private InputStream input;

    /**
     * Reader to read from stream with.
     */
    private MyInputStreamStringReader reader;

    /**
     * Current state of operator.
     */
    private boolean working;

    /**
     * Parametrized constructor.
     * @param input Stream to read data/commands from.
     */
    public InputStreamOperator(InputStream input) {
        this.input = input;
        reader = new MyInputStreamStringReader(input);
    }

    /**
     * Returns stream to read data/commands from.
     * @return Stream to read data/commands from.
     */
    protected InputStream getInput() {
        return input;
    }

    /**
     * Returns reader to read from stream with.
     * @return Reader to read from stream with.
     */
    protected MyInputStreamStringReader getReader() {
        return reader;
    }

    /**
     * Reads next string from stream.
     * Blocking operation.
     * @return Read string.
     */
    protected String readString() {
        return reader.read();
    }

    /**
     * Runs this operator.
     */
    public void run() {
        working = true;
        onStarted();
        while (working) {
            onMessage(readString());
        }
    }

    /**
     * Called every times operator started.
     */
    protected abstract void onStarted();

    /**
     * Called when operator receives message.
     * @param message Received message.
     */
    protected abstract void onMessage(String message);

    /**
     * Stops operator.
     */
    public void stop() {
        working = false;
    }
}
