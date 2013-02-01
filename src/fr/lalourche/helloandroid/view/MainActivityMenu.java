/**
 * Manually created sliding menu.
 */
package fr.lalourche.helloandroid.view;

import fr.lalourche.helloandroid.R;
import fr.lalourche.helloandroid.VadorActivity;
import fr.lalourche.helloandroid.db.DatabaseContract;
import fr.lalourche.helloandroid.db.DatabaseHelper;
import fr.lalourche.helloandroid.layout.SliderLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * @author Lalourche
 *
 */
public class MainActivityMenu implements OnItemClickListener
{
  /** The layout. */
  private SliderLayout layout_;
  /** Current context. */
  private Context context_;
  /** DFatabase helper. */
  private SQLiteOpenHelper databaseHelper_;

  /**
   * @param context current context
   */
  public MainActivityMenu(Context context)
  {
    context_ = context;
    databaseHelper_ = new DatabaseHelper(context_);
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
    SQLiteDatabase db = databaseHelper_.getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    String[] projection = {
      DatabaseContract.StarWars._ID,
      DatabaseContract.StarWars.COLUMN_NAME_TEXT,
      DatabaseContract.StarWars.COLUMN_NAME_IMAGE,
    };

    // Selecting the wole table
    Cursor c = db.query(
        DatabaseContract.StarWars.TABLE_NAME,
        projection,
        null,
        null,
        null,
        null,
        null);

    // List of columns to display
    String[] from = {
      DatabaseContract.StarWars.COLUMN_NAME_TEXT,
      DatabaseContract.StarWars.COLUMN_NAME_IMAGE,
    };

    // List of view that will contain the individual values
    int[] to = new int[] {
      R.id.menuText,
      R.id.menuImage,
    };

    // Build adapter
    SimpleCursorAdapter adapter = new SimpleCursorWithBlobAdapter(
        context_,
        R.layout.menu_layout,
        c,
        from,
        to,
        CursorAdapter.NO_SELECTION);

    // Associate adapter to menu
    ListView menuList = (ListView) layout_.findViewById(R.id.menuList);
    menuList.setAdapter(adapter);
    menuList.setOnItemClickListener(this);
  }

  /**
   * @return the layout
   */
  public final SliderLayout getLayout()
  {
    return layout_;
  }

  /* (non-Javadoc)
   * @see android.view.View.OnClickListener#onClick(android.view.View)
   */
  /* (non-Javadoc)
   * @see android.widget.AdapterView.OnItemClickListener
   * #onItemClick(android.widget.AdapterView, android.view.View, int, long)
   */
  @Override
  public final void onItemClick(
      AdapterView<?> parent,
      View view,
      int position,
      long id)
  {
    Cursor cursor = (Cursor) parent.getItemAtPosition(position);
    String itemText = cursor.getString(cursor.getColumnIndexOrThrow(
        DatabaseContract.StarWars.COLUMN_NAME_TEXT));

    // Robustness
    if (itemText == null)
    {
      return;
    }

    if (itemText.equals(context_.getText(R.string.darthvader)))
    {
      // Open Vador activity
      Intent nextActivity = new Intent(context_, VadorActivity.class);
      context_.startActivity(nextActivity);
    }
  }
}
