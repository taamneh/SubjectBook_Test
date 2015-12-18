package Models;
public class BarPercentage
{
    public double stressed;
    public double normal;
    public double relaxed;

    public BarPercentage(double rel,double nor, double stre)
    {
        relaxed = rel;
        normal= nor;
        stressed= stre;
    }
}