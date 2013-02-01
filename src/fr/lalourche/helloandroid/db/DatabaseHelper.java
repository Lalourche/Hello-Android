/**
 * Database creator and updater.
 */
package fr.lalourche.helloandroid.db;

import java.io.ByteArrayOutputStream;

import fr.lalourche.helloandroid.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;

/**
 * @author Lalourche
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
  /** If you change the database schema,
   *  you must increment the database version. */
  public static final int DATABASE_VERSION = 1;
  /** Database file. */
  public static final String DATABASE_NAME = "HelloAndroid.db";

  /** Current context. */
  private Context context_;

  /**
   * @param context current context
   */
  public DatabaseHelper(Context context)
  {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    context_ = context;
  }

  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper
   * #onCreate(android.database.sqlite.SQLiteDatabase)
   */
  @Override
  public final void onCreate(SQLiteDatabase db)
  {
    // Create table
    String createQuery =
        "CREATE TABLE " + DatabaseContract.StarWars.TABLE_NAME + " (" +
        DatabaseContract.StarWars._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        DatabaseContract.StarWars.COLUMN_NAME_IMAGE + " BLOB, " +
        DatabaseContract.StarWars.COLUMN_NAME_TEXT + " TEXT" +
        ");";
    db.execSQL(createQuery);

    // Insert data
    CharSequence[] texts = new CharSequence[] {
        context_.getText(R.string.r2d2),
        context_.getText(R.string.darthvader),
    };
    int[] images = new int[] {
      R.drawable.r2d2,
      R.drawable.darthvader,
    };

    // Inserting values
    for (int i = 0; i < texts.length && i < images.length; i++)
    {
      ContentValues values = new ContentValues();
      // Text
      values.put(DatabaseContract.StarWars.COLUMN_NAME_TEXT, (String) texts[i]);

      // Build byte array from image
      BitmapDrawable bitmapDrawable =
          (BitmapDrawable) context_.getResources().getDrawable(images[i]);
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      bitmapDrawable.getBitmap().compress(
          CompressFormat.PNG,
          100,
          stream);
      values.put(
          DatabaseContract.StarWars.COLUMN_NAME_IMAGE,
          stream.toByteArray());
      db.insert(DatabaseContract.StarWars.TABLE_NAME, null, values);
    }
  }

  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper
   * #onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {
    // Useless for the moment
  }

}
