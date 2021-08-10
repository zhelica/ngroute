package lamda;

import lamda.impl.*;

/**
 * @Description:
 * @author Guoqianliang
 * @date 19:50 - 2021/2/15
 */
public class BasicSyntax {
    public static void main(String[] args) {
        // 1.实现无参数,无返回值的函数式接口
        NoneReturnNoneParameter lambda1 = () -> {
            System.out.println("这是无参,无返回值的方法");
        };
        lambda1.test();

        // 2.实现一个参数,无返回值的函数式接口
        NoneReturnSingleParameter lambda2 = (int a) -> {
            System.out.println("这是一个参数,无返回值的方法,参数a:" + a);
        };
        lambda2.test(10);

        // 3.实现多个参数,无返回值的函数式接口
        NoneReturnMutipleParameter lambda3 = (int a, int b) -> {
            System.out.println("这是多个参数,无返回值的方法,参数a=" + a + ",b=" + b);
        };
        lambda3.test(10, 20);

        // 4.实现无参数,有返回值有返回值的函数式接口
        SingleReturnNoneParameter lambda4 = () -> {
            System.out.println("这是无参数,有返回值的方法,返回值是:");
            return 10;
        };
        System.out.println(lambda4.test());

        // 5.实现一个参数,有返回值的函数式接口
        SingleReturnSingleParameter lambda5 = (int a) -> {
            System.out.println("这是一个参数,有返回值的方法,返回值是:");
            return a;
        };
        System.out.println(lambda5.test(10));

        // 6.实现多个参数,有返回值的函数式接口
        SingleReturnMutipleParameter lambda6 = (int a, int b) -> {
            System.out.println("这是多个参数,有返回值的方法,返回值是:");
            return a + b;
        };
        System.out.println(lambda6.test(1, 2));
    }
}
