package ca.mcmaster.se2aa4.mazerunner;
public class Factorized_Path {
    private String s;

    public Factorized_Path(String s){
        this.s = s;
    }

    public String Compress_Moves(){
        StringBuilder compressed = new StringBuilder();
        char currentChar = s.charAt(0);
        int count = 1;

        for(int i = 1; i < s.length(); i++ ){
            char c = s.charAt(i);
            if(c == currentChar){
                count++;
            }else{
                if(count > 1){
                    compressed.append(count).append(currentChar);
                }else{
                    compressed.append(currentChar);
                }
                currentChar = c;
                count = 1;
            }
        }

        if(count > 1){
            compressed.append(count).append(currentChar);
        }else{
            compressed.append(currentChar);
        }

        return compressed.toString();
    }
}
