/**
 * Manually created sliding menu.
 */
package fr.lalourche.helloandroid.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lalourche.helloandroid.R;
import fr.lalourche.helloandroid.layout.SliderLayout;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author Lalourche
 *
 */
public class MainActivityMenu
{
  /** Key for menu text adapter. */
  private static final String MENU_TEXT = "menu.text";
  /** Key for menu image adapter. */
  private static final String MENU_IMAGE = "menu.image";

  /** The layout. */
  private SliderLayout layout_;
  /** current context. */
  private Context context_;

  /**
   * @param context current context
   */
  public MainActivityMenu(Context context)
  {
    context_ = context;
    layout_ = (SliderLayout) View.inflate(
        context_, R.layout.activity_main, null);
    View menuLayout = layout_.findViewById(R.id.menuLayout);
    buildMenu();
    layout_.setToHide(menuLayout);
  }

  /**
   * Builds the menu content.
   */
  private void buildMenu()
  {
    CharSequence[] texts = new CharSequence[] {
      context_.getText(R.string.r2d2),
      context_.getText(R.string.darthvader),
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
        context_,
        list,
        R.layout.menu_layout,
        from,
        to);

    // Associate adapter to menu
    ListView menuList = (ListView) layout_.findViewById(R.id.menuList);
    menuList.setAdapter(adapter);
  }

  /**
   * @return the layout
   */
  public final SliderLayout getLayout()
  {
    return layout_;
  }
}
