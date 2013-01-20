/**
 * Listener for menu button (not android menu).
 */
package fr.lalourche.helloandroid.listener;

import fr.lalourche.helloandroid.layout.SliderLayout;

import android.view.View;
import android.widget.ToggleButton;

/**
 * @author Lalourche
 *
 */
public class MenuButtonListener implements View.OnClickListener
{
  /** The layout. */
  private SliderLayout layout_;
  /** Menu button. */
  private ToggleButton menuButton_;

  /**
   * @param layout the SliderLayout
   * @param button the menu button
   */
  public MenuButtonListener(SliderLayout layout, ToggleButton button)
  {
    layout_ = layout;
    menuButton_ = button;
  }

  /* (non-Javadoc)
   * @see android.view.View.OnClickListener#onClick(android.view.View)
   */
  @Override
  public final void onClick(View v)
  {
    menuClick();
  }

  /**
   * Show/hide menu.
   */
  protected final void menuClick()
  {
    boolean isMenuOpen = layout_.toggle();
    menuButton_.setChecked(isMenuOpen);
  }

}
