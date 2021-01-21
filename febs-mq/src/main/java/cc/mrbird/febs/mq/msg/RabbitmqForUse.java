package cc.mrbird.febs.mq.msg;

import java.lang.annotation.*;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RabbitmqForUse {

    String value() default "";
}
