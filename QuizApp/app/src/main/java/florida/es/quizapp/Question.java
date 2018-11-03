package florida.es.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a question in the quiz
 */
public class Question {

    /**
     * The content of the question
     */
    protected String sDescription = null;
    /**
     * The available options/answers for the question
     */
    protected List<String> mOptions = null;
    /**
     * The index of the correct option among the answers
     */
    protected int iCorrectOption = -1;

    /**
     * Constructor that takes a question's content, the list of available answers, and the correct option
     *
     * @param description The content of the questions
     * @param options     The options to choose from
     * @param correct     The correc toption
     */
    protected Question(String description, List<String> options, String correct) {
        sDescription = new String(description);
        mOptions = new ArrayList<String>(options);
        //Linear search to find the index of the correct option
        for (int i = 0; i < mOptions.size(); i++) {
            String option = mOptions.get(i);
            if (option.equals(correct)) {
                iCorrectOption = i;
                break;
            }
        }
    }

    /**
     * Gets the content of the question
     *
     * @return A string with the content of the question
     */
    public String getsDescription() {
        return sDescription;
    }

    /**
     * The list of options for the answers
     *
     * @return A list of Strings with the options for the answers
     */
    public List<String> getOptions() {
        return mOptions;
    }

    /**
     * Gets the correct option for the question
     *
     * @return A string with the correct option for the question
     */
    public String getCorrectOption() {
        return mOptions.get(iCorrectOption);
    }

}
