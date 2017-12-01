import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getRootLogger();
        logger.trace("Program started");
        new MainController().run();
    }
}
