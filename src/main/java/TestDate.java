import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        String strDate = "2017-11-18";// 定義日期字串
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 定義日期格式
        Date date = null;
        try {
            date = format.parse(strDate);// 將字串轉換為日期
        } catch (ParseException e) {
            System.out.println("輸入的日期格式不合理!");
        }

//		System.out.println(new Date());
        System.out.println(getWeek(date));
        System.out.println(getWeek(new Date()));
    }

    //方法1
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    //方法2
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }
}
