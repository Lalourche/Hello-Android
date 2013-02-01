/**
 * Database constants.
 */
package fr.lalourche.helloandroid.db;

import android.provider.BaseColumns;

/**
 * @author Lalourche
 *
 */
public final class DatabaseContract
{
  /**
   *  Prevents the Contract class from being instantiated.
   */
  private DatabaseContract()
  {
  }

  /**
   * @author Lalourche
   *
   */
  public static class StarWars implements BaseColumns
  {
    /** Name of the table.*/
    public static final String TABLE_NAME = "starwars";

    /** Name of the text column.*/
    public static final String COLUMN_NAME_TEXT = "text";
    /** Name of the image column.*/
    public static final String COLUMN_NAME_IMAGE = "image";
  }

}
