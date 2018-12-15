package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import util.KeyUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author ACM-PC
 */
@Repository
public class AuthRepository {
    private final StringRedisTemplate template;
    private final ValueOperations<String, String> valueOps;

    @Autowired
    public AuthRepository(StringRedisTemplate template) {
        this.template = template;
        valueOps = template.opsForValue();
    }

    public Integer getUid(String auth) {
        String uid = valueOps.get(KeyUtils.authKey(auth));
        return uid != null ? Integer.valueOf(uid) : null;
    }

    public String addAuth(Integer uid) {
        String auth = UUID.randomUUID().toString();
        valueOps.set(KeyUtils.auth(uid), auth);
        valueOps.set(KeyUtils.authKey(auth), uid.toString());
        return auth;
    }

    public void deleteAuth(Integer uid) {
        String authKey = KeyUtils.auth(uid);
        String auth = valueOps.get(authKey);
        template.delete(Arrays.asList(authKey, KeyUtils.authKey(auth)));
    }

}
