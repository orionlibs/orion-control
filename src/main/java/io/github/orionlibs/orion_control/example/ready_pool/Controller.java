package io.github.orionlibs.orion_control.example.ready_pool;

public class Controller
{
    private double errorMultiplier;
    private double cumulativeErrorMultiplier;
    private double cumulativeError;


    public Controller(double errorMultiplier, double cumulativeErrorMultiplier)
    {
        this.errorMultiplier = errorMultiplier;
        this.cumulativeErrorMultiplier = cumulativeErrorMultiplier;
    }


    public double work(int error)
    {
        cumulativeError += error;
        return (errorMultiplier * error) + (cumulativeErrorMultiplier * cumulativeError);
    }
}
