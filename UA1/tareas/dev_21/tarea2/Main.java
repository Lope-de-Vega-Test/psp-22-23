
public class Main {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "Tarea2ProcessBuilder.java");
        pb.redirectErrorStream(true);
        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
