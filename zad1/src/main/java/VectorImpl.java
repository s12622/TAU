import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector {

    List<Double> wektor = new ArrayList<Double>();

    List<Double> v = new ArrayList<Double>();

    public List<Double> addVectors(List<Double> w1, List<Double> w2) throws VectorException {

        //asserts
        assert w1.size() == w2.size() : "Wektory musza miec ta sama ilosc parametrow";
        assert !w1.isEmpty() : "Wektor pierwszy jest pusty";
        assert !w2.isEmpty() : "Wektor drugi jest pusty";

        //exceptions
        if(w1.isEmpty()) throw new VectorException("Wektor pierwszy jest pusty");
        if(w2.isEmpty()) throw new VectorException("Wektor drugi jest pusty");
        if(w1.size()!=w2.size()) throw new VectorException("Wektory musza miec ta sama ilosc parametrow");
        else {
            for(int i=0; i<w1.size(); i++)
            {
                wektor.add(w1.get(i) + w2.get(i));
            }
            return wektor;
        }
    }

    public List<Double> get() {
        return wektor;
    }

    public void set(List<Double> lista) {
        assert lista.size() > 0: "Lista nie moze byc pusta";
        wektor = lista;
    }
}

