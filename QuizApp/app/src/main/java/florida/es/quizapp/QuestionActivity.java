package florida.es.quizapp;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Activity that shows the questions and lets the player play the quiz game
 */
public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * References to elements in the graphical interface
     */
    protected TextView mCurrentQuestionText = null;
    protected TextView mCurrentScoreText = null;
    protected LinearLayout mQuestionsLinearLayout = null;
    protected Chronometer mChrono = null;

    /**
     * Variables that hold the list of questions for the quiz, the current question in the quiz, and the current score
     */
    protected List<Question> mListQuestions = null;
    protected int iCurrentQuestion = -1;
    protected int iCurrentScore = 0;

    /**
     * Keys employed to read fields from the json database of questions
     */
    protected static String RESULTS_JSON = "results";
    protected static String QUESTION_JSON = "question";
    protected static String QUESTION_INCORRECT_OPTIONS = "incorrect_answers";
    protected static String QUESTION_CORRECT_OPTION = "correct_answer";


    /**
     * method that reads and parses the questions from the json database of the app, and loads them
     * into a List of Questions
     * @return A List of Question with the questions for the quiz. Null if error reading file
     */
    protected List<Question> readQuestions(){
        InputStream stream = null;
        BufferedReader reader = null;
        JSONObject jsonObject = null;
        List<Question> listQuestions = null;
        try {
            stream = getResources().openRawResource(R.raw.questions);
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            //Read file line by line and make a create a single string with the content of the file
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            stream.close();

            //Create json object with the database from the single string
            jsonObject = new JSONObject(jsonString.toString());
            JSONArray questions = jsonObject.getJSONArray(RESULTS_JSON);

            listQuestions = new ArrayList<Question>();

            int totalQuestions = questions.length();
            //Parse questions from the JSON object
            for(int i=0;i<totalQuestions;i++){
                JSONObject jsonQuestion = questions.getJSONObject(i);
                String description = jsonQuestion.getString(QUESTION_JSON);
                String correctAnswer = jsonQuestion.getString(QUESTION_CORRECT_OPTION);
                List<String> answers = new ArrayList<String>();
                answers.add(correctAnswer);
                JSONArray incorrectAnswers = jsonQuestion.getJSONArray(QUESTION_INCORRECT_OPTIONS);
                for(int j=0;j<incorrectAnswers.length();j++){
                    answers.add(incorrectAnswers.getString(j));
                }
                Collections.shuffle(answers); //Shuffle options orders
                Question question = new Question(description,answers,correctAnswer);
                listQuestions.add(question);
            }
            //Shuffle questions' order
            Collections.shuffle(listQuestions);

        } catch(IOException e){
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }

        return listQuestions;

    }

    /**
     * Method that creates a dynamic button programmatically by passing a text and a listener for clicks
     * @param text The text to be displayed in the button
     * @param listener The listener to be attached to the button
     * @return A dynamic button that can be embedded in the activity, using its look & feel
     */
    protected Button createButtonAnswer(String text, View.OnClickListener listener){
        Button b = new Button(this);
        //Generate a new unique id for the button
        b.setId(View.generateViewId());
        b.setOnClickListener(listener);
        b.setText(text);
        //Set the background color for the button
        b.setBackground(ContextCompat.getDrawable(this,R.color.colorButtonMainMenu));
        b.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        //Layout params for the button
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0,0,0,10);
        b.setLayoutParams( lp );
        return b;
    }

    /**
     * Method that changes the graphical interface according to the question to be shown on the screen,
     * or congratulations message in case that the game is finished
     * @param questionIndex The question to be rendered in the graphical interface
     */
    protected void loadQuestionInUI(int questionIndex){
        if(questionIndex>=0 &&  questionIndex<mListQuestions.size()){ //Check valid question index
            mCurrentScoreText.setText("Current Score: " + iCurrentScore); //Update score accordingly
            Question question = mListQuestions.get(questionIndex); //Get question from bank
            mCurrentQuestionText.setText(question.getsDescription()); //Update question on screen

            mQuestionsLinearLayout.removeAllViews(); //Delete previous options from linear layout
            for(String answer : question.getOptions()){ //For each answer, we create a button with a possible option
                Button b = createButtonAnswer(answer,this);
                mQuestionsLinearLayout.addView(b); //Add button to linear layout
            }


        } else if(questionIndex==mListQuestions.size()){ //End of the quiz
            mCurrentScoreText.setText("Current Score: " + iCurrentScore); //Update score
            mCurrentQuestionText.setText("Congratulations, you have finished the quiz with " +
                    iCurrentScore + " points"); //Show congratulations message
            mQuestionsLinearLayout.removeAllViews();

            Button b = createButtonAnswer("Go back to menu", new View.OnClickListener() { //Add back to menu button
                @Override
                public void onClick(View v) {
                    getIntent().putExtra("SCORE",iCurrentScore);
                    Intent intent = getIntent();
                    intent.putExtra(getString(R.string.score_key),iCurrentScore);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
            mQuestionsLinearLayout.addView(b);
        }
    }

    /**
     * On click listener that adds logic to answer buttons
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        List<Integer> listIdsAnswers = new ArrayList<Integer>();
        Question question =  mListQuestions.get(iCurrentQuestion);
        List<String> answers = question.getOptions();
        for(int i=0;i<mQuestionsLinearLayout.getChildCount();i++){ //For each button in linear layout
            View answerView = mQuestionsLinearLayout.getChildAt(i);
            if(answerView.getId()==id){ //Check if the button is the one that triggered the click
                String chosenAnswer = answers.get(i);
                if(chosenAnswer.equals(question.getCorrectOption())){ //If correct answer, update score
                    //Correct option
                    iCurrentScore++;
                    break;
                }

            }
        }
        iCurrentQuestion++; //Update question number
        loadQuestionInUI(iCurrentQuestion); //Reload question in UI
    }

    /**
     * Fills the top tool bar with options defined in xml
     * @param menu The menu to be created
     * @return true if everything went well, false otherwise
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_question_activity,menu);
        return true;
    }

    /**
     * The method employed to handle clicks on the top toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==  R.id.browser_action){ //Browser option, launch Google search with question

            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, mCurrentQuestionText.getText().toString());

            if( intent.resolveActivity( getApplicationContext().getPackageManager() ) != null ){
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Get references to views
        mCurrentQuestionText = findViewById(R.id.tv_question);
        mQuestionsLinearLayout = findViewById(R.id.ll_questions);
        mCurrentScoreText = findViewById(R.id.tv_current_score);
        mChrono = findViewById(R.id.cr_time);

        //Start chronometer
        mChrono.start();

        //Initialize questions in db
        iCurrentQuestion = 0;
        iCurrentScore = 0;
        mListQuestions = readQuestions();


        //Update UI with question
        loadQuestionInUI(iCurrentQuestion);

    }




}
