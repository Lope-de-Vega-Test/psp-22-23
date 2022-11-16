
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.System.Logger;
import java.io.FileNotFoundException;

public class tarea_3 {
    public static void main(String[] args) {
        try {
            FileReader file1 = new FileReader(args[0]);
            FileReader file2 = new FileReader(args[1]);
            FileReader file3 = new FileReader(args[2]);
            FileReader file4 = new FileReader(args[3]);
            int caract = 0;
            FileReader fr;
            fr = new FileReader(args[0]);
            while (caract != -1) {
                caract = fr.read();
            }
        } catch (Exception ex) {

        }
        

    }

}
