package fr.lalourche.helloandroid;

import java.text.MessageFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

  @Override
  protected final void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    View layout = View.inflate(this, R.layout.activity_main, null);

    name_ = (EditText) layout.findViewById(R.id.name);

    text_ = (TextView) layout.findViewById(R.id.text);
    text_.setText("");

    Button validButton = (Button) layout.findViewById(R.id.nameButton);
    validButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        valid(v);
      }
    });

    setContentView(layout);
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

}
