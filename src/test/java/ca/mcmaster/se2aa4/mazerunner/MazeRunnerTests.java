package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MazeRunnerTests {
    private char[][] testMaze;
    private Right_Hand_MazeRunner mazeRunner;
    
    @BeforeEach
    void setUp() {
        testMaze = new char[][] {
            {'#', '#', '#', '#', '#'},
            {'#', '#', '#', 'P', 'P'},
            {'#', 'P', 'P', 'P', '#'},
            {'P', 'P', '#', '#', '#'},
            {'#', '#', '#', '#', '#'}
        };
        mazeRunner = new Right_Hand_MazeRunner(testMaze);
    }

    @Test
    void testMazeSingleton() {
        Maze maze1 = Maze.getInstance("maze.txt");
        Maze maze2 = Maze.getInstance("maze.txt");
        assertSame(maze1, maze2, "Maze should be a singleton");
    }

    @Test
    void testFindEntrance() {
        mazeRunner.find_entrance();
        assertEquals(3, mazeRunner.getEntrance(), "Entrance should be at row index 3");
    }

    @Test
    void testFindExit() {
        mazeRunner.find_exit();
        assertEquals(1, mazeRunner.getExitRow(), "Exit should be at row index 1");
    }

    @Test
    void testMazeRunnerMoves() {
        mazeRunner.find_entrance();
        mazeRunner.find_exit();
        String sequence = mazeRunner.Add_Sequence();
        assertNotNull(sequence, "Generated sequence should not be null");
        assertFalse(sequence.isEmpty(), "Generated sequence should not be empty");
    }
    
    @Test
    void testObserverNotification() {
        MazeObserver observer = Mockito.mock(MazeObserver.class);
        mazeRunner.addObserver(observer);
        mazeRunner.notifyObservers("F");
        Mockito.verify(observer, Mockito.times(1)).update("F");
    }

    @Test
    void testFactorizedPathCompressionSimple() {
        Factorized_Path fp = new Factorized_Path("FFFF");
        assertEquals("4F", fp.Compress_Moves(), "Compression should be 4F");
    }

    @Test
    void testFactorizedPathCompressionComplex() {
        Factorized_Path fp = new Factorized_Path("FFFRFF");
        assertEquals("3FR2F", fp.Compress_Moves(), "Compression should be 3FR2F");
    }
    @Test
    void testFactorizedPathCompressionNoRepeat() {
        Factorized_Path fp = new Factorized_Path("FRLF");
        assertEquals("FRLF", fp.Compress_Moves(), "Compression should return the same sequence when no moves are repeated");
    }

    @Test
    void testLoggerObserver() {
        LoggerObserver loggerObserver = new LoggerObserver();
        assertDoesNotThrow(() -> loggerObserver.update("F"), "Logger should not throw exceptions");
    }

    @Test
    void testMultipleEntrancesOrExits() {
        char[][] invalidMaze = {
            {'#', '#', '#', '#', '#'},
            {'P', 'P', '#', 'P', 'P'}, 
            {'#', 'P', '#', 'P', '#'},
            {'P', 'P', '#', 'P', 'P'},
            {'#', '#', '#', '#', '#'}
        };

        Right_Hand_MazeRunner runner = new Right_Hand_MazeRunner(invalidMaze);
        
        Exception entranceException = assertThrows(IllegalStateException.class, runner::find_entrance, "Should throw an exception for multiple entrances");
        assertEquals("There can only be one entrance", entranceException.getMessage());

        Exception exitException = assertThrows(IllegalStateException.class, runner::find_exit, "Should throw an exception for multiple exits");
        assertEquals("There can only be one exit", exitException.getMessage());
    }

}
