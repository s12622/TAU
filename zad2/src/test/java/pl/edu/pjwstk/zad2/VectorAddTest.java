package pl.edu.pjwstk.zad2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.*;
import org.junit.runners.JUnit4;

@RunWith ( JUnit4. class )
public class VectorAddTest{

    Vector wektor = new VectorImpl();


    @org.junit.Test
    public void firstAddVectorTest() {
        List<Double> wektor1 = new ArrayList<Double>();
        List<Double> wektor2 = new ArrayList<Double>();
        List<Double> wektor3 = new ArrayList<Double>();

        wektor1.add(3.5);
        wektor1.add(1.2);
        wektor1.add(3.3);

        wektor2.add(14.5);
        wektor2.add(9.5);
        wektor2.add(3.2);

        //suma wektorów wektor1, wektor2
        wektor3.add(18.0);
        wektor3.add(10.7);
        wektor3.add(6.5);

        try {
            assertEquals(wektor3, wektor.addVectors(wektor1, wektor2));
        } catch (VectorException e) {
//            e.printStackTrace();
            assertFalse(true);
        }

    }

    @org.junit.Test
    public void secondAddVectorTest() {
        List<Double> wektor1 = new ArrayList<Double>();
        List<Double> wektor2 = new ArrayList<Double>();
        List<Double> wektor3 = new ArrayList<Double>();

        wektor1.add(2.1);
        wektor1.add(4.2);
        wektor1.add(3.3);

        wektor2.add(12.5);
        wektor2.add(3.5);
        wektor2.add(6.2);

        //suma wektorów wektor1, wektor2
        wektor3.add(14.6);
        wektor3.add(7.7);
        wektor3.add(9.5);

        try {
            assertEquals(wektor3, wektor.addVectors(wektor1, wektor2));
        } catch (VectorException e) {
//            e.printStackTrace();
            assertFalse(true);
        }

    }
}