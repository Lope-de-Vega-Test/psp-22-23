/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Arrays;

/**
 *
 * @author adrian
 */
public class Main {
    public static void main(String[] args) {
        try{
            
            ProcessBuilder myProcess = new ProcessBuilder("java", "ArgComparator.java", Arrays.toString(args)/* (Incluye corchetes y comas) + Arrays.toString(args)*/);
            Process p = myProcess.start();
            /*
            BufferedReader br = p.inputReader();
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            */            
            p.waitFor();
            System.out.println(p.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
