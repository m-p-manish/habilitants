package an.chopsticks.provider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation identifies the annotated method is an adjudication algorithm. The value of this annotation is the
 * corresponding adjudication algorithn's id. This annotation allows register 1 java method for multiple adjudication
 * algorithms.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdjudicationAlgorithm {
    String[] value() default {};
}