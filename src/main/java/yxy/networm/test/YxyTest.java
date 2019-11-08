package yxy.networm.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YxyTest {

    public void testReg(String str){
        String reg=".*(煤炭|煤电).*";
        // 首先要编译正则规则形式
        Pattern p = Pattern.compile(reg);
        // 将正则进行匹配
        Matcher m = p.matcher(str);
        // 进行判断
        boolean b = m.matches();

        System.out.println("b==="+b);

        System.out.println("str.matches=="+str.matches(reg));
    }

    public static void main(String[] args) {
        new YxyTest().testReg("70年来，我国建成了门类齐全的现代装备制造体系，装备制造业实现了跨越式发展。");
    }
}
