package io.github.rkraneis.checker.tests;

import static io.github.rkraneis.checker.units.UnitsToolsExtra.N;
import static io.github.rkraneis.checker.units.UnitsToolsExtra.Ns;
import io.github.rkraneis.checker.units.qual.Hz;
import io.github.rkraneis.checker.units.qual.N;
import io.github.rkraneis.checker.units.qual.Ns;
import static org.checkerframework.checker.units.UnitsTools.kg;
import static org.checkerframework.checker.units.UnitsTools.mPERs;
import static org.checkerframework.checker.units.UnitsTools.mPERs2;
import static org.checkerframework.checker.units.UnitsTools.s;
import org.checkerframework.checker.units.qual.kg;
import org.checkerframework.checker.units.qual.mPERs;
import org.checkerframework.checker.units.qual.mPERs2;
import org.checkerframework.checker.units.qual.s;

public class CompileUnits {

    // F = m a
    {
        @kg int mass = kg;
        @mPERs2 int acceleration = mPERs2;
        @N int force = mass * acceleration;
    }

    // m = F / a
    {
        @N int force = N;
        @mPERs2 int acceleration = mPERs2;
        @kg int mass = force / acceleration;
    }

    // a = F / m
    {
        @N int force = N;
        @kg int mass = kg;
        @mPERs2 int acceleration = force / mass;
    }

    // p = m v
    {
        @kg int mass = kg;
        @mPERs int velocity = mPERs;
        @Ns int momentum = mass * velocity;
    }

    // m = p / v
    {
        @Ns int momentum = Ns;
        @mPERs int velocity = mPERs;
        @kg int mass = momentum / velocity;
    }

    // a = F / m
    {
        @Ns int momentum = Ns;
        @kg int mass = kg;
        @mPERs int velocity = momentum / mass;
    }

    // t = p / F
    {
        @Ns int momentum = Ns;
        @N int force = N;
        @s int time = momentum / force;
    }
    // f = F / p
    {
        @N int force = N;
        @Ns int momentum = Ns;
        @Hz int frequency = force / momentum; // would anyone ever assign that?
    }
}
