package com.jrkg.jrkgbites.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteStatement;
import com.jrkg.jrkgbites.model.FavoriteRestaurantId;
import java.lang.Boolean;
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
public final class FavoriteRestaurantDao_Impl implements FavoriteRestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<FavoriteRestaurantId> __insertAdapterOfFavoriteRestaurantId;

  private final EntityDeleteOrUpdateAdapter<FavoriteRestaurantId> __deleteAdapterOfFavoriteRestaurantId;

  public FavoriteRestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfFavoriteRestaurantId = new EntityInsertAdapter<FavoriteRestaurantId>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `favorite_restaurants` (`favorite_restaurant`) VALUES (?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final FavoriteRestaurantId entity) {
        if (entity.getFavoriteRestaurantId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getFavoriteRestaurantId());
        }
      }
    };
    this.__deleteAdapterOfFavoriteRestaurantId = new EntityDeleteOrUpdateAdapter<FavoriteRestaurantId>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `favorite_restaurants` WHERE `favorite_restaurant` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final FavoriteRestaurantId entity) {
        if (entity.getFavoriteRestaurantId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getFavoriteRestaurantId());
        }
      }
    };
  }

  @Override
  public Object insert(final FavoriteRestaurantId favoriteRestaurant,
      final Continuation<? super Unit> $completion) {
    if (favoriteRestaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfFavoriteRestaurantId.insert(_connection, favoriteRestaurant);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object insertAll(final List<FavoriteRestaurantId> favoriteRestaurants,
      final Continuation<? super Unit> $completion) {
    if (favoriteRestaurants == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfFavoriteRestaurantId.insert(_connection, favoriteRestaurants);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final FavoriteRestaurantId favoriteRestaurant,
      final Continuation<? super Unit> $completion) {
    if (favoriteRestaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfFavoriteRestaurantId.handle(_connection, favoriteRestaurant);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<String>> getAllFavoriteRestaurantIdsFlow() {
    final String _sql = "SELECT favorite_restaurant FROM favorite_restaurants";
    return FlowUtil.createFlow(__db, false, new String[] {"favorite_restaurants"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final List<String> _result = new ArrayList<String>();
        while (_stmt.step()) {
          final String _item;
          if (_stmt.isNull(0)) {
            _item = null;
          } else {
            _item = _stmt.getText(0);
          }
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getAllFavoriteRestaurantIds(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT favorite_restaurant FROM favorite_restaurants";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final List<String> _result = new ArrayList<String>();
        while (_stmt.step()) {
          final String _item;
          if (_stmt.isNull(0)) {
            _item = null;
          } else {
            _item = _stmt.getText(0);
          }
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object isFavorited(final String restaurantId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM favorite_restaurants WHERE favorite_restaurant = ?)";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (restaurantId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, restaurantId);
        }
        final Boolean _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp == null ? null : _tmp != 0;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM favorite_restaurants WHERE favorite_restaurant = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM favorite_restaurants";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
