package com.uncc.mhealth.service;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncc.mhealth.dao.FeedbackDAO;
import com.uncc.mhealth.dao.QuestionDAO;
import com.uncc.mhealth.dao.UserDAO;
import com.uncc.mhealth.dao.UserSurveyDAO;
import com.uncc.mhealth.model.Feedback;
import com.uncc.mhealth.model.PF;
import com.uncc.mhealth.model.Question;
import com.uncc.mhealth.model.SubQuestion;
import com.uncc.mhealth.model.Survey;
import com.uncc.mhealth.model.User;
import com.uncc.mhealth.model.UserSurvey;
import com.uncc.mhealth.model.UserSurveyResponse;
@Service
public class FeedbackManager {

	@Autowired
    private UserDAO userDao;
	@Autowired
    private FeedbackDAO feedbackDao;
	@Autowired
	private UserSurveyDAO userSurveyDao;
	@Autowired
    private QuestionDAO questionDao;
	
	public static List<Question> createUserSurveyObj(List<UserSurveyResponse> userResponse,List<Question> listQuestions){
		for(UserSurveyResponse userSurveyResponse:userResponse){
			int index=listQuestions.indexOf(new Question(userSurveyResponse.getQuestionId()));
			if(index>-1){
				Question q=listQuestions.get(index);
				if(userSurveyResponse.getAnswer()!=null && !userSurveyResponse.getAnswer().equals("")){
					q.setAnswer(userSurveyResponse.getAnswer());
				}else if(userSurveyResponse.getSubQuestionId()!=-1){
					for(SubQuestion s:q.getSubquestions()){
						if(s.getId()==userSurveyResponse.getSubQuestionId()){
							s.setAnswer(userSurveyResponse.getSubAnswer());
							break;
						}
					}
				}
			}
		}
		return listQuestions;
	}
	/**
	 * This calculates various feedback related data based on survey filled by user. E.g: BAC, alcohol frequency, AUDIT risk
	 * With all these values, a feedback object is created for further processing
	 * @param feedbackDao
	 * @param userSurveyDao
	 * @param user
	 */
	
