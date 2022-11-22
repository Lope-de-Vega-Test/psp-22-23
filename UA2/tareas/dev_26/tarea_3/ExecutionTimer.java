class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public long getelapsedTime() {
        timeElapsed = endTime - startTime;
        return timeElapsed;
    }

    public void printElapsedTime()    
    {
        getelapsedTime();
        System.out.println("Tiempo de ejecución en nanosegundos: " + timeElapsed);
        System.out.println("Tiempo de ejecución en milisegundos: " + timeElapsed / 1000000);
    }
}
