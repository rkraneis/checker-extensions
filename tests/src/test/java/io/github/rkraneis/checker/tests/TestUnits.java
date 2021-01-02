package io.github.rkraneis.checker.tests;

import static io.github.rkraneis.checker.units.UnitsToolsExtra.Hz;
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

public class TestUnits {
    public void testFrequency() {
        @s double time = 0.2 * s;
        @Hz double frequency = 5.0 * Hz;
        double actual = time * frequency;
        
        assert actual == 1.0 : "actual " + actual;
    }
    
    public void testForce() {
        @kg double mass = 0.2 * kg;
        @mPERs2 double acceleration = 5.0 * mPERs2;
        @N double force = mass * acceleration;
                
        assert force == 1.0 : "force " + force;
    }
    
    public void testMomentum() {
        @kg double mass = 0.2 * kg;
        @mPERs double velocity = 5.0 * mPERs;
        @Ns double momentum = mass * velocity;
                
        assert momentum == 1.0 : "momentum " + momentum;
    }
    
    public void testAll() {
        @Ns double momentum = 5.0 * Ns;
        @N double force = 0.2 * N;
        @s double time = momentum / force;
        assert time == 25.0 * s : "time " + time;
        
        @Hz double frequency = force / momentum;
        assert frequency == 0.04 * Hz : "frequency " + frequency;
    }
}
