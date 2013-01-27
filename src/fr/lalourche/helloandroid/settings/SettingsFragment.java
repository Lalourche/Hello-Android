/**
 * Settings fragment.
 */
package fr.lalourche.helloandroid.settings;

import fr.lalourche.helloandroid.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * @author Lalourche
 *
 */
public class SettingsFragment extends PreferenceFragment
{

  /* (non-Javadoc)
   * @see android.preference.PreferenceFragment#onCreate(android.os.Bundle)
   */
  @Override
  public final void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    // Load the preferences from an XML resource
    addPreferencesFromResource(R.xml.preferences);
  }

}
