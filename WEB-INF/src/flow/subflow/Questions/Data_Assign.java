package flow.subflow.Questions;

/**
 * This servlet is used to forward the request to the entry point of a
 * project callflow (subflow).
 * Last generated by Orchestration Designer at: 2022-DEC-23  10:42:43 AM
 */
public class Data_Assign extends com.avaya.sce.runtime.Subflow {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2022-DEC-23  10:42:43 AM
	 */
	public Data_Assign() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * Returns the name of the subflow that is being invoked.  This name is used for
	 * determining the URL mapping for the the entry point of the subflow..
	 *
	 * @return the name of the subflow
	 * Last generated by Orchestration Designer at: 2022-DEC-23  01:20:36 PM
	 */
	protected String getSubflowName() {
		return("DataAssign");
	}
	/**
	 * Returns the name of the mapping of sub flow exit points to the URL mappings
	 * of the servlets to return back to in the calling flow.
	 *
	 * @return map of sub flow exit points to servlets in the calling flow.
	 * Last generated by Orchestration Designer at: 2022-DEC-23  01:20:36 PM
	 */
	protected java.util.Map<String,String> getExitPoints() {
		java.util.Map<String, String> exitPoints;
		exitPoints = new java.util.HashMap<String, String>();
		exitPoints.put("DataAssign-exit1", "Questions-question2");
		return exitPoints;
	}
}
