public class Calculator
{
    private double result = 0;

    public double getResult()
    {
        return result;
    }

    public void setResult(double result)
    {
        this.result = result;
    }

    public double add(double a, double b)
    {
        return a + b;
    }

    public double subtract(double a, double b)
    {
        return a - b;
    }

    public double multiply(double a, double b)
    {
        return a * b;
    }

    public double divide(double dividend, double divisor)
    {
        return dividend / divisor;
    }
}
