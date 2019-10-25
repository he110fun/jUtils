package fun.hellofun.jUtils.classes.map;

import java.util.HashMap;

import fun.hellofun.jUtils.predicate.empty.Empty;

/**
 * 该类由 <b>张东冬</b> 于 2019年10月11日 星期五 10时06分02秒  创建；<br>
 * 作用是：<b>api项目的RESTful接口的返回类</b>；<br>
 */
public class R extends HashMap<String, Object> {

    /**
     * 业务响应码
     */
    private static final String CODE = "code";
    /**
     * 非正常原因
     */
    private static final String REASON = "reason";
    /**
     * 提示语
     */
    private static final String TIP = "tip";
    /**
     * 有效载荷
     */
    private static final String DATA = "data";
    /**
     * 系统当前时间
     */
    private static final String TIME = "time";

    /**
     * 业务成功码
     */
    public static final int CODE_SUCCESS = 100;

    /**
     * 默认业务错误码
     */
    public static final int CODE_ERROR = 500;

    /**
     * 默认业务错误提示语
     */
    public static final String DEFAULT_ERROR_TIP = "锄禾日当午，服务器真辛苦...";

    private static final R get(int code) {
        R r = new R();
        r.put(CODE, code);
        r.put(TIME, System.currentTimeMillis());
        return r;
    }

    public static final R ok() {
        return get(CODE_SUCCESS);
    }

    public static final R ok(Object o) {
        R r = get(CODE_SUCCESS);
        r.put(DATA, o);
        return r;
    }

    public static final R error() {
        return error(DEFAULT_ERROR_TIP);
    }

    public static final R error(String tip) {
        R r = get(CODE_ERROR);
        r.put(TIP, tip);
        return r;
    }

    public static final R error(String tip, String reason) {
        R error = error(tip);
        error.put(REASON, reason);
        return error;
    }

    public static final R error(int code, String tip) {
        R r = get(code);
        r.put(TIP, tip);
        return r;
    }

    public static final R error(int code, String tip, String reason) {
        R error = error(code, tip);
        error.put(REASON, reason);
        return error;
    }

    public static final boolean isSuccess(R r) {
        if (Empty.yes(r)) {
            return false;
        }
        Object code = r.get(CODE);
        if (Empty.yes(code)) {
            return false;
        }
        if (!(code instanceof Integer)) {
            return false;
        }
        if (CODE_SUCCESS != Integer.valueOf(code.toString())) {
            return false;
        }
        return true;
    }

    public static final String getTip(R r) {
        if (Empty.yes(r)) {
            return "";
        }
        Object o = r.get(TIP);
        return Empty.yes(o) ? "" : o.toString();
    }

    public static final String getReason(R r) {
        if (Empty.yes(r)) {
            return "";
        }
        Object o = r.get(REASON);
        return Empty.yes(o) ? "" : o.toString();
    }

    public static final long getTime(R r) {
        if (Empty.yes(r)) {
            return 0;
        }
        Object o = r.get(TIME);
        return Empty.yes(o) ? 0 : Long.valueOf(o.toString());
    }
}