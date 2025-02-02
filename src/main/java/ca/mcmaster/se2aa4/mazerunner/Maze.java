package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Maze {
    private char[][] maze;
    private String MazeFilePath;

    public Maze(String MazeFilePath){
        this.MazeFilePath = MazeFilePath;
    }

    public char[][]makeArray(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MazeFilePath));
            List<char[]> mazeList = new ArrayList<>();
            String line;
            line = reader.readLine();
            int width = line.length();
            line = line.replace(' ', 'P');
            mazeList.add(line.toCharArray());
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    line = "P".repeat(width); 
                } else {
                    line = line.replace(' ', 'P');
                }
                char[] lineArray = new char[line.length()];
                for(int i = 0; i < lineArray.length; i++){
                    if(line.charAt(i) == ' '){
                        lineArray[i] = 'P';
                    }else{
                        lineArray[i] = line.charAt(i);
                    }
                }
                mazeList.add(lineArray);
                
            }
            reader.close();

            char[][] maze = new char[mazeList.size()][];
            mazeList.toArray(maze);

            return maze;
            
        }  catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
                return null;
        }
        
    }
}
