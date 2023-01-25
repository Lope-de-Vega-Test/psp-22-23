public class ua3tarea3 {    
    public static void main(String[] args) throws Exception {
        
        Thread.sleep(1000);

        ProcessBuilder myProcess = new ProcessBuilder("java", "Servidor.java");
        
        myProcess.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        myProcess.start();
        Thread.sleep(1000);
        myProcess = new ProcessBuilder("java", "Cliente.java", args[0]);
        myProcess.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        myProcess.start();

        
    }
}

