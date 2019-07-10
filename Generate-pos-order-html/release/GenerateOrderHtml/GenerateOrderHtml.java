import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateOrderHtml {
    //行分隔符 :数据文件的一行数据对应一条数据库记录，各行之间分隔符为UNIX样式的换行符（ASCII码0x0A）。
    public static final char ENTER_CF = 0x0A;

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream propIs = null;
        BufferedReader tplBr = null;
        OutputStreamWriter osw = null;
        try {
            propIs = ClassLoader.getSystemResourceAsStream("generateOrderHtml.properties");
            InputStream tplIs = ClassLoader.getSystemResourceAsStream("template.tpl");
            prop.load(new InputStreamReader(propIs, "utf-8"));
            tplBr = new BufferedReader(
                    new InputStreamReader(tplIs, "utf-8"));

            // 数据预处理
            prop = dataPreHandle(prop);

            // 表达式正则表达式
            Pattern p = Pattern.compile("\\#\\{\\w*[=]?\\w*[:]?\\w*\\}");

            // 开始读取处理
            String line = null;
            StringBuilder resultBuilder = new StringBuilder();
            while (null != (line = tplBr.readLine())) {
//                #{sfmc}
                Matcher m = p.matcher(line);
                List<String> dataExpressionList = new LinkedList(); //数据表达式
                try {
                    while (m.find()) {
                        dataExpressionList.add(m.group());
                    }
                } catch (IllegalStateException e) {
                    throw e;//No match found
                }
                if (null != dataExpressionList) for (String dataExpress : dataExpressionList) {
                    int braceStart = dataExpress.indexOf("#{");
                    int braceEnd = dataExpress.lastIndexOf("}");
                    String deContent = dataExpress.substring(braceStart + 2, braceEnd);
                    String data = prop.getProperty(deContent);
                    try {
                        line = line.replaceFirst("\\#\\{" + deContent + "\\}", data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 该行处理完毕放入结果串中
                resultBuilder.append(line);
                resultBuilder.append(ENTER_CF);
            }
            String resultStr = resultBuilder.toString();
            File file = new File(System.currentTimeMillis() + ".html");
            if (file.createNewFile()) {
                osw = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("utf-8"));
                osw.write(resultStr);
            } else {
                System.err.println("创建结果文件失败！~");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生错误！~");
        } finally {
            // 关闭最外层的流
            if (null != propIs) {
                try {
                    propIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != tplBr) {
                try {
                    tplBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    数据预处理
    public static Properties dataPreHandle(Properties prop) {
        //===========需要自动生成的数据
        int num = 0;
        // 商户名称
        String shmc = prop.getProperty("shmc");
        if (null == shmc || "".equals(shmc)) {
            prop.setProperty("shmc", "武汉有格商贸有限公司");
        }
        // 商户编号
        String shbh = prop.getProperty("shbh");
        if (null == shbh || "".equals(shbh)) {
            num = getIntRandNum(1000, 10000);
            prop.setProperty("shbh", String.valueOf(num));
        }
        // 操作员
        String czy = prop.getProperty("czy");
        if (null == czy || "".equals(czy)) {
            prop.setProperty("czy", "01");
        }
        // 终端编号
        String zdbh = prop.getProperty("zdbh");
        if (null == zdbh || "".equals(zdbh)) {
            num = getIntRandNum(10000, 100000);
            prop.setProperty("zdbh", String.valueOf(num));
        }
        // 清算日期
        String qsrq = prop.getProperty("qsrq");
        if (null == qsrq || "".equals(qsrq)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            String val = sdf.format(new Date());
            prop.setProperty("qsrq", val);
        }
        // 卡号
        String kh = prop.getProperty("kh");
        if (null == kh || "".equals(kh)) {
//                62255718*****3599
            String cardBinStr = "436728 436742 453242 491031 532421 532432 553242 544033 622707 622725 622728 622700 621284 625955 625956 621700 622966 622988 421349 434061 434062 524094 526410 552245 589970 620060 621080 621081 621082 621466 621467 621488 621499 621598 622280 621621 544887 557080 436718 436745 489592 532450 532458 436738 436748 552801 558895 559051 622168 628266 628366 622708 622166 531693 356895 356896 356899 625964 625965 625966 622381 622675 622676 622677 621083 622382 621487 621084";
            String[] cardBins = cardBinStr.split("\\s");
            num = getIntRandNum(0, cardBins.length + 1);
            String cardBin = cardBins[num];
            int seq = getIntRandNum(10, 100);
            int endNum = getIntRandNum(1000, 10000);

            String cardNo = String.format("%s%s*****%s", cardBin, seq, endNum);
            prop.setProperty("kh", cardNo);
        }
        // 发卡行
        String fkh = prop.getProperty("fkh");
        if (null == fkh || "".equals(fkh)) {
            prop.setProperty("fkh", "招商银行");
        }
        // 收单行
        String sdh = prop.getProperty("sdh");
        if (null == sdh || "".equals(sdh)) {
            num = getIntRandNum(100000, 1000000);
            prop.setProperty("sdh", String.valueOf(num));
        }
        // 有效期
        String yxq = prop.getProperty("yxq");
        if (null == yxq || "".equals(yxq)) {
            prop.setProperty("yxq", "2020/08");
        }
        // 批次号
        String pch = prop.getProperty("pch");
        if (null == pch || "".equals(pch)) {
            num = getIntRandNum(1000, 10000);
            prop.setProperty("pch", String.valueOf(num));
        }
        // 凭证号
        String pzh = prop.getProperty("pzh");
        if (null == pzh || "".equals(pzh)) {
            num = getIntRandNum(100000, 1000000);
            prop.setProperty("pzh", String.valueOf(num));
        }
        // 日期
        String rq = prop.getProperty("rq");
        if (null == rq || "".equals(rq)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String val = sdf.format(new Date());
            prop.setProperty("rq", val);
        }
        // 时刻
        String sk = prop.getProperty("sk");
        if (null == sk || "".equals(sk)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String val = sdf.format(new Date());
            prop.setProperty("sk", val);
        }
        // 授权号
        String sqh = prop.getProperty("sqh");
        if (null == sqh || "".equals(sqh)) {
            num = getIntRandNum(10, 100);
            prop.setProperty("sqh", "00"+num);
        }
        // 参考号
        String ckh = prop.getProperty("ckh");
        if (null == ckh || "".equals(ckh)) {
            num = getIntRandNum(100000, 1000000);
            prop.setProperty("ckh", String.valueOf(num));
        }

        // 金额
        String amount = prop.getProperty("amount");
        if (null == amount || "".equals(amount)) {
            prop.setProperty("amount", "100.00");
        }

        // 备注
        String bak = prop.getProperty("bak");
        if (null == bak || "".equals(bak)) {
            prop.setProperty("bak", "01");
        }
        // ARQC
        String arqc = prop.getProperty("arqc");
        if (null == arqc || "".equals(arqc)) {
            prop.setProperty("arqc", "E34AEDC1");
        }
        // AID
        String aid = prop.getProperty("aid");
        if (null == aid || "".equals(aid)) {
            prop.setProperty("aid", "B09060002");
        }
        // TUR
        String tur = prop.getProperty("tur");
        if (null == tur || "".equals(tur)) {
            prop.setProperty("tur", "008004E");
        }
        // tsi
        String tsi = prop.getProperty("tsi");
        if (null == tsi || "".equals(tsi)) {
            prop.setProperty("tsi", "F809");
        }
        // atc
        String atc = prop.getProperty("atc");
        if (null == atc || "".equals(atc)) {
            prop.setProperty("atc", "002E");
        }
        // 应用标签
        String yybq = prop.getProperty("yybq");
        if (null == yybq || "".equals(yybq)) {
            prop.setProperty("yybq", "PBOO");
        }
        // 首选名称
        String ssmc = prop.getProperty("ssmc");
        if (null == ssmc || "".equals(ssmc)) {
            prop.setProperty("ssmc", "DEBIT");
        }
        // 最后记账日期
//        JUL  25 2018
        String finalDate = prop.getProperty("finalDate");
        if (null == finalDate || "".equals(finalDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                rq = prop.getProperty("rq");
                Date date = sdf.parse(rq);
                sdf = new SimpleDateFormat("MMM  dd yyyy", Locale.US);
                String val = sdf.format(date);
                prop.setProperty("finalDate", val);
            } catch (ParseException e) {
                e.printStackTrace();
                prop.setProperty("finalDate", "JUL  25 2018");
            }
        }

        return prop;
    }

    /**
     *  生成一个数字随机数
     * @param start 开始数-范围
     * @param end 结束数-范围
     * @return
     */
    public static int getIntRandNum(int start, int end) {
        int num = (int) Math.floor(Math.random() * (end - start) + start);
        return num;
    }
}
