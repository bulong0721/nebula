/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nebula.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 名称: RegexUtil
 * 描述: 正则表达式校验工具类，校验邮箱、身份证等
 * </pre>
 *
 * @author
 * @since 1.0.0
 */
public final class RegexUtil {
    private static final Pattern PATTERN_MAIL = Pattern.compile("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?");
    private static final Pattern PATTERN_ID_CARD = Pattern.compile("[1-9]\\d{13,16}[a-zA-Z0-9]{1}");
    private static final Pattern PATTERN_MOBILE = Pattern.compile("^1[3456789]\\d{9}$");
    private static final Pattern PATTERN_PHONE = Pattern.compile("(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("\\-?[1-9]\\d+");
    private static final Pattern PATTERN_DECIMALS = Pattern.compile("\\-?[1-9]\\d+(\\.\\d+)?");
    private static final Pattern PATTERN_BLANKSPACE = Pattern.compile("\\s+");
    private static final Pattern PATTERN_CHINESE = Pattern.compile("[\u4E00-\u9FA5]+$");
    private static final Pattern PATTERN_BIRTHDAY = Pattern.compile("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}");
    private static final Pattern PATTERN_URL = Pattern.compile("(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?");
    private static final Pattern PATTERN_DOMAIN = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_POSTCODE = Pattern.compile("[1-9]\\d{5}");
    private static final Pattern PATTERN_IPADDRESS = Pattern.compile("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))");

    private RegexUtil() {
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(final String email) {
        return PATTERN_MAIL.matcher(email).matches();
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(final String idCard) {
        return PATTERN_ID_CARD.matcher(idCard).matches();
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(final String mobile) {
        return PATTERN_MOBILE.matcher(mobile).matches();
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(final String phone) {
        return PATTERN_PHONE.matcher(phone).matches();
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(final String digit) {
        return PATTERN_DIGIT.matcher(digit).matches();
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(final String decimals) {
        return PATTERN_DECIMALS.matcher(decimals).matches();
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(final String blankSpace) {
        return PATTERN_BLANKSPACE.matcher(blankSpace).matches();
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(final String chinese) {
        return PATTERN_CHINESE.matcher(chinese).matches();
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(final String birthday) {
        return PATTERN_BIRTHDAY.matcher(birthday).matches();
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(final String url) {
        return PATTERN_URL.matcher(url).matches();
    }

    /**
     * <pre>
     * 获取网址 URL 的一级域名
     * http://www.zuidaima.com/share/1550463379442688.htm ->> zuidaima.com
     * </pre>
     *
     * @param url 网址
     * @return 验证成功返回true，验证失败返回false
     */
    public static String getDomain(final String url) {
        // 获取完整的域名
        Matcher matcher = PATTERN_DOMAIN.matcher(url);
        matcher.find();
        return matcher.group();
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(final String postcode) {
        return PATTERN_POSTCODE.matcher(postcode).matches();
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(final String ipAddress) {
        return PATTERN_IPADDRESS.matcher(ipAddress).matches();
    }

}
