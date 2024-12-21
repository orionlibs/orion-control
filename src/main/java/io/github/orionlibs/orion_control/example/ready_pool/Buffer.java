package io.github.orionlibs.orion_control.example.ready_pool;

import io.github.orionlibs.orion_mathematics.RandomNumberGenerator;

public class Buffer
{
    private int numberOfQueuedObjects;
    private int readyPool;
    private int maximumNumberOfReadyPool;
    private int maximumNumberOfProcessedObjects;
    private double averageNumberOfProcessedObjects;


    public Buffer(int maximumNumberOfReadyPool, int maximumNumberOfProcessedObjects)
    {
        this.maximumNumberOfReadyPool = maximumNumberOfReadyPool;
        this.maximumNumberOfProcessedObjects = maximumNumberOfProcessedObjects;
        this.averageNumberOfProcessedObjects = maximumNumberOfProcessedObjects / 2.0;
    }


    public int work(double numberOfObjectsToAddToTheReadyPool)
    {
        addObjectsToReadyPool(numberOfObjectsToAddToTheReadyPool);
        transferFromReadyPoolToQueue();
        processObjectsFromQueue();
        return numberOfQueuedObjects;
    }


    private void addObjectsToReadyPool(double numberOfObjectsToAddToTheReadyPool)
    {
        numberOfObjectsToAddToTheReadyPool = Math.max(0, Math.round(numberOfObjectsToAddToTheReadyPool));
        numberOfObjectsToAddToTheReadyPool = Math.min(numberOfObjectsToAddToTheReadyPool, maximumNumberOfProcessedObjects);
        readyPool += numberOfObjectsToAddToTheReadyPool;
    }


    private void transferFromReadyPoolToQueue()
    {
        int r = RandomNumberGenerator.generateInteger(Math.abs(readyPool));
        readyPool -= r;
        numberOfQueuedObjects += r;
    }


    private void processObjectsFromQueue()
    {
        int r = RandomNumberGenerator.generateInteger(maximumNumberOfReadyPool);
        r = Math.min(r, numberOfQueuedObjects);
        numberOfQueuedObjects -= r;
    }
}
