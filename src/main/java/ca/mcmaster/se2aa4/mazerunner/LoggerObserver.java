package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerObserver implements MazeObserver {
    private static final Logger logger = LogManager.getLogger(LoggerObserver.class);

    @Override
    public void update(String move) {
        logger.info("MazeRunner made move: " + move);
    }
}