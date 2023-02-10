package flow;

import java.sql.ResultSet;

import org.CallSurvey.DAO.CallSurveyDAO;

import com.avaya.sce.runtime.tracking.TraceInfo;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.SCESession;



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
 * Orchestration Designer at: 2022-DEC-28 02:49:05 PM
 */
public class valid extends com.avaya.sce.runtime.Data {

	// {{START:CLASS:FIELDS
	// }}END:CLASS:FIELDS

	/**
	 * Default constructor Last generated by Orchestration Designer at: 2022-DEC-28
	 * 02:49:05 PM
	 */
	public valid() {
		// {{START:CLASS:CONSTRUCTOR
		super();
		// }}END:CLASS:CONSTRUCTOR
	}

	@Override
	public void requestBegin(SCESession mySession) {

		CallSurveyDAO cs = new CallSurveyDAO();
		ResultSet rs=null;
		String bookid = mySession.getVariableField(IProjectVariables.BOOKING__ID, IProjectVariables.BOOKING__ID_FIELD_VALUE).getStringValue();

		try {
			try {
				cs.propertyRead();
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Property File accessed sucessfully ", mySession);
			} catch (Exception e) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Property File not accessed" + e, mySession);
			}

			try {
				cs.getConnectionDetails();
			} catch (Exception e2) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR,"exception occoured in connection creation" + e2.getMessage(), mySession);
			}
			
			try {
				rs = cs.connectionPooling(query(mySession));
			} catch (Exception e) {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "Error Occoured"+e, mySession);
			}
			
			if (rs.next()) {
				if (rs.getString("booking_id").equals(bookid)) {
					mySession.getVariableField(IProjectVariables.VALIDUSER).setValue("true");

					String name = rs.getString("customer_name");
					mySession.getVariableField(IProjectVariables.USER, IProjectVariables.USER_FIELD_USERNAME)
							.setValue(name);

					TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "customer_name retrived sucessfully " + name,
							mySession);
				} else {
					mySession.getVariableField(IProjectVariables.VALIDUSER).setValue("false");
				}
			} else {
				TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "customer_name not retrived", mySession);
			}

			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_INFO, "Database accessed succesfully", mySession);

		} catch (Exception e) {
			TraceInfo.trace(ITraceInfo.TRACE_LEVEL_ERROR, "exception occoured"+e, mySession);
		}

		mySession.getVariableField(IProjectVariables.CALL__HISTORY, IProjectVariables.CALL__HISTORY_FIELD_EXITNODE).setValue("Booking id Validation node");
		mySession.getVariableField(IProjectVariables.CALL__HISTORY, IProjectVariables.CALL__HISTORY_FIELD_EXITREASON).setValue("Hangup");
	}

	private String query(SCESession mySession) {
		String bookid = mySession
				.getVariableField(IProjectVariables.BOOKING__ID, IProjectVariables.BOOKING__ID_FIELD_VALUE)
				.getStringValue();
		return "select * from hotelcustomer where booking_id=" + bookid;
	}

	/**
	 * Returns the Next item which will forward application execution
	 * to the next form in the call flow.
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:34 PM
	 */
	public com.avaya.sce.runtime.Next getNext(com.avaya.sce.runtimecommon.SCESession mySession) {
		com.avaya.sce.runtime.Next next = null;
		return next;
	}

	/**
	 * Create a list of local variables used by items in the data node.
	 * 
	 * This method is generated automatically by the code generator
	 * and should not be manually edited.  Manual edits may be overwritten
	 * by the code generator.
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:34 PM
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
	 * Last generated by Orchestration Designer at: 2023-FEB-09  04:28:34 PM
	 */
	public boolean executeDataActions(com.avaya.sce.runtimecommon.SCESession mySession) throws Exception {
		java.util.Collection actions = null;

		actions = new java.util.ArrayList(1);
		if(evaluateActions(actions, mySession)) {
			return true;
		}
		actions = null;

		if(((com.avaya.sce.runtime.Condition)new com.avaya.sce.runtime.Condition("condition1", "validuser", com.avaya.sce.runtime.Expression.STRING_EQUAL, "true", false).setDebugId(437)).evaluate(mySession)) {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("welcome_user", "validUser").setDebugId(434));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;

		} else {
			actions = new java.util.ArrayList(1);
			actions.add(new com.avaya.sce.runtime.Next("InvalidDetails", "invalidUser").setDebugId(439));
			if(evaluateActions(actions, mySession)) {
				return true;
			}
			actions = null;
		}


		// return false if the evaluation of actions did not cause a servlet forward or redirect
		return false;
	}
}