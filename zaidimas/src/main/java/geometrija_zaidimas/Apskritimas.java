package geometrija_zaidimas;

public class Apskritimas extends Figura
{
	protected double x;
	protected double y;
	protected double radius;
	protected boolean busena = false;
	protected double plotas;
	
	public Apskritimas(double x, double y, double radius, boolean busena)
	{
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.busena = busena;
	}
	
	public double getPlotas() {
		return plotas;
	}

	public Apskritimas(double x, double y, double radius)
	{
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public Apskritimas(double x_from, double x_till, double y_from, double y_till, double radius_max)
	{
		this.x = x_till - (x_till-x_from) * Math.random();
		this.y = y_till - (y_till-y_from) *  Math.random();
		this.radius = Math.random() * radius_max;
	}
	
	public void setPlotas()
	{
		plotas = 3.14 * radius * radius;
	}
	public double getX()
	{
		return x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public double atstumasNuoCentroKitoCentro( Apskritimas kitas )
	{
		double atstumas_x = x - kitas.x;
		double atstumas_y = y - kitas.y;
		//System.out.println("Tikrinamas apskritimas: " + x + " " + y + " " + radius);
		return Math.sqrt ( atstumas_x * atstumas_x + atstumas_y * atstumas_y );
	}
	
	public boolean arPersidengia ( Apskritimas kitas ) // pasiziureti
	{
		double atstumas = atstumasNuoCentroKitoCentro( kitas );
		//System.out.println("Suskaičiuotas atstumas: " + atstumas);
		return atstumas < (( radius + kitas.radius )/2);
	}

	public boolean getBusena()
	{
		return busena;
	}

	public void setBusena(boolean busena)
	{
		this.busena = busena;
	}
}
