import java.io.*;
import java.util.Scanner;

public class Lanzador {

	public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("CMD.exe");
        pb.start();
    }
}