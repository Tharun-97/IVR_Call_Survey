package english.grammars;

/**
 * This class is generated automatically.
 * Only exit this class for Dynamic External grammars and override the method String getURL().
 * Last generated by Orchestration Designer at: 2022-DEC-07  06:26:34 PM
 */
public class Question2 extends com.avaya.sce.runtime.Grammar {

    //{{START:CLASS:FIELDS
    //}}END:CLASS:FIELDS

    /**
    * Constructor for Question2.
    */
    public Question2() {
		//{{START:CLASS:CONSTRUCTOR
        super();
        setRootLanguage("en-us");
        setMode("dtmf");
        setBuiltin(true);
        setBuiltinMode("dtmf");
        setType("digits");
        setOptions("length=1");
		//}}END:CLASS:CONSTRUCTOR
    }

}
