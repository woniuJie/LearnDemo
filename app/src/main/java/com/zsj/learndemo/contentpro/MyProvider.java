package com.zsj.learndemo.contentpro;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private Context mContext;
    private DBHelper mDbHelper = null;
    private SQLiteDatabase db = null;
    public static final String AUTOHORITY = "com.zsj.myprovider";

    public static final int USER_CODE = 1;
    public static final int JOB_CODE = 2;

    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHORITY, "user", USER_CODE);
        mMatcher.addURI(AUTOHORITY, "job", JOB_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDbHelper = new DBHelper(getContext());
        db = mDbHelper.getWritableDatabase();

        db.execSQL("delete from user");
        db.execSQL("insert into user values(1,'张士杰');");
        db.execSQL("insert into user values(2,'lhy');");

        db.execSQL("delete from job");
        db.execSQL("insert into job values(1,'teacher');");
        db.execSQL("insert into job values(2,'engineer');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        String tab = getTableName(uri);

        return db.query(tab,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        String table = getTableName(uri);

        db.insert(table,null,values);

        mContext.getContentResolver().notifyChange(uri,null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public String getTableName(Uri uri){
        String tabName = null;
        switch (mMatcher.match(uri)){
            case USER_CODE:
                tabName = DBHelper.USER_TABLE_NAME;
                break;
            case JOB_CODE:
                tabName = DBHelper.JOB_TABLE_NAME;
                break;
        }
        return tabName;
    }
}
