package zsc;

//算法初体验
public class Test01 {
    public static void main(String[] args) {
        sum1(100);
    }

    //定义一个方法，计算从1到n的累加和
    public static void sum1(int n){
        int sum = 0;
        for (int i = 1;i<=n;i++){
            sum += i;
        }
        System.out.println(sum);
    }

    //高斯
    public static void sum2(int n){
        int sum = n*(n+1)/2;
        System.out.println(sum);
    }

    public static void method1(int n){
        int i = 1;
        int count = 0;
        while (i<=n){
            i = i * 2;
            count++;
        }
        System.out.println("count = " + count);
    }

    public static void method2(int n){
        int count = 0;
        int s = 0;
        while (s<n){
            count++;
            s = s + count;
        }
        System.out.println("count = "+count);
    }

    public static void method3(int n){
        int count = 0;
        for (int i = 1;i <= n;i++){
            for (int j = 1;j<=n;j++){
                count++;
            }
        }
        System.out.println("count = "+count);
    }
}
