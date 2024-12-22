package io.github.orionlibs.orion_control.example.cache;

import io.github.orionlibs.orion_simulation.chart.Chart;
import java.util.ArrayList;
import java.util.List;

public class Cache
{
    public static void main(String[] args)
    {
        double setpointCacheHitRate = 0.6d;
        double gainFactor = 50;
        double output = 0.0d;
        double cumulativeError = 0.0d;
        List<Double> timeSteps = new ArrayList<>();
        List<Double> cacheHitRates = new ArrayList<>();
        for(int i = 0; i <= 200; i++)
        {
            timeSteps.add((double)i);
            double error = setpointCacheHitRate - output;
            cumulativeError += error;
            double correctiveAction = gainFactor * cumulativeError;
            output = getCacheHitRate(correctiveAction);
            cacheHitRates.add(output);
            //System.out.println("output = " + output + "--correctiveAction = " + correctiveAction);
        }
        double[] xData = timeSteps.stream().mapToDouble(d -> d).toArray();
        double[] yData = cacheHitRates.stream().mapToDouble(d -> d).toArray();
        Chart.show2DPlot("Sample Chart", "time steps", "cache hit rates", "y(x)", xData, yData);
    }


    private static double getCacheHitRate(double cacheSize)
    {
        if(cacheSize < 0)
        {
            return 0.0d;
        }
        else if(cacheSize > 100)
        {
            return 1.0d;
        }
        else
        {
            return cacheSize / 100.0d;
        }
    }
}
