package org.academicode.tipcalculator;

//This is the class that will first be run when the app is first opened

//import is #include from C++
    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    //Extends = inherits, implements means it interfaces with another class
    public class Main extends Activity implements OnClickListener
    {
        //Declaring and setting string variables
        private static final String TAG_DEBUG = Main.class.getName();
        public static final String TAG_TIP = "tag";
        public static final String TAG_GRAND_TOTAL = "total";

        //Creating the EditText object
        private EditText et;
        private EditText et2;
	
	//Creating button objects, which extend (inherit) the View class
	private Button signIn;
	private Button signUp;
	
	@Override
	/*An onCreate method/function is what is called when a screen is first showed.
	  In this case, this is the main menu screen.
	*/
	protected void onCreate(Bundle savedInstanceState) 
	{
		//The super keyword is used to refer to the parent class in java
		super.onCreate(savedInstanceState);
		
		//How the activity actually looks is inside main.xml, inside the layout folder
		setContentView(R.layout.main);
		
		// The edit text has a string value set by the edit Text IDs in Main.xml
		et = (EditText) findViewById(R.id.main_nickname_input);
        et2 = (EditText) findViewById(R.id.main_password_input);


		
		//The buttons have parameters corresponding to the IDs in Main.xml
		signIn = (Button) findViewById(R.id.main_signIn);
		signUp = (Button) findViewById(R.id.main_signUp);
		
		/*The buttons now have onClickListeners set, a method/function of the button class
		 * to start a new activity/intent when pressed. In this case, pressing a button
		 * will go to the results page.
		 * */
		signIn.setOnClickListener(this);
		signUp.setOnClickListener(this);
	}

	@Override
	/*onClick is what is called when the buttons are pressed and they take in Views as arguments
	 * as buttons are children of the view class, buttons can polymorphically be passed in. The button
	 * that called the onClick is automatically fed in*/


    //BELOW ,WE WILL IMPLEMENT THE ERROR CHECKING FUNCTION TO MAKE SURE PASSWORD+USERNAME+NICKNAME ARE VALID ENTRIES:
 	public void onClick(View v)
	{
		double tipPercent = 0.0d;
		
		//The switch statements grab the id values of the button pressed and calculates the tip accordingly
		switch(v.getId()){

			case R.id.tip15 :
			{
				tipPercent = 0.15d;
				
				break;
			}
			case R.id.tip20 :
			{
				tipPercent = 0.20d;
				
				break;
			}
			default :
			{
				break;
			}
		}
		
		//Pulls the input from the EditText
		String text = et.getText().toString();
        String text2 = et2.getText().toString();
		
		//If the user tried to proceed without entering a value
		if(text.equals(""))
		{
			//Show a toast telling them they need to enter a value. Text is pulled from strings.xml
			Toast.makeText(Main.this, getResources().getString(R.string.error_et), Toast.LENGTH_LONG).show();
			//Breaks out of the onClick, as we do not want to launch a new Activity without entered values
			return;
		}
        else if(text2.equals("") || text3.equals("") || text4.equals("") || text5.equals(""))
        {
            //Show a toast telling them they need to enter a username, password, or nickname rspctly. Text is pulled from strings.xml
            Toast.makeText(Main.this, geResources().getString(r.string.error_et2), Toast.LENGTH_LONG).show();
            Toast.makeText(Main.this, geResources().getString(r.string.error_et3), Toast.LENGTH_LONG).show();
            Toast.makeText(Main.this, geResources().getString(r.string.error_et4), Toast.LENGTH_LONG).show();
            Toast.makeText(Main.this, geResources().getString(r.string.error_et5), Toast.LENGTH_LONG).show();
            //Breaks out of the onClick, as we do not want to launch a new Activity without entered values
            return;
        }
		
		/*If all is good, launch a new activity that passes in the amount entered and tip percentage
		NOTE: Double.parseDouble(string) turns a string into a double. It is used to cast the user's input
		to a double and feed it into the new activity
		*/
		launchResultActivity(Double.parseDouble(et.getText().toString()), tipPercent);
	}
	
	/* The launchResultActivity method is used to start a new activity from within an onClickListener
	 * */

    //BELOW, WE WILL HAVE THE "SIGN UP" BUTTON IMPLEMENTED SO WHEN THE USER CLICKS IT AFTER REGISTERING
        //THEY WILL BE DIRECTED TO THE NEW SET OF SCREENS (I.E ACTIVITY LOG, PROFILE, ACTIVE CHATS E.T.C)
 	private void launchResultActivity(double total, double tipPercent)
	{
		double tip = total * tipPercent;
		double grandTotal = total + tip;
		
		/*The intent class represents an action is used to "load" activities into a variable so they can be passed in and launched from
		 * the startActivity method. Basic intents take two arguments, the current class(.java) and the class(.java) that the app will move to
		 *  The line below initializes an Intent named resultActivity and passes in (Main.this,Result.class) much like the this-> pointer in C++,
		 *  the this keyword in java is used by classes to reference themselves*/
		Intent resultActivity = new Intent(Main.this, SignUp.class);
		
		/*Since this method is private, if we want the Result Activity/class to access it's members (the strings TAG_TIP and TAG_GRAND_TOTAL),
		 *we can "push" members from the Main Acivity/class to Result, much like how a friend function can "pull" private members from objects
		*/
		resultActivity.putExtra(TAG_TIP, tip);
		resultActivity.putExtra(TAG_GRAND_TOTAL, grandTotal);
		
		Log.d(TAG_DEBUG, "Tip: " + tip);
		Log.d(TAG_DEBUG, "Grand Total: " + grandTotal);
		
		//Launches the new activity
		startActivity(resultActivity);
	}
}