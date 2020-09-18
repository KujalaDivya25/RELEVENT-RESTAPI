package com.rest;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.service.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import com.rest.dto.Blog;
import com.rest.dto.CreateEvent;
import com.rest.dto.Experience;
import com.rest.dto.Payment;
import com.rest.dto.Professional;
import com.rest.dto.Rating;
import com.rest.dto.SendMail;
import com.rest.dto.User;
import com.rest.dto.ValidateCard;
import com.ts.dao.BlogDAO;
import com.ts.dao.CreateEventDAO;
import com.ts.dao.ExperienceDAO;
import com.ts.dao.PaymentDAO;
import com.ts.dao.ProfessionalDAO;
import com.ts.dao.RatingDAO;
import com.ts.dao.UserDAO;

@Path("myresource1")
public class MyResource1 {

	@Path("regProfessional")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String regProfessional() {

		Professional professional1=new Professional();
		professional1.setProfessionalName("Pranay");
		professional1.setServiceName("TastyFoods");
		professional1.setServiceType("caterer");
		professional1.setExperience(5);
		professional1.setMobile("9246838321");
		professional1.setMailId("pranay@gmail.com");
		professional1.setPassword("pranay");

		Professional professional=new Professional();
		professional.setProfessionalName("Abcd");
		professional.setServiceName("TastyFoods");
		professional.setServiceType("caterer");
		professional.setExperience(5);
		professional.setMobile("9246838321");
		professional.setMailId("abcd@gmail.com");
		professional.setPassword("abcd");

		ProfessionalDAO professionalDao = new ProfessionalDAO();
		professionalDao.registerProfessional(professional1);
		professionalDao.registerProfessional(professional);

		return "Success";
	}

	@Path("addRating")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addRating() {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> prof = new ArrayList<Integer>();
		ArrayList<Integer> rat = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		for(int i =0;i<arr.size();i=i+2){
			int a = arr.get(i);
			prof.add(a);
		}
		for(int i = 1;i<arr.size();i=i+2){
			int a = arr.get(i);
			rat.add(a);
		}
		System.out.println(arr);
		for(int i=0;i<arr.size();i++){
						
			Professional professional = new Professional();
			professional.setProfessionalId(prof.get(i));

			Rating rating = new Rating();
			rating.setRating(rat.get(i));
			rating.setProfessional(professional);

			RatingDAO ratingdao = new RatingDAO();
			ratingdao.addRating(rating);
		}
		return "success";
	}

	@Path("cardValidate")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean validateCard() throws Exception {

		String number = "5196081888500645";
		ValidateCard validate = new ValidateCard();
		boolean x = ValidateCard.validateCreditCardNumber(number);
		return x;
	}

	@Path("regUser")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String regUser() {

		User user=new User();
		user.setUserName("Preetha");
		user.setContact("8688508995");
		user.setEmailId("harshitha1699@gmail.com");
		user.setPassword("password");

		UserDAO userDao = new UserDAO();
		userDao.registerUser(user);

		return "Success";
	}

	@Path("regEvent")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String regEvent() {

		User user=new User();
		user.setUserId(2);

		Professional professional=new Professional();
		professional.setProfessionalId(1);

		Professional professional1=new Professional();
		professional1.setProfessionalId(2);

		CreateEvent event=new CreateEvent();
		event.setEventName("Corda Boot camp");
		event.setCategory("workshop");
		event.setEventType("public");
		event.setEventStartDate("Mon Aug 29, 2020");
		event.setEventEndDate("Mon Aug 30, 2020");
		event.setVenue("Gachibowli");
		event.setAbout("It's all about latest Technologies which are gonna boom in future");
		event.setSponsor("no");
		event.setTicketPrice(1100);
		event.setAttendeesCount(200);
		event.setOrganiserName("ABCD");
		event.setEndTime("10:00 AM - 10:30 AM");
		event.setStartTime("12:30 PM -  1:00 PM");
		event.setStartOverview("Welcome Speech by WTF President\n-Introduction to the program");
		event.setEndOverview("Question and Answer");
		event.setTime1("10:30 AM - 11: 30 AM");
		event.setTime2("11:30 AM - 12:30 PM");
		event.setOverview1("Case Study\n -On latest Techs");
		event.setOverview2("Presentation\n- How to learn?");
		event.setGuest2("Mrs Paulomi Sharma");
		event.setGuest1("Mrs Shalini Agarwal");
		event.setGuest3("Mrs Shikha Gupta");
		event.setUser(user);

		event.getProfessionalList().add(professional);
		event.getProfessionalList().add(professional1);

		CreateEventDAO createEventdao = new CreateEventDAO();
		createEventdao.registerEvent(event);

		professional.getEventList().add(event);
		professional1.getEventList().add(event);

		return "added event";
	}

	@Path("regUserPayment")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addPayment() {

		CreateEvent event=new CreateEvent();
		event.setEventId(1);

		User user=new User();
		user.setUserId(1);

		Payment payment=new Payment();
		payment.setCardNum("5678-1234-5647-1234");
		payment.setNameOnCard("Anil");
		payment.setExpiryDate(new java.util.Date());
		payment.setAmount(1100);
		payment.setEvent(event);
		payment.setUser(user);

		PaymentDAO paymentDao = new PaymentDAO();
		paymentDao.addPayment(payment);

		return "Success";
	}
	@Path("addBlog")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addBlog() {

		User user=new User();
		user.setUserId(1);

		Blog blog = new Blog();
		blog.setTitle("3 tips for photography");
		blog.setCategory("photography");
		blog.setContent("A paragraph is a self-contained unit of discourse in writing dealing with a particular point or idea. A paragraph consists of one or more sentences. Though not required by the syntax of any language, paragraphs are usually an expected part of formal writing, used to organize longer prose.");
		blog.setUserblog(user);
		BlogDAO blogdao = new BlogDAO();
		blogdao.addBlog(blog);
		return "success";
	}

	@Path("shareExperience")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String shareMyExperience() {

		Professional professional=new Professional();
		professional.setProfessionalId(1);

		Experience exp = new Experience();
		exp.setProfessional(professional);
		exp.setExperience("My experience with Rel-event from past 2 months has been amazing it really made my work a lot easier and increased my profits");

		ExperienceDAO expdao = new ExperienceDAO();
		expdao.addExp(exp);

		return "success";
	}

	@Path("UserLogin/{userName}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object userLogin(@PathParam("userName") String userName,@PathParam("password") String password) {
		UserDAO userdao = new UserDAO();
		Object user = userdao.getUser(userName,password);
		System.out.println("data" + user);
		return user;
	}  

	@Path("ProfLogin/{profName}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object profLogin(@PathParam("profName") String profName,@PathParam("password") String password) {
		ProfessionalDAO profdao = new ProfessionalDAO();
		Object prof = profdao.getProfessional(profName,password);
		System.out.println("data" + prof);
		return prof;
	} 

	@Path("shareEvent")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sharevent() {

		int eventId = 4;
		CreateEvent event = new CreateEvent();

		CreateEventDAO eventdao = new CreateEventDAO();
		event = eventdao.getEventById(eventId);

		System.out.println(event);
		String path = event.getPoster();

		return path;
	}


}
