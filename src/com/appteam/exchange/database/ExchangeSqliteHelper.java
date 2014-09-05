package com.appteam.exchange.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appteam.exchange.model.ExchangeItemModel;

public class ExchangeSqliteHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "EXCHANGE_TABLE";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_TO = "TO";
	public static final String COLUMN_FROM = "FROM";
	public static final String COLUMN_RATE = "RATE";
	public static final String COLUMN_CURRENT_TIME = "CURRENT_TIME";

	private static final String DATABASE_NAME = "exchangim.db";
	private static final int DATABASE_VERSION = 1;

	public ExchangeSqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}	
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table" + TABLE_NAME
			+ "(" + COLUMN_ID + " integer primary key autoincrement,"
			+ COLUMN_TO + " text," + COLUMN_FROM + " text," + COLUMN_RATE
			+ " text," + COLUMN_CURRENT_TIME + " long);";

	
	public long createExchangeItemModel(ExchangeItemModel exchangeItemModel) {
		
		SQLiteDatabase writableDatabase = getWritableDatabase();
		long insertId = writableDatabase.insert(TABLE_NAME, null,
				exchangeItemModelToContentValues(exchangeItemModel));
		
		return insertId;
	}

	public List<ExchangeItemModel> getAllExchangeItemModel() {
		List<ExchangeItemModel> exchangeItemModelList = new ArrayList<ExchangeItemModel>();

		SQLiteDatabase writableDatabase = getWritableDatabase();
		String[] allColumns = { COLUMN_ID, COLUMN_TO, COLUMN_FROM, COLUMN_RATE,
				COLUMN_CURRENT_TIME };

		Cursor cursor = writableDatabase.query(TABLE_NAME, allColumns, null,
				null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ExchangeItemModel exchangeItemModel = cursorToExchangeItemModel(cursor);
			exchangeItemModelList.add(exchangeItemModel);
			cursor.moveToNext();
		}
		cursor.close();
		return exchangeItemModelList;
	}

	private ContentValues exchangeItemModelToContentValues(
			ExchangeItemModel exchangeItemModel) {

		ContentValues values = new ContentValues();

		values.put(COLUMN_ID, exchangeItemModel.getId());
		values.put(COLUMN_TO, exchangeItemModel.getTo());
		values.put(COLUMN_FROM, exchangeItemModel.getFrom());
		values.put(COLUMN_RATE, exchangeItemModel.getRate());
		values.put(COLUMN_CURRENT_TIME, exchangeItemModel.getCurrenttime());

		return values;
	}

	private ExchangeItemModel cursorToExchangeItemModel(Cursor cursor) {
		ExchangeItemModel exchangeItemModel = new ExchangeItemModel();
		exchangeItemModel.setId(cursor.getLong(0));
		exchangeItemModel.setTo(cursor.getString(1));
		exchangeItemModel.setFrom(cursor.getString(2));
		exchangeItemModel.setRate(cursor.getString(3));
		exchangeItemModel.setCurrenttime(cursor.getLong(4));
		return exchangeItemModel;
	}



	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

}