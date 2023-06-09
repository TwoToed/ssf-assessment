package vttp2023.batch3.ssf.frontcontroller.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class FrontController {
	// TODO: Task 2, Task 3, Task 4, Task 6
	@Autowired
	private AuthenticationService service;
	@GetMapping(path="/")
    public String showLandingPage(Model m){
        m.addAttribute("login", new Login());
        return "view0";
    }
	@PostMapping("/login")
	public String login(Model m, HttpSession session, @Valid Login login, BindingResult result) throws Exception{

		if(result.hasErrors()){
			System.out.println(login);
            return "view0";
        }

		System.out.println(login.getAttempts());

		String username = login.getUsername();
		String password = login.getPassword();
		if(service.checkdisabled(username).equals("banned")){
			m.addAttribute("bannedname", username);
			return "view2";
		}
		Login authentication = (Login) session.getAttribute("authentication");
		System.out.println(authentication);
		if (authentication == null) {
			authentication = new Login(username, password);
			}
		service.authenticate(username, password);
		if(service.authenticate(username, password) == 1){
			authentication.setAuthenticated(true);
			session.setAttribute("authentication", authentication);
			return "redirect:/protected/view1.html";
		};
		authentication.setAuthenticated(false);
		authentication.failedattempt();
		session.setAttribute("authentication", authentication);
		if (authentication.getAttempts() > 2){
			session.invalidate();
			service.disableUser(authentication.getUsername());
			m.addAttribute("bannedname", authentication.getUsername());
			return "view2";
		}
		m.addAttribute("error","error: invalid requests");
		m.addAttribute("question",service.captcha().getquestion());
		return "view0";
	}

	@GetMapping("/protected/view1.html")
	public String protectedview(){
		return "view1";
	}

}
