package com.jrkg.jrkgbites.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteStatement;
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId;
import java.lang.Class;
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
public final class NeverAgainRestaurantDao_Impl implements NeverAgainRestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<NeverAgainRestaurantId> __insertAdapterOfNeverAgainRestaurantId;

  private final EntityDeleteOrUpdateAdapter<NeverAgainRestaurantId> __deleteAdapterOfNeverAgainRestaurantId;

  public NeverAgainRestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfNeverAgainRestaurantId = new EntityInsertAdapter<NeverAgainRestaurantId>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `never_again_restaurants` (`never_again_restaurant`) VALUES (?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final NeverAgainRestaurantId entity) {
        if (entity.getNeverAgainRestaurantId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getNeverAgainRestaurantId());
        }
      }
    };
    this.__deleteAdapterOfNeverAgainRestaurantId = new EntityDeleteOrUpdateAdapter<NeverAgainRestaurantId>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `never_again_restaurants` WHERE `never_again_restaurant` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final NeverAgainRestaurantId entity) {
        if (entity.getNeverAgainRestaurantId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getNeverAgainRestaurantId());
        }
      }
    };
  }

  @Override
  public Object insert(final NeverAgainRestaurantId neverAgainRestaurant,
      final Continuation<? super Unit> $completion) {
    if (neverAgainRestaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfNeverAgainRestaurantId.insert(_connection, neverAgainRestaurant);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final NeverAgainRestaurantId neverAgainRestaurant,
      final Continuation<? super Unit> $completion) {
    if (neverAgainRestaurant == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfNeverAgainRestaurantId.handle(_connection, neverAgainRestaurant);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<String>> getAllNeverAgainRestaurantIdsFlow() {
    final String _sql = "SELECT never_again_restaurant FROM never_again_restaurants";
    return FlowUtil.createFlow(__db, false, new String[] {"never_again_restaurants"}, (_connection) -> {
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
  public Object getAllNeverAgainRestaurantIds(
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT never_again_restaurant FROM never_again_restaurants";
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
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM never_again_restaurants WHERE never_again_restaurant = ?";
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
    final String _sql = "DELETE FROM never_again_restaurants";
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
