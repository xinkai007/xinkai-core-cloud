package com.xinkai.gateway.captcha.component;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.xinkai.gateway.captcha.enums.CaptchaTypeEnum;
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
 */
@Component
public class CaptchaProducer {
    //由于定义验证码图片尺寸变量较小，所以此处使用byte定义 占用字节：4取值范围：-128和 127
    /**
     * 验证码内容长度
     */
    private static final byte LENGTH = 4;
    /**
     * 验证码图片宽度
     */
    private static final byte WIDTH = 120;
    /**
     * 验证码图片高度
     */
    private static final byte HEIGHT = 36;
    /**
     * 字体
     */
    private static final String FONT = "Verdana";
    /**
     * 字体样式
     */
    private static final int FONT_STYLE = Font.PLAIN;
    /**
     * 字体大小
     */
    private static final byte FONT_SIZE = 20;

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
                captcha = new ArithmeticCaptcha(WIDTH, HEIGHT, 2);
                break;
            //中文
            case CHINESE:
                captcha = new ChineseCaptcha(WIDTH, HEIGHT, LENGTH);
                break;
            //中文闪图
            case CHINESE_GIF:
                captcha = new ChineseGifCaptcha(WIDTH, HEIGHT, LENGTH);
                break;
            //闪图
            case GIF:
                captcha = new GifCaptcha(WIDTH, HEIGHT, LENGTH);
                break;
            //字符
            case SPEC:
                captcha = new SpecCaptcha(WIDTH, HEIGHT, LENGTH);
                break;
            default:
                throw new RuntimeException("验证码配置信息错误！");
        }
        //设置字体样式
        captcha.setFont(new Font(FONT, FONT_STYLE, FONT_SIZE));
        return captcha;
    }
}
