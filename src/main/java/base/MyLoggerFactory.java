package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLoggerFactory {
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }
}
