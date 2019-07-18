package com.hlx.fast.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.system.SystemUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @program: fast-starter
 * @description:
 * @author: hlx
 * @create: 2019-06-27 16:48
 **/

@Component
public class PushNotice {


    public static void sendEmail(String pic, String title, String content) {

        content = SystemUtil.getRuntimeInfo().toString();
        //  String pic = "http://5b0988e595225.cdn.sohucs.com/images/20180924/3aebde7c237f41d3be9c052eaf1b8578.gif";
        pic = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561633425659&di=f0c1b77ee52200643433d4af4f246601&imgtype=0&src=http%3A%2F%2Fzkres1.myzaker.com%2F201901%2F5c38882c6227681850034b61_raw.gif";
        FileReader fileReader = new FileReader("../email/notice.html");
        String result = StrUtil.format(fileReader.readString(), pic, title, content, DateUtil.now());
        ArrayList<String> tos = CollUtil.newArrayList("493854729@qq.com");
        MailUtil.send(tos, "系统启动", result, true);
    }

}