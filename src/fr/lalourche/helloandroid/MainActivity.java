package fr.lalourche.helloandroid;

import java.text.MessageFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Hello Android.
 * @author Lalourche
 *
 */
public class MainActivity extends Activity
{

  /** Text to display. */
  private TextView text_;
  /** Name to input. */
  private EditText name_;
  /** Valid button. */
  private Button validButton_;

  @Override
  protected final void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    View layout = View.inflate(this, R.layout.activity_main, null);

    name_ = (EditText) layout.findViewById(R.id.name);
    name_.addTextChangedListener(new TextWatcher()
    {
      @Override
      public void onTextChanged(
          CharSequence s, int start, int before, int count)
      {
        processName(s);
      }

      @Override
      public void beforeTextChanged(
          CharSequence s, int start, int count, int after)
      {
        // Do nothing
      }

      @Override
      public void afterTextChanged(Editable s)
      {
        // Do nothing
      }
    });

    text_ = (TextView) layout.findViewById(R.id.text);
    text_.setText("");


    validButton_ = (Button) layout.findViewById(R.id.nameButton);

    // Add an animation on start up
    Animation animation =
        AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.view_animation);
    validButton_.startAnimation(animation);

    // Add touch listener
    validButton_.setOnTouchListener(new View.OnTouchListener()
    {
      @Override
      public boolean onTouch(View v, MotionEvent event)
      {
        return validOnTouch(v, event);
      }
    });

    // Set keyboard hidden by default
    getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    setContentView(layout);
  }

  /**
   * Process the input name even if no other action is triggered.
   * @param s the current name input
   */
  private void processName(CharSequence s)
  {
    CharSequence adminName = name_.getResources().getText(R.string.adminName);
    if (s.toString().equals(adminName.toString())) {
      Toast.makeText(
          getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT)
          .show();
    }
  }

  /**
   * Manages the onTouch event on the valid button.
   * @param v the view
   * @param event the MotionEvent
   * @return true if the event is consumed
   */
  private boolean validOnTouch(View v, MotionEvent event)
  {
    // Log.d("Lalourche", "onTouch " + event.getX() + " : " + event.getY());
    updateSize(v, event);

    // The click event is now caught by this one
    if (event.getAction() == MotionEvent.ACTION_UP) {
      valid(v);
    }

    // Next event must be caught
    return true;
  }

  /**
   * Called on click on Valid button.
   * @param v calling view
   */
  private void valid(View v)
  {
    String format = (String) v.getContext().getResources()
        .getText(R.string.hello);
    text_.setText(MessageFormat.format(format, name_.getText()));
  }

  /**
   * Called on touch on Valid button.
   * @param v calling view
   * @param event the current MotionEvent
   */
  private void updateSize(View v, MotionEvent event)
  {
    // Get current pointer absolute position
    float currentX = event.getRawX();
    float currentY = event.getRawY();
//    Log.d("Lalourche", "updateSize " + currentX + " : " + currentY);

    // Compute font size
    float newTextSize =
        Math.abs(currentX - (validButton_.getWidth() / 2)) +
        Math.abs(currentY - (validButton_.getHeight() / 2));

    validButton_.setTextSize(newTextSize);
  }

}
