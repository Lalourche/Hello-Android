package fr.lalourche.helloandroid.view;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Adapter that supports the BLOB images in a cursor.
 * Only manages ImùageView and TextView.
 * @author Lalourche
 *
 */
public class SimpleCursorWithBlobAdapter extends SimpleCursorAdapter
{

  /** The layout. */
  private int layout_;
  /** Current context. */
  private Context context_;
  /** The cursor. */
  private Cursor cursor_;
  /** Columns to display. */
  private String[] from_;
  /** Views to fill in. */
  private int[] to_;

  /**
   * Standard constructor.
   * @param context The context where the ListView associated with this
   *            SimpleListItemFactory is running
   * @param layout resource identifier of a layout file that defines the views
   *            for this list item. The layout file should include at least
   *            those named views defined in "to"
   * @param c The database cursor.
   *   Can be null if the cursor is not available yet.
   * @param from A list of column names representing the data to bind to the UI.
   *   Can be null
   *            if the cursor is not available yet.
   * @param to The views that should display column in the "from" parameter.
   *            These should all be TextViews. The first N views in this list
   *            are given the values of the first N columns in the from
   *            parameter.  Can be null if the cursor is not available yet.
   * @param flags Flags used to determine the behavior of the adapter,
   * as per {@link CursorAdapter#CursorAdapter(Context, Cursor, int)}.
   */
  public SimpleCursorWithBlobAdapter(Context context, int layout,
      Cursor c, String[] from,
      int[] to, int flags)
  {
    super(context, layout, c, from, to, flags);
    context_ = context;
    layout_ = layout;
    cursor_ = c;
    from_ = from;
    to_ = to;
  }

  /* (non-Javadoc)
   * @see android.widget.CursorAdapter
   * #getView(int, android.view.View, android.view.ViewGroup)
   */
  @Override
  public final View getView(int position, View convertView, ViewGroup parent)
  {
    View v = convertView;
    // Inflate the view if not already done
    if (v == null)
    {
      LayoutInflater inflater =
          (LayoutInflater) context_.getSystemService(
              Context.LAYOUT_INFLATER_SERVICE);
      v = inflater.inflate(layout_, null);
    }

    cursor_.moveToPosition(position);

    // Iterate on destination views in order to manage the imageViews as blobs
    for (int i = 0; i < to_.length && i < from_.length; i++)
    {
      View destinationView = v.findViewById(to_[i]);
      if (destinationView != null && destinationView instanceof ImageView)
      {
        byte[] value = cursor_.getBlob(cursor_.getColumnIndex(from_[i]));
        if (value != null)
        {
          ImageView iv = (ImageView) destinationView;
          iv.setImageBitmap(
              BitmapFactory.decodeByteArray(value, 0, value.length));
        }
      }
      else if (destinationView != null && destinationView instanceof TextView)
      {
        String value = cursor_.getString(cursor_.getColumnIndex(from_[i]));
        if (value != null)
        {
          TextView tv = (TextView) destinationView;
          tv.setText(value);
        }
      }
    }

    return v;
  }

}
