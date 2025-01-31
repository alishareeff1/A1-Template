package ca.mcmaster.se2aa4.mazerunner;

public class Shortest_MazeRunner extends MazeRunner {
    private char[][] maze;
    private int entrance;
    private int exit_row;
    private int exit_col;
    public Shortest_MazeRunner(char[][] maze){
        super(maze);
        this.maze = maze;
    }
    

}
