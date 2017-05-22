import java.io.*;

public class GetIPRangeFromListOfIPAddresses {
    public static void main( String[] args ) throws FileNotFoundException, IOException {

        File file = new File("src/main/black list of IP.csv");

        BufferedReader br = new BufferedReader ( new InputStreamReader(new FileInputStream( file ), "UTF-8"));
        BufferedWriter outfile = new BufferedWriter(new FileWriter("output.txt"));

        String line = null;
        while ((line = br.readLine()) != null) {
            //variable line does NOT have new-line-character at the end
            
            System.out.println(line+"-"+line+", \n");
            outfile.write(line+"-"+line+", \n");
        }
        br.close();
        outfile.close();
    }

}