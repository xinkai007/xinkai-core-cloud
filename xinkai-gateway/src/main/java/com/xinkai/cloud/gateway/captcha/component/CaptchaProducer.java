package com.xinkai.cloud.gateway.captcha.component;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.xinkai.cloud.gateway.captcha.enums.CaptchaTypeEnum;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * 验证码生产商
 *
 * @author xinkai
 * @className: CaptchaProducer
 * @description: 验证码生成器
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/2/12
 * @date 2023/02/12
 */
@Component
public class CaptchaProducer {
    /**
     * 由于定义验证码图片尺寸变量较小，所以此处使用byte定义 占用字节：4取值范围：-128和 127
     */

    /**
     * 验证码内容长度
     */
    private byte length = 4;
    /**
     * 验证码图片宽度
     */
    private byte width = 120;
    /**
     * 验证码图片高度
     */
    private byte height = 36;
    /**
     * 字体
     */
    private String font = "Verdana";
    /**
     * 字体样式
     */
    private int fontStyle = Font.PLAIN;
    /**
     * 字体大小
     */
    private byte fontSize = 20;

    /**
     * 获取验证码
     * 根据传入参数生成对应风格的验证码图片
     *
     * @param captchaTypeEnum 验证码类型枚举
     * @return {@link Captcha}
     */
    public Captcha getCaptcha(CaptchaTypeEnum captchaTypeEnum) {
        Captcha captcha;
        switch (captchaTypeEnum) {
            //算术
            case ARITHMETIC:
                captcha = new ArithmeticCaptcha(width, height, 2);
                break;
            //中文
            case CHINESE:
                captcha = new ChineseCaptcha(width, height, length);
                break;
            //中文闪图
            case CHINESE_GIF:
                captcha = new ChineseGifCaptcha(width, height, length);
                break;
            //闪图
            case GIF:
                captcha = new GifCaptcha(width, height, length);
                break;
            //字符
            case SPEC:
                captcha = new SpecCaptcha(width, height, length);
                break;
            default:
                throw new RuntimeException("验证码配置信息错误！");
        }
        //设置字体样式
        captcha.setFont(new Font(font, fontStyle, fontSize));
        return captcha;
    }

    public static void main(String[] args) {
        CaptchaProducer captchaProducer = new CaptchaProducer();
        CaptchaTypeEnum arithmetic = CaptchaTypeEnum.GIF;
        Captcha captcha = captchaProducer.getCaptcha(arithmetic);
        String s = captcha.toBase64();
        System.out.println(s);
        String text = captcha.text();
        System.out.println(text);
    }
}
