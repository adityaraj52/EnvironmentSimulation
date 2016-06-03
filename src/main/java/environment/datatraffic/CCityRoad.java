package environment.datatraffic;



import org.apache.commons.lang3.tuple.Pair;


/**
 * Created by adityaraj on 03.06.16.
 */
public class CCityRoad implements IDefaultRoad<Double>
{
    @Override
    public Pair<Double, Double> getCoordinates()
    {
        return null;
    }

    /*
        @set coordinates using bottom left and top right coordinates of a rectangle
     */
    void setCoordinates( final Double p_bl, final Double p_tr )
    {

    }

    /*
        @set coordinates of a road
        @param Get cooridinates in pairs
     */
    /*void setCoordinates( final Pair p_pair )
    {
        this( (Double) p_pair.getLeft(), (Double)p_pair.getRight() );
    }
    */

}
