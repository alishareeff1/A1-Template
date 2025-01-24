package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class MazeRunner {
    private char[][] maze;
    public MazeRunner(char[][] maze){
        this.maze = maze;
    }

    public ArrayList<String> GenerateSequence(){
        ArrayList<String> sequence = new ArrayList<>();
        int entrance = 0;
        int rows = maze.length;
        int cols = maze[0].length;
        for(int i = 0; i < rows; i++){
            if(maze[i][1] == 'P'){
                entrance = i;
            }
        }
        for(int j = 0; j < cols; j++){
            if(maze[entrance][j] == 'P'){
                sequence.add("F");
            }
        }
        return sequence;
    }
}
