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
        // logger.info("** Starting Maze Runner");

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
            // logger.info("Reading the maze from file: " + mazeFilePath);

            Maze maze = Maze.getInstance(mazeFilePath);
            char[][] mazeArray = maze.getMaze();
            Right_Hand_MazeRunner sequence = new Right_Hand_MazeRunner(mazeArray);
            sequence.addObserver(new LoggerObserver());
            sequence.find_entrance();
            sequence.find_exit();
            String computedSequence = sequence.Add_Sequence();
            Factorized_Path factorized = new Factorized_Path(computedSequence);
            String seq = factorized.Compress_Moves();

            System.out.println("Sequence of moves is: " + seq);

            if (cmd.hasOption("p")) {
                String userSequence = cmd.getOptionValue("p");
                if (seq.equals(userSequence)) {
                    logger.info("Correct path entered!");
                } else {
                    logger.info("Incorrect path entered.");
                }
            }

            // logger.info("** End of MazeRunner");
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }
    }
}
