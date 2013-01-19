/**
 * Custom sliding layout.
 * Cf. tutorial from siteduzero.
 */
package fr.lalourche.helloandroid.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * @author Lalourche
 *
 */
public class SliderLayout extends LinearLayout
{

  /** Speed of animation in ms. */
  static final int SPEED = 2000;

  /** Stores if layout is visible or not. */
  private boolean isOpen_ = true;
  /** The sub-layout to show or hide. */
  private View toHide_;

  /** Listener for close animation. */
  private AnimationListener closeListener_;
  /** Listener for open animation. */
  private AnimationListener openListener_;

  /**
   * Constructor.
   * @param context the context
   * @param attrs the attributes
   * @param defStyle the style
   */
  public SliderLayout(Context context, AttributeSet attrs, int defStyle)
  {
    super(context, attrs, defStyle);
    init();
  }

  /**
   * Constructor.
   * @param context the context
   * @param attrs the attributes
   */
  public SliderLayout(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    init();
  }

  /**
   * Constructor.
   * @param context the context
   */
  public SliderLayout(Context context)
  {
    super(context);
    init();
  }

  /**
   * Inits the layout.
   */
  private void init()
  {
    /* Listener pour l'animation de fermeture du menu */
    closeListener_ = new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation animation)
      {
        // On dissimule le menu
        toHide_.setVisibility(GONE);
      }

      public void onAnimationRepeat(Animation animation)
      {
        // Do nothing
      }

      public void onAnimationStart(Animation animation)
      {
        // Do nothing
      }
    };

    /* Listener pour l'animation d'ouverture du menu */
    openListener_ = new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation animation)
      {
      }

      public void onAnimationRepeat(Animation animation)
      {
      }

      public void onAnimationStart(Animation animation)
      {
        // On affiche le menu
        toHide_.setVisibility(VISIBLE);
      }
    };
  }

  /**
  * Utilisée pour ouvrir ou fermer le menu.
  * @return true si le menu est désormais ouvert.
  */
  public final boolean toggle()
  {
    // Animation for the display/hiding
    TranslateAnimation animation = null;

    // Switching open <-> close
    isOpen_ = !isOpen_;

    if (isOpen_) {
      // Animate from top to bottom : show
      animation = new TranslateAnimation(0.0f, 0.0f,
          -toHide_.getHeight(), 0.0f);
      animation.setAnimationListener(openListener_);
    } else {
      // Animate from bottom to up : hide
      animation = new TranslateAnimation(0.0f, 0.0f, 0.0f,
          -toHide_.getHeight());
      animation.setAnimationListener(closeListener_);
    }

    // Customize and start animation
    animation.setDuration(SPEED);
    animation.setInterpolator(new AccelerateInterpolator());
    startAnimation(animation);

    return isOpen_;
  }

  /**
   * @return the isOpen
   */
  public final boolean isOpen()
  {
    return isOpen_;
  }

  /**
   * @param toHide the toHide to set
   */
  public final void setToHide(View toHide)
  {
    toHide_ = toHide;
  }
}
