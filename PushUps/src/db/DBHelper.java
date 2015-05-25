package db;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;








import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {


	private static final String DB_NAME =  "pushup.db";   
    private static final String TBL_NAME = "pushup";  
    private static final String CREATE_TBL = " create table "  
            + "pushup(_id integer primary key autoincrement,"
            + "liliang int,naili int, tunwei int,xiongwei int,type int,"
            + "name varchar(45)"
            + ") ";  
	public  SQLiteDatabase db;  
    public DBHelper(Context c) {
    	
    	
    	 super(c, DB_NAME, null, 2);
//    	String DB_DIR = Environment.getExternalStorageDirectory().getPath()   
//               + File.separator + "PushUpDbData" + File.separator   
//               + DBHelper.class.getPackage().getName();
//          File dir = new File(DB_DIR);   
//          // 如果/sdcard/dictionary目录不中存在，创建这个目录   
//       if (!dir.exists()){   
//      	dir.mkdirs();  System.out.println("创建目录成功"); 
//      }    
//          
//            
//       
//          String databaseFilename = "/mnt/sdcard/PushUpDbData/db/pushup.db";
//          File f=new File(databaseFilename);
//       try {
//          if (!f.exists())   
//          {   f.createNewFile();
//             
//		}
//       }
//          catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}   
//             
//          
         
          
          // 打开/sdcard/dictionary目录中的dictionary.db文件   
         
	}

	static {
		String DB_DIR = Environment.getExternalStorageDirectory().getPath()
				+ File.separator + "PushUpDbData" + File.separator
				+ DBHelper.class.getPackage().getName();
		File dir = new File(DB_DIR);
		// 如果/sdcard/dictionary目录不中存在，创建这个目录
		if (!dir.exists()) {
			dir.mkdirs();
			System.out.println("创建目录成功");
		}

		String databaseFilename = "/mnt/sdcard/PushUpDbData/db/pushup.db";
		File f = new File(databaseFilename);
		try {
			if (!f.exists()) {
				f.createNewFile();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 
		
       try {db.execSQL(CREATE_TBL);  
        this.db = db;
        System.out.println("创建成功");
       }
       catch(Exception e){
        e.printStackTrace();
        System.out.println("创建失败");
       }
       
	}
	  public Cursor query( String sql) {  
	        SQLiteDatabase db = getWritableDatabase();
	        Cursor c = db.rawQuery(sql, null);  
	        return c;  
	    }   
   
    public void close() {  
        if (db != null)  
            db.close();  
    }
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}  


}
