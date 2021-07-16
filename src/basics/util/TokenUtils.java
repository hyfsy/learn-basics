package basics.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author baB_hyf
 * @date 2021/07/14
 */
public class TokenUtils {

    public static final String TOKEN_TYPE            = "JWT";

    public static final String ALGORITHM_HS256       = "HS256";
    public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256"; // token仅由一个服务端校验

    public static final String ALGORITHM_RSA         = "RSA";
    public static final String ALGORITHM_RSA256      = "RSA256"; // token在两方服务器之间传输，无法保护密钥

    // TODO ES256

    public static final Object SECRET_KEY1            = "secret_key";
    public static final Object SECRET_KEY2            = EncodeUtils.createRSAKeyPair().getOriginPrivateKey();

    // 配置

    public static final String ALGORITHM             = ALGORITHM_RSA256;
    public static final Object SECRET_KEY            = SECRET_KEY2;

    public static void main(String[] args) {
        // TODO 此示例rs256生成的signature有点长，且有特殊字符，有些问题
        String jwtToken = createJWTToken();
        System.out.println(jwtToken);
    }

    public static Map<String, Object> getPayload() {
        long now = System.currentTimeMillis();

        Map<String, Object> payload = new HashMap<>(); // 载荷 claim
        payload.put("iss", "hyf"); // issuer 签发人
        payload.put("sub", "all people"); // subject 主题
        payload.put("aud", "client"); // audience 受众
        payload.put("iat", now); // issued at 签发时间
        payload.put("exp", now + 10000); // expiration time 过期时间
        payload.put("nbf", now + 3000); // not before 生效时间
        payload.put("jti", UUID.randomUUID().toString()); // jwt id 编号
        // other

        // Registered claims : 这里有一组预定义的声明，它们不是强制的，但是推荐。比如：iss (issuer), exp (expiration time), sub (subject), aud (audience)等。
        // Public claims : 可以随意定义。
        // Private claims : 用于在同意使用它们的各方之间共享信息，并且不是注册的或公开的声明。
        return payload;
    }

    public static String createJWTToken() {

        String header = createHeader(TOKEN_TYPE, ALGORITHM); // 头部
        String payload = createPayload(getPayload()); // 载荷
        String signature = createSignature(header, payload, SECRET_KEY);
        return header + "." + payload + "." + signature;
    }

    public static String createHeader(String type, String algorithm) {
        Map<String, Object> header = new HashMap<>(); // 头部
        header.put("typ", type);
        header.put("alg", algorithm);

        return EncodeUtils.base64UrlEncode(json(header));
    }

    public static String createPayload(Map<String, Object> payload) {
        return EncodeUtils.base64UrlEncode(json(payload));
    }

    public static String createSignature(String header, String payload, Object secret) {

        if (ALGORITHM.equals(ALGORITHM_HS256) || ALGORITHM.equals(ALGORITHM_HMAC_SHA256)) {
            return EncodeUtils.hs256(header + "." + payload, secret.toString());
        }
        else if (ALGORITHM.equals(ALGORITHM_RSA) || ALGORITHM.equals(ALGORITHM_RSA256)) {
            return EncodeUtils.rs256(header + "." + payload, (PrivateKey) secret);
        }
        else {
            throw new IllegalArgumentException("Algorithm not match: " + ALGORITHM);
        }
    }

    public static String json(Map<String, Object> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
