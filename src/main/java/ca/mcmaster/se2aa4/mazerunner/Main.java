package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
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

            Maze maze = new Maze(mazeFilePath);
            char[][] mazeArray = maze.makeArray();
            if (mazeArray != null) {
                for (int i = 0; i < mazeArray.length; i++) {
                    for (int j = 0; j < mazeArray[i].length; j++) {
                        System.out.print(mazeArray[i][j]);
                    }
                    System.out.println();
                }
            }
            
            MazeRunner sequence = new MazeRunner(mazeArray);
            

            logger.info("**** Computing path");
            logger.info("sequence of moves is " + sequence.GenerateSequence());
            logger.info("** End of MazeRunner");
            

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
    }
}
