package com.ky.vmk;

import com.ky.param.OpenRequest;
import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;

public class SignUtils {

    public static <T> String sign(@NonNull String ua, @NonNull String call, @NonNull String timestamp, @NonNull T arg) {
        String jsonString = JsonUtils.toJsonString(arg);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ua).append(call).append(timestamp).append(jsonString);
        return DigestUtils.md5Hex(stringBuilder.toString());
    }


    public static <T> boolean verify(@NonNull final OpenRequest<T> request) {
        String sign = request.getSign();
        if (isEmpty(sign)) {
            return false;
        }
        return sign.equals(sign(request.getUa(),request.getCall(),request.getTimestamp(),request.getArgs()));
    }


    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static <T> String sign(@NonNull OpenRequest<T> openRequest) {
        return sign(openRequest.getUa(),openRequest.getCall(),openRequest.getTimestamp(),openRequest.getArgs());
    }
}
