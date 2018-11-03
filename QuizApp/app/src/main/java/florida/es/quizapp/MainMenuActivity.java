package florida.es.quizapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Main activity of the application. Shows a main menu where the user can either start a new game
 * or share his/her best score
 */
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * References to the buttons in the graphical interface
     */
    protected Button mNewGameButton = null;
    protected Button mShareButton = null;

    /**
     * Identifier to distinguish if the intent that returned is the new game
     */
    protected static int REQUEST_CODE_NEW_GAME = 0;

    /**
     * Attribute employed to hold the best score in this run
     */
    protected int iBestScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Get references to the buttons, so that they can be handled in Java
        mNewGameButton = findViewById(R.id.bt_new_game);
        mShareButton = findViewById(R.id.bt_share);

        //Sets a listener for each of the buttons.
        mNewGameButton.setOnClickListener(this);
        mShareButton.setOnClickListener(this);

    }

    /**
     * Method employed to handle returns activity results
     * @param requestCode The id of the type of intent that returned the result
     * @param resultCode How the intent request finished
     * @param data The intent of data returned from the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_NEW_GAME && resultCode == RESULT_OK && data!=null){
            iBestScore=Math.max(iBestScore,data.getExtras().getInt(getString(R.string.score_key)));
        }
    }

    /**
     * The listener implemented in this activity. Adds logic to the buttons when clicked
     * @param v The view that triggered the onClick event
     */
    @Override
    public void onClick(View v) {
        //Get the id of the control that triggered the event
        int id = v.getId();

        switch(id){
            case R.id.bt_new_game: //New game. Launch activity for the new game, and wait for score result
                Intent intent = new Intent(this,QuestionActivity.class);
                startActivityForResult(intent,REQUEST_CODE_NEW_GAME);
                break;
            case R.id.bt_share: //Share best score using apps that can send text
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"My best score this time is " + iBestScore);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
                break;
        }

    }

}
