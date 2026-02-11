package com.jrkg.jrkgbites;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.jrkg.jrkgbites.data.RestaurantDao;
import com.jrkg.jrkgbites.data.RestaurantDao_Impl;
import com.jrkg.jrkgbites.data.RestaurantRatingDao;
import com.jrkg.jrkgbites.data.RestaurantRatingDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile RestaurantDao _restaurantDao;

  private volatile RestaurantRatingDao _restaurantRatingDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(2, "0c0ed7d213dfe7ca76e0ae529b7938a2", "2121a35bec0fd423e20cfacaeafcf9fb") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `restaurants` (`id` TEXT NOT NULL, `name` TEXT, `category` TEXT, `cuisine` TEXT, `level` TEXT, `location` TEXT, `lat` TEXT, `lng` TEXT, `logoResourceName` TEXT, `tags` TEXT, `isFavorite` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `restaurant_ratings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `restaurantId` TEXT NOT NULL, `rating` INTEGER NOT NULL, `comment` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0c0ed7d213dfe7ca76e0ae529b7938a2')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `restaurants`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `restaurant_ratings`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsRestaurants = new HashMap<String, TableInfo.Column>(11);
        _columnsRestaurants.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("cuisine", new TableInfo.Column("cuisine", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("level", new TableInfo.Column("level", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("lat", new TableInfo.Column("lat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("lng", new TableInfo.Column("lng", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("logoResourceName", new TableInfo.Column("logoResourceName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("tags", new TableInfo.Column("tags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysRestaurants = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesRestaurants = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRestaurants = new TableInfo("restaurants", _columnsRestaurants, _foreignKeysRestaurants, _indicesRestaurants);
        final TableInfo _existingRestaurants = TableInfo.read(connection, "restaurants");
        if (!_infoRestaurants.equals(_existingRestaurants)) {
          return new RoomOpenDelegate.ValidationResult(false, "restaurants(com.jrkg.jrkgbites.model.Restaurant).\n"
                  + " Expected:\n" + _infoRestaurants + "\n"
                  + " Found:\n" + _existingRestaurants);
        }
        final Map<String, TableInfo.Column> _columnsRestaurantRatings = new HashMap<String, TableInfo.Column>(5);
        _columnsRestaurantRatings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurantRatings.put("restaurantId", new TableInfo.Column("restaurantId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurantRatings.put("rating", new TableInfo.Column("rating", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurantRatings.put("comment", new TableInfo.Column("comment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurantRatings.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysRestaurantRatings = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesRestaurantRatings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRestaurantRatings = new TableInfo("restaurant_ratings", _columnsRestaurantRatings, _foreignKeysRestaurantRatings, _indicesRestaurantRatings);
        final TableInfo _existingRestaurantRatings = TableInfo.read(connection, "restaurant_ratings");
        if (!_infoRestaurantRatings.equals(_existingRestaurantRatings)) {
          return new RoomOpenDelegate.ValidationResult(false, "restaurant_ratings(com.jrkg.jrkgbites.model.RestaurantRating).\n"
                  + " Expected:\n" + _infoRestaurantRatings + "\n"
                  + " Found:\n" + _existingRestaurantRatings);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "restaurants", "restaurant_ratings");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "restaurants", "restaurant_ratings");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RestaurantDao.class, RestaurantDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RestaurantRatingDao.class, RestaurantRatingDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RestaurantDao restaurantDao() {
    if (_restaurantDao != null) {
      return _restaurantDao;
    } else {
      synchronized(this) {
        if(_restaurantDao == null) {
          _restaurantDao = new RestaurantDao_Impl(this);
        }
        return _restaurantDao;
      }
    }
  }

  @Override
  public RestaurantRatingDao restaurantRatingDao() {
    if (_restaurantRatingDao != null) {
      return _restaurantRatingDao;
    } else {
      synchronized(this) {
        if(_restaurantRatingDao == null) {
          _restaurantRatingDao = new RestaurantRatingDao_Impl(this);
        }
        return _restaurantRatingDao;
      }
    }
  }
}
