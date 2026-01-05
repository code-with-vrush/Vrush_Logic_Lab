public class arr {
     public static  long maxMatrixSum(int[][] matrix) {

        int[][] num = matrix.clone();
        int max_sum=Integer.MAX_VALUE;

        for(int i=0;i< num.length;i++){
        for(int j=1;j<num[i].length;j++){
            int prv = num[i][j-1];
            if(prv < 0 && num[i][j] < 0){
                num[i][j-1]=prv*-1;
                num[i][j]= num[i][j]*-1;
            }
        }
       }
        
       max_sum=0;
       for(int i=0;i< num.length;i++){
        for(int j=0;j<num[i].length;j++){
          max_sum+=num[i][j];
        }


       }

        return max_sum;
        
    }
        public static void main(String[] args) {
        //int[][] num={{1,-1},{-1,1}};
        int[][] num={{1,2,3},{1,2,3},{1,2,3}};
        int max_sum=0;
       for(int i=0;i< num.length;i++){
        for(int j=0;j<num[i].length;j++){
          max_sum+=num[i][j];
        }
    }
        System.out.println(max_sum);

        
       System.out.println(maxMatrixSum(num));




        
        
    }
}
