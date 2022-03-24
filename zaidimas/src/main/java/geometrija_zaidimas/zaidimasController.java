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
			zaidimas.naujasApskritimas(x, y, radius); //apskritimai.add(new Apskritimas(x, y, radius));
			zaidimas.pridetiZaidejoApskritimuKiekis();
			zaidimas.nustatytiBusenas();
			//zaidimas.issaugotiZaidejoApskritima();
		}
		else
		{
			if(isvalyti.equals("isvalyti"))
			{
				zaidimas.pasalintiApskritimus();
			}
			if(apskritimai.size() == 0) // zaidimas.arTuriApskritimu() metoda
			{
				zaidimas.pasalintiApskritimus();
				zaidimas.sukurtiApskritimus(15);
				zaidimas.issaugotiApskritimus();
			}
		}
		model.addAttribute("apskritimai", zaidimas.getApskritimai());
		return "greeting";
	}
}