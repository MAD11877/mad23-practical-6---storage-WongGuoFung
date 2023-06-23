package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    String title = "DBHandler";

    public static String DATABASE_NAME = "accountDB.db";
    public static int DATABASE_VERSION = 1;

    /* Users */
    public static final String USERS = "Users";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESC = "Description";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOLLOWED = "Followed";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i(title, "DB constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_USER = "CREATE TABLE " + USERS + "("
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESC + " TEXT, "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + "BOOLEAN " +")";
        db.execSQL(CREATE_TABLE_USER);
        db.close();
    }
    public void addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO User VALUES( \"" + u.getName() + "\", \"" + u.getDescription() + "\", + \"" + u.getId() + "\", + \"" + u.isFollowed() + "\")");
        db.close();
    }
    public ArrayList<User> getUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<User> list = new ArrayList<User>();

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext())
        {
            User newUser = new User();
            newUser.setName(cursor.getString(0));
            newUser.setDescription(cursor.getString(1));
            newUser.setId(cursor.getInt(2));
            newUser.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            list.add(newUser);
        }

        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        Log.i(title, "Drop and Create new DB");
        onCreate(db);
    }
    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE User SET Followed = \""+ u.isFollowed() +"\" " +  "WHERE id = \""+ u.getId() +"\"");
        db.close();
    }
    private int randomOTP(){
        Random ran = new Random();
        int value = ran.nextInt(999999999);
        return value;
    }
    private User createUser(){
        User randomUser = new User();
        int ran1 = randomOTP();
        int ran2 = randomOTP();
        randomUser.setName("Name" + ran1);
        randomUser.setFollowed(false);
        randomUser.setDescription("Description" + ran2);
        return randomUser;
    }
    public int Count()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        return cursor.getCount();       //Returns no. of rows
    }
}