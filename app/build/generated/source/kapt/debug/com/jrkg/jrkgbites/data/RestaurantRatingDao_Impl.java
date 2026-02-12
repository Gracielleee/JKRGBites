package com.jrkg.jrkgbites.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.jrkg.jrkgbites.model.RestaurantRating;
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
public final class RestaurantRatingDao_Impl implements RestaurantRatingDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<RestaurantRating> __insertAdapterOfRestaurantRating;

  private final EntityDeleteOrUpdateAdapter<RestaurantRating> __deleteAdapterOfRestaurantRating;

  private final EntityDeleteOrUpdateAdapter<RestaurantRating> __updateAdapterOfRestaurantRating;

  public RestaurantRatingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfRestaurantRating = new EntityInsertAdapter<RestaurantRating>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `restaurant_ratings` (`id`,`restaurantId`,`rating`,`comment`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRestaurantId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getRestaurantId());
        }
        statement.bindLong(3, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getComment());
        }
        statement.bindLong(5, entity.getTimestamp());
      }
    };
    this.__deleteAdapterOfRestaurantRating = new EntityDeleteOrUpdateAdapter<RestaurantRating>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `restaurant_ratings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRestaurantRating = new EntityDeleteOrUpdateAdapter<RestaurantRating>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `restaurant_ratings` SET `id` = ?,`restaurantId` = ?,`rating` = ?,`comment` = ?,`timestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRestaurantId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getRestaurantId());
        }
        statement.bindLong(3, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getComment());
        }
        statement.bindLong(5, entity.getTimestamp());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    if (rating == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfRestaurantRating.insert(_connection, rating);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    if (rating == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfRestaurantRating.handle(_connection, rating);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    if (rating == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfRestaurantRating.handle(_connection, rating);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<RestaurantRating>> getAllRatings() {
    final String _sql = "SELECT * FROM restaurant_ratings";
    return FlowUtil.createFlow(__db, false, new String[] {"restaurant_ratings"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfRestaurantId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "restaurantId");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfComment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comment");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final List<RestaurantRating> _result = new ArrayList<RestaurantRating>();
        while (_stmt.step()) {
          final RestaurantRating _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpRestaurantId;
          if (_stmt.isNull(_columnIndexOfRestaurantId)) {
            _tmpRestaurantId = null;
          } else {
            _tmpRestaurantId = _stmt.getText(_columnIndexOfRestaurantId);
          }
          final int _tmpRating;
          _tmpRating = (int) (_stmt.getLong(_columnIndexOfRating));
          final String _tmpComment;
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null;
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _item = new RestaurantRating(_tmpId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpTimestamp);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<RestaurantRating> getLatestRatingForRestaurant(final String restaurantId) {
    final String _sql = "SELECT * FROM restaurant_ratings WHERE restaurantId = ? ORDER BY timestamp DESC LIMIT 1";
    return FlowUtil.createFlow(__db, false, new String[] {"restaurant_ratings"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (restaurantId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, restaurantId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfRestaurantId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "restaurantId");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfComment = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comment");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final RestaurantRating _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpRestaurantId;
          if (_stmt.isNull(_columnIndexOfRestaurantId)) {
            _tmpRestaurantId = null;
          } else {
            _tmpRestaurantId = _stmt.getText(_columnIndexOfRestaurantId);
          }
          final int _tmpRating;
          _tmpRating = (int) (_stmt.getLong(_columnIndexOfRating));
          final String _tmpComment;
          if (_stmt.isNull(_columnIndexOfComment)) {
            _tmpComment = null;
          } else {
            _tmpComment = _stmt.getText(_columnIndexOfComment);
          }
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          _result = new RestaurantRating(_tmpId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpTimestamp);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
