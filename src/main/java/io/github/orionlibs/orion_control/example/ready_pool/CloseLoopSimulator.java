package io.github.orionlibs.orion_control.example.ready_pool;

import io.github.orionlibs.orion_simulation.chart.Chart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CloseLoopSimulator
{
    public static void main(String[] args) throws IOException
    {
        Buffer sys = new Buffer(10, 5);
        Controller c = new Controller(1.25, 0.01);
        int y = 0;
        int iterations = 5000;
        List<Double> timeSteps = new ArrayList<>();
        List<Double> objectsInBuffer = new ArrayList<>();
        for(int i = 0; i < iterations; i++)
        {
            timeSteps.add((double)i);
            int r = setPoint(i);
            int error = r - y;
            double numberOfObjectsToAddToTheReadyPool = c.work(error);
            y = sys.work(numberOfObjectsToAddToTheReadyPool);
            objectsInBuffer.add((double)y);
            //System.out.println("i = " + i + "--r = " + r + "--error = " + error + "--numberOfObjectsToAddToTheReadyPool = " + numberOfObjectsToAddToTheReadyPool + "--y = " + y);
        }


        double[] xData = timeSteps.stream().mapToDouble(d -> d).toArray();
        double[] yData = objectsInBuffer.stream().mapToDouble(d -> d).toArray();
        Chart.show2DPlot(xData, yData);
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
