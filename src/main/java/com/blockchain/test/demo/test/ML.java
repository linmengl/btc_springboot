package com.blockchain.test.demo.test;

public enum  ML {
    ML;

    public void print(){
        System.out.println("枚举单例");
    }
}

class MLTest{
    public static void main(String[] args) {
        ML ml = ML.ML;
        ML ml2 = ML.ML;
        System.out.println(ml==ml2);
        ml.print();

        Meng meng = Meng.getMeng();
    }
}

class Meng{
    private static class Lin{
        private static Meng meng = new Meng();
    }

    private Meng(){
        System.out.println("meng..Constructor.....");
    }

    public static Meng getMeng(){
        return Lin.meng;
    }
}
