/**
 * About dialog
 */
package fr.lalourche.helloandroid.dialog;

import fr.lalourche.helloandroid.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Lalourche
 *
 */
public class AboutDialog extends DialogFragment
{
  /** Current context. */
  private Context context_;

  /* (non-Javadoc)
   * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
   */
  @Override
  public final Dialog onCreateDialog(Bundle savedInstanceState)
  {
    // Get version from manifest
    String version = null;
    try
    {
      version = context_.getPackageManager().getPackageInfo(
          context_.getPackageName(), 0).versionName;
    }
    catch (NameNotFoundException e)
    {
      Log.e(this.toString(), e.getMessage());
    }

    // Use the Builder class for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder
    .setTitle(R.string.aboutLabel)
      .setMessage(version)
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

  /**
   * @param context the context to set
   */
  public final void setContext(Context context)
  {
    context_ = context;
  }

}
