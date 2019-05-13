import java.util.Random;

public class Sequence {
    private int l;
    private int[] sequence;
    private boolean [] throwIsCheated;
    private double[][] allPropability;
    private double[][] allPropabilityRange;
    private double[] changeDiceProbability;
    private int numberOfSides;
    private double fairDiceStartPropability;

    public Sequence(int l, double fairDiceStartPropability){
        this.l = l;
        this.sequence = new int[l];
        this.throwIsCheated = new boolean[l];
        changeDiceProbability = new double[2];
        this.fairDiceStartPropability = fairDiceStartPropability;
        GenerateSequence();
    }

    private void GenerateSequence(){
        fillPropabilityData();
        int diceIndex=0;
        Random generator = new Random();
        int fairStart = generator.nextInt(100);
        if(fairDiceStartPropability*100 < fairStart){
            diceIndex= Dice.LOADED.getIndex();
        }
        for(int j =0; j<l; j++){
            if(diceIndex==Dice.LOADED.getIndex()){
                throwIsCheated[j] =true;
            }
            int randomThrow = generator.nextInt(100);
            for(int intex=0; intex<numberOfSides; intex++){
                if(randomThrow<allPropabilityRange[diceIndex][intex]){
                    sequence[j]= intex+1;
                    break;
                }
            }
            int changeDice = generator.nextInt(100);
            if(changeDice<changeDiceProbability[diceIndex]*100){
                diceIndex = (diceIndex+1)%2;
            }
        }
    }

    private void fillPropabilityData(){
        allPropability = new double[2][numberOfSides];
        FileReader fairProp = new FileReader("src/fairProbability.txt");
        allPropability[Dice.FAIR.getIndex()] = fairProp.getProbability();
        changeDiceProbability[Dice.FAIR.getIndex()]= fairProp.getChangeDiceProbability();

        FileReader loadedProp = new FileReader("src/loadedProbability.txt");
        allPropability[Dice.LOADED.getIndex()] = loadedProp.getProbability();
        changeDiceProbability[Dice.LOADED.getIndex()]= loadedProp.getChangeDiceProbability();

        numberOfSides = fairProp.getNumberOfSides();

        allPropabilityRange = new double[2][numberOfSides];
        allPropabilityRange[Dice.FAIR.getIndex()][0] = allPropability[Dice.FAIR.getIndex()][0]*100;
        for(int j = 1; j<numberOfSides; j++){
            allPropabilityRange[Dice.FAIR.getIndex()][j]= allPropabilityRange[Dice.FAIR.getIndex()][j-1]+ allPropability[Dice.FAIR.getIndex()][j]*100;
        }
        allPropabilityRange[Dice.LOADED.getIndex()][0] = allPropability[Dice.LOADED.getIndex()][0]*100;
        for(int j = 1; j<numberOfSides; j++){
            allPropabilityRange[Dice.LOADED.getIndex()][j]= allPropabilityRange[Dice.LOADED.getIndex()][j-1]+ allPropability[Dice.LOADED.getIndex()][j]*100;
        }
    }

    public int getL() {
        return l;
    }

    public int[] getSequence() {
        return sequence;
    }

    public boolean[] getThrowIsCheated() {
        return throwIsCheated;
    }

    public double getFairDiceStartPropability() {
        return fairDiceStartPropability;
    }

    public double[][] getAllPropability() {
        return allPropability;
    }

    public double[] getChangeDiceProbability() {
        return changeDiceProbability;
    }

}
