package cc.mrbird.febs.test;

import org.springframework.util.Assert;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/10 9:26
 */
public class Test {
    public static void main(String[] args) {
        try {
            int num = 2;
            Assert.state(num == 1,"错误");
            System.out.println("wwwww");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
