import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    private double[] probability;
    private double changeDiceProbability;
    private int numberOfSides = 6;

    public FileReader(String path){
        probability  = new double[numberOfSides];
        readDataFromFile(path);
    }

    private void readDataFromFile(String path) {
        try {
            Scanner input = new Scanner(new File(path));
            for(int i=0; i<numberOfSides; i++){
                if(input.hasNextLine()){
                    String[] s = input.nextLine().split("/");
                    double a = Double.parseDouble(s[0]);
                    double b = Double.parseDouble(s[1]);
                    probability[i] = a/b;
                }
            }
            if(input.hasNextLine()){
                changeDiceProbability = Double.parseDouble(input.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double[] getProbability() {
        return probability;
    }

    public double getChangeDiceProbability() {
        return changeDiceProbability;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}
