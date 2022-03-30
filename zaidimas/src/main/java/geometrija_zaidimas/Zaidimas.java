package geometrija_zaidimas;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import geometrija_zaidimas.*;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class Zaidimas
{
	protected ArrayList<Apskritimas> apskritimai = new ArrayList<Apskritimas>();
	private boolean apskritimai_issaugoti = false;
	protected int zaidejo_apskritimu_kiekis = 0;
	static final double x_max = 400;
	static final double y_max = 400;
	static final double plotas_max = x_max * y_max;
	protected double taskai = 0;
	
	public Zaidimas(ArrayList<Apskritimas> apskritimai)
	{
		super();
		this.apskritimai = apskritimai;
	}
	
	public Zaidimas()
	{
		super();
	}
	
	public void issaugotiApskritimus()
	{
		FileWriter irasyti;
		try
		{
			irasyti = new FileWriter("data/duomenys.csv", false);
			for (int i = 0; i < apskritimai.size(); i++)
			{
				irasyti.write(""+apskritimai.get(i).getX()+",");// x
				irasyti.write(""+apskritimai.get(i).getY()+","); // y
				irasyti.write(""+apskritimai.get(i).getRadius()+","); //in.write(); // radius
				irasyti.write(""+apskritimai.get(i).getBusena()); // busena issaugoti
				irasyti.write("\n"); 
			}
			irasyti.close();
			apskritimai_issaugoti = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void issaugotiZaidejoApskritima()
	{
		FileWriter irasyti;
		try
		{
			irasyti = new FileWriter("data/duomenys.csv", true);
			irasyti.write(""+apskritimai.get(apskritimai.size()-1).getX()+",");
			irasyti.write(""+apskritimai.get(apskritimai.size()-1).getY()+",");
			irasyti.write(""+apskritimai.get(apskritimai.size()-1).getRadius()+",");
			irasyti.write(""+apskritimai.get(apskritimai.size()-1).getBusena());
			irasyti.write("\n");
			irasyti.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void pasalintiApskritimus()
	{
		try
		{
			File file = new File("data/duomenys.csv");
			file.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void sukurtiFaila()
	{
		try
		{
			File file = new File("data/duomenys.csv");
			file.createNewFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void nuskaitymasApskritimu(String file_kelias)
	{
		File file = new File(file_kelias);
		Scanner scanner;
		try
		{
			if(file.exists())
			{
				scanner = new Scanner(file);
				while (scanner.hasNextLine())
				{
					String informacija = scanner.nextLine();
					String padalinta[] = informacija.split(",");
					double x = Double.parseDouble(padalinta[0]);
					double y = Double.parseDouble(padalinta[1]);
					double radius = Double.parseDouble(padalinta[2]);
					boolean busena = Boolean.parseBoolean(padalinta[3]);
					apskritimai.add(new Apskritimas(x, y, radius, busena));
				}
				scanner.close();
			}
			else
			{
				sukurtiFaila();
				sukurtiApskritimus(15);
				issaugotiApskritimus();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean arYraIssaugoti()
	{
		return apskritimai_issaugoti;
	}
	
	public void sukurtiApskritimus(int kiekis)
	{
		for (int i=0; i < kiekis; i++)
		{
			apskritimai.add(new Apskritimas(-90, 90, -90, 90, 90));
		}
		issaugotiApskritimus();
	}
	
	public void nustatytiBusenas()
	{
		for (int i = 0; i < apskritimai.size(); i++)
		{
			if(i == apskritimai.size()-1) { break; }
			else
			{
				boolean busena = apskritimai.get(i).arPersidengia(apskritimai.get(apskritimai.size()-1));
				apskritimai.get(i).setBusena(busena);
			}
		}
		pasalintiApskritimus();
		sukurtiFaila();
		issaugotiApskritimus();
	}

	public ArrayList<Apskritimas> getApskritimai()
	{
		return apskritimai;
	}

	public int getZaidejoApskritimuKiekis()
	{
		return zaidejo_apskritimu_kiekis;
	}

	public void pridetiZaidejoApskritimuKiekis()
	{
		zaidejo_apskritimu_kiekis++;
	}
	
	public void naujasApskritimas(double x, double y, double radius)
	{
		if (x_max >= x && y_max >= y)
		{
			apskritimai.add(new Apskritimas(x, y, radius));
		}
	}
	
	public void suskaiciuotiPlotus()
	{
		for (int i = 0; i < apskritimai.size(); i++)
		{
			apskritimai.get(i).setPlotas();
			//System.out.println(apskritimai.get(i).getPlotas());
		}
	}
	
	public void taskai()
	{
		for (int i = 15; i < apskritimai.size(); i++)
		{
			for (int j = 1; j < 100; j++)
			{
				if(apskritimai.get(i).getPlotas() > (plotas_max/(j)))
				{
					taskai += apskritimai.get(i).getPlotas() * 1/j;
				}
			}
		}
		//System.out.println(plotas_max);
		//System.out.println("Taskai " + taskai);
	}
}