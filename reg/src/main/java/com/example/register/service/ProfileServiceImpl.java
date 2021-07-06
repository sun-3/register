package com.example.register.service;


import com.example.register.model.Profile;
import com.example.register.repository.ProfileRepository;
import com.example.register.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository repository;
	
	
	@Override
	public void add(Profile profile) {
		repository.save(profile);
		
	}

	@Override
	public Profile getProfile(String username) {
		
		return repository.getById(username);
	}

	@Override
	public List<Profile> getAllProfiles() {
		
		return repository.findAll();
	}

	@Override
	public String showProfileData(String username) {
		if (repository.existsById(username)) {
			Profile profile = repository.getById(username);
			return showProfilePage(profile);
		} else {
			return "<h1>Username not found</h1>";
		}
	}

	@Override
	public void deleteProfile(String username) {

		repository.delete(repository.getById(username));
	}

	@Override
	public String showRegisterPage() {
		Profile profile = new Profile();
		profile.setName("");
		profile.setEmail("");
		profile.setUsername("");
		profile.setMobile(0L);
		profile.setSkills("");
		profile.setState("");
		profile.setGender("");
		return createRegistrationPage(profile);
	}

	@Override
	public String showEditPage(String username) {
		Profile profile = repository.getById(username);
		return createRegistrationPage(profile);
	}

	private String showProfilePage(Profile profile) {
		String html = "<html>\n" +
				"    <head>\n" +
				"        <title>Result</title>\n" +
				"        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
				"    </head>\n" +
				"    <body>\n" +
				"        <center><h1>Result</h2></center>\n" +
				"        <table class=\"table table-borderless\">\n" +
				"            <tr>\n" +
				"                <td>Name</td>\n" +
				"                <td id=\"name_val\">"+profile.getName()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>Username</td>\n" +
				"                <td id=\"username_val\">"+profile.getUsername()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>Email</td>\n" +
				"                <td id=\"email_val\">"+profile.getEmail()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>Mobile</td>\n" +
				"                <td id=\"mobile_val\">"+profile.getMobile()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>Gender</td>\n" +
				"                <td id=\"gender_val\">"+profile.getGender()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>Skills</td>\n" +
				"                <td id=\"skills_val\">"+profile.getSkills()+"</td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>State</td>\n" +
				"                <td id=\"state_val\">"+profile.getState()+"</td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    </body>\n" +
				" <center><button onclick=\"edit()\">Edit</button></center> "+
				"<script>function edit(){" +
				"window.location.href = \"http://localhost:8080/profile/"+profile.getUsername()+"\";" +
				"}</script>"+
				"</html>\n" +
				"\n" +
				"\n";
		return html;
	}

	private String createRegistrationPage(Profile profile){
		String checkedMale = "";
		String checkedFemale = "";
		if(profile.getGender().equals("male")){
			checkedMale = "checked";
		}
		else
		{
			checkedFemale = "checked";
		}
		System.out.println(profile.getEmail());
		System.out.println(profile.getMobile());
		System.out.println(profile.getGender());
		System.out.println(profile.getSkills());
		String html="<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"\t<style>\n" +
				"\t\n" +
				"#btn{\n" +
				"\tbackground-color:blue;\n" +
				"\tcolor:white;\n" +
				"\theight:35px;\n" +
				"\twidth:100px;\n" +
				"\tborder-radius:25px ;\n" +
				"\t}\n" +
				"\t#btn:hover\n" +
				"\t{\n" +
				"\tbackground-color:red;\n" +
				"\t}\n" +
				"\tspan\n" +
				"\t{\n" +
				"\tcolor:red;\n" +
				"\t}\n" +
				"\t\n" +
				"\t</style>\n" +
				"\t<title>Form</title>\n" +
				"</head>\n" +
				"\n" +
				"<form>\n" +
				"\n" +
				"<body>\n" +
				"\t<table align=\"center\" cellspacing=\"20\">\n" +
				"<tr>\n" +
				"<td>Name<td>\n" +
				"\t<input type=\"text\" id=\"name\" name =\"name\"value=\""+profile.getName() +"\"required placeholder=\"Name\">\n" +
				"\t<br><span id=\"namee\"></span>\n" +
				"\t</td>\n" +
				"</tr>\n" +
				"<tr>\n" +
				"<td>Username<td>\n" +
				"\t<input type=\"text\" id=\"username\" name =\"username\"value=\""+profile.getUsername() +"\" required placeholder=\"Username\">\n" +
				"\t<br><span id=\"user\"></span>\n" +
				"\t</td>\n" +
				"</tr>\n" +
				"\n" +
				"<td>E-mail<td>\n" +
				"\t<input type=\"email\" id=\"email\" name=\"email\"value=\""+profile.getEmail()+"\" required placeholder=\"E-mail\">\n" +
				"\t<br><span id=\"eml\"></span>\n" +
				"\t</td>\n" +
				"</tr>\n" +
				"\n" +
				"<td>Mobile<td>\n" +
				"\t<input type=\"tel\" id=\"mobile\" name=\"mobile\"value=\""+profile.getMobile()+"\"required placeholder=\"Mobile\">\n" +
				"\t<br><span id=\"mobil\"></span>\n" +
				"\t</td>\n" +
				"</tr>\n" +
				"\n" +
				"<td>Gender<td>\n" +
				"\t<input type=\"radio\" name=\"gender\" value =\"male\""+checkedMale+" required>\n" +
				"\t<label>Male</label>\n" +
				"\t<input type=\"radio\" name=\"gender\" value=\"female\""+checkedFemale+" required>\n" +
				"\t<label>Female</label>\n" +
				"\t</td>\n" +
				"</tr>\n" +
				"\n" +
				"\n" ;
		html += "<td>Skills<td>\n" +
				"<td>";

		for (String skill : Utils.allSkills()) {
			String checked = "";
			for (String selected : profile.getSkills().split(",")) {
				if (skill.toLowerCase().equals(selected.toLowerCase())) {
					checked = "checked";
				}
				System.out.println(skill+":"+selected);
			}
			html += "<label for=\""+skill+"\">"+skill.toUpperCase()+"</label><br>\n";
			html += "<input type=\"checkbox\" id=\"" + skill + "\" name=\"skills\" value=\"" + skill.toUpperCase() + "\""+checked+">\n";
		}

		html += "</tr>\n" +
				"\n" +
				"\n" +
				"<td>State<td>\n" +
				"<select name=\"state\" id=\"state\">\n" +
				"\t<option value=\"Select state\">Select state</option>\n";

		for (String state : Utils.allStates()) {
			String checked = "";
			if (state.equals(profile.getState())) {
				checked = "selected";
			}

			html += "\t<option "+checked+" value=\""+state+"\">"+state.toUpperCase()+"</option>\n";
		}
		html += "</select>\n" +
				"<br>\n" +
				"\n" +
				"<tr>\n" +
				"<td>Profile Picture<td>\n" +
				"<label for=\"img\">Select image:</label>\n" +
				"<input type=\"file\" id=\"img\" name=\"file\" accept=\"image/*\" required>\n" +
				"  </td>\n" +
				"  </tr>\n" +
				"<tr>\n" +
				"\t<td colspan=\"2\" align=\"center\">\n" +
				"\t<input type=\"submit\" id=\"btn\" ></a> \n" +
				"\t</td>\n" +
				"</tr>\t\n" +
				"</table>\n" +
				"</form>\n" +
				"\n" +
				"<script>\n" +
				"const formElem = document.querySelector('form');\n" +
				"formElem.addEventListener('submit', (e) => {\n" +
				"  // on form submission, prevent default\n" +
				"  e.preventDefault();\n" +
				"\n" +
				"  // construct a FormData object, which fires the formdata event\n" +
				"  new FormData(formElem);\n" +
				"});\n" +
				"\n" +
				"formElem.addEventListener('formdata', (e) => {\n" +
				"    let data = e.formData;\n" +
				"    let request = new XMLHttpRequest();\n" +
				"    request.open(\"POST\", \"http://localhost:8080/profile/api\");\n" +
				"    request.send(data);\n" +
				"    setTimeout(function(){\n" +
				"        window.location.href = \"http://localhost:8080/profile/view/\" + document.getElementById(\"username\").value;\n" +
				"    }, 1000);\n" +
				";\n" +
				"\n" +
				"});\n" +
				"</script>\n" +
				"\n" +
				"</body>\n" +
				"</html>\n" +
				"\n" +
				"\n";
		return html;
	}

}
