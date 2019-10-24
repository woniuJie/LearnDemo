package com.zsj.learndemo.print.cc;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zhangshijie on 2019/7/15;
 */
public class Cups {

    static String PROOT = "./proot.sh";
    static String LPSTAT = "/usr/bin/lpstat";
    static String IMG = "/img";
    static String LPOPTIONS = "/usr/bin/lpoptions";

    private static String[] printers = null;
    private static HashSet<String> printerIsBusy = new HashSet<String>();
    private static HashMap<String, HashMap<String, String[]> > printerOptions = new HashMap<String, HashMap<String, String[]> >();

    public static void updatePrintersInfo(Context p)
    {
        ArrayList<String> printerList = new ArrayList<String>();
        Proc pp = new Proc(new String[] {PROOT, LPSTAT, "-v"}, chrootPath(p));
        for (String s: pp.out)
        {
            if (!s.startsWith("device for ") || s.indexOf(":") == -1)
                continue;
            printerList.add(s.substring(("device for ").length(), s.indexOf(":")));
        }

        HashSet<String> busy = new HashSet<String>();
        for(String printer: printerList)
        {
            pp = new Proc(new String[] {PROOT, LPSTAT, "-p", printer}, chrootPath(p));
            if (pp.out.length == 0 || pp.status != 0)
                continue;
            if (pp.out[0].indexOf("is idle") == -1)
                busy.add(printer);
        }

        HashMap<String, HashMap<String, String[]>> allOptions = new HashMap<String, HashMap<String, String[]> >();
        for(String printer: printerList)
        {
            pp = new Proc(new String[] {PROOT, LPOPTIONS, "-p", printer, "-l"}, chrootPath(p));
            if (pp.out.length == 0 || pp.status != 0)
                continue;
            HashMap<String, String[]> options = new HashMap<String, String[]>();
            for(String s: pp.out)
            {
                if (s.indexOf("/") == -1 || s.indexOf(": ") == -1)
                    continue;
                String k = s.substring(0, s.indexOf("/"));
                String vv[] = s.substring(s.indexOf(": ") + 2).split("\\s+");
                for (int i = 0; i < vv.length; i++)
                {
                    if (vv[i].startsWith("*"))
                    {
                        String dd = vv[i].substring(1);
                        vv[i] = vv[0];
                        vv[0] = dd;
                        break;
                    }
                }
                options.put(k, vv);
            }
            allOptions.put(printer, options);
        }

        setPrinterList(printerList.toArray(new String[0]), busy, allOptions);
    }

    public static File chrootPath(Context p)
    {
        return new File(p.getFilesDir().getAbsolutePath() + IMG);
    }
    synchronized private static void setPrinterList(String [] _printers, HashSet<String> _printerIsBusy, HashMap<String, HashMap<String, String[]> > _printerOptions)
    {
        printers = _printers;
        printerIsBusy = _printerIsBusy;
        printerOptions = _printerOptions;
    }

    synchronized public static String[] getPrinters(Context p)
    {
        if (printers == null)
            updatePrintersInfo(p);
        return printers;
    }
}
