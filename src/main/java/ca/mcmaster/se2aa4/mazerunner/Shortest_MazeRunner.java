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
    public void find_entrance(){
        System.out.println("starting find entrance method");
        entrance = -1;
        int entrance_count = 0;
        int rows = maze.length;
        for(int i = 0; i < rows; i++){
            if(maze[i][0] == 'P'){
                entrance = i;
                entrance_count++;
            }
        }
        if(entrance == -1){
            throw new IllegalStateException("No entrance found in first column");
        }
        if(entrance_count > 1){
            throw new IllegalStateException("There can only be one entrance");
        }
        System.out.println(entrance);
    }

}
