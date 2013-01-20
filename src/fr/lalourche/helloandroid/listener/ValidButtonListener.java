/**
 * Listener for menu button (not android menu).
 */
package fr.lalourche.helloandroid.listener;

import java.text.MessageFormat;

import fr.lalourche.helloandroid.R;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Lalourche
 *
 */
public class ValidButtonListener implements View.OnTouchListener
{

  /** Text to display. */
  private TextView text_;
  /** Name to input. */
  private EditText name_;
  /** Valid button. */
  private Button validButton_;

  /**
   * @param text the displayed text
   * @param name the input name
   * @param validButton the valid button
   */
  public ValidButtonListener(TextView text, EditText name, Button validButton)
  {
    text_ = text;
    name_ = name;
    validButton_ = validButton;
  }

  /* (non-Javadoc)
   * @see android.view.View.OnTouchListener
   * #onTouch(android.view.View, android.view.MotionEvent)
   */
  @Override
  public final boolean onTouch(View v, MotionEvent event)
  {
    return validOnTouch(v, event);
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
