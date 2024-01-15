package org.feather.skill.service;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.GateWayApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: PredicateTest
 * @author: feather
 * @description: java8 使用方法与思想
 * @since: 15-Jan-24 8:53 PM
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = GateWayApplication.class)
@RunWith(SpringRunner.class)
public class PredicateTest {
    public static List<String> MICRO_SERVICE= Arrays.asList("nacos",
            "authority", "gateway","ribbon","feign","hystrix","skill");

    /**
     * description: 主要用于参数符不符合规则，返回值是boolean
     **/
    @Test
    public  void  testPredicateTest(){
        Predicate<String> letterLengthLimit=s->s.length()>5;
        MICRO_SERVICE.stream().filter(letterLengthLimit).forEach(System.out::println);
    }

    /**
     * description: and  方法等同于逻辑  && 存在短路特性，需要所有的条件都满足
     **/
    @Test
    public  void  testPredicateAnd(){
        Predicate<String>  letterLengthLimit=s->s.length()>5;
        Predicate<String>  letterStartWith=s->s.startsWith("gate");

        MICRO_SERVICE.stream().filter(letterLengthLimit.and(letterStartWith)).forEach(System.out::println);
    }


    /**
     * description: 等同于我们的逻辑或 ||, 多个条件主要一个满足即可
     **/
    @Test
    public void testPredicateOr() {

        Predicate<String> letterLengthLimit = s -> s.length() > 5;
        Predicate<String> letterStartWith = s -> s.startsWith("gate");

        MICRO_SERVICE.stream().filter(
                letterLengthLimit.or(letterStartWith)
        ).forEach(System.out::println);
    }

    /**
     * <h2>negate 等同于我们的逻辑非 !</h2>
     * */
    @Test
    public void testPredicateNegate() {

        Predicate<String> letterStartWith = s -> s.startsWith("gate");
        MICRO_SERVICE.stream().filter(letterStartWith.negate()).forEach(System.out::println);
    }

    /**
     * <h2>isEqual 类似于 equals(), 区别在于: 先判断对象是否为 NULL,
     * 不为 NULL 再使用 equals 进行比较</h2>
     * */
    @Test
    public void testPredicateIsEqual() {

        Predicate<String> equalGateway = s -> Predicate.isEqual("gateway").test(s);
        MICRO_SERVICE.stream().filter(equalGateway).forEach(System.out::println);
    }

}
