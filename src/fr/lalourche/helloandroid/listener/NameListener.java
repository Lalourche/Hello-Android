/**
 * Listener for name input.
 */
package fr.lalourche.helloandroid.listener;

import fr.lalourche.helloandroid.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

/**
 * @author Lalourche
 *
 */
public class NameListener implements TextWatcher
{
  /** Current context. */
  private Context context_;

  /**
   * @param context current context
   */
  public NameListener(Context context)
  {
    context_ = context;
  }

  /* (non-Javadoc)
   * @see android.text.TextWatcher
   * #beforeTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after)
  {
    // Do nothing
  }

  /* (non-Javadoc)
   * @see android.text.TextWatcher
   * #onTextChanged(java.lang.CharSequence, int, int, int)
   */
  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count)
  {
    // Do nothing
  }

  /* (non-Javadoc)
   * @see android.text.TextWatcher
   * #afterTextChanged(android.text.Editable)
   */
  @Override
  public final void afterTextChanged(Editable s)
  {
    processName(s);
  }

  /**
   * Process the input name even if no other action is triggered.
   * @param s the current name input
   */
  private void processName(CharSequence s)
  {
    CharSequence adminName =
        context_.getResources().getText(R.string.adminName);
    if (s.toString().equals(adminName.toString())) {
      Toast.makeText(
          context_, R.string.toast, Toast.LENGTH_SHORT)
          .show();
    }
  }

}
