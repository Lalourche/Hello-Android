package fr.lalourche.helloandroid;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lalourche.helloandroid.layout.SliderLayout;
import fr.lalourche.helloandroid.listener.NameListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Hello Android.
 * @author Lalourche
 *
 */
public class MainActivity extends Activity
{
  /** Key for menu text adapter. */
  private static final String MENU_TEXT = "menu.text";
  /** Key for menu image adapter. */
  private static final String MENU_IMAGE = "menu.image";

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

    layout_ = (SliderLayout) View.inflate(this, R.layout.activity_main, null);
    View menuLayout = layout_.findViewById(R.id.menuLayout);
    buildMenu();
    layout_.setToHide(menuLayout);

    name_ = (EditText) layout_.findViewById(R.id.name);
    name_.addTextChangedListener(new NameListener(this));

    text_ = (TextView) layout_.findViewById(R.id.text);
    text_.setText("");


    menuButton_ = (ToggleButton) layout_.findViewById(R.id.menuButton);
    menuButton_.setChecked(true);
    menuButton_.setOnClickListener(new View.OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        menuClick(v);
      }
    });

    validButton_ = (Button) layout_.findViewById(R.id.nameButton);

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

    setContentView(layout_);
  }

  /**
   * Builds the menu content.
   */
  private void buildMenu()
  {
    CharSequence[] texts = new CharSequence[] {
      getResources().getText(R.string.r2d2),
      getResources().getText(R.string.darthvader),
    };
    int[] images = new int[] {
      R.drawable.r2d2,
      R.drawable.darthvader,
    };
    String[] from = new String[] {
      MENU_TEXT,
      MENU_IMAGE,
    };
    int[] to = new int[] {
      R.id.menuText,
      R.id.menuImage,
    };

    List<HashMap<String, Object>> list =
        new ArrayList<HashMap<String, Object>>();
    HashMap<String, Object> element;

    // Building list
    for (int i = 0; i < texts.length && i < images.length; i++) {
      element = new HashMap<String, Object>();
      element.put(MENU_TEXT, texts[i]);
      element.put(MENU_IMAGE, Integer.toString(images[i]));
      list.add(element);
    }

    // Build adapter
    ListAdapter adapter = new SimpleAdapter(
        this,
        list,
        R.layout.menu_layout,
        from,
        to);

    // Associate adapter to menu
    ListView menuList = (ListView) layout_.findViewById(R.id.menuList);
    menuList.setAdapter(adapter);
  }

  /**
   * Show/hide menu.
   * @param v the view
   */
  protected final void menuClick(View v)
  {
    boolean isMenuOpen = layout_.toggle();
    menuButton_.setChecked(isMenuOpen);
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
