public class Viterbi {
    Sequence sequence;
    private double[][] v;
    private boolean [] throwIsCheated;
    private int l;

    public Viterbi(Sequence sequence){
        this.sequence = sequence;
        this.l = sequence.getL();
        this.v = new double[2][this.l];
        this.throwIsCheated = new boolean[this.l];
    }

    public boolean[] predict(){
        double fairDiceProp = sequence.getFairDiceStartPropability();
        v[Dice.FAIR.getIndex()][0] = fairDiceProp;
        v[Dice.LOADED.getIndex()][0] = 1 - fairDiceProp;
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        double[][] allProb = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        double fair, loaded;
        for(int i =1; i<l; i++){
            //l=0 fair
            fair = v[Dice.FAIR.getIndex()][i-1] * (1-changeDiceProp[Dice.FAIR.getIndex()]);
            loaded = v[Dice.LOADED.getIndex()][i-1] * changeDiceProp[Dice.LOADED.getIndex()];
            if (fair > loaded) {
                v[Dice.FAIR.getIndex()][i] = allProb[Dice.FAIR.getIndex()][observation[i]-1] *fair;
            }
            else {
                v[Dice.FAIR.getIndex()][i] = allProb[Dice.FAIR.getIndex()][observation[i]-1] *loaded;
            }
            //l=1 loaded
            fair = v[Dice.FAIR.getIndex()][i-1] * changeDiceProp[Dice.FAIR.getIndex()];
            loaded = v[Dice.LOADED.getIndex()][i-1] *(1-changeDiceProp[Dice.LOADED.getIndex()]);
            if (fair > loaded) {
                v[Dice.LOADED.getIndex()][i] = allProb[Dice.LOADED.getIndex()][observation[i]-1] *fair;
            }
            else {
                v[Dice.LOADED.getIndex()][i] = allProb[Dice.LOADED.getIndex()][observation[i]-1] *loaded;
            }
            if(v[Dice.LOADED.getIndex()][i] > v[Dice.FAIR.getIndex()][i])
                throwIsCheated[i]=true;
        }
        return throwIsCheated;
    }
}
