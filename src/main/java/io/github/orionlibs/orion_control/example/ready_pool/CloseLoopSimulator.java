package io.github.orionlibs.orion_control.example.ready_pool;

public class CloseLoopSimulator
{
    public static void main(String[] args)
    {
        Buffer sys = new Buffer(10, 5);
        Controller c = new Controller(1.25, 0.01);
        int y = 0;
        int iterations = 5000;
        for(int i = 0; i < iterations; i++)
        {
            int r = setPoint(i);
            int error = r - y;
            double numberOfObjectsToAddToTheReadyPool = c.work(error);
            y = sys.work(numberOfObjectsToAddToTheReadyPool);
            //System.out.println("i = " + i + "--r = " + r + "--error = " + error + "--numberOfObjectsToAddToTheReadyPool = " + numberOfObjectsToAddToTheReadyPool + "--y = " + y);
        }
    }


    private static int setPoint(int i)
    {
        if(i < 100)
        {
            return 0;
        }
        else if(i < 300)
        {
            return 50;
        }
        else
        {
            return 10;
        }
    }
}
