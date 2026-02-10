package com.jrkg.jrkgbites.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jrkg.jrkgbites.model.RestaurantRating;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantRatingDao_Impl implements RestaurantRatingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RestaurantRating> __insertionAdapterOfRestaurantRating;

  private final EntityDeletionOrUpdateAdapter<RestaurantRating> __deletionAdapterOfRestaurantRating;

  private final EntityDeletionOrUpdateAdapter<RestaurantRating> __updateAdapterOfRestaurantRating;

  public RestaurantRatingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRestaurantRating = new EntityInsertionAdapter<RestaurantRating>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `restaurant_ratings` (`id`,`restaurantId`,`rating`,`comment`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRestaurantId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRestaurantId());
        }
        statement.bindLong(3, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getComment());
        }
        statement.bindLong(5, entity.getTimestamp());
      }
    };
    this.__deletionAdapterOfRestaurantRating = new EntityDeletionOrUpdateAdapter<RestaurantRating>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `restaurant_ratings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRestaurantRating = new EntityDeletionOrUpdateAdapter<RestaurantRating>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `restaurant_ratings` SET `id` = ?,`restaurantId` = ?,`rating` = ?,`comment` = ?,`timestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RestaurantRating entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRestaurantId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRestaurantId());
        }
        statement.bindLong(3, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getComment());
        }
        statement.bindLong(5, entity.getTimestamp());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRestaurantRating.insert(rating);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRestaurantRating.handle(rating);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RestaurantRating rating,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRestaurantRating.handle(rating);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RestaurantRating>> getAllRatings() {
    final String _sql = "SELECT * FROM restaurant_ratings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"restaurant_ratings"}, new Callable<List<RestaurantRating>>() {
      @Override
      @NonNull
      public List<RestaurantRating> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<RestaurantRating> _result = new ArrayList<RestaurantRating>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RestaurantRating _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpRestaurantId;
            if (_cursor.isNull(_cursorIndexOfRestaurantId)) {
              _tmpRestaurantId = null;
            } else {
              _tmpRestaurantId = _cursor.getString(_cursorIndexOfRestaurantId);
            }
            final int _tmpRating;
            _tmpRating = _cursor.getInt(_cursorIndexOfRating);
            final String _tmpComment;
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _tmpComment = null;
            } else {
              _tmpComment = _cursor.getString(_cursorIndexOfComment);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new RestaurantRating(_tmpId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<RestaurantRating> getLatestRatingForRestaurant(final String restaurantId) {
    final String _sql = "SELECT * FROM restaurant_ratings WHERE restaurantId = ? ORDER BY timestamp DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (restaurantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, restaurantId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"restaurant_ratings"}, new Callable<RestaurantRating>() {
      @Override
      @Nullable
      public RestaurantRating call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurantId");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final RestaurantRating _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpRestaurantId;
            if (_cursor.isNull(_cursorIndexOfRestaurantId)) {
              _tmpRestaurantId = null;
            } else {
              _tmpRestaurantId = _cursor.getString(_cursorIndexOfRestaurantId);
            }
            final int _tmpRating;
            _tmpRating = _cursor.getInt(_cursorIndexOfRating);
            final String _tmpComment;
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _tmpComment = null;
            } else {
              _tmpComment = _cursor.getString(_cursorIndexOfComment);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new RestaurantRating(_tmpId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpTimestamp);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
