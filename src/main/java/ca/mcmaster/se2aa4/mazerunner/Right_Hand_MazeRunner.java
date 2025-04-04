package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Right_Hand_MazeRunner extends MazeRunner {
    private char[][] maze;
    private int entrance;
    private int exit_row;
    private int exit_col;
    private List<MazeObserver> observers = new ArrayList<>();

    public Right_Hand_MazeRunner(char[][] maze){
        super(maze);
        this.maze = maze;
    }

    public void addObserver(MazeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(MazeObserver observer){
        observers.remove(observer);
    }

    protected void notifyObservers(String move){
        for (MazeObserver observer : observers){
            observer.update(move);
        }
    }

    public void find_entrance(){
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
    }

    public void find_exit(){
        exit_row = -1;
        int exit_count = 0;
        exit_col = maze[0].length - 1;
        int rows = maze.length;
        for(int i = 0; i < rows; i++){
            if(maze[i][exit_col] == 'P'){
                exit_row = i;
                exit_count++;
            }
        }
        if(exit_row == -1){
            throw new IllegalStateException("No entrance found in first column");
        }
        if(exit_count > 1){
            throw new IllegalStateException("There can only be one exit");
        }
    }

    public int getEntrance() {
        return entrance;
    }

    public int getExitRow(){
        return exit_row;
    }

    public String Add_Sequence() {
        StringBuilder sequence = new StringBuilder();
        int row = entrance;
        int col = 0;
        String direction = "right";
        try {
            while (true) {
                if (direction.equals("right")) {
                    if (maze[row + 1][col] == '#') {
                        if (maze[row][col + 1] == 'P') {
                            sequence.append("F");
                            notifyObservers("F");
                            col++;
                            if (col == exit_col) {
                                break;
                            }
                        } else {
                            if (maze[row - 1][col] == '#') {
                                direction = "left";
                                sequence.append("LL");
                                notifyObservers("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                col--;
                            } else {
                                direction = "up";
                                sequence.append("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                row--;
                            }
                        }
                    } else {
                        direction = "down";
                        sequence.append("R");
                        notifyObservers("R");
                        sequence.append("F");
                        notifyObservers("F");
                        row++;
                    }
                } else if (direction.equals("down")) {
                    if (maze[row][col - 1] == '#') {
                        if (maze[row + 1][col] == 'P') {
                            sequence.append("F");
                            notifyObservers("F");
                            row++;
                        } else {
                            if (maze[row][col + 1] == '#') {
                                direction = "up";
                                sequence.append("LL");
                                notifyObservers("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                row--;
                            } else {
                                direction = "right";
                                sequence.append("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                col++;
                                if (col == exit_col) {
                                    break;
                                }
                            }
                        }
                    } else {
                        direction = "left";
                        sequence.append("R");
                        notifyObservers("R");
                        sequence.append("F");
                        notifyObservers("F");
                        col--;
                    }
                } else if (direction.equals("left")) {
                    if (maze[row - 1][col] == '#') {
                        if (maze[row][col - 1] == 'P') {
                            sequence.append("F");
                            notifyObservers("F");
                            col--;
                        } else {
                            if (maze[row + 1][col] == '#') {
                                direction = "right";
                                sequence.append("LL");
                                notifyObservers("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                col++;
                                if (col == exit_col) {
                                    break;
                                }
                            } else {
                                direction = "down";
                                sequence.append("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                row++;
                            }
                        }
                    } else {
                        direction = "up";
                        sequence.append("R");
                        notifyObservers("R");
                        sequence.append("F");
                        notifyObservers("F");
                        row--;
                    }
                } else if (direction.equals("up")) {
                    if (maze[row][col + 1] == '#') {
                        if (maze[row - 1][col] == 'P') {
                            sequence.append("F");
                            notifyObservers("F");
                            row--;
                        } else {
                            if (maze[row][col - 1] == '#') {
                                direction = "down";
                                sequence.append("LL");
                                notifyObservers("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                row++;
                            } else {
                                direction = "left";
                                sequence.append("L");
                                notifyObservers("L");
                                sequence.append("F");
                                notifyObservers("F");
                                col--;
                            }
                        }
                    } else {
                        direction = "right";
                        sequence.append("R");
                        notifyObservers("R");
                        sequence.append("F");
                        notifyObservers("F");
                        col++;
                        if (col == exit_col) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return sequence.toString(); 
    }
}
