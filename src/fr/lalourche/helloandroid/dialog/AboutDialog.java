/**
 * About dialog
 */
package fr.lalourche.helloandroid.dialog;

import fr.lalourche.helloandroid.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author Lalourche
 *
 */
public class AboutDialog extends DialogFragment
{

  /* (non-Javadoc)
   * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
   */
  @Override
  public final Dialog onCreateDialog(Bundle savedInstanceState)
  {
    // Use the Builder class for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder
    .setTitle(R.string.aboutLabel)
      .setMessage(R.string.version)
      .setNeutralButton(
        android.R.string.ok,
        new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int id)
          {
            // Do nothing
          }
        });

    // Create the AlertDialog object and return it
    return builder.create();
  }

}
