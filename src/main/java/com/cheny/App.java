package com.cheny;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpContent;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.lf5.util.StreamUtils;

import com.cheny.zip.ZipFactory;
import com.cheny.zip.spi.Extractor;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception{

        File file = new File("/Users/chenyong/myapp/unzip/testDir");
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());

        URL _url = new URL("file:///Users/chenyong/myapp/unzip/testDir/aaa.txt");
        URLConnection con = _url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(3000);
        try(InputStream inputStream = con.getInputStream();){
            String a = IOUtils.toString(inputStream,"UTF-8");
            System.out.println(a);
        }catch (Exception e){
            e.printStackTrace();
        }

        testImportZip();

        Set<String>  ss = new LinkedHashSet<>();
        ss.add("a");
        ss.add("b");
        ss.add("c");

        ss.addAll(Arrays.asList("a","c"));


        System.out.println(ss.toString());


//        testImport(123456);

       // testImageUpload();

//        //unzip.extract("","");
//        String fileName = "/Users/chenyong/Downloads/test.rar";
//        String dest = "/Users/chenyong/myapp/unzip";
//
//        Extractor extractor = ZipFactory.createExtractor(fileName);
//        System.out.println(extractor.extract(fileName,dest));
//


//        try(InputStream inputStream = new URL("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1584022468,1240003319&fm=58").openStream()){
//            String mimeTypeString = URLConnection.guessContentTypeFromStream(new BufferedInputStream(inputStream));
//
//            System.out.println(mimeTypeString);
//
//            MimeType mimeType = new MimeType(mimeTypeString);
//            //System.out.println(mimeType.getBaseType());
//
//
//            TikaConfig config = TikaConfig.getDefaultConfig();
//            MediaType mediaType = config.getMimeRepository().detect(new BufferedInputStream(inputStream),new Metadata());
//            org.apache.tika.mime.MimeType t  =config.getMimeRepository().forName(mimeTypeString);
//
//            String exten = t.getExtension();
//            System.out.println(exten);
//
//        }





//        for(int i = 430 ; i< 450; i++) {
//            testImport(i);
//        }

//        for(int i = 430 ; i< 441; i++) {
//            testProgress(i);
//        }

//        testImport(900);

//        List<User> list = new ArrayList<>();
//        User u1 = new User();
//        u1.setId("u");
//        u1.setName("u");
//        u1.setTelephoneNumber("123345");
//
//        list.add(u1);
//
//        User u2 = new User();
//        u2.setId("u");
//        u2.setName("u");
//        u1.setTelephoneNumber("54321");
//        list.add(u2);
//
//        Map<String,User> umap =list.stream().collect(Collectors.toMap(u -> u.getId()+"-"+u.getName(), Function.identity(),(ua,ub) -> ua));
//
//        System.out.println(umap);

    }

    public static void testImportZip(){

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url1 = "http://localhost:8080/login";
            String url2 = "http://localhost:8080/api/image/zip/upload";
            File file = new File(App.class.getClassLoader().getResource("p00001.zip").getFile());
            HttpGet httpGet1 = new HttpGet(url1);
            try (CloseableHttpResponse resp1 = httpClient.execute(httpGet1); InputStream in1 = resp1.getEntity().getContent()) {
                System.out.println(url1);
                System.out.println(new String(StreamUtils.getBytes(in1)));

                HttpEntity data = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody("files", file, ContentType.DEFAULT_BINARY, file.getName())
                        .build();
                HttpUriRequest request = RequestBuilder
                        .post(url2)
                        .setEntity(data)
                        .build();


                try (CloseableHttpResponse resp2 = httpClient.execute(request); InputStream in2 = resp2.getEntity().getContent()) {
                    System.out.println(url2);
                    System.out.println(new String(StreamUtils.getBytes(in2)));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public static void testImageUpload(){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url1 = "http://localhost:8080/login";

            HttpGet httpGet1 = new HttpGet(url1);
            try (CloseableHttpResponse resp1 = httpClient.execute(httpGet1); InputStream in1 = resp1.getEntity().getContent()) {
                System.out.println(url1);
                System.out.println(new String(StreamUtils.getBytes(in1)));

                String url2 = "http://localhost:8080/api/import/uploadImg";

                String[] imgUrls = new String[100];

                List<NameValuePair> nvs = new ArrayList<>();

                for(int i = 0 ;i< imgUrls.length;i++){
                    imgUrls[i] = "http://cn.bing.com/az/hprichbg/rb/AvalancheCreek_EN-US9065774002_1920x1080.jpg";

                    nvs.add(new BasicNameValuePair("imgUrls",imgUrls[i]));
                }

                HttpPost httpPost = new HttpPost(url2);

                httpPost.setEntity(new UrlEncodedFormEntity(nvs));
                long start =System.currentTimeMillis();
                try (CloseableHttpResponse resp2 = httpClient.execute(httpPost); InputStream in2 = resp2.getEntity().getContent()) {

                    System.out.println(url2);
                    System.out.println(new String(StreamUtils.getBytes(in2)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("cost:"+(System.currentTimeMillis()-start) + "ms.");

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }


    public static void testProgress(int i){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url1 = "http://localhost:8080/login?userId=A" + i;

            HttpGet httpGet1 = new HttpGet(url1);
            try (CloseableHttpResponse resp1 = httpClient.execute(httpGet1); InputStream in1 = resp1.getEntity().getContent()) {
                System.out.println(url1);
                System.out.println(new String(StreamUtils.getBytes(in1)));

                String url2 = "http://localhost:8080/api/import/progress?module=item";
                HttpGet httpGet2 = new HttpGet(url2);
                try (CloseableHttpResponse resp2 = httpClient.execute(httpGet2); InputStream in2 = resp2.getEntity().getContent()) {
                    System.out.println(url2);
                    System.out.println(new String(StreamUtils.getBytes(in2)));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public static void testImport(int i){

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String url1 = "http://localhost:8080/login?userId=A" + i;
            String url2 = "http://localhost:8080/api/import/item/d2c";
            File file = new File(App.class.getClassLoader().getResource("item.xls").getFile());
            HttpGet httpGet1 = new HttpGet(url1);
            try (CloseableHttpResponse resp1 = httpClient.execute(httpGet1); InputStream in1 = resp1.getEntity().getContent()) {
                System.out.println(url1);
                System.out.println(new String(StreamUtils.getBytes(in1)));

                HttpEntity data = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName())
                        .build();
                HttpUriRequest request = RequestBuilder
                        .post(url2)
                        .setEntity(data)
                        .build();


                try (CloseableHttpResponse resp2 = httpClient.execute(request); InputStream in2 = resp2.getEntity().getContent()) {
                    System.out.println(url2);
                    System.out.println(new String(StreamUtils.getBytes(in2)));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }
}
