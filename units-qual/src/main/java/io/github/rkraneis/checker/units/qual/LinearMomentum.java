package io.github.rkraneis.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.units.qual.UnitsRelations;
import org.checkerframework.checker.units.qual.UnknownUnits;
import org.checkerframework.framework.qual.SubtypeOf;

/**
 * Units of linear momentum, such as newton seconds (@{@link Ns}).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf({UnknownUnits.class})
@UnitsRelations(UnitsRelationsExtra.class)
public @interface LinearMomentum {
}
