package pl.edu.pjwstk.zad2;

import java.util.List;

public interface Vector {

    public List<Double> addVectors(List<Double> w1, List<Double> w2) throws VectorException;
    public List<Double> subVectors(List<Double> w1, List<Double> w2) throws VectorException;
    public void set(List<Double> lista);
    public List<Double> get();

}