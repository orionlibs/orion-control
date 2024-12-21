package io.github.orionlibs.orion_control.example.ready_pool;

public class OpenLoopSimulator
{
    public static void main(String[] args)
    {
        Buffer sys = new Buffer(10, 5);
        int iterations = 5000;
        int numberOfObjectsToAddToTheReadyPool = 5;

        for(int i = 0; i < iterations; i++)
        {
            int y = sys.work(numberOfObjectsToAddToTheReadyPool);
            //System.out.println("i = " + i + "--numberOfObjectsToAddToTheReadyPool = " + numberOfObjectsToAddToTheReadyPool + "--y = " + y);
        }
    }
}
