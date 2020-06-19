package com.example.quochuy.common;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String formatCurrency(double number) {
        DecimalFormat numberFormat = new DecimalFormat("###,###,###");
        return numberFormat.format(number) + " VND";
    }

    @SuppressLint("NewApi")
    public String getCurrentDateTime() {
        /// Khởi tạo ngày, giờ hiện tại
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime current_date_time = LocalDateTime.now();
        String now = dtf.format(current_date_time);
        return now;
    }
}
