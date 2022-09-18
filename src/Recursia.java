public class Recursia {

    static void from1ToN(int number){
        if (number==1)
            System.out.print(number);
        else {
            from1ToN(number - 1);
            System.out.print(number);
        }
    }
    static int fromAtoB(int a, int b){
        if (a==b)
            return a;
        else if (a > b){
            fromAtoB(a,b+1);
            return b;
        }
        else{
            fromAtoB(a,b-1);
            return b;
        }
    }
    static int fAkkerman(int m, int n){
        if (m==0)
            return n+1;
        else
            if (n==0){
                return fAkkerman(m-1, 1);
            }
            else{
                return fAkkerman(m-1, fAkkerman(m, n - 1));
        }
    }
    static String ftwo( int n){
        if (n % 2 != 0)
            return "NO";
        if (n - 2 == 0)
            return "YES";
        return ftwo(n/2);
    }
    static int sumDigital(int n){

        if (n / 10 == 0)
            return n % 10;

        return sumDigital(n/10);
    }

    public static void main(String[] args) {
        System.out.println(sumDigital(17));
    }


}
