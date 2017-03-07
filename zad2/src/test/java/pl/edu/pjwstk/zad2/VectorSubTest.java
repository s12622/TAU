package pl.edu.pjwstk.zad2;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.*;
import org.junit.runners.JUnit4;

@RunWith( JUnit4. class )
public class VectorSubTest{

    Vector wektor = new VectorImpl();

    @org.junit.Test
    public void firstSubVectorTest() throws VectorException {
        List<Double> wektor1 = new ArrayList<Double>();
        List<Double> wektor2 = new ArrayList<Double>();
        List<Double> wektor3 = new ArrayList<Double>();

        wektor1.add(18.0);
        wektor1.add(10.0);
        wektor1.add(6.0);

        wektor2.add(14.5);
        wektor2.add(8.0);
        wektor2.add(3.2);

        //różnica wektorów wektor1, wektor2
        wektor3.add(3.5);
        wektor3.add(2.0);
        wektor3.add(2.8);

        assertEquals(wektor3, wektor.subVectors(wektor1, wektor2));


    }

    @org.junit.Test
    public void secondSubVectorTest() throws VectorException{
        List<Double> wektor1 = new ArrayList<Double>();
        List<Double> wektor2 = new ArrayList<Double>();
        List<Double> wektor3 = new ArrayList<Double>();

        wektor1.add(14.0);
        wektor1.add(7.0);
        wektor1.add(9.0);

        wektor2.add(12.5);
        wektor2.add(3.5);
        wektor2.add(6.2);

        //różnica wektorów wektor1, wektor2
        wektor3.add(1.5);
        wektor3.add(3.5);
        wektor3.add(2.8);

        assertEquals(wektor3, wektor.subVectors(wektor1, wektor2));


    }
}