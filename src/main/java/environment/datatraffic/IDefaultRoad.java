package environment.datatraffic;

import org.apache.commons.lang3.tuple.Pair;


/**
 * Created by adityaraj on 03.06.16.
 */
public interface IDefaultRoad<T>
{
    /*
        @return the co-ordinates of a road as pair
     */
    Pair<T, T> getCoordinates();
}
