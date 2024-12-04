package com.liyuanqing.wecom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyuanqing.util.RSAUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.net.URLEncoder;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
@Data
public class ExternalLoginDTO {
    public interface Admin {

    }

    public interface Employee {

    }

    @ApiModelProperty(value = "请求调用时间戳,格式yyyyMMddHHmmss")
    @NotBlank(message = "请求调用时间戳不可为空")
    public String timeStamp;

    @NotBlank(message = "[银行号]不能为空", groups = {Admin.class, Employee.class})
    private String bankCode;

    @NotBlank(message = "[机构号]不能为空", groups = {Employee.class, Admin.class})
    private String instCode;

    @NotBlank(message = "[工号]不能为空", groups = {Employee.class})
    private String employeeNo;

    @NotBlank(message = "[身份类型]不能为空", groups = {Employee.class, Admin.class})
    private String loginType;

    @NotBlank(message = "[来源方]平台", groups = {Employee.class, Admin.class})
    private String source;
    @NotBlank(message = "当前用户的唯一标识", groups = {Admin.class})
    private String uniqueId;
    //  手机号
    private String mobileNo;

    private String branchNo;
    //  头像Url
    private String avatarUrl;
    //  二维码url
    private String QRCodeUrl;
    //  姓名
    private String nickname;
    //  机构名称
    private String instName;
    //  银行名称
    private String bankName;
    //  是否实时发卡,0/非实时,1/实时
    private String isIssued;
    //  申请件条形码
    private String microfilm;
    //  场景编号
    private String sceneNum;
    //  回调地址
    private String redirectUrl;

    public static void main(String[] args) throws Exception {
        //  第三方登陆
        ExternalLoginDTO dto = new ExternalLoginDTO();
        dto.setSource("LYQ");
        dto.setLoginType("userAPI");
        dto.setBankCode("6555");
        dto.setInstCode("QYWX99");
        dto.setEmployeeNo("5080");
        dto.setMobileNo("18266245080");
        dto.setNickname("李源青_LYQ");
        dto.setMicrofilm("50805080");
        dto.setSceneNum("1");
        dto.setRedirectUrl("www.baidu.com");
        String param = JSON.toJSONString(dto);
        JSONObject json = new JSONObject();
        json.put("appId", "LYQ");
        json.put("sign", RSAUtil.sign(param));
        json.put("data", RSAUtil.encrypt(param));
        System.out.println(URLEncoder.encode(json.toJSONString(), "utf-8"));
    }
}
