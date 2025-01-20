package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input", true, "Path to the maze file");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")){
                logger.error("Misssing required position: -i");
                System.exit(1);
            }

            String mazeFilePath = cmd.getOptionValue("i");
            logger.info("Reading the maze from file: " + mazeFilePath);

            BufferedReader reader = new BufferedReader(new FileReader(mazeFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
            reader.close();

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
