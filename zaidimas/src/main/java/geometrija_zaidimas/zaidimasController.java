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
		ArrayList<Apskritimas> apskritimai = new ArrayList<Apskritimas>();
		if(sukurti != null)
		{
			if(x != apskritimai.get(apskritimai.size()-1).getX() && y != apskritimai.get(apskritimai.size()-1).getY() && radius != apskritimai.get(apskritimai.size()-1).getRadius())
			{
				zaidimas.nuskaitymasApskritimu("data/duomenys.csv");
				apskritimai = zaidimas.getApskritimai();
				apskritimai.add(new Apskritimas(x, y, radius));
				zaidimas.pridetiZaidejoApskritimuKiekis();
				for (int i = 0; i < apskritimai.size(); i++)
				{
					apskritimai.get(i).setBusena(apskritimai.get(i).arPersidengia(apskritimai.get(apskritimai.size()-1)));
				}
				zaidimas.issaugotiZaidejoApskritima();
			}
		}
		else
		{
			zaidimas.sukurtiApskritimus(15);
			zaidimas.issaugotiApskritimus();
			apskritimai = zaidimas.getApskritimai();
		}
		model.addAttribute("apskritimai", apskritimai);
		return "greeting";
	}
}