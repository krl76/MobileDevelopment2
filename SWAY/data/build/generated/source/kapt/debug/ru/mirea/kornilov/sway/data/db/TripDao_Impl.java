package ru.mirea.kornilov.sway.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TripEntity> __insertionAdapterOfTripEntity;

  public TripDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTripEntity = new EntityInsertionAdapter<TripEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `trips` (`userId`,`placeId`,`wantToVisit`,`visited`,`note`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TripEntity entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getUserId());
        }
        statement.bindLong(2, entity.getPlaceId());
        final int _tmp = entity.getWantToVisit() ? 1 : 0;
        statement.bindLong(3, _tmp);
        final int _tmp_1 = entity.getVisited() ? 1 : 0;
        statement.bindLong(4, _tmp_1);
        if (entity.getNote() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNote());
        }
      }
    };
  }

  @Override
  public void upsert(final TripEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTripEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TripEntity> getAll(final String userId) {
    final String _sql = "SELECT * FROM trips WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfPlaceId = CursorUtil.getColumnIndexOrThrow(_cursor, "placeId");
      final int _cursorIndexOfWantToVisit = CursorUtil.getColumnIndexOrThrow(_cursor, "wantToVisit");
      final int _cursorIndexOfVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "visited");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final List<TripEntity> _result = new ArrayList<TripEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final TripEntity _item;
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final int _tmpPlaceId;
        _tmpPlaceId = _cursor.getInt(_cursorIndexOfPlaceId);
        final boolean _tmpWantToVisit;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfWantToVisit);
        _tmpWantToVisit = _tmp != 0;
        final boolean _tmpVisited;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVisited);
        _tmpVisited = _tmp_1 != 0;
        final String _tmpNote;
        if (_cursor.isNull(_cursorIndexOfNote)) {
          _tmpNote = null;
        } else {
          _tmpNote = _cursor.getString(_cursorIndexOfNote);
        }
        _item = new TripEntity(_tmpUserId,_tmpPlaceId,_tmpWantToVisit,_tmpVisited,_tmpNote);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TripEntity getByPlace(final String userId, final int placeId) {
    final String _sql = "SELECT * FROM trips WHERE userId = ? AND placeId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, placeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfPlaceId = CursorUtil.getColumnIndexOrThrow(_cursor, "placeId");
      final int _cursorIndexOfWantToVisit = CursorUtil.getColumnIndexOrThrow(_cursor, "wantToVisit");
      final int _cursorIndexOfVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "visited");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final TripEntity _result;
      if (_cursor.moveToFirst()) {
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        final int _tmpPlaceId;
        _tmpPlaceId = _cursor.getInt(_cursorIndexOfPlaceId);
        final boolean _tmpWantToVisit;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfWantToVisit);
        _tmpWantToVisit = _tmp != 0;
        final boolean _tmpVisited;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfVisited);
        _tmpVisited = _tmp_1 != 0;
        final String _tmpNote;
        if (_cursor.isNull(_cursorIndexOfNote)) {
          _tmpNote = null;
        } else {
          _tmpNote = _cursor.getString(_cursorIndexOfNote);
        }
        _result = new TripEntity(_tmpUserId,_tmpPlaceId,_tmpWantToVisit,_tmpVisited,_tmpNote);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
