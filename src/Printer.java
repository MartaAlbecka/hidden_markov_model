public class Printer {

    public static void printSequence(int[] sequence){
        System.out.print("\t\t");
        for(int i=0; i<sequence.length; i++) {
            System.out.print(sequence[i] + ", ");
        }
        System.out.println();
    }

    public static void printBoolean(boolean[] table, String name){
        System.out.print(name + "\t");
        for(int i=0; i<table.length; i++) {
            if(table[i]){
                System.out.print("L, ");
            }
            else {
                System.out.print("F, ");
            }
        }
        System.out.println();
    }


}
