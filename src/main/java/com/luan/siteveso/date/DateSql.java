package com.luan.siteveso.date;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class DateSql {
    private Date dateSql;
    private Calendar calendar;

    public DateSql() {
    }

    public DateSql(Date dateSql, Calendar calendar) {
        this.dateSql = dateSql;
        this.calendar = calendar;
    }

    public Date getDateSql() {
        return dateSql;
    }

    public void setDateSql(Date dateSql) {
        this.dateSql = dateSql;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return "DateSql{" +
                "dateSql=" + dateSql +
                '}';
    }

    // Lấy ngày hiện tại theo định dạng date sql
    public Date getDateSqlCurrent(){
        // Lấy ngày tháng năm hiện tại
       calendar = Calendar.getInstance();
       dateSql = new Date(calendar.getTimeInMillis());
       return dateSql;
    }
    // chuyển đổi String sang định dạng Date Sql
    public Date parseStringToDateSql(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new Date(utilDate.getTime());
            return sqlDate;
        }catch (Exception e){
            return null;
        }

    }
    // lay gio hien tai
    public String getHourCurrent(){
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedTime = sdf.format(currentDate);
        return formattedTime;
    }
}
