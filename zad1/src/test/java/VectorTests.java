
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.JUnit4;


@RunWith ( JUnit4. class )
public class VectorTests {

    @org.junit.Test
    public void testVector(){
        List<Double> wektor1 = new ArrayList<Double>();
        List<Double> wektor2 = new ArrayList<Double>();
        List<Double> wektor3 = new ArrayList<Double>();

        wektor1.add(5.62);
        wektor1.add(3.14);
        wektor1.add(2.30);

        wektor2.add(5.12);
        wektor2.add(3.29);
        wektor2.add(7.51);

        //suma wektorów wektor1, wektor2
        wektor3.add(10.74);
        wektor3.add(6.43);
        wektor3.add(9.81);

        assertEquals(wektor3, vector.add(wektor1, wektor2));


    }
