package com.example.castle.csite.util;

import android.app.ActivityManager;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author 王维波
 * @time 2016/8/1  9:23
 * @desc 获得当前系统相关的信息
 */

public class SystemInfoUtils {

    //获得当前设备正在运行中的进程
    public static int getRunnningProcess(Context context){
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        return  infos.size();
    }


    //剩余内存
    public  static long   getAvalibMeme(Context context){
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo meminfo=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(meminfo);
        long availMem = meminfo.availMem;

        return availMem;
    }


    public  static long getTotalMeme(Context context){
       /* ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo meminfo=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(meminfo);
        long totalMem = meminfo.totalMem;*/
        try {
            File file=new File("/proc/meminfo");

            FileInputStream in=new FileInputStream(file);
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();//读一行,代表读当前这一行里面的所有内存,包括空格
            StringBuffer sb=new StringBuffer();
            for (char c  : line.toCharArray()) {
                if(c>='0'&&c<='9') {
                    sb.append(c);
                }
            }
            return Long.parseLong(sb.toString())*1024;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}

