public class matches {
    public static void main(String[] args){
        String regex = "\\w{4,16}";

        String regex2 = "\\d{17}(/d|x|X)";

        int sum = 2025;
        int i = 0;
        while( sum > 0 ){
            i++;
            int j = 5;


            if( i % 2 == 0){
                j += 2;
            }else{
                j += 15;
            }


            if( i % 3 == 0){
                j += 7;
            }else if( i % 3 == 1){
                j += 2;
            }else{
                j += 10;
            }

            sum -= j;
        }
        System.out.println(i);
    }
}