	public void createFeedback(User user) {
		
		/*UserSurvey userSurvey1 = userSurveyDao.get(user.getId(), "baseline_survey_page1");
		UserSurvey userSurvey2 = userSurveyDao.get(user.getId(), "baseline_survey_page2");
		UserSurvey userSurvey3 = userSurveyDao.get(user.getId(), "baseline_survey_page3");
		UserSurvey userSurvey4 = userSurveyDao.get(user.getId(), "baseline_survey_page4");
		UserSurvey userSurvey5 = userSurveyDao.get(user.getId(), "baseline_survey_page5");
		UserSurvey userSurvey6 = userSurveyDao.get(user.getId(), "baseline_survey_page6");
		UserSurvey userSurvey7 = userSurveyDao.get(user.getId(), "baseline_survey_page7");
		UserSurvey userSurvey8 = userSurveyDao.get(user.getId(), "baseline_survey_page8");*/
		
		/*List<UserSurveyResponse> userSurvey1=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page1");
		List<UserSurveyResponse> userSurvey2=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page2");
		List<UserSurveyResponse> userSurvey3=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page3");
		List<UserSurveyResponse> userSurvey4=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page4");
		List<UserSurveyResponse> userSurvey5=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page5");
		List<UserSurveyResponse> userSurvey6=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page6");
		List<UserSurveyResponse> userSurvey7=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page7");
		List<UserSurveyResponse> userSurvey8=userSurveyDao.getUserSurveyResponse(user.getId(),"baseline_survey_page8");*/
		
		List<UserSurveyResponse> userSurveyResponse=userSurveyDao.getAllUserSurveyResponse(user.getId());
		
		HashMap<String,String> map=new HashMap<String,String>();
		
		for(UserSurveyResponse userResponse:userSurveyResponse){
			if(userResponse.getQuestionId()!=-1)
				map.put("q"+userResponse.getQuestionId(),userResponse.getAnswer());
			if(userResponse.getSubQuestionId()!=-1)
				map.put("sq"+userResponse.getSubQuestionId(), userResponse.getSubAnswer());
		}
		//System.out.println("Map ::"+map.toString());
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			/*ArrayList<Question> survey1 = (ArrayList<Question>)createUserSurveyObj(userSurvey1,questionDao.getSurvey("baseline",1));
			
			ArrayList<Question> survey2 = (ArrayList<Question>)createUserSurveyObj(userSurvey2,questionDao.getSurvey("baseline",2));
			
			ArrayList<Question> survey3 = (ArrayList<Question>)createUserSurveyObj(userSurvey3,questionDao.getSurvey("cea",3));
			
			ArrayList<Question> survey4 = (ArrayList<Question>)createUserSurveyObj(userSurvey4,questionDao.getSurvey("baseline",4));
			
			ArrayList<Question> survey5 = (ArrayList<Question>)createUserSurveyObj(userSurvey5,questionDao.getSurvey("baseline",5));
			
			ArrayList<Question> survey6 = (ArrayList<Question>)createUserSurveyObj(userSurvey6,questionDao.getSurvey("pss",6));
			
			ArrayList<Question> survey7 = (ArrayList<Question>)createUserSurveyObj(userSurvey7,questionDao.getSurvey("rcq",7));
			
			ArrayList<Question> survey8 = (ArrayList<Question>)createUserSurveyObj(userSurvey8,questionDao.getSurvey("baseline",8));*/
			
			/*ArrayList<Question> survey1 = mapper.readValue(userSurvey1.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey2 = mapper.readValue(userSurvey2.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey3 = mapper.readValue(userSurvey3.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey4 = mapper.readValue(userSurvey4.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey5 = mapper.readValue(userSurvey5.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey6 = mapper.readValue(userSurvey6.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey7 = mapper.readValue(userSurvey7.getResponse(), new TypeReference<ArrayList<Question>>(){});
			
			ArrayList<Question> survey8 = mapper.readValue(userSurvey8.getResponse(), new TypeReference<ArrayList<Question>>(){});*/
			
			
			
			
			
			
			
			/**
			 * actual feedback calculation
			 */
			/*HashMap<String, String> map = new HashMap<String, String>();
			System.out.println("begin...");
			// find gender of the current user
			int genderIndex = survey1.indexOf(new Question(5));
			String gender = "0";
			if(genderIndex > -1) {
				Question genderQuestion = survey1.get(genderIndex);
				gender = genderQuestion.getAnswer();
				map.put("q"+genderQuestion.getId(), gender);
			}*/
			System.out.println("begin...");
			//find gender of the current user
			//HashMap<String, String> dataMap = new HashMap<String, String>();
			// find gender of the current user
			//UserSurveyResponse genderList=map.get("5");
			String gender = "0";
		    gender=map.get("q5");
				
			
			/*int weightIndex = survey1.indexOf(new Question(8));
			double weight = 0d;
			if(weightIndex > -1) {
				Question weightQuestion = survey1.get(weightIndex);
				try{
					weight = Double.parseDouble(weightQuestion.getAnswer())  ;
				}catch(Exception e){
					
				}
				
			}*/
		    double weight = 0d;
		    weight=Double.parseDouble(map.get("q8"));
		    
			System.out.println("@@ Weight : "+weight);
			
			/*int heightIndex = survey1.indexOf(new Question(59));
			double height = 0d;
			if(heightIndex > -1) {
				Question heightQuestion = survey1.get(heightIndex);
				try{
					height = Double.parseDouble(heightQuestion.getAnswer())  ;
				}catch(Exception e){
					
				}
				
			}*/
			
			double height = 0d;
			/*height=Double.parseDouble(map.get("q59"));*/
			

			System.out.println("alcohol percentile");
			/**
			 * calculate alcohol percentile
			 */
					/*int index = survey2.indexOf(new Question(19));
					if(index > -1) {
						Question question = survey2.get(index);
						for(SubQuestion sq : question.getSubquestions()) {
							map.put("sq"+sq.getId(), sq.getAnswer());
						}
					}*/
				
				   
				
					int alcoholUsage = 0;
					try{
						alcoholUsage = Integer.parseInt(map.get("sq"+15));
					}catch(Exception e){
						
					}
				    int diff = 30 - alcoholUsage;
				    float alcoholPercentile = 0;
				    float alcoholPercentage = 0;
//					if(diff >= 21 && diff <= 29 ){
//						alcoholPercentage = 49.3f;
//						alcoholPercentile = 21.7f + 14.5f + 49.3f;
//					} else if (diff >= 1 && diff <= 20){
//						alcoholPercentage = 13.5f;
//						alcoholPercentile = 21.7f + 14.5f + 49.3f + 13.5f;
//					} else if( diff == 0){
//						alcoholPercentage = 1.0f;
//						alcoholPercentile = 21.7f + 14.5f + 49.3f + 13.5f + 1.0f;
//					} else if( diff == 30){
//						alcoholPercentage = 14.5f;
//						alcoholPercentile = 21.7f + 14.5f;		
//					}
					
					//now based on data provided by Dr. Li
					if(alcoholUsage == 0){
						alcoholPercentage = 14.5f;
						alcoholPercentile = 21.7f + 14.5f;
					} else if(alcoholUsage == 1 || alcoholUsage == 2){
						alcoholPercentage = 19.9f;
						alcoholPercentile = 21.7f + 14.5f + 19.9f;
					} else if(alcoholUsage >= 3 && alcoholUsage <= 5){
						alcoholPercentage = 18.9f;
						alcoholPercentile = 21.7f + 14.5f + 19.9f + 18.9f;
					} else if(alcoholUsage >= 6 && alcoholUsage <= 9){
						alcoholPercentage = 10.5f;
						alcoholPercentile = 21.7f + 14.5f + 19.9f + 18.9f + 10.5f;;
					} else if(alcoholUsage >= 10 && alcoholUsage <= 19){
						alcoholPercentage = 10;
						alcoholPercentile = 21.7f + 14.5f + 19.9f + 18.9f + 10.5f + 10;
					} else {//20+
						alcoholPercentage = 4.5f;
						alcoholPercentile = 21.7f + 14.5f + 19.9f + 18.9f + 10.5f + 10 + 4.5f;
					}
					
					System.out.println("quantity of drinking...");	
				/**
				 * calculate quantity of drinking
				 */
				/*int ddqIndex = survey2.indexOf(new Question(20));
				 if(ddqIndex > -1) {
					Question question = survey2.get(ddqIndex);
					for(SubQuestion sq : question.getSubquestions()){
						map.put("sq"+sq.getId(), sq.getAnswer());
					}
				} */
				String drinksAnswer = map.get("sq"+19);
				if(drinksAnswer == null) {
					drinksAnswer = "";
				}
				int firstDrink = 0;
				
				String [] drinksArray =  drinksAnswer.split(",");
				
				int total = 0;
				int drinkIndex = 0;
				for(String drink : drinksArray){
					if(drink != null && !drink.trim().equals("")) {
						try{
							total +=Integer.parseInt(drink);
							
							if(drinkIndex == 0){
								firstDrink = Integer.parseInt(drink);
							}
						}catch(Exception e){
							
						}
						
					}
					drinkIndex++;
				}
				
				
				String drinkHoursAnswer = map.get("sq"+20);
				if(drinkHoursAnswer == null) {
					drinkHoursAnswer = "";
				}
				int firstDrinkHours = 0;
				
				String [] drinkHoursArray =  drinkHoursAnswer.split(",");
				if(drinkHoursArray.length > 0){
					try{
						firstDrinkHours = Integer.parseInt(drinkHoursArray[0]);
					}catch(Exception e){
						
					}
				}
				
				
				int averageDrink = total / drinksArray.length;
				float drinkPercentile = 0;
				float drinkPercentage = 0;
//				if(averageDrink <= 4){
//					drinkPercentage = 69.0f;
//					drinkPercentile = 69.0f;
//				} else if(averageDrink == 5){
//					drinkPercentage = 10.9f;
//					drinkPercentile = 69.0f + 10.9f;
//				} else if(averageDrink == 6){
//					drinkPercentage = 8.0f;
//					drinkPercentile = 69.0f + 10.9f + 8.0f;
//				} else if(averageDrink >= 7){
//					drinkPercentage = 12.1f;
//					drinkPercentile = 69.0f + 10.9f + 8.0f + 12.1f;
//				}
				
				//now based on new data sent by Dr. Li
				switch(averageDrink){
				case 0:
					drinkPercentage = 30;
					drinkPercentile = 30;
					break;
				case 1:
					drinkPercentage = 11;
					drinkPercentile = 30 + 11;
					break;
				case 2:
					drinkPercentage = 15.3f;
					drinkPercentile = 30 + 11 + 15.3f;
					break;
				case 3:
					drinkPercentage = 12.4f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f;
					break;
				case 4:
					drinkPercentage = 9.6f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f;
					break;
				case 5:
					drinkPercentage = 7.7f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f + 7.7f;
					break;
				case 6:
					drinkPercentage = 5.6f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f + 7.7f + 5.6f;
					break;
				case 7:
					drinkPercentage = 2.2f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f + 7.7f + 5.6f + 2.2f;
					break;
				case 8:
					drinkPercentage = 3.3f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f + 7.7f + 5.6f + 2.2f + 3.3f;
					break;
				default:
					drinkPercentage = 2.9f;
					drinkPercentile = 30 + 11 + 15.3f + 12.4f + 9.6f + 7.7f + 5.6f + 2.2f + 3.3f + 2.9f;
					break;
				}
			
				System.out.println("highest of number of drinks...");	
			/**
			 * calculating highest number of drinks
			 */
			/*int maxDrinkIndex = survey2.indexOf(new Question(80));
				 if(maxDrinkIndex > -1) {
					Question question = survey2.get(maxDrinkIndex);
					for(SubQuestion sq : question.getSubquestions()){
						map.put("sq"+sq.getId(), sq.getAnswer());
					}
				}*/
				int maxWine = 0;
				int maxBeer = 0;
				int maxShots = 0;
				int maxLiqour = 0;
				
				try{
					maxWine = Integer.parseInt(map.get("sq"+120));
				}catch(Exception e){
					
				}
				
				try{
					maxBeer = Integer.parseInt(map.get("sq"+119));
				}catch(Exception e){
					
				}
				
				try{
					maxShots = Integer.parseInt(map.get("sq"+121));
				}catch(Exception e){
					
				}
				
				try{
					maxLiqour = Integer.parseInt(map.get("sq"+240));
				}catch(Exception e){
					
				}
				
				
				float maxHours = 0;
				try{
					maxHours = Float.parseFloat(map.get("sq"+122));
				}catch(Exception e){
					maxHours = 0;
				}
				
				int maxTotal = maxWine + maxBeer + maxShots + maxLiqour;
				
				
				
				System.out.println("CEA");
				/**
				 * get cea survey answers
				 */
				Question cea1 = null;
				Question cea2 = null;
				/*int cea1QuestionIndex = survey3.indexOf(new Question(47));
				if(cea1QuestionIndex > -1) {
					cea1 = survey3.get(cea1QuestionIndex);
				}
				int cea2QuestionIndex = survey3.indexOf(new Question(50));
				if(cea1QuestionIndex > -1) {
					cea2 = survey3.get(cea2QuestionIndex);
				}*/
				
				cea1=questionDao.getQuestion(47);
				cea2=questionDao.getQuestion(50);
				
				cea1.setAnswer(map.get("q47"));
				for(SubQuestion sq:cea1.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				cea2.setAnswer(map.get("q50"));
				for(SubQuestion sq:cea2.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				System.out.println("B YAACQ");
				/**
				 * get B-yaacq question
				 */
				Question byaacq = null;
				/*int byaacqIndex = survey5.indexOf(new Question(103));
				if(byaacqIndex > -1) {
					byaacq = survey5.get(byaacqIndex);
				}*/
				byaacq=questionDao.getQuestion(103);
				byaacq.setAnswer(map.get("q"+103));
				for(SubQuestion sq:byaacq.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				/**
				 * get B-yaacq first question
				 */
				Question byaacq1 = null;
				/*int byaacqIndex1 = survey4.indexOf(new Question(100));
				if(byaacqIndex1 > -1) {
					byaacq1 = survey4.get(byaacqIndex1);
				}
				*/
				byaacq1=questionDao.getQuestion(100);
				byaacq1.setAnswer(map.get("q100"));
				for(SubQuestion sq:byaacq1.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				
				int byaacqScore = 0;
				String byaacqProblems = "";
				int problemToBeDisplayedCount = 0;
				if(byaacq1 != null) {
					for(SubQuestion sq : byaacq1.getSubquestions()){
						String answer = sq.getAnswer();
						if(answer != null && answer.trim().equals("1")){
							if(problemToBeDisplayedCount <= 2){
								byaacqProblems = byaacqProblems+sq.getText()+",";
								problemToBeDisplayedCount ++;
							}
							byaacqScore++;
						}
					}
				}
				
				String byaacqRange = "";
				if(byaacqScore >= 0  && byaacqScore <= 4){
					byaacqRange = "Low";
				} else if(byaacqScore >= 5  && byaacqScore <= 9){
					byaacqRange = "Moderate";
				} else if(byaacqScore >= 5  && byaacqScore <= 9){
					byaacqRange = "Moderate";
				} else if(byaacqScore >= 10  && byaacqScore <= 14){
					byaacqRange = "Significant";
				} else if(byaacqScore >= 15  && byaacqScore <= 20){
					byaacqRange = "Severe";
				} else if(byaacqScore >= 21){
					byaacqRange = "Very Severe";
				}
				
				System.out.println("AUDIT");
				/**
				 * get AUDIT questions
				 */
				
				Question audit1 = null;
				/*int audit1Index = survey2.indexOf(new Question(86));
				if(audit1Index > -1) {
					audit1 = survey2.get(audit1Index);
				}*/
				audit1=questionDao.getQuestion(86);
				audit1.setAnswer(map.get("q86"));
				for(SubQuestion sq:audit1.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				Question audit2 = null;
				/*int audit2Index = survey2.indexOf(new Question(87));
				if(audit2Index > -1) {
					audit2 = survey2.get(audit2Index);
				}*/
				audit2=questionDao.getQuestion(87);
				audit2.setAnswer(map.get("q87"));
				for(SubQuestion sq:audit2.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				Question audit3 = null;
				/*int audit3Index = survey2.indexOf(new Question(88));
				if(audit3Index > -1) {
					audit3 = survey2.get(audit3Index);
				}*/
				audit3=questionDao.getQuestion(88);
				audit3.setAnswer(map.get("q88"));
				for(SubQuestion sq:audit3.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				Question audit4 = null;
				/*int audit4Index = survey2.indexOf(new Question(89));
				if(audit4Index > -1) {
					audit4 = survey2.get(audit4Index);
				}*/
				audit4=questionDao.getQuestion(89);
				audit4.setAnswer(map.get("q89"));
				for(SubQuestion sq:audit4.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				int auditScore = 0;
				ArrayList<String> auditPoints = new ArrayList<String>();
				if(audit1 != null){
					for(SubQuestion sq : audit1.getSubquestions()){
						try{
							int point = Integer.parseInt(sq.getAnswer());;
							auditScore += point;
							String options = "Never";
							switch(point){
							case 1:
								options = "Monthly or less";
								break;
							case 2:
								options = "2-4 times a month";
								break;
							case 3:
								options = "2-3 times a week";
								break;
							case 4:
								options = "4 or more times a week";
								break;
							}
							
							if(point > 0)
							auditPoints.add(sq.getText()+" : <span class=\"dynamic_field\">"+options + "("+point+" point)</span>");
						}catch(Exception e){
							
						}
					}
				}
				if(audit2 != null){
					for(SubQuestion sq : audit2.getSubquestions()){
						try{
							int point = Integer.parseInt(sq.getAnswer());;
							auditScore += point;
							String options = "1 or 2";
							switch(point){
							case 1:
								options = "3 or 4";
								break;
							case 2:
								options = "5 or 6";
								break;
							case 3:
								options = "7 to 9";
								break;
							case 4:
								options = "10 or more";
								break;
							}
							if(point > 0)
							auditPoints.add(sq.getText()+" : <span class=\"dynamic_field\">"+options + "("+point+" point)</span>");
						}catch(Exception e){
							
						}
					}
				}
				if(audit3 != null){
					for(SubQuestion sq : audit3.getSubquestions()){
						try{
							int point = Integer.parseInt(sq.getAnswer());;
							auditScore += point;
							String options = "Never";
							switch(point){
							case 1:
								options = "Less than Monthly";
								break;
							case 2:
								options = "Monthly";
								break;
							case 3:
								options = "Weekly";
								break;
							case 4:
								options = "Daily or Almost Daily";
								break;
							}
							if(point > 0)
							auditPoints.add(sq.getText()+" : <span class=\"dynamic_field\">"+options + "("+point+" point)</span>");
						}catch(Exception e){
							
						}
					}
				}
				if(audit4 != null){
					for(SubQuestion sq : audit4.getSubquestions()){
						try{
							int point = Integer.parseInt(sq.getAnswer());;
							auditScore += point;
							String options = "No";
							switch(point){
							case 2:
								options = "Yes, but not in the last year";
								break;
							case 4:
								options = "Yes, during the last year";
								break;
							}
							if(point > 0)
							auditPoints.add(sq.getText()+" : <span class=\"dynamic_field\">"+options + "("+point+" point)</span>");
						}catch(Exception e){
							
						}
					}
				}
				
				String auditRisc = "";
				if(auditScore >= 0 && auditScore <= 7){
					auditRisc = "LOW RISK";
				} else if(auditScore >= 8 && auditScore <= 15){
					auditRisc = "RISKY OR HAZARDOUS";
				} else if(auditScore >= 16 && auditScore <= 19){
					auditRisc = "HIGH RISK OR HARMFUL";
				} else if(auditScore >= 20){
					auditRisc = "HIGHEST RISK";
				}  
				
				
				
				System.out.println("PSS");
				/**
				 * get PSS question
				 */
				Question pss = null;
				/*int pssIndex = survey6.indexOf(new Question(61));
				if(pssIndex > -1) {
					pss = survey6.get(pssIndex);
				}*/
				pss=questionDao.getQuestion(61);
			    pss.setAnswer(map.get("q61"));
			    for(SubQuestion sq:pss.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				ArrayList<String> pssNever = new ArrayList<String>();
				ArrayList<String> pssRarely = new ArrayList<String>();
				ArrayList<String> pssOccasionally = new ArrayList<String>();
				ArrayList<String> pssSometimes = new ArrayList<String>();
				ArrayList<String> pssUsually = new ArrayList<String>();
				ArrayList<String> pssAlways = new ArrayList<String>();
				
				if(pss != null){
					for(SubQuestion sq : pss.getSubquestions()){
						String ans = sq.getAnswer();
						if(ans != null){
							if(ans.equals("0")){
								pssNever.add(sq.getText());
							} else if(ans.equals("1")){
								pssRarely.add(sq.getText());
							} else if(ans.equals("2")){
								pssOccasionally.add(sq.getText());
							} else if(ans.equals("3")){
								pssSometimes.add(sq.getText());
							} else if(ans.equals("4")){
								pssUsually.add(sq.getText());
							} else if(ans.equals("5")){
								pssAlways.add(sq.getText());
							}
						}
					}
				}
				
				
				
				System.out.println("readiness");
				/**
				 * get readiness ruler
				 */
				Question ruler1 = null;
				String ruler1Text = "";
				/*int ruler1Index = survey8.indexOf(new Question(38));
				if(ruler1Index > -1) {
					ruler1 = survey8.get(ruler1Index);
				}*/
				ruler1=questionDao.getQuestion(38);
				ruler1.setAnswer(map.get("q38"));
				for(SubQuestion sq:ruler1.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				if(ruler1 != null){
					for(SubQuestion sq : ruler1.getSubquestions()){
						int val = 0;
						try{
							val = Integer.parseInt(sq.getAnswer());
						}catch(Exception e){
							
						}
						if(val >= 0 && val <= 3) {
							ruler1Text = "it is not important";
						} else if (val >= 4 && val <= 7) {
							ruler1Text = "you are unsure of the importance";
						} else {
							ruler1Text = "it is very important";
						}
					}
				}
				
				
				Question ruler2 = null;
				String ruler2Text = "";
				/*int ruler2Index = survey8.indexOf(new Question(96));
				if(ruler2Index > -1) {
					ruler2 = survey8.get(ruler2Index);
				}*/
				ruler2=questionDao.getQuestion(96);
				ruler2.setAnswer(map.get("q96"));
				for(SubQuestion sq:ruler2.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				if(ruler2 != null){
					for(SubQuestion sq : ruler2.getSubquestions()){
						int val = 0;
						try{
							val = Integer.parseInt(sq.getAnswer());
						}catch(Exception e){
							
						}
						if(val >= 0 && val <= 3) {
							ruler2Text = "not confident in";
						} else if (val >= 4 && val <= 7) {
							ruler2Text = "unsure about";
						} else {
							ruler2Text = "very confident in";
						}
					}
				}
				
				/**
				 * Genetic Risk
				 */
				Question genetic = null;
				String geneticRisk = "LOW";
				/*int geneticIndex = survey1.indexOf(new Question(17));
				if(geneticIndex > -1) {
					genetic = survey1.get(geneticIndex);
				}*/
				genetic=questionDao.getQuestion(17);
				genetic.setAnswer(map.get("q17"));
				for(SubQuestion sq:genetic.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				
				int parents = 0;
				int other = 0;
				
				if(genetic != null){
					for(SubQuestion sq : genetic.getSubquestions()){
						if(sq.getId() == 7){
							parents = Integer.parseInt(sq.getAnswer());
						} else {
							other += Integer.parseInt(sq.getAnswer());
						}
					}
				}
				if(parents >= 1){
					geneticRisk = "High";
				} else if(other >= 3) {
					geneticRisk = "Medium";
				} else if(other == 2) {
					geneticRisk = "Low";
				} else if(other <= 1) {
					geneticRisk = "None";
				}
				
				/**
				 * P, C & A calculation
				 */
				Question pcaQuestion = null;
				String pcaCategory = "p";
				int pScore = 0;
				int cScore = 0;
				int aScore = 0;
				pcaQuestion=questionDao.getQuestion(65);
				pcaQuestion.setAnswer(map.get("q65"));
				for(SubQuestion sq:pcaQuestion.getSubquestions())
					sq.setAnswer(map.get("sq"+sq.getId()));
				/*int pcqIndex = survey7.indexOf(new Question(65));
				if(pcqIndex > -1) {
					pcaQuestion = survey7.get(pcqIndex);
				}*/
				
				if(pcaQuestion != null) {
					for(SubQuestion sq : pcaQuestion.getSubquestions()){
					int answer = Integer.parseInt(sq.getAnswer()) - 2;	
						
					
						if(sq.getId() == 100 && sq.getId() == 104 && sq.getId() == 109  && sq.getId() == 111){//p
							pScore = pScore + answer;
						} else if(sq.getId() == 102 && sq.getId() == 103 && sq.getId() == 107 && sq.getId() ==108){//c
							cScore = cScore + answer;
						} else if(sq.getId() == 101 && sq.getId() == 105 && sq.getId() == 106 && sq.getId() ==110){//a
							aScore = aScore + answer;
						}
					}
					
				}
				int max = -999;
				if(max < pScore)
					max = pScore;
				if(max < cScore)
					max = cScore;
				if(max < aScore)
					max = aScore;
				
				if(max == aScore){
					pcaCategory = "P";
				} else if(max == cScore){
					pcaCategory = "C";
				} else {
					pcaCategory = "A";
				}
				
				
				System.out.println("calorie");
				/**
				 * calorie percent calculation
				 */
				int monthlyCalReq = gender.equals("0") ? (30 * 2800) : (30 * 2200);
				int dailyCalReq = gender.equals("0") ? (2800) : (2200);
				int alcoholCal = averageDrink * 4 * 250;//using higher range calorie for MI

				double alcoholPercent = 100 * alcoholCal/(monthlyCalReq);
				
				int maxAlcoholCal = maxTotal * 250;
				double maxAlcoholPercent = 100 * maxAlcoholCal/(dailyCalReq);
				
				int alcoholCal1 = total * 4 * 150;
				int alcoholCal2 = total * 4 * 250;
				
				double alcoholPercent1 = 100 * alcoholCal1/(monthlyCalReq);
				double alcoholPercent2 = 100 * alcoholCal2/(monthlyCalReq);
				
				int maxAlcoholCal1 = maxTotal * 150;
				int maxAlcoholCal2 = maxTotal * 250;
				
				double maxAlcoholPercent1 = 100 * maxAlcoholCal1/(dailyCalReq);
				double maxAlcoholPercent2 = 100 * maxAlcoholCal2/(dailyCalReq);
				
				
				
				
				//calculate peak BAC
				//formula for BAC = (A x 5.14 / W x r) – .015 x H
				//A = liquid ounces of alcohol consumed
				//W = a person’s weight in pounds
				//r = a gender constant of alcohol distribution (.73 for men and .66 for women)*
				//H = hours elapsed since drinking commenced
				String genderString = (gender.equals("0")) ? "Male" : "Female";
				double r = (gender.equals("0")) ? 0.73d : 0.66d;
				double A = (maxBeer * 12 * 0.05) + (maxWine * 5 * 0.12) + (maxShots * 1.5 * 0.40) + (maxLiqour * 9 * 0.07);
				double alpha = (A * 5.14/(weight * r));
				double peakBAC = alpha - 0.015 * maxHours;
				double peakSober = ((A * 5.14/(weight * r)) - 0.059) / 0.015;
				if(peakSober < 0){
					peakSober = 0;
				}
				
				DecimalFormat df = new DecimalFormat(".###");
				df.setRoundingMode(RoundingMode.CEILING);
				
				//Update Code to update formula for typical BAC ; now it will take whole week's data into consideration
				double typicalBAC_sum = 0;
				//double typicalBAC;
				for(int i=0; i<drinksArray.length;i++){
					if(drinksArray[i]!=null && !drinksArray[i].trim().equals("")){
						try{
							firstDrink = Integer.parseInt(drinksArray[i]);
							firstDrinkHours = Integer.parseInt(drinkHoursArray[i]);
							double typicalA = firstDrink * 12 * 0.05;
							double daily_typicalBAC = (typicalA * 5.14/(weight * r)) - 0.015 * firstDrinkHours;
							typicalBAC_sum+=daily_typicalBAC;
						}catch(Exception e){
							
						}
					}
				}
				double typicalBAC = typicalBAC_sum/drinksArray.length;
				
				double typicalA = firstDrink * 12 * 0.05;
				//double typicalBAC = (typicalA * 5.14/(weight * r)) - 0.015 * firstDrinkHours;
				double typicalSober = ((typicalA * 5.14/(weight * r)) - 0.059) / 0.015;
				
				if(typicalSober < 0){
					typicalSober = 0;	
				}
				
				/**
				 * preferred time calculation
				 */
				System.out.println("Preferred time calculation");
				Question preferredTimeQuestion = null;
				int preferredTimeMon = -1;
				int preferredTimeTue = -1;
				int preferredTimeWed = -1;
				int preferredTimeThu = -1;
				int preferredTimeFri = -1;
				int preferredTimeSat = -1;
				int preferredTimeSun = -1;
				
				/*int preferredTimeIndex = survey1.indexOf(new Question(107));
				if(preferredTimeIndex > -1) {
					preferredTimeQuestion = survey1.get(preferredTimeIndex);
				}*/
				
				if(user.getIstoApp().equals("Y")){
					preferredTimeQuestion=questionDao.getQuestion(107);
					preferredTimeQuestion.setAnswer(map.get("q107"));
					for(SubQuestion sq:preferredTimeQuestion.getSubquestions())
						sq.setAnswer(map.get("sq"+sq.getId()));
					
					if(preferredTimeQuestion != null){
						for(SubQuestion sq : preferredTimeQuestion.getSubquestions()){
							switch(sq.getId()){
							case 241:
								preferredTimeMon = Integer.parseInt(sq.getAnswer());
								break;
							case 242:
								preferredTimeTue = Integer.parseInt(sq.getAnswer());
								break;
							case 243:
								preferredTimeWed = Integer.parseInt(sq.getAnswer());
								break;
							case 244:
								preferredTimeThu = Integer.parseInt(sq.getAnswer());
								break;
							case 245:
								preferredTimeFri = Integer.parseInt(sq.getAnswer());
								break;
							case 246:
								preferredTimeSat = Integer.parseInt(sq.getAnswer());
								break;
							case 247:
								preferredTimeSun = Integer.parseInt(sq.getAnswer());
								break;
							}
						}
					}
				}
				
				System.out.println("end !!");
				
				
				/**
				 * set feedback object
				 */
				PF pf = new PF();
				pf.alcoholPercentage = alcoholPercentage;
				pf.alcoholPercentile = alcoholPercentile;
				pf.averageDrink = averageDrink;
				pf.drinkPercentile = drinkPercentile;
				pf.total = total;
				pf.maxTotal = maxTotal;
				pf.firstDrink = firstDrink;
				pf.firstDrinkHours = firstDrinkHours;
				pf.maxHours = (int)maxHours;
				pf.typicalBAC = df.format(typicalBAC);
				pf.typicalSober = (int)typicalSober;
				pf.peakBAC = df.format(peakBAC);
				pf.peakSober = (int)peakSober;
				pf.monthlyCalReq = monthlyCalReq;
				pf.alcoholCal = alcoholCal;
				pf.alcoholPercent = alcoholPercent;
				pf.byaacqScore = byaacqScore;
				pf.byaacqProblems = byaacqProblems;
				pf.byaacqRange = byaacqRange;
				pf.auditScore = auditScore;
				pf.auditRisc = auditRisc;
				pf.geneticRisk = geneticRisk;
				pf.ruler1Text = ruler1Text;
				pf.ruler2Text = ruler2Text;
				pf.pcaCategory = pcaCategory;
				pf.preferredTimeMon = preferredTimeMon;
				pf.preferredTimeTue = preferredTimeTue;
				pf.preferredTimeWed = preferredTimeWed;
				pf.preferredTimeThu = preferredTimeThu;
				pf.preferredTimeFri = preferredTimeFri;
				pf.preferredTimeSat = preferredTimeSat;
				pf.preferredTimeSun = preferredTimeSun;
				pf.gender = gender;
				pf.genderString = genderString;
				pf.weight = weight;
				pf.maxAlcoholCal = maxAlcoholCal;
				pf.maxAlcoholPercent = maxAlcoholPercent;
				pf.alpha = alpha;
				pf.alcoholUsage = alcoholUsage;
				pf.cea1 = cea1;
				pf.cea2 = cea2;
				pf.alcoholPercent1 = alcoholPercent1;
				pf.alcoholPercent2 = alcoholPercent2;
				pf.maxAlcoholPercent1 = maxAlcoholPercent1;
				pf.maxAlcoholPercent2 = maxAlcoholPercent2;
				pf.maxAlcoholCal1 = maxAlcoholCal1;
				pf.maxAlcoholCal2 = maxAlcoholCal2;
				pf.byaacq = byaacq;
				pf.auditPoints = auditPoints;
				pf.ruler1 = ruler1;
				pf.ruler2 = ruler2;
				pf.pssNever = pssNever;
				pf.pssRarely = pssRarely;
				pf.pssOccasionally = pssOccasionally;
				pf.pssSometimes = pssSometimes;
				pf.pssUsually = pssUsually;
				pf.pssAlways = pssAlways;
				pf.r = r;
				
				
				
				String pfJson = mapper.writeValueAsString(pf);
				System.out.println("pfJson ::"+pfJson);
				Feedback feedback = new Feedback();
				feedback.setFeedback(pfJson);
				feedback.setUser_id(user.getId());
				
				feedbackDao.saveOrUpdate(feedback);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getGender(int user_id){
		String gender = "Male";
		Feedback feedback = feedbackDao.get(user_id);
		ObjectMapper mapper = new ObjectMapper();
		PF pf;
		try {
			pf = mapper.readValue(feedback.getFeedback(), PF.class);
			//System.out.println("PF : "+pf);
			gender=pf.genderString;
		}catch(Exception e){
			e.printStackTrace();
		}
		return gender;
	}
	
	public Survey getAllSurveyQuestionsWithFeedback(User user){
		Survey survey = new Survey();
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page1"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page2"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page3"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page4"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page5"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page6"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page7"));
		survey.getUserSurveyList().add(userSurveyDao.get(user.getId(), "baseline_survey_page8"));
		
		return survey;
	}
	
	/*public NewUserSurvey getAllNewSurveyQuestionsWithFeedback(User user){
		NewUserSurvey survey = new NewUserSurvey();
		List<UserSurveyResponse> userSurveyResponse=userSurveyDao.getAllUserSurveyResponse(user.getId());
		if(userSurveyResponse!=null && userSurveyResponse.size()>0)
			survey.setId(user.getId());
		for(UserSurveyResponse u:userSurveyResponse){
			survey.getUserSurveyList().add(u);
		}
		return survey;
	}*/
	
	public Survey getAllNewSurveyQuestionsWithFeedback(User user) {
		Survey survey = new Survey();
		ObjectMapper mapper = new ObjectMapper();

		List<UserSurveyResponse> userSurvey1 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page1");
		List<UserSurveyResponse> userSurvey2 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page2");
		List<UserSurveyResponse> userSurvey3 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page3");
		List<UserSurveyResponse> userSurvey4 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page4");
		List<UserSurveyResponse> userSurvey5 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page5");
		List<UserSurveyResponse> userSurvey6 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page6");
		List<UserSurveyResponse> userSurvey7 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page7");
		List<UserSurveyResponse> userSurvey8 = userSurveyDao.getUserSurveyResponse(user.getId(),
				"baseline_survey_page8");

		UserSurvey userSurvey = null;

		if (userSurvey1 != null && userSurvey1.size() > 0) {
			ArrayList<Question> survey1 = (ArrayList<Question>) createUserSurveyObj(userSurvey1,
					questionDao.getSurvey("baseline", 1));

			try {
				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page1");
				userSurvey.setResponse(mapper.writeValueAsString(survey1));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey2 != null && userSurvey2.size() > 0) {
			ArrayList<Question> survey2 = (ArrayList<Question>) createUserSurveyObj(userSurvey2,
					questionDao.getSurvey("baseline", 2));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page2");
				userSurvey.setResponse(mapper.writeValueAsString(survey2));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey3 != null && userSurvey3.size() > 0) {
			ArrayList<Question> survey3 = (ArrayList<Question>) createUserSurveyObj(userSurvey3,
					questionDao.getSurvey("cea", 3));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page3");
				userSurvey.setResponse(mapper.writeValueAsString(survey3));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey4 != null && userSurvey4.size() > 0) {
			ArrayList<Question> survey4 = (ArrayList<Question>) createUserSurveyObj(userSurvey4,
					questionDao.getSurvey("baseline", 4));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page4");
				userSurvey.setResponse(mapper.writeValueAsString(survey4));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey5 != null && userSurvey5.size() > 0) {
			ArrayList<Question> survey5 = (ArrayList<Question>) createUserSurveyObj(userSurvey5,
					questionDao.getSurvey("baseline", 5));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page5");
				userSurvey.setResponse(mapper.writeValueAsString(survey5));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey6 != null && userSurvey6.size() > 0) {
			ArrayList<Question> survey6 = (ArrayList<Question>) createUserSurveyObj(userSurvey6,
					questionDao.getSurvey("pss", 6));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page6");
				userSurvey.setResponse(mapper.writeValueAsString(survey6));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey7 != null && userSurvey7.size() > 0) {
			ArrayList<Question> survey7 = (ArrayList<Question>) createUserSurveyObj(userSurvey7,
					questionDao.getSurvey("rcq", 7));

			try {

				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page7");
				userSurvey.setResponse(mapper.writeValueAsString(survey7));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userSurvey8 != null && userSurvey8.size() > 0) {
			ArrayList<Question> survey8 = (ArrayList<Question>) createUserSurveyObj(userSurvey8,
					questionDao.getSurvey("baseline", 8));

			try {
				userSurvey = new UserSurvey();
				userSurvey.setId(user.getId());
				userSurvey.setSurvey("baseline_survey_page8");
				userSurvey.setResponse(mapper.writeValueAsString(survey8));
				survey.getUserSurveyList().add(userSurvey);

			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return survey;
	}
	public boolean isFeedBackGenerated(int user_id){
		boolean isGenerated = false;
		Feedback feedback = feedbackDao.get(user_id);
		if(feedback!=null)
			isGenerated=true;
		return isGenerated;
	}
	
}
