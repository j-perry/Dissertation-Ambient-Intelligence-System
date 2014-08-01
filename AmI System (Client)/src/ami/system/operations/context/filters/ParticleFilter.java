


package ami.system.operations.context.filters;

import java.util.Random;

/**
 *
 * @author Dan Chalmers, based on standard algorithm
 *
 * modified by Chris Kiefer, pervasive computing 2014
 */
public class ParticleFilter {

    double[][] particles;        // importance weights, values
    int particleCount = 100;
    int initialPValMin = 0;
    int initialPValMax = 600;
    double modelVariance;
    Random rnd;
    double resampleVariance = 30;

    public ParticleFilter(int particles, int minVal, int maxVal, double resampVariance) {
        particleCount = particles;
        initialPValMin = minVal;
        initialPValMax = maxVal;
        modelVariance = 0.4 / ((initialPValMax - initialPValMin) / particleCount);
        resampleVariance = resampVariance;
        initialParticles();
        rnd = new Random(System.currentTimeMillis());
    }

    public double estimate() {
        double sum;

        sum = 0;
        for (int i = 0; i < particleCount; i++) {
            sum += particles[0][i] * particles[1][i];
//            System.out.print(particles[1][i] + ",");
        }
//        System.out.println();
        return sum;
    }

    public void update(double sample) {
        resample(sample);
        normaliseWeights();
    }

    protected double[] prob(double sample) {
        double p1;
        double[] psample;
        double sum;

        psample = new double[particleCount];
        p1 = -Math.PI * Math.pow(modelVariance, 2);
        for (int i = 0; i < particleCount; i++) {
            psample[i] = modelVariance * Math.pow(Math.E, p1 * Math.pow(particles[1][i] - sample, 2));
        }

        sum = 0.0;
        for (int i = 0; i < particleCount; i++) {
            sum += psample[i];
        }
        if (sum > 0) {
            for (int i = 0; i < particleCount; i++) {
                psample[i] = psample[i] / sum;
            }
        }
        return psample;
    }

    protected void normaliseWeights() {
        double sum;

        sum = 0;
        for (int i = 0; i < particleCount; i++) {
            sum += particles[0][i];
        }
        for (int i = 0; i < particleCount; i++) {
            particles[0][i] = particles[0][i] / sum;
        }
    }

    protected void resample(double sample) {
        double[] wweights;
        double[][] newpweights;
        double initialWeight;
        double r, s;
        int j;

        wweights = prob(sample);
        initialWeight = 1.0 / particleCount;
        newpweights = new double[2][particleCount];
        for (int i = 0; i < particleCount; i++) {
            newpweights[0][i] = 0;
        }

        for (int i = 0; i < particleCount; i++) {
            r = Math.random();
            s = 0.0;
            j = 0;
            while (j < particleCount && s < r) {
                s += wweights[j];
                j++;
            }
            j--;
            newpweights[0][i] += wweights[j];
//            newpweights[1][i] = particles[1][j] + ((Math.random() * 60.0) - 30.0);
            //add some noise to the new sample
            newpweights[1][i] = particles[1][j] + (rnd.nextGaussian() * resampleVariance);

        }
        particles = newpweights;

    }

    private void initialParticles() {
        double initialWeight;
        double valueStep, value;

        valueStep = (initialPValMax - initialPValMin) / particleCount;
        value = initialPValMin;
        particles = new double[2][particleCount];
        initialWeight = ((double) 1.0 / particleCount);

        for (int i = 0; i < particleCount; i++) {
            particles[0][i] = initialWeight;
            particles[1][i] = value;
            value += valueStep;
        }
    }

    public String toString() {
        String s;
        s = "";
        /*
         for (int i=0; i<particleCount; i++) {
         s += (particles[0][i] + "*" + particles[1][i] + ", ");
         }
         */
        s += " est= " + estimate();
        return s;
    }
}
