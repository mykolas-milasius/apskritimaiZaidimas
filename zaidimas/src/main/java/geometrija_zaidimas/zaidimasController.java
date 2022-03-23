package geometrija_zaidimas;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import geometrija_zaidimas.Apskritimas;
import geometrija_zaidimas.Zaidimas;

@Controller
public class zaidimasController
{
	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(name="name", required=false, defaultValue="World") String name,
			@RequestParam(name="sukurti_name", required=false, defaultValue="nesukurti") String sukurti,
			@RequestParam(name="in_x_id", required=false, defaultValue="0") Double x,
			@RequestParam(name="in_y_id", required=false, defaultValue="0") Double y,
			@RequestParam(name="in_radius_id", required=false, defaultValue="0") Double radius,
			@RequestParam(name="isvalyti_name", required=false, defaultValue="neisvalyti") String isvalyti,
 			Model model)
	{
		model.addAttribute("name", name);
		Zaidimas zaidimas = new Zaidimas();
		zaidimas.nuskaitymasApskritimu("data/duomenys.csv");
		ArrayList<Apskritimas> apskritimai = zaidimas.getApskritimai();
		if(sukurti.equals("sukurti"))
		{
			apskritimai.add(new Apskritimas(x, y, radius));
			zaidimas.pridetiZaidejoApskritimuKiekis();
			for (int i = 0; i < apskritimai.size(); i++)
			{
				boolean busena = apskritimai.get(i).arPersidengia(apskritimai.get(apskritimai.size()-1));
				System.out.println(busena);
				apskritimai.get(i).setBusena(busena);
			}
			zaidimas.issaugotiZaidejoApskritima();
		}
		else
		{
			if(isvalyti.equals("isvalyti"))
			{
				zaidimas.pasalintiApskritimus();
				//zaidimas.issaugotiApskritimus();
				//apskritimai = zaidimas.getApskritimai(); 
			}
			zaidimas.sukurtiApskritimus(15);
			zaidimas.issaugotiApskritimus();
		}
		zaidimas.nuskaitymasApskritimu("data/duomenys.csv");
		apskritimai = zaidimas.getApskritimai();
		model.addAttribute("apskritimai", apskritimai);
		return "greeting";
	}
}