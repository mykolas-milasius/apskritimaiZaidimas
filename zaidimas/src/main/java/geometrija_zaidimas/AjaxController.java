package geometrija_zaidimas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController
{
	@GetMapping("/apskritimai")
	@ResponseBody
	public ArrayList<Apskritimas> apskritimai()
	{
		Zaidimas zaidimas = new Zaidimas();
		zaidimas.nuskaitymasApskritimu("data/duomenys.csv");
		zaidimas.suskaiciuotiPlotus();
		ArrayList<Apskritimas> apskritimai = zaidimas.getApskritimai();
		zaidimas.taskai();
		return apskritimai;
	}
}