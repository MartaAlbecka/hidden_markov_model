public class Main {
    public static void main(String[] args) {
        double viterbiAvg=0;
        double aposterioriAvg=0;
        int iterations = 1;
        for(int i=0; i<iterations; i++) {
            Sequence sequence = new Sequence(300, 1);
            Printer.printSequence(sequence.getSequence());
            Printer.printBoolean(sequence.getThrowIsCheated(), "Dice");
            Viterbi viterbi = new Viterbi(sequence);
            boolean[] vPrediction = viterbi.predict();
            Printer.printBoolean(vPrediction, "Viter");
            Aposteriori aposteriori = new Aposteriori(sequence);
            boolean[] aPrediction = aposteriori.predict();
            Printer.printBoolean(aPrediction, "Apost");
            viterbiAvg+=Validator.validate(sequence.getThrowIsCheated(), vPrediction, "Viterby");
            aposterioriAvg+=Validator.validate(sequence.getThrowIsCheated(), aPrediction, "Aposteriori");
        }
        System.out.println("\nŚrednia poprawność dla " + iterations +" prób");
        System.out.println("Viterbi: "+viterbiAvg/iterations);
        System.out.println("Aposteriori: "+aposterioriAvg/iterations);


    }
}
