package fr.lalourche.helloandroid;

import fr.lalourche.helloandroid.dialog.AboutDialog;
import fr.lalourche.helloandroid.layout.SliderLayout;
import fr.lalourche.helloandroid.listener.MenuButtonListener;
import fr.lalourche.helloandroid.listener.NameListener;
import fr.lalourche.helloandroid.listener.ValidButtonListener;
import fr.lalourche.helloandroid.settings.SettingsActivity;
import fr.lalourche.helloandroid.view.MainActivityMenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Hello Android.
 * @author Lalourche
 *
 */
public class MainActivity extends Activity
{
  /** The layout. */
  private SliderLayout layout_;
  /** Text to display. */
  private TextView text_;
  /** Name to input. */
  private EditText name_;
  /** Menu button. */
  private ToggleButton menuButton_;
  /** Valid button. */
  private Button validButton_;

  @Override
  protected final void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    MainActivityMenu menuView = new MainActivityMenu(this);
    layout_ = menuView.getLayout();

    name_ = (EditText) layout_.findViewById(R.id.name);
    name_.addTextChangedListener(new NameListener(this));

    text_ = (TextView) layout_.findViewById(R.id.text);
    text_.setText("");


    menuButton_ = (ToggleButton) layout_.findViewById(R.id.menuButton);
    menuButton_.setChecked(true);
    menuButton_.setOnClickListener(
        new MenuButtonListener(layout_, menuButton_));

    validButton_ = (Button) layout_.findViewById(R.id.nameButton);

    // Add an animation on start up
    Animation animation =
        AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.view_animation);
    validButton_.startAnimation(animation);

    // Add touch listener
    validButton_.setOnTouchListener(
        new ValidButtonListener(text_, name_, validButton_));

    // Set keyboard hidden by default
    getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    // Set theme according to preferences
    setTheme();

    setContentView(layout_);
  }

  /**
   * Set theme according to preferences.
   */
  private void setTheme()
  {
    // Retrieve theme from preferences
    SharedPreferences sharedPref =
        PreferenceManager.getDefaultSharedPreferences(this);
    String themePreference = sharedPref.getString("pref_theme", "");

    int themeId = android.R.style.Theme;
    if (themePreference != null &&
        themePreference.equals((String) getText(R.string.theme_white)))
    {
      themeId = android.R.style.Theme_Light;
    }

    setTheme(themeId);
  }

  /* (non-Javadoc)
   * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  @Override
  public final boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();

    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  /* (non-Javadoc)
   * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
   */
  @Override
  public final boolean onOptionsItemSelected(MenuItem item)
  {
    boolean actionManaged = false;

    switch (item.getItemId())
    {
      case R.id.aboutMenuItem:
        // Show about dialog
        AboutDialog dialog = new AboutDialog();
        dialog.setContext(this);
        String tag = getResources().getString(R.string.tag_about_dialog);
        dialog.show(getFragmentManager(), tag);
        break;
      case R.id.settingsMenuItem:
        // Show settings
        Intent settingsActivity = new Intent(this, SettingsActivity.class);
        startActivity(settingsActivity);
        break;
      default:
        break;
    }

    return actionManaged;
  }

}
