package com.xzl.springboot.starter;

import com.xzl.http.client.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/29 10:13
 * @version: v0.0.1
 * @history:
 */
@RestController
@RequestMapping("/starter")
public class TestHttpStarterController {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public void test() throws Exception{
        userService.test();
        HttpEntity httpEntity = httpClient.execute(new HttpGet("http://www.baidu.com")).getEntity();
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        HttpResponse httpResponse = null;
        try {
            //执行请求访问
            httpResponse = httpClient.execute(httpGet);
            //获取返回HTTP状态码
            int satausCode = httpResponse.getStatusLine().getStatusCode();
            if(satausCode == 200 ){
                String content = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                System.out.println("百度首页页面："+content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
