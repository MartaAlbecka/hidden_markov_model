public class Aposteriori {
    private Sequence sequence;
    private int l;
    private double[][] f;
    private double[][] b;
    private double p;
    private boolean[] prediction;

    public Aposteriori(Sequence sequence){
        this.sequence = sequence;
        this.l = sequence.getL();
        this.f = new double[2][this.l];
        this.b = new double[2][this.l];
        this.prediction = new boolean[l];
    }

    public boolean[] predict(){
        forward();
        backward();
        for(int i=0; i<l; i++){
            if((f[Dice.FAIR.getIndex()][i]*b[Dice.FAIR.getIndex()][i])
                    <(f[Dice.LOADED.getIndex()][i]*b[Dice.LOADED.getIndex()][i])){
                prediction[i]=true;
            }
        }
        return prediction;
    }
    private void forward(){
        double fairDiceProp = sequence.getFairDiceStartPropability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        f[Dice.FAIR.getIndex()][0] = fairDiceProp;
        f[Dice.LOADED.getIndex()][0] = 1 - fairDiceProp;
        for(int i =1; i<l; i++){

            for(int l =0; l<2; l++){
                f[l][i] =0;
                for(int k =0; k<2; k++) {
                    double change;
                    if(k==l)
                        change = 1-changeDiceProp[k];
                    else
                        change = changeDiceProp[k];
                    f[l][i] += f[k][i-1] * change;
                }
                f[l][i] *= allProp[l][observation[i]-1];
            }
        }

    }

    private void backward(){
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        b[Dice.FAIR.getIndex()][l-1] = 1;
        b[Dice.LOADED.getIndex()][l-1] = 1;
        for(int i = l-2; i>=0; i--) {
            for (int k = 0; k < 2; k++) {
                b[k][i] = 0;
                for (int l = 0; l < 2; l++) {
                    double change;
                    if(k==l)
                        change = 1-changeDiceProp[k];
                    else
                        change = changeDiceProp[k];
                    b[k][i] += change * allProp[l][observation[i + 1] - 1] * b[l][i + 1];
                }
            }
        }

    }
   /* private void forward(){
        double fairDiceProp = sequence.getFairDiceStartPropability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        f[Dice.FAIR.getIndex()][0] = fairDiceProp;
        f[Dice.LOADED.getIndex()][0] = 1 - fairDiceProp;
        double sum;
        for(int i = 1; i <l; i++){
            //l=0 fair
            sum=f[Dice.FAIR.getIndex()][i-1]*(1-changeDiceProp[Dice.FAIR.getIndex()] )+
                    f[Dice.LOADED.getIndex()][i-1]*changeDiceProp[Dice.LOADED.getIndex()];
            f[Dice.FAIR.getIndex()][i] = allProp[Dice.FAIR.getIndex()][observation[i]-1]*sum;
            //l=1 loaded
            sum=f[Dice.FAIR.getIndex()][i-1]*changeDiceProp[Dice.FAIR.getIndex()]+
                    f[Dice.LOADED.getIndex()][i-1]*(1-changeDiceProp[Dice.LOADED.getIndex()]);
            f[1][i]=allProp[1][observation[i]-1]*sum;
        }
        p = f[Dice.FAIR.getIndex()][l-1] + f[Dice.LOADED.getIndex()][l-1];
    }

    private void backward(){
        double[] changeDiceProp = sequence.getChangeDiceProbability();
        double[][] allProp = sequence.getAllPropability();
        int [] observation = sequence.getSequence();
        b[Dice.FAIR.getIndex()][l-1] = 1;
        b[Dice.LOADED.getIndex()][l-1] = 1;
        for(int i = l-2; i>=0; i--){
            //k=0 fair
            b[0][i] = (1-changeDiceProp[Dice.FAIR.getIndex()])*allProp[Dice.FAIR.getIndex()][observation[i+1]-1]* b[Dice.FAIR.getIndex()][i+1]+
                    changeDiceProp[Dice.FAIR.getIndex()] * allProp[Dice.FAIR.getIndex()][observation[i+1]-1]* b[Dice.FAIR.getIndex()][i+1];
            //k=1 loaded
            b[1][i] = changeDiceProp[Dice.LOADED.getIndex()] * allProp[Dice.LOADED.getIndex()][observation[i+1]-1]* b[Dice.LOADED.getIndex()][i+1]+
                    (1-changeDiceProp[Dice.LOADED.getIndex()]) * allProp[Dice.LOADED.getIndex()][observation[i+1]-1]* b[Dice.LOADED.getIndex()][i+1];
        }
        double p2 = (1-changeDiceProp[0])*allProp[0][observation[0]-1]*b[0][0]+
                changeDiceProp[0]*allProp[1][observation[0]-1]*b[1][0];
    }*/
}
