import com.google.gson.Gson;
import com.qiniu.common.Constants;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.ProxyConfiguration;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.Assert;
import org.junit.Test;

public class MyTest {

    private Client httpManager = new Client();
    String accessKey = "0ygyCNdhOs4z9SOcS5uBYMo5sv80imPMeb3LhsPQ";
    String secretKey = "KareIDSEvAnMJNgODTQ43CYD4Nj5XIy5NWqsr65x";

    @Test
    public void testUpload() {
        //...生成上传凭证，然后准备上传
        String accessKey = "0ygyCNdhOs4z9SOcS5uBYMo5sv80imPMeb3LhsPQ";
        String secretKey = "KareIDSEvAnMJNgODTQ43CYD4Nj5XIy5NWqsr65x";
        String bucket = "JobEntity-management-system-based-on-open-cloud-storage";
//        String bucket = "open-test";

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png

        String localFilePath = "C:\\Users\\ACM-PC\\IdeaProjects\\JMSBOCO\\src\\main\\resources\\Dictionary.md";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "Dictionary.md";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void getBucketList() {

        Response r = null;
        try {
            String url = "http://rs.qbox.me/buckets";
            Auth auth = Auth.create(accessKey, secretKey);
            StringMap parameter = auth.authorization(url);
            r = httpManager.get(url, parameter);
            System.out.println(parameter.get("Authorization"));
            System.out.println(r.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryDomain() {
        Response r = null;
        try {
            String url = "http://api.qiniu.com/v6/domain/list?tbl=<bucketName>";
            url = url.replace("<bucketName>", "open-test");
            Auth auth = Auth.create(accessKey, secretKey);
            StringMap parameter = auth.authorization(url);
            r = httpManager.get(url, parameter);
            System.out.println(parameter.get("Authorization"));
            System.out.println(r.bodyString());
        } catch (QiniuException e) {
            Assert.assertNotNull(e.response.reqId);
        }
    }

    @Test
    public void testPost3() {
        Response r = null;
        try {
            r = httpManager.post("http://httpbin.org/status/500", "hello", null);
            Assert.fail();
        } catch (QiniuException e) {
            if (e.code() != -1) {
                Assert.assertEquals(500, e.code());
            } else {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPost4() {
        Response r = null;
        try {
            r = httpManager.post("http://httpbin.org/status/418", "hello", null);
            Assert.fail();
        } catch (QiniuException e) {
            if (e.code() != -1) {
                Assert.assertEquals(418, e.code());
            } else {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPost5() {
        Response r = null;
        try {
            r = httpManager.post("http://httpbin.org/status/298", "hello", null);
            if (r.statusCode != -1) {
                Assert.assertEquals(298, r.statusCode);
            }
        } catch (QiniuException e) {
            if (e.code() != -1) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testProxy() {
        ProxyConfiguration proxy = new ProxyConfiguration("115.231.183.168", 80);
        Client c = new Client(null, false, proxy,
                Constants.CONNECT_TIMEOUT, Constants.READ_TIMEOUT, Constants.WRITE_TIMEOUT,
                Constants.DISPATCHER_MAX_REQUESTS, Constants.DISPATCHER_MAX_REQUESTS_PER_HOST,
                Constants.CONNECTION_POOL_MAX_IDLE_COUNT, Constants.CONNECTION_POOL_MAX_IDLE_MINUTES);
        Response r = null;
        try {
            r = c.post("http://upproxy1.qiniu.com", "hello", null);
            Assert.fail();
        } catch (QiniuException e) {
            Assert.assertNotNull(e.response.reqId);
            Assert.assertEquals(e.response.statusCode, 400);
        }
    }
}