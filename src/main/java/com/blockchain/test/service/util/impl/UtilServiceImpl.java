package com.blockchain.test.service.util.impl;

import com.blockchain.test.service.util.IUtilService;
import com.blockchain.test.service.util.bean.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UtilServiceImpl implements IUtilService {

    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };

    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "圆",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };

    private static final Pattern CHINESE_CHARACTER_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]+(\\.|·)?[\\u4e00-\\u9fa5]+");

    private static final String CN_FULL = "整";

    private static final String CN_NEGATIVE = "负";

    private static final int MONEY_PRECISION = 2;

    private static final String CN_ZERO_FULL = "零元" + CN_FULL;

    private static final HashMap<Long, String> NUMBER_MONEY_CACHE = new HashMap<>();

    private IdGenerator idGenerator;

    @PostConstruct
    public void init() {
        this.idGenerator = new IdGenerator();
    }

    @Override
    public Long generateId() {
        return idGenerator.generateId();
    }

    @Override
    public boolean isChinesesName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        Matcher m = CHINESE_CHARACTER_PATTERN.matcher(name);
        return m.find() && m.group(0).equals(name);
    }

    @Override
    public String longList2Str(List<Long> list2beConverted, String seperator) {
        String s = seperator;
        if (CollectionUtils.isEmpty(list2beConverted)) {
            return "";
        }
        if (StringUtils.isEmpty(s)) {
            s = ",";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list2beConverted.size() - 1; ++i) {
            builder.append(list2beConverted.get(i));
            builder.append(s);
        }
        builder.append(list2beConverted.get(list2beConverted.size() - 1));
        return builder.toString();
    }

    @Override
    public String longList2Str(List<Long> list2beConverted) {
        return longList2Str(list2beConverted, ",");
    }

    @Override
    public List<Long> str2LongList(String str2beConverted) {
        return str2LongList(str2beConverted, ",");
    }

    @Override
    public List<Long> str2LongList(String str2beConverted, String seperator) {
        String s = seperator;
        if (StringUtils.isEmpty(str2beConverted)) {
            return Collections.EMPTY_LIST;
        }
        if (StringUtils.isEmpty(s)) {
            s = ",";
        }
        String[] strList = str2beConverted.split(s);
        List<Long> longList = new ArrayList<>();
        for (String str : strList) {
            Long number = Long.valueOf(str);
            longList.add(number);
        }
        return longList;
    }

    @Override
    public String number2CNMoney(BigDecimal moneyDecimal) {
        StringBuilder sb = new StringBuilder();
        int signum = moneyDecimal.signum();
        if (signum == 0) {
            return CN_ZERO_FULL;
        }
        long number = moneyDecimal.movePointRight(MONEY_PRECISION).
                setScale(0, 4).abs().longValue();
        Long numberKey = number;
        if (NUMBER_MONEY_CACHE.containsKey(numberKey)) {
            return NUMBER_MONEY_CACHE.get(numberKey);
        }
        long scale = number % 100;
        int numUnit;
        int numIndex = 0;
        boolean getZero = false;

        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }
                    if (numIndex == 2) {
                        if (number > 0) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                    getZero = true;
                }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        String result = sb.toString();
        NUMBER_MONEY_CACHE.put(numberKey, result);
        return result;
    }

    @Override
    public String safeMap2Str(Map<String, String> info) {
        if (null == info) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        info.forEach((key, value) -> {
            if ("password".equals(key)) {
                return;
            }
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append(",");
        });
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String safeObjectMap2Str(Map<String, Object> info) {
        if (null == info) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        info.forEach((key, value) -> {
            if ("password".equals(key)) {
                return;
            }
            builder.append(key);
            builder.append("=");
            builder.append(value);
            builder.append(",");
        });
        builder.append("}");
        return builder.toString();
    }

    @Override
    public String generateKey(int round, int length) {

        String key = "";

        for (int i = 0; i < length; i++) {

            Random rand = new Random();
            int random = rand.nextInt(round) % 16;

            switch (random) {
                case 0:
                    key += "0";
                    break;
                case 1:
                    key += "1";
                    break;
                case 2:
                    key += "2";
                    break;
                case 3:
                    key += "3";
                    break;
                case 4:
                    key += "4";
                    break;
                case 5:
                    key += "5";
                    break;
                case 6:
                    key += "6";
                    break;
                case 7:
                    key += "7";
                    break;
                case 8:
                    key += "8";
                    break;
                case 9:
                    key += "9";
                    break;
                case 10:
                    key += "A";
                    break;
                case 11:
                    key += "B";
                    break;
                case 12:
                    key += "C";
                    break;
                case 13:
                    key += "D";
                    break;
                case 14:
                    key += "E";
                    break;
                case 15:
                    key += "F";
                    break;
                default:
                    i--;
            }
        }

        return Base64.encode(key.getBytes());
    }


    public static class Base64 {

        private static char[] codec_table = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', '+', '/'};

        public Base64() {

        }

        public static byte[] decode(String data) throws Exception {

            if (data == null || "".equals(data))
                return null;
            return new sun.misc.BASE64Decoder().decodeBuffer(data);
        }

        public static String encode(byte[] a) {

            int totalBits = a.length * 8;
            int nn = totalBits % 6;
            int curPos = 0;// process bits
            StringBuffer toReturn = new StringBuffer();
            while (curPos < totalBits) {
                int bytePos = curPos / 8;
                switch (curPos % 8) {
                    case 0:
                        toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);
                        break;
                    case 2:

                        toReturn.append(codec_table[(a[bytePos] & 0x3f)]);
                        break;
                    case 4:
                        if (bytePos == a.length - 1) {
                            toReturn.append(codec_table[((a[bytePos] & 0x0f) << 2) & 0x3f]);
                        } else {
                            int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
                            toReturn.append(codec_table[pos]);
                        }
                        break;
                    case 6:
                        if (bytePos == a.length - 1) {
                            toReturn.append(codec_table[((a[bytePos] & 0x03) << 4) & 0x3f]);
                        } else {
                            int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
                            toReturn.append(codec_table[pos]);
                        }
                        break;
                    default:
                        //never hanppen
                        break;
                }
                curPos += 6;
            }
            if (nn == 2) {
                toReturn.append("==");
            } else if (nn == 4) {
                toReturn.append("=");
            }
            return toReturn.toString();

        }

    }

}
