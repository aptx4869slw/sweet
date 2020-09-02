package com.song.sweet.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;

@Component
public class ImageUtils {

    private final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    private static String endpoint = "oss-cn-shanghai.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAIjA8JwE74FmhQ";
    private static String accessKeySecret = "fOrZYcMOvyu0h9rBEE5IbeI550KKOB";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName = "liwen-bucket";

    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    /**
     * 图片上传
     *
     * @param contentType
     * @param content
     * @return
     */
    public String upload(String contentType, byte[] content) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            String key = RandomStringUtils.random(20, true, true);
            this.ossClient.putObject(bucketName, key, new ByteArrayInputStream(content), meta);
            URL url = new URL("http", endpoint, "/" + key);
            return url.toString();
        } catch (Exception e) {
            this.logger.error("upload error", e);
            return null;
        }
    }

}
