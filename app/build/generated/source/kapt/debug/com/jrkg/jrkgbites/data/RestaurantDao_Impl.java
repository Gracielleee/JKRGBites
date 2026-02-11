package com.jrkg.jrkgbites.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.jrkg.jrkgbites.model.Restaurant;
import com.jrkg.jrkgbites.utils.Converters;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class RestaurantDao_Impl implements RestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Restaurant> __insertAdapterOfRestaurant;

  private final EntityDeleteOrUpdateAdapter<Restaurant> __deleteAdapterOfRestaurant;

  private final EntityDeleteOrUpdateAdapter<Restaurant> __updateAdapterOfRestaurant;

  public RestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfRestaurant = new EntityInsertAdapter<Restaurant>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `restaurants` (`id`,`name`,`category`,`cuisine`,`level`,`location`,`lat`,`lng`,`logoResourceName`,`tags`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Restaurant entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getCategory());
        }
        if (entity.getCuisine() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCuisine());
        }
        if (entity.getLevel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getLevel());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getLocation());
        }
        if (entity.getLat() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getLat());
        }
        if (entity.getLng() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getLng());
        }
        if (entity.getLogoResourceName() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getLogoResourceName());
        }
        final String _tmp = Converters.INSTANCE.fromStringList(entity.getTags());
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, _tmp);
        }
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
      }
    };
    this.__deleteAdapterOfRestaurant = new EntityDeleteOrUpdateAdapter<Restaurant>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `restaurants` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Restaurant entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfRestaurant = new EntityDeleteOrUpdateAdapter<Restaurant>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `restaurants` SET `id` = ?,`name` = ?,`category` = ?,`cuisine` = ?,`level` = ?,`location` = ?,`lat` = ?,`lng` = ?,`logoResourceName` = ?,`tags` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Restaurant entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getCategory());
        }
        if (entity.getCuisine() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getCuisine());
        }
        if (entity.getLevel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getLevel());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getLocation());
        }
        if (entity.getLat() == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, entity.getLat());
        }
        if (entity.getLng() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getLng());
        }
        if (entity.getLogoResourceName() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getLogoResourceName());
        }
        final String _tmp = Converters.INSTANCE.fromStringList(entity.getTags());
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, _tmp);
        }
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        if (entity.getId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final Restaurant restaurant, final Continuation<? super Unit> arg1) {
    if (restaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfRestaurant.insert(_connection, restaurant);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object insertAll(final List<Restaurant> restaurants,
      final Continuation<? super Unit> arg1) {
    if (restaurants == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfRestaurant.insert(_connection, restaurants);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object delete(final Restaurant restaurant, final Continuation<? super Unit> arg1) {
    if (restaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfRestaurant.handle(_connection, restaurant);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object update(final Restaurant restaurant, final Continuation<? super Unit> arg1) {
    if (restaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfRestaurant.handle(_connection, restaurant);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<Restaurant>> getAllRestaurants() {
    final String _sql = "SELECT * FROM restaurants";
    return FlowUtil.createFlow(__db, false, new String[] {"restaurants"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _columnIndexOfCuisine = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cuisine");
        final int _columnIndexOfLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "level");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfLat = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lat");
        final int _columnIndexOfLng = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lng");
        final int _columnIndexOfLogoResourceName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logoResourceName");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsFavorite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isFavorite");
        final List<Restaurant> _result = new ArrayList<Restaurant>();
        while (_stmt.step()) {
          final Restaurant _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory);
          }
          final String _tmpCuisine;
          if (_stmt.isNull(_columnIndexOfCuisine)) {
            _tmpCuisine = null;
          } else {
            _tmpCuisine = _stmt.getText(_columnIndexOfCuisine);
          }
          final String _tmpLevel;
          if (_stmt.isNull(_columnIndexOfLevel)) {
            _tmpLevel = null;
          } else {
            _tmpLevel = _stmt.getText(_columnIndexOfLevel);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpLat;
          if (_stmt.isNull(_columnIndexOfLat)) {
            _tmpLat = null;
          } else {
            _tmpLat = _stmt.getText(_columnIndexOfLat);
          }
          final String _tmpLng;
          if (_stmt.isNull(_columnIndexOfLng)) {
            _tmpLng = null;
          } else {
            _tmpLng = _stmt.getText(_columnIndexOfLng);
          }
          final String _tmpLogoResourceName;
          if (_stmt.isNull(_columnIndexOfLogoResourceName)) {
            _tmpLogoResourceName = null;
          } else {
            _tmpLogoResourceName = _stmt.getText(_columnIndexOfLogoResourceName);
          }
          final List<String> _tmpTags;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = Converters.INSTANCE.toStringList(_tmp);
          final boolean _tmpIsFavorite;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsFavorite));
          _tmpIsFavorite = _tmp_1 != 0;
          _item = new Restaurant(_tmpId,_tmpName,_tmpCategory,_tmpCuisine,_tmpLevel,_tmpLocation,_tmpLat,_tmpLng,_tmpLogoResourceName,_tmpTags,_tmpIsFavorite);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Restaurant> getRestaurantById(final String id) {
    final String _sql = "SELECT * FROM restaurants WHERE id = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"restaurants"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfCategory = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "category");
        final int _columnIndexOfCuisine = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cuisine");
        final int _columnIndexOfLevel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "level");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfLat = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lat");
        final int _columnIndexOfLng = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lng");
        final int _columnIndexOfLogoResourceName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logoResourceName");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsFavorite = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isFavorite");
        final Restaurant _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpCategory;
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory);
          }
          final String _tmpCuisine;
          if (_stmt.isNull(_columnIndexOfCuisine)) {
            _tmpCuisine = null;
          } else {
            _tmpCuisine = _stmt.getText(_columnIndexOfCuisine);
          }
          final String _tmpLevel;
          if (_stmt.isNull(_columnIndexOfLevel)) {
            _tmpLevel = null;
          } else {
            _tmpLevel = _stmt.getText(_columnIndexOfLevel);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpLat;
          if (_stmt.isNull(_columnIndexOfLat)) {
            _tmpLat = null;
          } else {
            _tmpLat = _stmt.getText(_columnIndexOfLat);
          }
          final String _tmpLng;
          if (_stmt.isNull(_columnIndexOfLng)) {
            _tmpLng = null;
          } else {
            _tmpLng = _stmt.getText(_columnIndexOfLng);
          }
          final String _tmpLogoResourceName;
          if (_stmt.isNull(_columnIndexOfLogoResourceName)) {
            _tmpLogoResourceName = null;
          } else {
            _tmpLogoResourceName = _stmt.getText(_columnIndexOfLogoResourceName);
          }
          final List<String> _tmpTags;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = Converters.INSTANCE.toStringList(_tmp);
          final boolean _tmpIsFavorite;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfIsFavorite));
          _tmpIsFavorite = _tmp_1 != 0;
          _result = new Restaurant(_tmpId,_tmpName,_tmpCategory,_tmpCuisine,_tmpLevel,_tmpLocation,_tmpLat,_tmpLng,_tmpLogoResourceName,_tmpTags,_tmpIsFavorite);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getRestaurantCount(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM restaurants";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
