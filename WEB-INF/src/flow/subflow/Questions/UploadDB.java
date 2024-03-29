package flow.subflow.Questions;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;

import flow.IProjectVariables;

/**
 * The Data class handles many types of server-side operations including data
 * collection (from a data sources such as a database, or web service), variable
 * assignments and operations (like copying variable values, performing
 * mathematic operations, and collection iteration), conditional evaluation to
 * control callflow execution based on variable values, and logging/tracing
 * statements.
 * 
 * Items created in the getDataActions() method are executed/evaluated in order
 * and if a condional branch condition evaluates to "true" then the branch is
 * activated and the execution of data actions is halted. If no "true"
 * conditions are encountered, then all data actions will be executed/evaluated
 * and the application will proceed to the "Default" servlet. Last generated by
 * Orchestration Designer at: 2022-DEC-23 01:20:36 PM
 */
public class UploadDB extends com.avaya.sce.runtime.Data {

	// {{START:CLASS:FIELDS
	// }}END:CLASS:FIELDS

	/**
	 * Default constructor Last generated by Orchestration Designer at: 2022-DEC-23
	 * 01:20:36 PM
	 */
	public UploadDB() {
		// {{START:CLASS:CONSTRUCTOR
		super();
		// }}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:33 PM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = new com.avaya.sce.runtime.Next("Questions-thanks", "Default");
		next.setDebugId(144);
		return next;
	}

	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:33 PM
	 */
	public java.util.Collection<VariableInfo> getLocalVariables(){
		java.util.Collection<VariableInfo> variables = new java.util.ArrayList<VariableInfo>();

		return variables;
	}

	/**
	 * Creates and conditionally executes operations that have been configured
	 * in the Callflow.  This method will build a collection of operations and
	 * have the framework execute the operations by calling evaluateActions().
	 * If the evaluation causes the framework to forward to a different servlet
	 * then execution stops.
	 * Returning true from this method means that the framework has forwarded the
	 * request to a different servlet.  Returning false means that the default
	 * Next will be invoked.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:33 PM
	 */
	public boolean executeDataActions(com.avaya.sce.runtimecommon.SCESession mySession) throws Exception {
		java.util.Collection actions = null;

		actions = new java.util.ArrayList(1);
		if(evaluateActions(actions, mySession)) {
			return true;
		}
		actions = null;

		// return false if the evaluation of actions did not cause a servlet forward or redirect
		return false;
	}

	@Override
	public void requestBegin(SCESession mySession) {
		
		String user=mySession.getVariableField(IProjectVariables.USER,IProjectVariables.USER_FIELD_USERNAME).getStringValue();
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Username:"+user, mySession);

		String ques1 = getAnswer(mySession.getVariableField(IProjectVariables.QUESTION_1,IProjectVariables.QUESTION_1_FIELD_VALUE).getStringValue());
		String ques2 = getAnswer(mySession.getVariableField(IProjectVariables.QUESTION_2,IProjectVariables.QUESTION_2_FIELD_VALUE).getStringValue());
		String ques3 = getAnswer(mySession.getVariableField(IProjectVariables.QUESTION_3,IProjectVariables.QUESTION_3_FIELD_VALUE).getStringValue());
		String ques4 = getAnswer(mySession.getVariableField(IProjectVariables.QUESTION_4,IProjectVariables.QUESTION_4_FIELD_VALUE).getStringValue());
		
		
		TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "question1:"+ques1, mySession);
		
				try {
			FileInputStream f = new FileInputStream("D:/IVR Projects/CallSurvey/data/JDBC.properties");

			Properties p = new Properties();
			p.load(f);
			
			String url=p.getProperty("url"); 
			String username=p.getProperty("username");
			String password=p.getProperty("password");
			
			Connection con = null;
			
			try {
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				con = DriverManager.getConnection(url,username,password);
			} catch(Exception e2) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "exception occoured in connection creation"+e2.getMessage(), mySession);
			}

			PreparedStatement st = con.prepareStatement("insert into survey_response(customer_name,question1,question2,question3,question4)values(?,?,?,?,?)");
			st.setString(1, user);
			st.setString(2, ques1);
			st.setString(3, ques2);
			st.setString(4, ques3);
			st.setString(5, ques4);
			
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Database accessed succesfully", mySession);
			st.execute();
			f.close();
			con.close();
			st.close();
			
		} catch (Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "exception occoured", mySession);
		}
		
	}

	private String getAnswer(String value) {
		if (value.equals("1")) {
			return "Good";
		} else if (value.equals("2")) {
			return "Average";
		} else if (value.equals("3")) {
			return "Bad";
		}else {
			return "No match";
		}
	}
}
