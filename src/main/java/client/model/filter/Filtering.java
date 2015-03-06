package client.model.filter;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 * Created by SamsungEvo on 1/29/2015.
 */
public class Filtering {

    /*
       Computes Manhattan and Euclidean distances
       where r = 1, Manhattan distance
             r = 2, Euclidean Distance
             r= Integer.MAX_VALUE, Supremum Distance

    */
    public static double MinkowskiDistance(double [] x, double []y, int r,int n){

        return 0;
    }

    public static double pearsonsCoefficient(double [] x, double y []){
        return new PearsonsCorrelation().correlation(x, y);
    }

    public static double cosineSimilarity(double [] a, double [] b){
        ArrayRealVector vectorA = new ArrayRealVector(a);
        ArrayRealVector vectorB = new ArrayRealVector(b);
        return (vectorA.dotProduct(vectorB))/(magnitude(vectorA) * magnitude(vectorB));
    }

    private static double magnitude(RealVector a){
        return a.getNorm();
    }
}
