public class Validator {
    public static double validate(boolean[] target, boolean [] pretiction, String name){
        int numberOfErrors = 0;
        for(int i=0; i<target.length; i++){
            if(target[i]!=pretiction[i]){
                numberOfErrors++;
            }
        }
        System.out.println("\nAlgorytm "+ name);
        System.out.println("Ilość błędów: " + numberOfErrors);
        int numberOfGoodPredictions = target.length - numberOfErrors;
        System.out.println("Ilość poprawnych predykcji: " + numberOfGoodPredictions);
        float correctness = (((float)numberOfGoodPredictions)/((float)target.length) )* 100;
        System.out.println("Poprawność predykcji: " + correctness +" %");
        return correctness;
    }
}
