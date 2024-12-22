package io.github.orionlibs.orion_control.example.pid_controller;

public class PIDController
{
    private double proportionalityConstant;
    private double integralConstant;
    private double derivativeConstant;
    private double cumulativeError;
    private double derivativeOfError;
    private double previousError;
    private double timestampOfLastStepInNanoseconds;


    public PIDController(double proportionalityConstant, double integralConstant, double derivativeConstant)
    {
        this.proportionalityConstant = proportionalityConstant;
        this.integralConstant = integralConstant;
        this.derivativeConstant = derivativeConstant;
    }


    public double work(double error)
    {
        double durationBetween2SuccessiveSteps = 0.0d;
        if(timestampOfLastStepInNanoseconds == 0.0d)
        {
            timestampOfLastStepInNanoseconds = System.nanoTime();
            cumulativeError += 0.000000001d * error;
            durationBetween2SuccessiveSteps = System.nanoTime() - 0.000000001d;
        }
        else
        {
            durationBetween2SuccessiveSteps = System.nanoTime() - timestampOfLastStepInNanoseconds;
            cumulativeError += durationBetween2SuccessiveSteps * error;
        }
        derivativeOfError = (error - previousError) / durationBetween2SuccessiveSteps;
        previousError = error;
        return (proportionalityConstant * error) + (integralConstant * cumulativeError) + (derivativeConstant * derivativeOfError);
    }
}
