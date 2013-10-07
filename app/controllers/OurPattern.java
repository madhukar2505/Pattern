package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.DayCardDbo;
import models.EmailToUserDbo;
import models.StatusEnum;
import models.TimeCardDbo;
import models.Token;

import models.UserDbo;

import models.CompanyDbo;

import controllers.auth.Check;
import controllers.auth.Secure;
import controllers.auth.Secure.Security;
import play.Play;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.libs.Crypto;
import play.libs.Time;
import play.mvc.Controller;
import play.mvc.With;
import play.mvc.Scope.Session;

public class OurPattern extends Controller {

	private static final Logger log = LoggerFactory.getLogger(OurPattern.class);
	
	//This is what we list in the web page..
	private static List<UserDbo> ourUsers = new ArrayList<UserDbo>();
	
	public static void listUsers() {
		List<UserDbo> users = ourUsers;
		render(users);
	}

	public static void ajaxUser(Integer id) {
		if(id == null) { //this is a new user(ie. user add
			//use user?.name, user?.email, etc. etc. since user==null
			render();
		}
		
		UserDbo user = callDatabaseGetUser(id);
		render(user);
	}

	public static void postUser(UserDbo user) {
		postUserToDatabase(user);
		listUsers();
	}
	
	private static void postUserToDatabase(UserDbo user) {
		//normally we would post to the database but here just add to the list
		ourUsers.add(user);
	}

	private static UserDbo callDatabaseGetUser(int id) {
		//normally we would call into database for the purposes of this patter, it does not matter
		for(UserDbo user : ourUsers) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	
}
