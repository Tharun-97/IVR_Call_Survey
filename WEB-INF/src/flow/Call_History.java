package flow;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.CallSurvey.DAO.CallSurveyDAO;

import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;
import com.avaya.sce.runtimecommon.VariableTypeException;

/**
 * The Data class handles many types of server-side operations including data
 * collection (from a data sources such as a database, or web service), variable
 * assignments and operations (like copying variable values, performing mathematic
 * operations, and collection iteration), conditional evaluation to control callflow
 * execution based on variable values, and logging/tracing statements.
 * 
 * Items created in the getDataActions() method are executed/evaluated in order
 * and if a condional branch condition evaluates to "true" then the branch is
 * activated and the execution of data actions is halted.  If no "true" conditions
 * are encountered, then all data actions will be executed/evaluated and the 
 * application will proceed to the "Default" servlet.
 * Last generated by Orchestration Designer at: 2023-JAN-10  02:38:54 PM
 */
public class Call_History extends com.avaya.sce.runtime.Data {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2023-JAN-10  02:38:54 PM
	 */
	public Call_History() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2023-FEB-13  04:34:09 PM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = new com.avaya.sce.runtime.Next("exit", "Default");
		next.setDebugId(457);
		return next;
	}
	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2023-FEB-13  04:34:09 PM
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
	 * Last generated by Orchestration Designer at: 2023-FEB-13  04:34:09 PM
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
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String end = dtf.format(now);
		mySession.getVariableField(IProjectVariables.CALL__HISTORY, IProjectVariables.CALL__HISTORY_FIELD_ENDDATE).setValue(end);
		
		 try {
			mySession.getVariableField(IProjectVariables.CALL__HISTORY, IProjectVariables.CALL__HISTORY_FIELD_DURATION).setValue(Duration(mySession));
		} catch (VariableTypeException | ParseException e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Error in Duration calculation" + e, mySession);
		}    
		
		 InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			mySession.getVariableField(IProjectVariables.CALL__HISTORY, IProjectVariables.CALL__HISTORY_FIELD_IP).setValue(ip);
		} catch (UnknownHostException e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Error in IP address fetching" + e, mySession);
		}
		
		String flowname = "Call Survey";
		String ucid = mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_UCID).getStringValue();
		String clid = mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_ANI).getStringValue();
		String dnis = mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_DNIS).getStringValue();
		String sessionId = mySession.getVariableField(IProjectVariables.SESSION, IProjectVariables.SESSION_FIELD_SESSIONID).getStringValue();
		String startdate= mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_STARTDATE).getStringValue();
		String enddate=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_ENDDATE).getStringValue();
		String duration=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_DURATION).getStringValue();
		String exitlocation=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_EXITNODE).getStringValue();
		String exitreason=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_EXITREASON).getStringValue();
		String appip=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_IP).getStringValue();
		
		CallSurveyDAO cs = new CallSurveyDAO();

		try {
			cs.propertyRead();
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Property File accessed sucessfully ", mySession);
		} catch (Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Property File not accessed" + e, mySession);
		}

		try {
			cs.getConnectionDetails();
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Connection successful ", mySession);
		} catch (Exception e2) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "exception occoured in connection creation" + e2.getMessage(),
					mySession);
		}

		try {
			PreparedStatement dataInsert = cs.DataInsert(query(mySession));
		
			dataInsert.setString(1, flowname);
			dataInsert.setString(2, ucid);
			dataInsert.setString(3, clid);
			dataInsert.setString(4, dnis);
			dataInsert.setString(5, sessionId);
			dataInsert.setString(6, startdate);
			dataInsert.setString(7,enddate);
			dataInsert.setString(8, duration);
			dataInsert.setString(9, exitlocation);
			dataInsert.setString(10,exitreason);
			dataInsert.setString(11,appip);
			dataInsert.execute();
			
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Database inserted succesfully", mySession);
			dataInsert.close();
		} catch (Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Error Occoured" + e, mySession);
		}

	}

	private String query(SCESession mySession) {
		
		return "insert into call_history(flow_name,ucid,clid,dnis,session_id,start_date,end_date,duration,exit_location,end_reason,app_ip)values(?,?,?,?,?,?,?,?,?,?,?)" ;
	}
	
	private long Duration(SCESession mySession) throws ParseException {
		String startdate= mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_STARTDATE).getStringValue();
		String enddate=mySession.getVariableField(IProjectVariables.CALL__HISTORY,IProjectVariables.CALL__HISTORY_FIELD_ENDDATE).getStringValue();
		 SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

		    Date d1 = format.parse(startdate);
		    Date d2 = format.parse(enddate);
		   
		    long diff = d2.getTime() - d1.getTime();
		    long diffSeconds = diff / 1000;
		   System.out.println("diffSeconds:"+diffSeconds);
		    return diffSeconds;
	}
	
	
	
}
