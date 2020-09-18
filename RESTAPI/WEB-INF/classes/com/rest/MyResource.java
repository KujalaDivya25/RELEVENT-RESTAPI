package com.rest;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;

import javax.persistence.PostLoad;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.dto.Blog;
import com.rest.dto.CreateEvent;
import com.rest.dto.Email;
import com.rest.dto.Experience;
import com.rest.dto.OTP;
import com.rest.dto.Payment;
import com.rest.dto.Professional;
import com.rest.dto.Rating;
import com.rest.dto.SMS;
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


@Path("myresource")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
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


	@Path("sponsor/{organiserId}/{text}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)

	public String sponsor(@PathParam("organiserId") int organiserId, @PathParam("text") String text) {

		User user = new User();
		UserDAO userdao = new UserDAO();
		user = userdao.getUserById(organiserId);
		String to = user.getEmailId();

		String subject = "Request for sponsorship";

		SendMail sendmail = new SendMail();
		sendmail.sendMail(to, subject, text);

		return "success";

	}

	/*@Path("rateProfs/{profs}/{values}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	public void share(@PathParam("profs") String profs, @PathParam("values") String values) {
		List<String> professionals = Arrays.asList(profs.split("\\s*,\\s*"));
		List<String> values1 = Arrays.asList(values.split("\\s*,\\s*"));

		for(int i = 0; i < values1.size(); i++) {

			Professional professional = new Professional();
			professional.setProfessionalId(Integer.parseInt(professionals.get(i)));

			Rating rating = new Rating();
			rating.setRating(Integer.parseInt(values1.get(i)));
			rating.setProfessional(professional);

			RatingDAO ratingdao = new RatingDAO();
			ratingdao.addRating(rating);
		}
	}*/

	@Path("rateProfs/{profs}/{values}")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String share(@PathParam("profs") String profs, @PathParam("values") String values) {
		List<String> professionals = Arrays.asList(profs.split("\\s*,\\s*"));
		List<String> values1 = Arrays.asList(values.split("\\s*,\\s*"));

		for(int i = 0; i < values1.size(); i++) {


			Professional proff = new Professional();
			ProfessionalDAO proffdao = new ProfessionalDAO();
			proff = proffdao.getProfById(Integer.parseInt(professionals.get(i)));

			Rating rating = new Rating();
			rating.setRating(Integer.parseInt(values1.get(i)));
			rating.setProfessional(proff);

			RatingDAO ratingdao = new RatingDAO();
			ratingdao.addRating(rating);
		}
		return "success";
	}

	@Path("AllRatings/{professionalId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public float getaddedRatings(@PathParam("professionalId") int professionalId) {
		RatingDAO ratedao = new RatingDAO();
		List<Object> ratings = ratedao.getallRatings(professionalId);
		List<Integer> values = new ArrayList<Integer>();
		for (Object x: ratings) {
			int n = ((Rating) x).getRating();
			values.add(n);
		}
		System.out.println("values = " + values);
		int sum = 0;
		for (int i : values) {
			sum += i;
		}
		float avg = sum/(float)values.size();
		return avg;
	}

	@Path("shareEvent/{eventId}/{organiserId}/{to}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)

	public String share(@PathParam("eventId") int eventId, @PathParam("organiserId") int organiserId, @PathParam("to") String to) {

		String subject = "Shares Event";

		User user = new User();
		UserDAO userdao = new UserDAO();
		user = userdao.getUserById(organiserId);

		CreateEvent event = new CreateEvent();
		CreateEventDAO eventdao = new CreateEventDAO();
		event = eventdao.getEventById(eventId);

		String text = "Hello I am " + user.getUserName() + " I am sharing the event " + event.getEventName() + " with you. \n" 
				+ " Here are the details : \n" + " About : " + event.getAbout() + "\n Venue : " + event.getVenue()  + 
				"\n From " + event.getEventStartDate() + " To " + event.getEventEndDate();

		SendMail sendmail = new SendMail();

		List<String> items = Arrays.asList(to.split("\\s*,\\s*"));
		for(String elem: items) {
			System.out.println("element = " + elem);
			sendmail.sendMail(elem, subject, text);
		}
		System.out.println("List = " + items);

		return "success";

	}

	// User profile

	@Path("updateUser")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmp(User user){
		System.out.println("Data Recieved in update : " + user); 
		UserDAO userDao = new UserDAO();
		userDao.updateUser(user);

	}

	@Path("ShowEvents/{organiserId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getEventByUserId(@PathParam("organiserId") int organiserId) {
		CreateEventDAO eventdao = new CreateEventDAO();
		Object event = eventdao.EventonUserId(organiserId);
		System.out.println("data" + event);
		return event;
	}

	@Path("UserBlogs/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUserBlogs(@PathParam("userId") int userId) {
		BlogDAO blogdao = new BlogDAO();
		Object blog = blogdao.UserBlogs(userId);
		return blog;
	}

	@Path("BookedEvents/{regUserId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getBookedUser(@PathParam("regUserId") int regUserId) {
		PaymentDAO paymentdao = new PaymentDAO();
		Object payment = paymentdao.BookedUser(regUserId);
		return payment;
	}

	// End of user profile

	// Professional Profile

	@Path("MyEvents/{profId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getProfEvents(@PathParam("profId") int profId) {
		ProfessionalDAO profdao = new ProfessionalDAO();
		Professional prof = profdao.getProfById(profId);
		List<CreateEvent> events = prof.getEventList();
		return events;
	}

	@Path("ProfBlogs/{profId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getBlogs(@PathParam("profId") int profId) {
		BlogDAO blogdao = new BlogDAO();
		Object blog = blogdao.ProfBlogs(profId);
		return blog;
	}

	@Path("ProfExps/{profId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getExps(@PathParam("profId") int profId) {
		ExperienceDAO expdao = new ExperienceDAO();
		Object exps = expdao.profExps(profId);
		return exps;
	}

	//End of Professional Profile

	@Path("getEvent/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getEventById(@PathParam("eventId") int eventId) {
		CreateEventDAO eventdao = new CreateEventDAO();
		Object event =eventdao.getEventById(eventId);
		System.out.println("data" + event);
		return event;
	}

	@Path("getEventProfs/{eventId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professional> getEventPRofs(@PathParam("eventId") int eventId) {

		CreateEventDAO eventdao = new CreateEventDAO();
		Object event = eventdao.getEventById(eventId);

		List<Professional> profList = ((CreateEvent) event).getProfessionalList();
		/*List<String> profs = null;

		for(Professional prof : profList) {
			profs.add(prof.getProfessionalName());
			profs.add(prof.getServiceName());
			System.out.println(prof.getProfessionalId());
			System.out.println(prof.getProfessionalName());
			System.out.println(prof.getServiceName());
		} */
		return profList;
	}


	@Path("cardValidate/{number}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean validateCard(@PathParam("number") String number) throws Exception {

		ValidateCard validate = new ValidateCard();
		boolean x = ValidateCard.validateCreditCardNumber(number);
		System.out.println(x);
		return x;
	}

	@Path("CheckUser/{userName}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean CheckUser(@PathParam("userName") String userName) {
		UserDAO userdao = new UserDAO();
		User user = userdao.getLoggedUser(userName);
		if(user == null) {
			return true;
		}
		return false;
	}

	@Path("getSocialUser/{userName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getSocialUser(@PathParam("userName") String userName) {
		UserDAO userdao = new UserDAO();
		User user = userdao.getLoggedUser(userName);
		return user;
	}

	@Path("CheckProf/{professionalName}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public boolean CheckUProf(@PathParam("professionalName") String professionalName) {
		ProfessionalDAO professionaldao = new ProfessionalDAO();
		boolean x = professionaldao.getLoggedProf(professionalName);
		System.out.println(x);
		return x;
	}

	@Path("verification/{mail}/{name}/{mobile}")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 

	public int verifyAccount(@PathParam("mail") String mail, @PathParam("name") String name, @PathParam("mobile") String mobile){

		OTP otp = new OTP();
		int OTP = otp.generateOTP();
		String s = String.valueOf(OTP);

		String to = mail;
		String subject = "Account confirmation";
		String text = "hey " + name + " this is your OTP : " + s;
		String mobile_number = mobile;

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);

		SMS sms = new SMS();
		sms.registration(mobile_number, text);

		return OTP;

	}
	
	@Path("verification1/{mail}/{name}")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 

	public int verifyAccount(@PathParam("mail") String mail, @PathParam("name") String name){

		OTP otp = new OTP();
		int OTP = otp.generateOTP();
		String s = String.valueOf(OTP);

		String to = mail;
		String subject = "Account confirmation";
		String text = "hey " + name + " this is your OTP : " + s;

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);
		
		return OTP;

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

	@Path("regUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void regUser(User user) {

		UserDAO userDao = new UserDAO();
		userDao.registerUser(user);
		String to = user.getEmailId();
		String subject = "Thank You for Registering in Rel-Event!";
		String text = "Hey " + user.getUserName() + " Welcome To Rel-Event! ";
		String mobile_number = user.getContact();

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);

		/*SMS sms = new SMS();
    	sms.registration(mobile_number, text);*/

		System.out.println("added user" + user);

	}

	@Path("regProfessional")
	@POST
	@Consumes((MediaType.MULTIPART_FORM_DATA))
	public void registerProfessional(@FormDataParam("serviceImage") InputStream fileInputStream,
			@FormDataParam("serviceImage") FormDataContentDisposition formDataContentDisposition,
			@FormDataParam("profDetails") String profDetails ) throws IOException {

		System.out.println(profDetails);
		ObjectMapper objectmapper = new ObjectMapper();
		Professional prof = objectmapper.readValue(profDetails,Professional.class);

		int read = 0;
		byte[] bytes = new byte[1024];
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+ "/images/", formDataContentDisposition.getFileName()));
		while((read=fileInputStream.read(bytes)) != -1  ){
			out.write(bytes,0,read);

		}
		out.flush();
		out.close();

		prof.setServiceImage(formDataContentDisposition.getFileName());
		ProfessionalDAO profdao = new ProfessionalDAO();
		profdao.registerProfessional(prof);

		String to = prof.getMailId();
		String subject = "Thank You for Registering in Rel-Event!";
		String text = "Hey " + prof.getProfessionalName() + " Welcome To Rel-Event! ";
		String mobile_number = prof.getMobile();

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);

	}

	@Path("regEvent")
	@POST
	@Consumes((MediaType.MULTIPART_FORM_DATA))
	public void registerEvent(@FormDataParam("poster") InputStream fileInputStream,
			@FormDataParam("poster") FormDataContentDisposition formDataContentDisposition,
			@FormDataParam("eventDetails") String eventDetails ) throws IOException {

		System.out.println(eventDetails);		
		ObjectMapper objectmapper = new ObjectMapper();
		CreateEvent event = objectmapper.readValue(eventDetails,CreateEvent.class);

		int read = 0;
		byte[] bytes = new byte[1024];
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+ "/images/", formDataContentDisposition.getFileName()));
		while((read=fileInputStream.read(bytes)) != -1  ){
			out.write(bytes,0,read);

		}
		out.flush();
		out.close();

		int userId = event.getUser().getUserId();
		User user = new User();
		UserDAO userdao = new UserDAO();
		user = userdao.getUserById(userId);
		event.setUser(user);

		List<Professional> profList = event.getProfessionalList();
		for(Professional prof : profList) {
			prof.getEventList().add(event);
			System.out.println(prof);
		} 

		event.setPoster(formDataContentDisposition.getFileName());
		CreateEventDAO eventdao = new CreateEventDAO();
		eventdao.registerEvent(event);

		String to = user.getEmailId();
		String subject = "Thank You for creating an event through Rel-Event!";
		String text = "Hey " + user.getUserName() + " Now you are the organiser of this event \n" + "Here are details of professionals for your event \n" + profList;
		String mobile_number = user.getContact();

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);


	}

	@Path("addBlog")
	@POST
	@Consumes((MediaType.MULTIPART_FORM_DATA))
	public void addBlog(@FormDataParam("blogImage") InputStream fileInputStream,
			@FormDataParam("blogImage") FormDataContentDisposition formDataContentDisposition,
			@FormDataParam("blogDetails") String blogDetails ) throws IOException {

		System.out.println(blogDetails);
		ObjectMapper objectmapper = new ObjectMapper();
		Blog blog = objectmapper.readValue(blogDetails,Blog.class);

		int read = 0;
		byte[] bytes = new byte[1024];
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathArr[] = path.split("/WEB-INF/classes/");
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+ "/images/", formDataContentDisposition.getFileName()));
		while((read=fileInputStream.read(bytes)) != -1  ){
			out.write(bytes,0,read);

		}
		out.flush();
		out.close();

		blog.setBlogImage(formDataContentDisposition.getFileName());

		int userId = blog.getUserblog().getUserId();
		User user = new User();
		UserDAO userdao = new UserDAO();
		user = userdao.getUserById(userId);
		blog.setUserblog(user);

		int profId = blog.getProfblog().getProfessionalId();
		Professional proff = new Professional();
		ProfessionalDAO proffdao = new ProfessionalDAO();
		proff = proffdao.getProfById(profId);
		blog.setProfblog(proff);

		BlogDAO blogdao = new BlogDAO();
		blogdao.addBlog(blog);

		System.out.println("added blog" + blog);

	}


	@Path("addExperience")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addExperience(Experience exp) {

		int profId = exp.getProfessional().getProfessionalId();
		Professional prof = new Professional();
		ProfessionalDAO profdao = new ProfessionalDAO();
		prof = profdao.getProfById(profId);
		exp.setProfessional(prof);

		ExperienceDAO expdao = new ExperienceDAO();
		expdao.addExp(exp);

		System.out.println("added experience" + exp);
	}

	@Path("ProfList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getProfessionals() {
		ProfessionalDAO profdao = new ProfessionalDAO();
		List<Object> profs = profdao.getListOfProfessional();
		System.out.println("data" + profs);
		return profs;
	}

	@Path("EventList/{category}/{eventType}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getEvents(@PathParam("category") String category, @PathParam("eventType") String eventType) {
		CreateEventDAO createEventdao = new CreateEventDAO();
		List<Object> events = createEventdao.getListOfEvents(category, eventType);
		System.out.println("data" + events);
		return events;
	}
	@Path("BlogList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getBlogs() {
		BlogDAO blogdao = new BlogDAO();
		List<Object> blogs = blogdao.getListOfBlogs();
		System.out.println("data" + blogs);
		return blogs;
	}

	@Path("ExpList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getExps() {
		ExperienceDAO expdao = new ExperienceDAO();
		List<Object> exps = expdao.getListOfExps();
		System.out.println("data" + exps);
		return exps;
	}

	@Path("regPayment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public void addPayment(Payment payment) {

		int userId = payment.getUser().getUserId();
		User user = new User();
		UserDAO userdao = new UserDAO();
		user = userdao.getUserById(userId);
		payment.setUser(user);

		int eventId = payment.getEvent().getEventId();
		CreateEvent event = new CreateEvent();
		CreateEventDAO eventdao = new CreateEventDAO();
		event = eventdao.getEventById(eventId);
		payment.setEvent(event);

		PaymentDAO paymentDao = new PaymentDAO();   
		paymentDao.addPayment(payment);

		String to = user.getEmailId();
		String subject = "Thank You for booking a ticket in " + event.getEventName();
		String text = "Here are your booking details : \n" + 
				"Booking-Id : " + payment.getPaymentId() + "\n" +
				"Name : " + user.getUserName() + "\n" +
				"Amount : " + event.getTicketPrice() + "\n" +
				"Venue : " + event.getVenue() + "\n" +
				"Date : " + event.getEventStartDate() + "\n" +
				"Time : " + event.getStartTime() + "\n" ;

		String mobile_number = user.getContact();

		SendMail sendmail = new SendMail();	
		sendmail.sendMail(to, subject, text);

		System.out.println("added payment" + payment +  " " + payment.getEvent());
	}

}
