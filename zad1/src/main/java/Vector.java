import java.util.List;

public interface Vector {

    public List<Double> add(List<Double> w1, List<Double> w2) throws VectorException;
    public void set(List<Double> lista);
    public List<Double> get();

}
