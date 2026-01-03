public class arr {
    public static  int countValidSelections(int[] nums) {
        int n = nums.length;
        int curr=0;

        for (int i = 0; i < n; i++) {
            if(nums[i]== 0){
                if(valid_direction(nums, i, -1,n)){
                curr++;
                }

                if (valid_direction(nums, i, 1, n)) {
                    curr++;
                }

            }
            
        }
        
        return curr;
    }
    public static  boolean valid_direction(int[] nums, int start, int dir, int n){
        int[] arr = nums.clone();
        
        int cuur = start;
        int dirr = dir;

        while(cuur >=0 && cuur < n){
            if(arr[cuur]==0){
                cuur+=dirr;
            }else{
                arr[cuur]--;
                dirr=-dirr;
                cuur+=dirr;
            }
        }

            for (int x : arr) {
            if (x != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] num={1,0,2,0,3};
        System.out.println(countValidSelections(num));
        
    }
}
