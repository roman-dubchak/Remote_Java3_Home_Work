package Lesson6_Log;

public class MethodsHW {
    int[] arr;

    public MethodsHW(int[] arr){
        this.arr = arr;
    }

    public MethodsHW(){
    }


    public int[] arraysFor(int[] arr)  {
        int[] arrAfterFor = new int [0];
        int j = 0;
        for (int i = 0; i < arr.length -1 ; i++) {
            if (arr[i] == 4) {
                arrAfterFor = new int[arr.length - 1 - i];
                System.arraycopy(arr, i+1,arrAfterFor,0,arr.length -1 - i);
            }
        }
        return arrAfterFor;
    }

    public boolean arryaChekNumber (int [] arr)  {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                for (int j = 0; j < arr.length; j++){
                    if(arr[j] == 4) return true;
                }
            }
        }
        return false;
    }
}