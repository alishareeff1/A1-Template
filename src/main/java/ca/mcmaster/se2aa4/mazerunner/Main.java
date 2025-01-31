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
        options.addOption("p", "path", true, "Optional user-provided path sequence");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("Missing required option: -i");
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

            Shortest_MazeRunner sequence = new Shortest_MazeRunner(mazeArray);
            sequence.find_entrance();
            sequence.find_exit();

            logger.info("**** Computing path");
            String computedSequence = sequence.Add_Sequence();
            logger.info("Sequence of moves is: " + computedSequence);

            if (cmd.hasOption("p")) {
                String userSequence = cmd.getOptionValue("p");
                if (computedSequence.equals(userSequence)) {
                    logger.info("Correct path entered!");
                } else {
                    logger.info("Incorrect path entered.");
                }
            }

            logger.info("** End of MazeRunner");
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }
    }
}
