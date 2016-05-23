package datacollector;

/**
 * Created by adityaraj on 23.05.16.
 */

import java.net.*;
import java.io.*;

public class CReadData {

    String myurl;
    CReadData(String url) {
        myurl = url;
    }

    String readBufferedData() throws Exception{

        URL oracle = new URL(myurl);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine = "";

        while ((in.readLine()) != null)
            inputLine .concat(in.readLine());
        System.out.println(inputLine);
        in.close();
    return inputLine;
    }
}
