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
				irasyti.write(""+apskritimai.get(i).getRadius()); //in.write(); // radius
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
			irasyti.write(""+apskritimai.get(apskritimai.size()-1).getRadius());
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
	
	public void nuskaitymasApskritimu(String file_kelias)
	{
		File file = new File(file_kelias);
		Scanner scanner;
		try
		{
			scanner = new Scanner(file);
			while (scanner.hasNextLine())
			{
				String informacija = scanner.nextLine();
				String padalinta[] = informacija.split(",");
				double x = Double.parseDouble(padalinta[0]);
				double y = Double.parseDouble(padalinta[1]);
				double radius = Double.parseDouble(padalinta[2]);
				apskritimai.add(new Apskritimas(x, y, radius));
			}
			scanner.close();
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
			apskritimai.add(new Apskritimas(-100, 100, -100, 100, 100));
		}
		issaugotiApskritimus();
	}
	/*
	public void setApskritimai(ArrayList <Apskritimas> apskritimu_array)
	{
		apskritimai = apskritimu_array;
	}
	*/
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
}